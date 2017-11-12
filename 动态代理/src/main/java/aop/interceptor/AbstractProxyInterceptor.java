package aop.interceptor;

import aop.chain.DefaultProxyChain;

import java.lang.reflect.Method;
/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public abstract class AbstractProxyInterceptor implements ProxyInterceptor {
    @Override
    public Object intercept(DefaultProxyChain chain) throws Throwable {

        Object result;

        Class<?> targetClass = chain.getTargetClass();
        Method method = chain.getMethod();
        Object[] params = chain.getParams();

        if (support()) {
            before(targetClass, method, params);
            result = chain.doChain();
            after(targetClass, method, params, result);
        } else {
            result = chain.doChain();
        }
        return result;
    }

    public abstract void before(Class<?> targetClass, Method method, Object[] params);

    public abstract void after(Class<?> targetClass, Method method, Object[] params, Object result);

    @Override
    public boolean support() {
        return true;
    }


}
