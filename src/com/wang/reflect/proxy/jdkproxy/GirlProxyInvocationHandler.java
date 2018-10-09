package com.wang.reflect.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GirlProxyInvocationHandler implements InvocationHandler {

    private Girl girl;

    public GirlProxyInvocationHandler(Girl girl){
        this.girl = girl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        // 执行目标对象的方法
        Object result = method.invoke(girl, args);
        System.out.println("proxy method executed");
        return null;
    }
}
