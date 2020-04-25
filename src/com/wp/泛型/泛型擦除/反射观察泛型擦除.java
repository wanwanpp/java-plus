package com.wp.泛型.泛型擦除;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王萍
 * @date 2017/12/3 0003
 */
//利用反射观察泛型擦除
public class 反射观察泛型擦除 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(12);
        Method add = integerList.getClass().getMethod("add", Object.class);
        add.invoke(integerList, "123abc");
        add.invoke(integerList, false);
        add.invoke(integerList, new Object());

        for (int i = 0; i < integerList.size(); i++) {
            System.out.println(integerList.get(i));
        }
    }
}
