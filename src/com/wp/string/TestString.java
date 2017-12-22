package com.wp.string;

public class TestString {

//    intern方法返回字符串首次出现的实例引用。
    public static void main(String[] args) {
        String str = "abc";
        String str1 = "abc";
        //str2中的value引用的是"abc"的地址，而不是复制abc的内容到value中
        String str2 = new String("abc");
        System.out.println(str == str1);
        System.out.println(str1 == "abc");
        System.out.println(str2 == "abc");
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2.intern());
        System.out.println(str2 == str2.intern());
        //String的hashCode方法会根据value生成值。
        System.out.println(str1.hashCode() == str2.hashCode());

//        char[] chars = new char[]{1, 2, 3, 4, 5};
//        char[] newchars = chars;
//        System.out.println(System.identityHashCode(chars));
//        System.out.println(System.identityHashCode(newchars));

        //会生成一个StringBuilder对象，然后调用其append，toString方法。
//        String s = str + "abc";
//        编译器直接编译为  String s = "1234"
//        String s = "12" + "34";

//        返回true
//        final String s = "a"; //注意:这里s用final修饰，相当于一个常量
//        String str5 = s + "b";
//        System.out.println(str5 == "ab");

//        返回false
//        String s = "a"; //注意:这里s没有用final修饰
//        String str5 = s + "b";
//        System.out.println(str5 == "ab");
//        String str3 = new String("wanwanpp");
//        System.out.println(str3.intern() == str3);
//        以"wanwanpp"作为构造函数的参数，这时会把"wanwanpp"这个常量串放到String池中
//        String str2 = new StringBuilder("wanwanpp").toString();
//        //返回false
//        System.out.println(str2.intern() == str2);
        //这里"wanwan"和"pp"是分开的。不会把"wanwanpp"这个常量串放到String池中。
//        String str5 = new StringBuilder("wanwan").append("pp").toString();
//        //返回true
//        System.out.println(str5.intern() == str5);

        //下面java和int是特殊字符，JVM开启，String池中就有这些特殊字符的常量串。因此返回false
//        String str4 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str4.intern() == str4);
//        String str1 = new StringBuilder("in").append("t").toString();
//        System.out.println(str1.intern() == str1);

        //下面的str1.intern()和str2.intern()都是返回池中的地址，因此相等。返回true
//        String str1 = new StringBuilder("in").append("t").toString();
//        String str2 = new StringBuilder("i").append("nt").toString();
//        System.out.println(str1.intern() == str2.intern());
    }
}
