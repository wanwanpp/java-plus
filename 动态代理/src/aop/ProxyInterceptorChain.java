package aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class ProxyInterceptorChain {

    private List<ProxyInterceptor> interceptors = new ArrayList<>();
    private int interceptorIndex = 0;

    private Class<?> targetClass;
    private Object targetObject;
    private Method method;
    private Object[] params;

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParams() {
        return params;
    }

    public ProxyInterceptorChain(Class<?> targetClass, Object targetObject, Method method, Object[] params, List<ProxyInterceptor> interceptors) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.method = method;
        this.params = params;
        this.interceptors = interceptors;
    }

    public Object doChain() throws Throwable {

        Object result;
        if (interceptorIndex < interceptors.size()) {
            result = interceptors.get(interceptorIndex++).intercept(this);
        } else {
            result = method.invoke(targetObject, params);
        }
        return result;
    }
}
