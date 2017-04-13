package reflect.泛型擦除;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TypeCounter extends HashMap<Class<?>, Integer> {   //继承HashMap是为了更方便的使用其get，put方法
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {  //判断baseType类型是否为type类型的父类或者间接父类
            throw new RuntimeException(
                    obj + " incorrect type " + type + ", should be type or subtype of " + baseType);
        }
        countClass(type);
    }

    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("} ");
        return result.toString();
    }

    public static void main(String[] args) {
        TypeCounter typeCounter = new TypeCounter(Object.class);
        typeCounter.count(new ArrayList<>());
        System.out.println(typeCounter.toString());
    }
}