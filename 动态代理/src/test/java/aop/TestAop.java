package aop;


import aop.factory.CglibProxyFactory;
import org.junit.Test;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class TestAop {


    @Test
    public void testAop() {

        CglibProxyFactory<UserDao> proxyFactory = new CglibProxyFactory<UserDao>();
        proxyFactory.addInterceptor(new TimeMonitorProxy());
        Dao proxy = proxyFactory.getProxy(UserDao.class);
        proxy.insert();
//        proxy.select();

        System.out.println("----------------------------------");
        proxyFactory.addInterceptor(new TimeMonitorProxy());
        proxy = proxyFactory.getProxy(UserDao.class);
        proxy.insert();
    }
}
