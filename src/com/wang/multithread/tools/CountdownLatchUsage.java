package com.wang.multithread.tools;

import java.util.concurrent.CountDownLatch;

/**
 * CountdownLatch的基本用法
 */
public class CountdownLatchUsage {
    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + " exec");
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + " exec");
            countDownLatch.countDown();
        });

        t1.start();
        t2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " exec");
    }
}
