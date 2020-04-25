package com.wp.静态导入;

/**
 * 静态导入，导入某个类中的某个静态方法，以免使用方法时重复书写类名加上方法名
 * since 1.5
 */

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class StaticImport {

    public static void main(String[] args) {
        System.out.println(max(5,10));
        System.out.println(abs(3-4));
    }
}
