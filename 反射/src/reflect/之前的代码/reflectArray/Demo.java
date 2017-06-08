package reflect.之前的代码.reflectArray;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class Demo {
    /**
     * 数组也是对象，他们的父类都是Object对象
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a1 = new int[3];
        int[] a2 = new int[4];
        int[][] a3 = new int[2][3];
        String[] a4 = new String[3];

        System.out.println(a1.getClass() == a2.getClass());

        //经编译器检查后，发现a1与a3，a4比较无法通过。
//        System.out.println(a1.getClass()==a4.getClass());
//        System.out.println(a1.getClass()==a3.getClass());

        System.out.println(a1.getClass().getName());
        System.out.println(a1.getClass().getSuperclass().getName());
        System.out.println(a4.getClass().getName());
        System.out.println(a4.getClass().getSuperclass().getName());
        System.out.println(a3.getClass().getName());
        System.out.println(a3.getClass().getSuperclass().getName());

        Object object1 = a1;
        Object object2 = a3;
        Object object3 = a4;

        //无法将int数组转为一个object数组对象
//        Object[] object4 = a1;

        //对于a3这种二维数组，可以将其中一个一维数组当做Object对象
        Object[] object5 = a3;

        //String本身的父类就是Object。
        Object[] object6 = a4;
    }
}
