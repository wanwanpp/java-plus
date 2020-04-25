package com.wp.ArrayList与HashSet比较及Hashcode分析;

import java.util.HashSet;
import java.util.Set;

public class HashTest {
    private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int hashCode() {
		return i % 10;
	}

	//若不重写equals方法那么下面HashTest两个实例对象是不相等的。
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HashTest hashTest = (HashTest) o;

		return i == hashTest.i;

	}

	public final static void main(String[] args) {
		HashTest a = new HashTest();
		HashTest b = new HashTest();
		a.setI(1);
		b.setI(1);
		Set<HashTest> set = new HashSet<HashTest>();
		set.add(a);
		set.add(b);
		System.out.println(a.hashCode() == b.hashCode());
		System.out.println(a.equals(b));
		System.out.println(set);
		System.out.println(set.size());
	}
}