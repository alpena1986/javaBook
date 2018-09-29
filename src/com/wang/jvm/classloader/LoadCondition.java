package com.wang.jvm.classloader;


/**
 * 会被加载
 * 因为value被使用了
 */
class SuperClass{
    static{
        System.out.println("super class init.");
    }
    public static int value=123;
}

/**
 * 不会被加载
 * 因为既没有new
 * 也没有静态成员或方法被调用
 */
class SubClass extends SuperClass{
    static{
        System.out.println("sub class init.");
    }
}


/**
 * 验证JVM会加载类的条件
 */
public class LoadCondition {
    public static void main(String[]args){
        System.out.println(SubClass.value);
    }
}
