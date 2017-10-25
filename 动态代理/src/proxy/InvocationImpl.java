package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 王萍 on 2017/10/24 0024.
 */
public class InvocationImpl implements InvocationHandler {

    private Object student;

    public InvocationImpl(Object student) {
        this.student = student;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method invoke");
        Object returnValue = method.invoke(student, args);
        System.out.println("after method invoke");
        return returnValue;
    }
}
