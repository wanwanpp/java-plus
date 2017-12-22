package com.wp;

/**
 * @author 王萍
 * @date 2017/12/22 0022
 */
public class IntegerWrapper {
    public static void main(String[] args) {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;
        //'=='两边的c和d都是引用则比较引用是否相等，因为3在-128到127之间，会将Integer对象提前缓存起来
        //Integer c = 3;实际上是Integer c = Integer.valueOf(3); valueOf方法会从IntegerCache中返回合适的Integer，即值在-128到127之间的。
        //因此c和d都指向缓存的那个Integer。
        System.out.println(c==d);  //true
        System.out.println(e==f);   //false
//        a+b为表达式，所以比较的是c与a+b的值
        System.out.println(c==(a+b));  //true
        //先a和b拆箱，然后相加，再装箱为Integer。根据Integer的equals规则比较。
        System.out.println(c.equals(a+b));//true
        System.out.println(g==(a+b));  //true，比较数值
        System.out.println(g.equals(a+b));//false，   比较引用，g为Long，右边a+b装箱后为Integer。
        System.out.println(g.equals(a+h));//true       比较引用，g为Long，右边a+b装箱后为Long。
    }
}
