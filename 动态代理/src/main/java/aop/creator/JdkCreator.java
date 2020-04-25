package aop.creator;

import aop.chain.DefaultProxyChain;
import aop.interceptor.ProxyInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class JdkCreator implements ProxyCreator {
    public <T> T createProxy(final Class<?> targetClass, final List<ProxyInterceptor> interceptors) {
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return new DefaultProxyChain(targetClass, targetClass.getConstructor().newInstance(), method, args, interceptors).doChain();
            }
        });
    }
}
