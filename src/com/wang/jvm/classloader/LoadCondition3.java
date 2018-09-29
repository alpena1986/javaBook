package com.wang.jvm.classloader;

/**
 * 在编译后的.class文件中，类的定义和常量池是分开的。
 * 引用VALUE常量不对导致类的加载
 */
class ConstClass{
    static{
        System.out.println("ConstClass init.");
    }
    public final static String VALUE = "hello";
}

public class LoadCondition3 {
    public static void main(String[] args){
        System.out.println(ConstClass.VALUE);
    }

}
