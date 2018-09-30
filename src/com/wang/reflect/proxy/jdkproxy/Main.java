package com.wang.reflect.proxy.jdkproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
        Girl girl = new Girl();
        GirlProxy girlProxy = new GirlProxy(girl);
        MakeTrouble a = (MakeTrouble)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), Girl.class.getInterfaces(), girlProxy);
        a.makeTrouble();
    }
}
