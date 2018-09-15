package com.wang.multithread.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 不加锁的时候，读写线程并发访问缓存会出现同步问题
 * 当然，如果进行同步处理的话，当然不会出现问题
 * 但是同步处理代价高
 * 这个类就测试如果使用读写锁，是否能解决并发访问问题
 * 结果是可以
 * 而且显然比较同步处理，效率更高
 */
public class ReadWriteLockCache {

    static Cache cache = new Cache();

    public static void main(String[] args){

        Callable t1 = () -> {
            //使用缓存1000次
            for(int i=0;i<10000;i++){
                int[] x = cache.getCache();
                // x[0]和x[1]相等是判断没有发生并发访问问题的
                if(x[0] != x[1]){
                    System.out.println("error error error error");
                }
            }
            return null;
        };


        // 100个线程并发使用缓存
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i=0;i<100;i++){
            Future future = executorService.submit(t1);
            //future.cancel(true);
        }

        executorService.shutdown();

    }

    /**
     * 缓存类
     * 缓存中的内容每次读取都会变为失效
     * 失效之后，下一个线程需要将缓存设值，并且置为有效
     */
    static class Cache{

        int[] content = new int[2];
        volatile boolean isValid = false;
        AtomicInteger readCount = new AtomicInteger(0);
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        int[] getCache(){
            readWriteLock.readLock().lock();
            if(!isValid){
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                if(!isValid){
                    content[0]++;
                    content[1]++;
                }
                isValid = true;
                readWriteLock.readLock().lock();
                readWriteLock.writeLock().unlock();
            }

            //将缓存的内容拷贝出来返回
            //相当于使用缓存
            int[] result = new int[2];
            result[0] = content[0];
            result[1] = content[1];

            //这里没有严格进行同步处理，但是并不紧要
            //不管是5次还是多于5次，只是想说明缓存在某个时间点会失效
            readCount.incrementAndGet();
            if(readCount.get() == 5){
                readCount.set(0);
                isValid = false;
            }
            readWriteLock.readLock().unlock();

            return result;
        }
    }
}


