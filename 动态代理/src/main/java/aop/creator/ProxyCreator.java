package aop.creator;

import java.util.List;

import aop.interceptor.ProxyInterceptor;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public interface ProxyCreator {

    public <T> T createProxy(final Class<?> targetClass, final List<ProxyInterceptor> interceptors);
}
