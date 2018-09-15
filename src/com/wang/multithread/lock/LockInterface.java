package com.wang.multithread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实验Lock接口被interrupt的情况
 * 结果表明tryLock(time)的版本也可以被interrupt
 * 当然，lockInterruptibly()也是可以
 * 但是lock()不可以
 */
public class LockInterface {

    private static final ReentrantLock lock = new ReentrantLock();

    static public void main(String[] args){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    Thread.sleep(10000);
                    lock.unlock();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted");
                }
                System.out.println(Thread.currentThread().getName() + " exec");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                // lock无法响应interrupt信号，也就是不能被中途中止
                lock.lock();
                lock.unlock();
                if(Thread.interrupted()){
                    System.out.println(Thread.currentThread().getName() + " interrupt signal received");
                }
                System.out.println(Thread.currentThread().getName() + " exec");
            }
        },"t2");

        t1.start();

        // 保证t1已经拿到lock
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        // 保证t2已经进入blocked状态
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 尝试是否可以interrupt t2 线程
        t2.interrupt();


    }
}
