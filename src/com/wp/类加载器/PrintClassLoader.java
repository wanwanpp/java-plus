package com.wp.类加载器;
public class PrintClassLoader{
	public PrintClassLoader(){
		System.out.println("classLoader is : "+this.getClass().getClassLoader());
	}
}