package com.wp.枚举;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟星期中的表示的天，每个星期天都表示一个对象
 * 1、类中的每一个枚举成员都是该类的一个实例对象
 * 2、构造函数私有化
 * 3、提供操作枚举成员的抽象方法和静态方法
 */
public abstract class WeekDate {

    /**
	 * 星期一
	 */
	public static final WeekDate MON = new WeekDate("MON",0) {	//匿名子类

		@Override
		public WeekDate nextDay() {
			return TUES;
		}

		@Override
		public WeekDate preDay() {
			return WEDNES;
		}
		
		@Override
		public String toString() {
			return "WeekDate.MON";
		}
		
	};	
	
	/**
	 * 星期二
	 */
	public static final WeekDate TUES = new WeekDate("TUES",1) {

		@Override
		public WeekDate nextDay() {
			return WEDNES;
		}

		@Override
		public WeekDate preDay() {
			return MON;
		}
		
		@Override
		public String toString() {
			return "WeekDate.TUES";
		}
	
	};
	
	/**
	 * 星期三
	 */
	public static final WeekDate WEDNES = new WeekDate("WEDNES",2) {

		@Override
		public WeekDate nextDay() {
			return MON;
		}

		@Override
		public WeekDate preDay() {
			return TUES;
		}
		
		@Override
		public String toString() {
			return "WeekDate.WEDNES";
		}
		
	};

	private static Map<String, WeekDate> valueMap = new HashMap<String, WeekDate>();
	
	/**
	 * 枚举名称
	 */
	private final String name;

	/**
	 * 枚举成员的顺序
	 */
	private final int ordinal;

	private WeekDate(String name,int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}

	/**
	 * 保存枚举成员
	 */
	private static WeekDate[] values = {
		MON,TUES,WEDNES
	};

	//初始化
	static {
		valueMap.put("MON", values[0]);
		valueMap.put("TUES", values[1]);
		valueMap.put("WEDNES", values[2]);
	}

	/**
	 * 下一天
	 * @return
	 */
	public abstract WeekDate nextDay();

	/**
	 * 前一天
	 * @return
	 */
	public abstract WeekDate preDay();

	/**
	 * 枚举中的所有成员
	 * @return
	 */
	public static WeekDate[] values() {
		return values;
	}

	/**
	 * 将一个字符串转换成一个枚举成员对象
	 * @param name 枚举名称
	 * @return 枚举对象
	 */
	public static WeekDate valueOf(String name) {
		if (name.equalsIgnoreCase("MON")) {
			return MON;
		} else if (name.equalsIgnoreCase("TUES")) {
			return TUES;
		} else if (name.equalsIgnoreCase("WEDES")) {
			return WEDNES;
		}else {
			throw new IllegalArgumentException("找不到" + name + "枚举类型！");
		}
	}
	
	/**
	 * 优化字符串转枚举对象
	 * @param name 枚举名称
	 * @return 枚举对象
	 */
	public static WeekDate valueOf_2(String name) {
		WeekDate value = valueMap.get(name.toUpperCase());
		if (value == null) {
			throw new IllegalArgumentException("找不到" + name + "枚举类型！");
		}
		return value;
	}

	public String getName() {
		return name;
	}

	public int getOrdinal() {
		return ordinal;
	}
	
	/**
	 * 下一天
	 * @return
	 */
	/*public String nextDay() {
		if(this == MON) {
			return "TUES";
		} else if (this == TUES) {
			return "WEDNES";
		} else if (this == WEDNES) {
			return "THURS";
		} else if (this == THURS) {
			return "FRI";
		} else if (this == FRI) {
			return "SATUR";
		} else if (this == SATUR) {
			return "SUN";
		} else {
			return "MON";
		}
	}*/
	
	/**
	 * 前一天
	 */
	/*public String preDay() {
		if (this == MON) {
			//....
		} else if (...)....
	}*/
	
}
