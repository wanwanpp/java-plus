package com.wp.类加载器;

/**
 * Created by 王萍 on 2017/10/24 0024.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        FileSystemClassLoader classLoader=new FileSystemClassLoader("wanwnapp","F:/tmp/");
        Class<?> printClassLoader = classLoader.loadClass("com.wp.PrintClassLoader");
        printClassLoader.newInstance();
    }
}
