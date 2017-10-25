package proxy;

/**
 * Created by 王萍 on 2017/10/24 0024.
 */
public class Student implements Study {
    @Override
    public void write() {
        System.out.println("I am writing");
    }

    @Override
    public void read() {
        System.out.println("I am reading");
    }
}
