package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.UndeclaredThrowableException;


/**
 * 伪代码   不可运行的
 * 重要的是动态代理的主要流程
 * 1.从代理接口中获取需要被代理调用的方法对象
 * 2.分派方法到调用处理器上去执行
 * 3.返回结果
 */
public interface Simulator {
    short simulate(int arg1, long arg2, String arg3) throws ExceptionA, ExceptionB;
}


// 假设代理类为 SimulatorProxy, 其类声明将如下
class SimulatorProxy implements Simulator {

    // 调用处理器对象的引用
    protected InvocationHandler handler;

    // 以调用处理器为参数的构造函数
    public SimulatorProxy(InvocationHandler handler) {
        this.handler = handler;
    }

    // 实现接口方法 simulate
    public short simulate(int arg1, long arg2, String arg3)
            throws ExceptionA, ExceptionB {

        // 第一步是获取 simulate 方法的 Method 对象
        java.lang.reflect.Method method = null;
        try {
            method = Simulator.class.getMethod(
                    "simulate",
                    new Class[]{int.class, long.class, String.class});
        } catch (Exception e) {
            // 异常处理 1（略）
        }

        // 第二步是调用 handler 的 invoke 方法分派转发方法调用
        Object r = null;
        try {
            r = handler.invoke(this,
                    method,
                    // 对于原始类型参数需要进行装箱操作
                    new Object[]{new Integer(arg1), new Long(arg2), arg3});
        } catch (ExceptionA e) {

            // 接口方法支持 ExceptionA，可以抛出
            throw e;

        } catch (ExceptionB e) {
            // 接口方法支持 ExceptionB，可以抛出
            throw e;

        } catch (Throwable e) {
            // 其他不支持的异常，一律抛 UndeclaredThrowableException
            throw new UndeclaredThrowableException(e);
        }
        // 第三步是返回结果（返回类型是原始类型则需要进行拆箱操作）
        return ((Short) r).shortValue();
    }
}

class ExceptionA extends Exception {

}

class ExceptionB extends Exception {

}