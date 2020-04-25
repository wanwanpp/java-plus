package concurrent.atomic;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: wangping
 * @Date: 2020/4/25 2:43 下午
 * https://blog.csdn.net/fouy_yun/article/details/77825039
 *
 * LongAdder是JDK1.8开始出现的，所提供的API基本上可以替换掉原先的AtomicLong。
 * LongAdder所使用的思想就是热点分离，这一点可以类比一下ConcurrentHashMap的设计思想。
 * 就是将value值分离成一个数组，当多线程访问时，通过hash算法映射到其中的一个数字进行计数。
 * 而最终的结果，就是这些数组的求和累加。这样一来，就减小了锁的粒度。
 */
public class LongAdderDemo {

    public static final int THREAD_COUNT = 1000;
    static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    static CompletionService<Long> completionService = new ExecutorCompletionService<Long>(pool);
    static final AtomicLong atomicLong = new AtomicLong(0L);
    static final LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        for(int i = 0; i < THREAD_COUNT; i++) {
            completionService.submit(() -> {
                for(int j = 0; j < 100000; j++) {

                    // 使用longadder比使用atomicLong性能高，并发越大时，差距越大。
                    atomicLong.incrementAndGet();

                    //longAdder.increment();
                    //longAdder.longValue();
                }
                return 1L;
            });
        }
        for(int i = 0; i < THREAD_COUNT; i++) {
            Future<Long> future = completionService.take();
            future.get();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        pool.shutdown();
    }
}

