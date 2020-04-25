package reflect.之前的代码.application;

import java.lang.reflect.Field;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class ReplaceChar {

    /**
     * 使用反射将Demo中的成员变量中字符串类型中有a的字符替换为z。
     */
    private String name = "wanwanpp";
    private String sentence = "hanhanpp";
    private String str1 = "fasw";
    private int num = 12;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<ReplaceChar> demoClass = ReplaceChar.class;
        Field[] declaredFields = demoClass.getDeclaredFields();
        ReplaceChar instance = demoClass.newInstance();
        for (Field field : declaredFields) {
            field.setAccessible(true);

            //排除非String对象
            if (field.getType() == String.class) {
                String string = (String) field.get(instance);
                System.out.println("before:" + string);

                //string类型是不可变的需要创建另一个string对象进行接收替换后的字符串。
                String s = string.replaceAll("a", "z");
                field.set(instance, s);

                System.out.println("after:" + field.get(instance));
            }
        }
    }
}
