package com.wang.multithread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockWithSynchronized {

    static final Object a = new Object();
    static final Object b = new Object();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                thread2();
            });
            executorService.submit(() -> {
                thread1();
            });
        }

        executorService.shutdown();

    }

    public static void thread1(){
        synchronized (a){
            synchronized (b){
                System.out.println("thread1");
            }
        }
    }

    public static void thread2(){
        synchronized (b){
            synchronized (a){
                System.out.println("thread2");
            }
        }
    }
}
