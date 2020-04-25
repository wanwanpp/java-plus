package com.wp.ArrayList与HashSet比较及Hashcode分析;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by 王萍 on 2017/2/6 0006.
 */
public class Demo {

    /**
     * Hashcode的作用参考 http://blog.csdn.net/fenglibing/article/details/8905007
     *
     * 在散列中找对象，先通过hashcode方法确定对象在哪个的桶里边，桶里边的实例对象有很多，然后需要根据equals方法找到具体对象。
     * @param args
     */

    public static void main(String[] args) {
        //集合为hashset，存入一个元素会根据hasecode值确定应该存储的空间，并确定这片区域是否
        //有相同的元素
        //可以自定义HashCode方法，判断Has何从的值是否相等。
        Collection collection = new HashSet();
        //arraylist的存储是按照先后顺序，不比较是否有相同元素
//        Collection collection = new ArrayList();
        Slave slave1=new Slave(1,1);
        Slave slave2=new Slave(5,5);
        Slave slave3=new Slave(1,1);


        collection.add(slave1);
        collection.add(slave2);
        collection.add(slave3);
        collection.add(slave1);

        //此处对slave1实例中的x成员变量进行更改，slave1产生了一个新的Hashcode的值。
        //因此，当对其进行remove（）操作时，无法找到以前那个存放地址，无法删除对象。
        //在内存中一直存在，属于内存泄漏。

        //在 Java 应用程序执行期间，在同一对象上多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是对象上 equals 比较中所用的信息没有被修改
        //当equals方法被重写时，通常有必要重写 hashCode 方法，以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。

        //  Hashcode的作用参考 http://blog.csdn.net/fenglibing/article/details/8905007

        slave1.x=7;

        collection.remove(slave1);

        System.out.println(collection.size());
    }
}
