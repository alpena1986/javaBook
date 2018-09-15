package com.wang.multithread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的基本用法
 */
public class LockCondition {

    private static final ReentrantLock lock = new ReentrantLock();

    static public void main(String[] args){

        Condition cond1 =  lock.newCondition();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                cond1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " exec");
        }, "t1");
        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cond1.signal();
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " exec");
        },"t2");

        t1.start();

        // 保证t1已经拿到lock并进入await状态
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
