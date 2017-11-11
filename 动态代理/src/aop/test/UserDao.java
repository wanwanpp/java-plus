package aop.test;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public class UserDao implements Dao {
    @Override
    public void select() {
        System.out.println("获取User信息");
    }

    @Override
    public void insert() {
        System.out.println("插入User信息");
    }
}
