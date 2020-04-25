package aop.creator;

import aop.chain.CglibProxyChain;
import aop.interceptor.ProxyInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class CglibCreator implements ProxyCreator {
    public <T> T createProxy(final Class<?> targetClass,final List<ProxyInterceptor> interceptors) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object targetObject, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
                return new CglibProxyChain(targetClass, targetObject, method, methodProxy, params, interceptors).doChain();
            }
        });
    }
}
