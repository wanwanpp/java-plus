package com.wp.可变参数;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class VariableParameter {

    /**
     * since 1.5
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(add(3,5));
        System.out.println(add(1,2,3,4,5));
    }

    //可变参数使用...表示
    public static int add(int origin,int...args){
        int sum = origin;
        /**
         * 增强for循环
         */
        for (int arg:args){
            sum+=arg;
        }
        return sum;
    }
}
