package aop.factory;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class JdkProxyFactory<T> extends AbstractProxyFactory<T> {

    @Override
    public T getProxy(Class<?> targetClass) {
        proxy = this.jdkCreator.createProxy(targetClass, interceptors);
        return proxy;
    }
}
