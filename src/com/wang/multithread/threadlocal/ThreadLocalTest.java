package com.wang.multithread.threadlocal;

public class ThreadLocalTest {

    public static ThreadLocal<Integer> a = new ThreadLocal<>();

    public static void main(String[] args){

        // 这时Thread对象的threadLocals才会被put一个值
        // key就是a对象本身
        a.set(1987);

        // 这时Thread对象的threadLocals中以a为key的键值对会被删除
        a.remove();


    }

}
