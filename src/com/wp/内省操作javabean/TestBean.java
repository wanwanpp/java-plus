package com.wp.内省操作javabean;

/**
 * Created by 王萍 on 2017/2/6 0006.
 */
public class TestBean {

    private Person person;

    private int num;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private String name;

    public TestBean(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public TestBean() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
