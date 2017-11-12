package aop.factory;

import aop.creator.CglibCreator;
import aop.creator.JdkCreator;
import aop.interceptor.ProxyInterceptor;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */

public abstract class AbstractProxyFactory<T> {

    protected List<ProxyInterceptor> interceptors=new ArrayList<>();

    protected JdkCreator jdkCreator = new JdkCreator();
    protected CglibCreator cglibCreator = new CglibCreator();

    protected T proxy;

    public void addInterceptor(ProxyInterceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    public void removeInterceptor(ProxyInterceptor interceptor) {
        this.interceptors.remove(interceptor);
    }

    protected Class<T> getTClass(){
        Class <T> targetClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return targetClass;
    }

    public abstract <T> T getProxy();
}
