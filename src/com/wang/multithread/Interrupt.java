package com.wang.multithread;

public class Interrupt {

    // 定义两个线程
    // 一个wait
    // 一个sleep
    // 两个线程执行同步代码块func
    static private Thread t1, t2;


    synchronized static void func(){
        // t1 线程进入wait queue
        if(Thread.currentThread().getName().equals("t1")){
            try {
                Interrupt.class.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
        // t2 线程sleep
        else if(Thread.currentThread().getName().equals("t2")){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
        System.out.println(Thread.currentThread().getName() + " func exec");
    }


    static public void main(String[] args){
        Runnable r = ()-> {
            func();
            System.out.println(Thread.currentThread().getName() + " exec");
        };
        t1 = new Thread(r, "t1");
        t2 = new Thread(r, "t2");
        t1.start();
        t2.start();

        // 等待t1和t2线程分别进入waiting状态
        // 保证不会在t2线程进入sleep状态之前就interrupt了t1线程。
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            return;
        }

        // 对t1 线程进行 interrupt
        // 结果是向t1线程发送了interrupt信号
        // 并且立即将其从waiting queue中取出
        // 放入runnable queue
        // 只不过mutex的竞争仍然存在
        // t1不得不重新去竞争mutex，因为t2已经拿到了mutex，所以t1进入blocked queue
        t1.interrupt();

        // 如果是对t2线程进行 interrupt
        // 那么t2线程会立即结束sleep
        // 并且抛出InterruptedException
        // t2.interrupt();
    }
}