package aop.chain;

import aop.interceptor.ProxyInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class DefaultProxyChain {

    protected List<ProxyInterceptor> interceptors = new ArrayList<ProxyInterceptor>();
    protected int interceptorIndex = 0;

    protected Class<?> targetClass;
    protected Object targetObject;
    protected Method method;
    protected Object[] params;

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParams() {
        return params;
    }

    public DefaultProxyChain(Class<?> targetClass, Object targetObject, Method method, Object[] params, List<ProxyInterceptor> interceptors) {
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
