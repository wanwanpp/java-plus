package aop;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public interface ProxyInterceptor {

    Object intercept(ProxyInterceptorChain chain) throws Throwable;

    boolean support();
}
