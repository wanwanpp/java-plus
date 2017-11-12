package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */

// TODO: 2017/11/11 0011 动态添加interceptor，即修改interceptors，然后重新生成代理对象。
public class ProxyFactory {

    public static <T> T getProxy(final Class<?> targetClass, final List<ProxyInterceptor> interceptors) {
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return new DefaultProxyChain(targetClass,targetClass.getConstructor().newInstance(),method,args,interceptors).doChain();
            }
        });
    }
}
