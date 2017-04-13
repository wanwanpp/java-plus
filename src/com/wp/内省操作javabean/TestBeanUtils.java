package com.wp.内省操作javabean;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王萍 on 2017/2/6 0006.
 */
public class TestBeanUtils {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        TestBean testBean = new TestBean(11, "wangping");
        Person person = new Person(18, "wangping");
        testBean.setPerson(person);

        System.out.println(BeanUtils.getProperty(testBean, "person.age"));

        //BeanUtils可以对对象的对象进行级联操作
        BeanUtils.setProperty(testBean, "person.age", 28);
        System.out.println(BeanUtils.getProperty(testBean, "person.age"));

        //将javabean转为map对象
        Map<String, String> describe = BeanUtils.describe(testBean);
        for (String s : describe.keySet()) {
            System.out.println(s + " is " + describe.get(s));
        }

        //将map对象填充至javabean实例中。
        Map<String, Object> map = new HashMap<>();
        TestBean testBean1 = new TestBean();
        map.put("num", 18);
        map.put("name", "wanwanpp");
        BeanUtils.populate(testBean1, map);
        System.out.println(BeanUtils.getProperty(testBean1,"name"));

        System.out.println(map);

    }
}
