package proxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Student student = new Student();
        InvocationImpl invocation = new InvocationImpl(student);
        ClassLoader classLoader = student.getClass().getClassLoader();
        Class<?>[] interfaces = student.getClass().getInterfaces();
        Study proxyInstance = (Study) Proxy.newProxyInstance(classLoader,interfaces , invocation);
        System.out.println("动态代理对象的类型："+proxyInstance.getClass().getName());
        proxyInstance.read();
        System.out.println("---------------------------------");
        proxyInstance.write();

        createProxyClassFile();
    }

//    public static void main(String[] args) {
//        Student student = new Student();
//        InvocationImpl invocation = new InvocationImpl(student);
//        ClassLoader classLoader = student.getClass().getClassLoader();
//        Class<?>[] interfaces = student.getClass().getInterfaces();
//        Study proxyInstance = (Study) Proxy.newProxyInstance(classLoader, interfaces, invocation);
//
//        proxyInstance.write();
////        proxyInstance.write();
////        proxyInstance.write();
//
//        //代理两层
////        Study proxyProxyInstance =(Study) Proxy.newProxyInstance(proxyInstance.getClass().getClassLoader(), proxyInstance.getClass().getInterfaces(), new ProxyInvocationImpl(proxyInstance));
////        System.out.println("动态代理对象的类型："+proxyInstance.getClass().getName());
////        proxyProxyInstance.read();
////        System.out.println("---------------------------------");
////        proxyProxyInstance.write();
//    }


    private static void createProxyClassFile() {
        String name = "StudentProxy";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Study.class});
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
