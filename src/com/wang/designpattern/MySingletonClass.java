package com.wang.designpattern;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class MySingletonClass {

    private MySingletonClass(){};

    private static Object instance;

    public static Object getInstance(){
        if(instance == null) {
            synchronized (MySingletonClass.class) {
                if (instance == null) {
                    instance = new Object();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args){

    }
}
