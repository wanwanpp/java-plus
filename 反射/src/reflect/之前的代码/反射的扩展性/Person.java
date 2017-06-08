package reflect.之前的代码.反射的扩展性;

/**
 * Created by 王萍 on 2017/2/5 0005.
 */
public class Person {

    public void produce() {
        System.out.println("我是一个man");
    }

    public  void can(Special special){
        special.mySpecial();
    }
}
