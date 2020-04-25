package concurrent.atomic;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * LongAccumulator是LongAdder的功能增强版。LongAdder的API只有对数值的加减，
 * 而LongAccumulator提供了自定义的函数操作。
 */
public class LongAccumulatorDemo {

    private static final int THREAD_NUMBER = 10;

    // 找出最大值
    public static void main(String[] args) throws InterruptedException {

        // 传入一个函数
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        // 也可以自定义函数
        //LongAccumulator accumulator2 = new LongAccumulator(LongAccumulatorDemo::getMax, Long.MIN_VALUE);
        Thread[] ts = new Thread[THREAD_NUMBER];

        for (int i = 0; i < THREAD_NUMBER; i++) {
            ts[i] = new Thread(() -> {
                Random random = new Random();
                long value = random.nextLong();
                System.out.println(value);
                accumulator.accumulate(value); // 比较value和上一次的比较值，然后存储较大者
            });
            ts[i].start();
        }
        for (int i = 0; i < THREAD_NUMBER; i++) {
            ts[i].join();
        }
        System.out.println("\n\n\n" + accumulator.longValue());
    }

    public static long getMax(long value1, long value2) {
        return Math.max(value1, value2);
    }
}
