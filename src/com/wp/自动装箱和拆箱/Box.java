package com.wp.自动装箱和拆箱;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class Box {

    /**
     * since 1.5
     * @param args
     */
    public static void main(String[] args) {
        //自动装箱，将数据类型自动转为包装类型
        Integer integer = 3;

        //自动拆箱，将包装类型转为数据类型
        System.out.println(integer.getClass().getName());
        System.out.println(integer+10);

        int i = 128;
        //栈对象
        Integer i2 = 128;
        //堆对象
        Integer i3 = new Integer(128);

        //Integer会自动拆箱为int，所以为true
        System.out.println(i == i2);//true
        System.out.println(i == i3);//true
        System.out.println(i2 == i3);//false  堆对象和栈对象，对象之间比较，而自动拆箱后对象都转换为int比较值
        System.out.println("----------------------");

        Integer i5 = 127;//java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
        Integer i6 = 127;
        System.out.println(i5 == i6);//true

        //对于-128到127之间的数，会进行缓存，Integer i5 = 127时，会将127进行缓存，下次再写Integer i6 = 127时，就会直接从缓存中取，就不会new了。
//        Integer i51 = 128;
//        Integer i61 = 128;
//        System.out.println(i51 == i61);//false

        Integer i52=new Integer(126);
        Integer i62=new Integer(126);
        System.out.println(i52==i62);//false  堆中两个不同对象
        System.out.println("----------------------");

        Integer ii5 = new Integer(127);
        System.out.println(i5 == ii5); //false  堆对象和栈对象，对象之间比较，
      //new出来的两个对象肯定不相等

    }
}
