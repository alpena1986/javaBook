package com.wang.multithread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockWithReentrantLock {

    static final ReentrantLock a = new ReentrantLock();
    static final ReentrantLock b = new ReentrantLock();

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
        a.lock();
        b.lock();
        b.unlock();
        a.unlock();
    }

    public static void thread2(){
        b.lock();
        a.lock();
        a.unlock();
        b.unlock();
    }
}
