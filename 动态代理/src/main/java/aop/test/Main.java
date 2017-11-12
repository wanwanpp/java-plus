package aop.test;


import aop.JdkCreator;
import aop.ProxyInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class Main {

    public static void main(String[] args) {
        List<ProxyInterceptor> interceptors = new ArrayList<ProxyInterceptor>();
        interceptors.add(new TimeMonitorProxy());
        interceptors.add(new TimeMonitorProxy());
        Dao proxy = new JdkCreator().createProxy(UserDao.class, interceptors);
        proxy.insert();
        proxy.select();
    }
}
