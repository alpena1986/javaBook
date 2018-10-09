package com.wang.multithread.executor;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实验一个线程池
 * 加入core threads 是10的话
 * 是否一创建的时候线程数就是10
 */
public class SizeWhenCreatedTest {

    public static void main(String[] args){

        AtomicInteger counter = new AtomicInteger();
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "SizeWhenCreatedTestPool_" + counter.incrementAndGet());
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(Thread.currentThread().getName() + " rejected");
                    }
                });

        for(int i = 0; i<150;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " executed");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 通过jstack判断，原来并不是一开始就把线程池中的core thread全都创建出来

        executorService.shutdown();
    }
}
