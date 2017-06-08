package reflect.之前的代码.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class RunOtherZMain {

    /**
     * 调用其他类的main方法
     * @param args
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<Num> numClass = Num.class;
        Method mainmethod = numClass.getMethod("main", String[].class);
        //这里传入Num的main方法中时，会将string[]拆为3个string对象。报参数个数错误。
//        mainmethod.invoke(null,new String[]{"111","222","333"});

        //将string[]对象转为以object对象
        mainmethod.invoke(null,(Object) new String[]{"111","222","333"});

        //将string[]对象封装为一个object对象数组。
        mainmethod.invoke(null,new Object[]{new String[]{"111","222","333"}});

    }


}

class Num{
    public static void main(String[] args) {

        for (String arg:args){
            System.out.println(arg);
        }
    }
}