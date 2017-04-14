package proxy.推演;

import proxy.推演.demo.Expo2010TicketSeller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.*;
import java.util.Map;

/**
 * Created by 王萍 on 2017/4/14 0014.
 */

/**
 * 受限于 Java 的类继承机制，扩展的动态代理机制也有其局限，它不能支持：
 * 1. 声明为 final 的类；
 * 2. 声明为 final 的函数；
 * 3. 构造函数均为 private 类型的类；
 */
public class ProxyEx extends Proxy {

    protected ProxyEx(InvocationHandler h) {
        super(h);
    }

    public static void main(String[] args) throws NoSuchMethodException {

        Expo2010TicketSeller ticketSeller = new Expo2010TicketSeller();
        Method method = ticketSeller.getClass().getMethod("buy", int.class, int.class);

        System.out.println(getMethodEntity(method));
    }

    //获取方法的参数类型   以int.class,java.lang.Object.class  形式打印
    public static String getMethodParameterTypesHelper(Method method) {

        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();

        for (Class parameterType : parameterTypes) {
            stringBuffer.append(parameterType.getName())
                    .append(".class")
                    .append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }

    public static String getMethodParameterValuesHelper(Method method) {

        Parameter[] parameters = method.getParameters();
        StringBuffer stringBuffer = new StringBuffer();

        for (Parameter parameter : parameters) {
            stringBuffer.append(parameter.getName())
                    .append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }

    public static String getMethodParameterThrowablesHelper(Method method) {
        return "catch (Exception e){\n" +
                "            System.err.print(e.getStackTrace());\n" +
                "        }";
    }

    public static String getMethodReturnHelper(Method method) {
        String returnType = getMethodReturnType(method);
        return "return (" + returnType + ") r;";
    }

    public static String getMethodModifiers(Method method) {
        return Modifier.toString(method.getModifiers());
    }

    public static String getMethodReturnType(Method method) {
        return method.getReturnType().getName();
    }

    public static String getMethodParameters(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();

        int i = 0;
        for (Class parameterType : parameterTypes) {
            stringBuffer.append(parameterType.getName())
                    .append(" ")
                    .append("arg")
                    .append(i)
                    .append(",");
            i++;
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();

    }

    public static String getMethodThrowables(Method method) {
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        StringBuffer stringBuffer = new StringBuffer("throws ");
        for (Class c : exceptionTypes) {
            stringBuffer.append(c.getSimpleName())  //获取不带包名的类名
                    .append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    private static String getMethodEntity(Method method) {
        String template = "\n{"
                + "\n    java.lang.reflect.Method method = null;"
                + "\n    try{"
                + "\n        method = &Class.getMethod( \"&MethodName\", &ParameterTypes );"    //若没有参数时该怎么办？
                + "\n    }"
                + "\n    catch(Exception e){"
                + "\n    }"
                + "\n    Object r = null;"
                + "\n    try{"
                + "\n         r = handler.invoke( this, method, &ParameterValues );"
                + "\n    }&Exceptions"
                + "\n    &Return"
                + "\n}";

        String result = template.replaceAll("&MethodName", method.getName())
                .replaceAll("&Class", method.getDeclaringClass().getName() + ".class")
                .replaceAll("&ParameterTypes", getMethodParameterTypesHelper(method))
                .replaceAll("&ParameterValues", getMethodParameterValuesHelper(method))
                .replaceAll("&Exceptions", getMethodParameterThrowablesHelper(method))
                .replaceAll("&Return", getMethodReturnHelper(method));

        return result;
    }

    public static void replace(Method method) {
        String declTemplate = "&Modifiers &ReturnType &MethodName(&Parameters) &Throwables";
        String bodyTemplate = "&Declaration &Body";
// 方法声明
        String declare = declTemplate.replaceAll("&Modifiers", getMethodModifiers(method))
                .replaceAll("&ReturnType", getMethodReturnType(method))
                .replaceAll("&MethodName", method.getName())
                .replaceAll("&Parameters", getMethodParameters(method))
                .replaceAll("&Throwables", getMethodThrowables(method));

// 方法声明以及实现
        String body = bodyTemplate.replaceAll("&Declaration", declare)
                .replaceAll("&Body", getMethodEntity(method));
    }

    private static String getTypeHelper(Class type) {
        if (type.isArray()) {
            Class c = type.getComponentType();
            return getTypeHelper(c) + "[]";
        } else {
            return type.getName();
        }
    }

    public static interface InvocationHandlerEx extends InvocationHandler {
        // 返回指定 stubClass 参数所对应的被代理类实体对象
        Object getStub(Class stubClass);
    }

    private synchronized void sync(Class clazz, boolean toStub) {
        // 判断是否为扩展调用处理器
        if (h instanceof InvocationHandlerEx) {
            Class superClass = this.getClass().getSuperclass();
            Class stubClass = (clazz != null ? clazz : superClass);

            // 通过扩展调用处理器获得stub对象
            Object stub = ((InvocationHandlerEx) h).getStub(stubClass);
            if (stub != null) {
                // 获得所有需同步的类成员列表，遍历并同步
                Field[] fields = getFields(superClass);
                for (int i = 0; fields != null && i < fields.length; i++) {
                    try {
                        fields[i].setAccessible(true);
                        // 执行代理类和被代理类的变量同步
                        if (toStub) {
                            fields[i].set(stub, fields[i].get(this));
                        } else {
                            fields[i].set(this, fields[i].get(stub));
                        }
                    } catch (Throwable e) {
                    }
                }
            }
        }
    }

    private static Field[] getFields(Class c) {

        //设计fieldsMap是为了提高反复查询时的性能，进行缓存
//        if (fieldsMap.containsKey(c)) {
//            return (Field[]) fieldsMap.get(c);
//        }

        Field[] fields = null;
        if (c == Object.class) {
            //获取object中的方法
            fields = c.getDeclaredFields();
        } else {
            //若不为Object类，获取此类及其超类中的方法
            Field[] fields0 = getFields(c.getSuperclass());  //迭代
            Field[] fields1 = c.getDeclaredFields();
            fields = new Field[fields0.length + fields1.length];
            System.arraycopy(fields0, 0, fields, 0, fields0.length);
            System.arraycopy(fields1, 0, fields, fields0.length, fields1.length);
        }
//        fieldsMap.put(c, fields);
        return fields;
    }


    private static byte[] getProxyClassCodeSource(String pkg, String className,
                                                  String declare) throws Exception {
        // 将类的源代码保存进一个名为类名加“.java”的本地文件
        File source = new File(className + ".java");
        FileOutputStream fos = new FileOutputStream(source);
        fos.write(declare.getBytes());
        fos.close();

        // 调用com.sun.tools.javac.Main类的静态方法compile进行动态编译
        int status = com.sun.tools.javac.Main.compile(new String[]{"-d", ".", source.getName()});

        if (status != 0) {
            source.delete();
            throw new Exception("Compiler exit on " + status);
        }

        // 编译得到的字节码将被输出到与包结构相同的一个本地目录，文件名为类名加”.class”
        String output = ".";
        int curIndex = -1;
        int lastIndex = 0;
        while ((curIndex = pkg.indexOf('.', lastIndex)) != -1) {
            output = output + File.separator + pkg.substring(lastIndex, curIndex);
            lastIndex = curIndex + 1;
        }
        output = output + File.separator + pkg.substring(lastIndex);
        output = output + File.separator + className + ".class";

        // 从输出文件中读取字节码，并存入字节数组
        File target = new File(output);
        FileInputStream f = new FileInputStream(target);
        byte[] codeSource = new byte[(int) target.length()];
        f.read(codeSource);
        f.close();

        // 删除临时文件
        source.delete();
        target.delete();

        return codeSource;
    }


    private static Class defineClassHelper(String pkg, String cName, byte[] codeSource) throws Exception {

        //获得Method对象
        Method defineClass = Proxy.class.getDeclaredMethod("defineClass0",  //defineClass0在java.lang.reflect.Proxy中
                new Class[]{ClassLoader.class, String.class, byte[].class, int.class, int.class});

        defineClass.setAccessible(true);


        //执行Proxy的defineClass0()方法，返回Class对象
        //private static native Class<?> defineClass0(ClassLoader loader, String name,byte[] b, int off, int len);
        return (Class) defineClass.invoke(Proxy.class,
                new Object[]{ProxyEx.class.getClassLoader(),
                        pkg.length() == 0 ? cName : pkg + "." + cName,
                        codeSource,
                        new Integer(0),
                        new Integer(codeSource.length)});
    }

    private static void addProxyClass(Class proxy) throws IllegalArgumentException {
        try {
            // 通过反射获取父类的私有 proxyClasses 变量并更新
            Field proxyClasses = Proxy.class.getDeclaredField("proxyClassCache");
            proxyClasses.setAccessible(true);
            ((Map) proxyClasses.get(Proxy.class)).put(proxy, null);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }


    public static InvocationHandler getInvocationHandler(Object proxy)
            throws IllegalArgumentException {
        // 如果Proxy实例，直接调父类的方法
        if (proxy instanceof Proxy) {
            return Proxy.getInvocationHandler(proxy);
        }

        // 如果不是代理类，抛异常
        if (!Proxy.isProxyClass(proxy.getClass())) {
            throw new IllegalArgumentException("Not a proxy instance");
        }

        //如果是代理类但不是Proxy实例
        try {
            // 通过反射获取扩展代理类的调用处理器对象
            Field invoker = proxy.getClass().getDeclaredField("handler");
            invoker.setAccessible(true);
            return (InvocationHandler) invoker.get(proxy);
        } catch (Exception e) {
            throw new IllegalArgumentException("Suspect not a proxy instance");
        }
    }
}
