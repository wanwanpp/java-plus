package aop.factory;

import java.util.ArrayList;
import java.util.List;

import aop.creator.CglibCreator;
import aop.creator.JdkCreator;
import aop.interceptor.ProxyInterceptor;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */

public abstract class AbstractProxyFactory<T> {

    protected List<ProxyInterceptor> interceptors=new ArrayList<ProxyInterceptor>();

    protected JdkCreator jdkCreator = new JdkCreator();
    protected CglibCreator cglibCreator = new CglibCreator();

    protected T proxy;

    public void addInterceptor(ProxyInterceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    public void removeInterceptor(ProxyInterceptor interceptor) {
        this.interceptors.remove(interceptor);
    }

    public abstract  T getProxy(Class<?> targetClass);
}
