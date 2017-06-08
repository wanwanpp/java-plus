package reflect.之前的代码.reflectArray;

import java.lang.reflect.Array;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class Demo2 {

    /**
     * java.lang.reflect包下的Array对象，用于Array的反射操作
     * @param args
     */
    public static void main(String[] args) {

        print(new int[]{1,2,3});
        print("haha");


        Object[] objects = {"1", 1, 1.2};
        for (Object o:objects){
            System.out.println(o.getClass().getName());
        }
    }

    public static void print(Object object){
        Class<?> objectClass = object.getClass();
        if (objectClass.isArray()){
            for (int i=0;i< Array.getLength(object);i++){
                System.out.println(Array.get(object,i));
            }
        }else {
            System.out.println(object);
        }
    }
}
