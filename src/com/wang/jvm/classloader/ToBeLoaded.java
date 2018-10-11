package com.wang.jvm.classloader;

public class ToBeLoaded {

    static{
        System.out.println("ToBeLoaded static executed");
    }

    public final static int a = 11;
}
