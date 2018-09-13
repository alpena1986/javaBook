package com.wang.multithread;

/**
 * 用此类演示join的基本用法
 * 真的很基本哦
 */
public class Join {
    public static void main(String[] args){
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("t1 exec");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("t2 exec");
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                System.out.println("t3 exec");
            }
        };

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
    }
}
