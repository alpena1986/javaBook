package com.wang.distributed;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 生成递增的唯一序列号, 可以用来生成订单号,例如216081817202494579
 * <p/>
 * 生成规则:
 * 业务类型 + redis中最大的序列号
 * <p/>
 * 约定:
 * redis中最大的序列号长度为17,包括{6位日期 + 6位时间 + 3位毫秒数 + 2位随机}
 * <p/>
 * 建议:
 * 为了容错和服务降级, SeqGenerator生成失败时最好采用UUID替换
 * <p/>
 * Created by juemingzi on 16/8/19.
 */
public class SeqGenerator {

    public static void main(String[] args){

        AtomicLong counter = new AtomicLong();

        ExecutorService executorService = new ThreadPoolExecutor(1000, 1000, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
        for(int i=0;i<100;i++){
            executorService.submit(()->{
                while(true) {
                    counter.incrementAndGet();
                }
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.get());
    }
}
