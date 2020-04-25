package com.wp.回调.callback2;

import java.util.concurrent.*;

/**
 * jdk1.8以前future的get()需要一个线程去阻塞等待，或者做一些其他的事然后轮询是否已经完成任务（isDone方法）。
 * 并不能做到真正的回调，即做好了任务就通知你。
 */
public class JavaFuture {
    public static void main(String[] args) throws Throwable, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> f = executor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("task started!");
                Thread.sleep(3000);
                System.out.println("task finished!");
                return "hello";
            }
        });

        System.out.println(f.isDone());
        //此处阻塞main线程  
        System.out.println(f.get());
        System.out.println("main thread is blocked");
    }
}  