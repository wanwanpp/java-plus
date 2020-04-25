package com.wp.枚举;

/**
 * 枚举功能测试
 */
public class EnumTest {

	public static void main(String[] args) {

        //使用普通JAVA类模拟枚举的应用
		WeekDate weekDate = WeekDate.MON;		//获得一个枚举对象
		//调用枚举中提供的方法
		System.out.println(weekDate.nextDay());	
		System.out.println(weekDate.preDay());
		System.out.println(weekDate.getName());
		//获得枚举成员所在枚举成员列表中的位置
		System.out.println(weekDate.getOrdinal());
		//调用某一个枚举成员的方法
		System.out.println(WeekDate.values()[0].preDay());
		System.out.println("---------------遍历枚举成员,普通JAVA类模拟--------------------------");
		for (WeekDate weekDate2 : WeekDate.values()) {
			System.out.println(weekDate2);
		}
		
		System.out.println("\n=================================================================\n");
		
		//使用JDK中提供的枚举特性功能应用
		WeekDateEnum weekDateEnum = WeekDateEnum.MON;	//获得一个枚举对象
		System.out.println(WeekDate.values().length); 	//获得枚举成员数量
		System.out.println(weekDateEnum.name());		//获得枚举的字符串名称
		System.out.println(weekDateEnum.toString());	//打印枚举对象，已重写toString方法，默认打印枚举的名称
		System.out.println(weekDateEnum.nextDay().ordinal());	//枚举成员列表中的位置
		System.out.println(WeekDateEnum.valueOf("FRI").nextDay().ordinal());
		System.out.println("---------------遍历枚举成员，使用JDK的枚举特性-------------------------");
		for (WeekDateEnum enumDemo : WeekDateEnum.values()) {
			System.out.println(enumDemo);
		}
		
	} 
	
}