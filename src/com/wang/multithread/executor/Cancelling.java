package com.wang.multithread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试Future.cancel与Thread.interrupt是否有区别
 */
public class Cancelling {


    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable t1 = () -> {
            for(int i = 0; i < 10000; i ++){
                System.out.println(i);
                if(Thread.interrupted()){
                    System.out.println("interrupted");
                    break;
                }
            }
            return 1;
        };
        Future future =  executorService.submit(t1);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            return;
        }
        // 如果在t1开始进入RUNNING状态之前就cancel的话，线程本身不会被执行
        // 如果在t1开始进入RUNNING状态之后cancel,跟interrupt一样，需要线程主动查看标志，做出处理。
        future.cancel(true);

        executorService.shutdown();
    }
}
