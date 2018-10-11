package com.wang.jvm.classloader;


/**
 * 通过-XX:+TraceClassResolution -verbose:class参数来观察类的loading和resolving
 * 可以发现，类加载器会load和link一个类，而不仅仅是load一个类
 * 而真正的new或者引用一个静态变量才会触发init
 *
 */
public class ResolutionTimingTest {

    public static void main(String[] args){
        try {
            // 随便载入一个类
            ResolutionTimingTest.class.getClassLoader().loadClass("com.wang.jvm.classloader.ToBeLoaded");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------------");
            System.out.println("----------------");
            System.out.println("----------------");
            System.out.println("----------------");
            System.out.println("----------------");
            int c = ToBeLoaded.a;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
