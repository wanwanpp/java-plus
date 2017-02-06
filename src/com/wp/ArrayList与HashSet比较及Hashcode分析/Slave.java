package com.wp.ArrayList与HashSet比较及Hashcode分析;

/**
 * Created by 王萍 on 2017/2/6 0006.
 */
public class Slave {
    public int x,y;

    public Slave(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slave slave = (Slave) o;

        if (x != slave.x) return false;
        return y == slave.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
