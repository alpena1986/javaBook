package com.wang.reflect.proxy.jdkproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
        Girl girl = new Girl();
        GirlProxyInvocationHandler girlProxyInvocationHandler = new GirlProxyInvocationHandler(girl);
        MakeTrouble a = (MakeTrouble)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), Girl.class.getInterfaces(), girlProxyInvocationHandler);
        a.makeTrouble();
    }
}
