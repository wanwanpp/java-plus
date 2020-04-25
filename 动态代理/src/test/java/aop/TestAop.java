package aop;


import aop.factory.AbstractProxyFactory;
import aop.factory.CglibProxyFactory;
import aop.factory.JdkProxyFactory;
import org.junit.Test;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class TestAop {


    @Test
    public void testCglibProxy() {

        AbstractProxyFactory<UserDao> proxyFactory = new CglibProxyFactory<UserDao>();
        proxyFactory.addInterceptor(new TimeMonitorProxy());
        Dao proxy = proxyFactory.getProxy(UserDao.class);
        proxy.insert();
//        proxy.select();

        System.out.println("----------------------------------");
        proxyFactory.addInterceptor(new TimeMonitorProxy());
        proxy = proxyFactory.getProxy(UserDao.class);
        proxy.insert();
    }

    @Test
    public void testJdkProxy() {

        AbstractProxyFactory<UserDao> proxyFactory = new JdkProxyFactory<UserDao>();
        proxyFactory.addInterceptor(new TimeMonitorProxy());
        Dao proxy = proxyFactory.getProxy(UserDao.class);
        proxy.insert();

        System.out.println("----------------------------------");
        proxyFactory.addInterceptor(new TimeMonitorProxy());
        proxy = proxyFactory.getProxy(UserDao.class);
        proxy.insert();
    }
}
