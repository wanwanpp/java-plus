package aop.chain;

import aop.interceptor.ProxyInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class CglibProxyChain extends DefaultProxyChain {

    private MethodProxy methodProxy;

    public CglibProxyChain(Class<?> targetClass, Object targetObject, Method method, MethodProxy methodProxy, Object[] params, List<ProxyInterceptor> interceptors) {
        super(targetClass, targetObject, method, params, interceptors);
        this.methodProxy = methodProxy;
    }

    public Object doChain() throws Throwable {

        Object result;
        if (interceptorIndex < interceptors.size()) {
            result = interceptors.get(interceptorIndex++).intercept(this);
        } else {
            result = methodProxy.invokeSuper(targetObject, params);
        }
        return result;
    }
}
