package aop;


import aop.interceptor.AbstractProxyInterceptor;

import java.lang.reflect.Method;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class TimeMonitorProxy extends AbstractProxyInterceptor {

    long t1;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] params) {
        t1 = System.currentTimeMillis();
        System.out.println("before currentTime: " + t1);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void after(Class<?> targetClass, Method method, Object[] params, Object result) {
        long t2 = System.currentTimeMillis();
        System.out.println("after currentTime: " + t2);
        System.out.println("执行方法：" + method.getName() + "用了" + (t2 - t1));
    }
}
