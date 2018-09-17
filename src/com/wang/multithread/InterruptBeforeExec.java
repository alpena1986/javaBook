package com.wang.multithread;


/**
 * 仅仅证明了interrupt不能先于Thread.start()被调用
 */
public class InterruptBeforeExec {



    static public void main(String[] args){
        Runnable r = ()-> {
            if(Thread.interrupted()){
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " exec");
        };
        Thread t1 = new Thread(r, "t1");
        t1.interrupt();
        t1.start();


    }
}