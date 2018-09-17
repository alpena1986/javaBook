package com.wang.multithread;

import java.util.concurrent.locks.LockSupport;

/**
 * 仅仅是LockSupport的基本使用方法
 */
public class Park {
    public static void main(String[] args){

        Thread t1 = new Thread(() -> {
            // new Object()与并发控制无关。单纯只是记录一下，可以在某些dump查看工具中看到这个对象，
            // 从而追踪线程因为什么原因Park了。
            LockSupport.park(new Object());
            System.out.println("t1 exec");
        }, "t1");

        t1.start();

        LockSupport.unpark(t1);
    }
}
