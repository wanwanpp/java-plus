package com.wp.枚举;

/**
 * 枚举的应用
 * 存储每周中的天份
 */
public enum WeekDateEnum {

    /**
     * 枚举中基本元素为名字和顺序
     * 当枚举中只有一个成员时，可以当做单例的一种实现方式。
     */
    MON(1) {
        @Override
        public WeekDateEnum nextDay() {
            return TUES;
        }

        @Override
        public WeekDateEnum preDay() {
            return SUN;
        }

    }, TUES {
        @Override
        public WeekDateEnum nextDay() {
            return WEDNES;
        }

        @Override
        public WeekDateEnum preDay() {
            return MON;
        }

    }, WEDNES {
        @Override
        public WeekDateEnum nextDay() {
            return THURS;
        }

        @Override
        public WeekDateEnum preDay() {
            return TUES;
        }

    }, THURS {
        @Override
        public WeekDateEnum nextDay() {
            return FRI;
        }

        @Override
        public WeekDateEnum preDay() {
            return WEDNES;
        }

    }, FRI {
        @Override
        public WeekDateEnum nextDay() {
            return SATUR;
        }

        @Override
        public WeekDateEnum preDay() {
            return THURS;
        }

    }, SATUR {
        @Override
        public WeekDateEnum nextDay() {
            return SATUR;
        }

        @Override
        public WeekDateEnum preDay() {
            return FRI;
        }

    }, SUN {
        @Override
        public WeekDateEnum nextDay() {
            return SATUR;
        }

        @Override
        public WeekDateEnum preDay() {
            return MON;
        }

    };

    private WeekDateEnum() {
        System.out.println("first");
    }

    private WeekDateEnum(int i){
        System.out.println("second");
    }

    /**
     * 下一天
     *
     * @return
     */
    public abstract WeekDateEnum nextDay();

    /**
     * 前一天
     *
     * @return
     */
    public abstract WeekDateEnum preDay();

    /**
     * 枚举对象公共的toString方法，可以在case块中反馈自己想要返回的信息
     */
    public String toString() {
        switch (this) {
            case MON:
                return "WeekDateEnum.MON";
            case TUES:
                return "WeekDateEnum.TUES";
            case WEDNES:
                return "WeekDateEnum.WEDNES";
            case THURS:
                return "WeekDateEnum.THURS";
            case FRI:
                return "WeekDateEnum.FRI";
            case SATUR:
                return "WeekDateEnum.SATUR";
            case SUN:
                return "WeekDateEnum.SUN";
            default:
                return null;
        }
    }
}
