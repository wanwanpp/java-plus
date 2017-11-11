package aop.test;

import aop.ProxyFactory;
import aop.ProxyInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class Main {

    public static void main(String[] args) {
        List<ProxyInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new TimeMonitorProxy());
        interceptors.add(new TimeMonitorProxy());
        Dao proxy = ProxyFactory.getProxy(new UserDao(), interceptors);
        proxy.insert();
        proxy.select();
    }
}
