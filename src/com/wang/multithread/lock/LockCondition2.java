package com.wang.multithread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的基本用法
 */
public class LockCondition2 {

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
                cond1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.println(Thread.currentThread().getName() + " exec");
        },"t2");

        t1.start();
        t2.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        cond1.signalAll();
        lock.unlock();
    }
}
