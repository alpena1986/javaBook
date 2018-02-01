package com.wang.practice;

/**
 * 这个类在测试什么？
 * 两个线程，都把同一个变量增加十万次。
 * 看看最终的结果是不是二十万。
 * 这个数字是变量a，使用volatile来确保各个线程对它的可见性。
 * 也就是，去除了缓存一致性问题的影响。
 * 这个类是在测试：即使去除了缓存一致性问题的影响，线程并发还是会产生很同步问题。
 */

public class Volatile {

    volatile static int a = 0;

    public static void main(String[] args) {

        Thread threadA = new Thread(){
            @Override
            public void run(){

                for(int i=0;i<100000;i++){
                    a++;
                }
            }
        };

        Thread threadB = new Thread(){
            @Override
            public void run(){

                for(int i=0;i<100000;i++){
                    a++;
                }
            }

        };

        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        }catch(InterruptedException e){

        }

        System.out.println(a);


    }
}
