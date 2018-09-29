package com.wang.jvm.classloader;

public class LoadCondition2 {
    /**
     * 数组不会触发类的加载
     */
    public static void main(String[] args){
        SuperClass[] arr = new SuperClass[10];
    }
}
