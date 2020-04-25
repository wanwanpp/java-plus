package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationImpl implements InvocationHandler {

    private Object student;

    public InvocationImpl(Object student) {
        this.student = student;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method invoke");
        Object returnValue = method.invoke(student, args);
        System.out.println("after method invoke");
        return returnValue;
    }
}
