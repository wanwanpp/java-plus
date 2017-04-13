package com.wp.内省操作javabean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 王萍 on 2017/2/6 0006.
 */
public class TestIntrospector {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        TestBean bean = new TestBean(12, "wanwanpp");
        String propertyName = "name";

        Object value = getProperty(bean, propertyName);
        System.out.println(value);

        Object newNum = "haha";
        setProperty(bean, propertyName, newNum);

        //使用此工具包需要引入common-utils和common-loggingjar包，前者依赖于后者
        System.out.println(BeanUtils.getProperty(bean, "name"));
        //PropertyUtils也可以对javabean进行属性的操作，propertyutils不会进行类型转换，而BeanUtils会。
        System.out.println(PropertyUtils.getProperty(bean, "name"));

        System.out.println(bean.getName());


    }

    private static void setProperty(TestBean bean, String propertyName, Object newValue) throws IntrospectionException, IllegalAccessException, InvocationTargetException {

        //使用ＡＰＩ操作属性。
        //propertyName为属性名
        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, bean.getClass());
        //获取指定属性的get方法
        Method writeMethod = descriptor.getWriteMethod();
        writeMethod.invoke(bean, newValue);
    }

    private static Object getProperty(TestBean bean, String propertyName) throws IntrospectionException, IllegalAccessException, InvocationTargetException {

//        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName,bean.getClass());
//        Method readMethod = descriptor.getReadMethod();
//        return readMethod.invoke(bean);

        Object o = null;
        //获取JavaBean的信息。
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        //这里面只提供了返回所有属性信息的方法，无法返回具体哪一个属性的信息
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        //使用迭代获取特定的属性信息
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(propertyName)) {
                Method readMethod = propertyDescriptor.getReadMethod();
                o = readMethod.invoke(bean);
                break;
            }
        }
        return o;
    }
}
