package aop.factory;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class CglibProxyFactory<T> extends AbstractProxyFactory<T> {

    @Override
    public T getProxy(Class<?> targetClass) {
        proxy = this.cglibCreator.createProxy(targetClass, interceptors);
        return proxy;
    }
}
