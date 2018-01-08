package com.wp.回调.callback2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class JavaPromise {
    public static void main(String[] args) throws Throwable, ExecutionException {
        // 两个线程的线程池  
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("task started!");
                try {
                    //模拟耗时操作  
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "task finished!";
            }
        }, executor);

        //采用lambada的实现方式
        future.thenAccept(e -> System.out.println(e + " ok"));
        System.out.println("main thread is running");
    }
}  