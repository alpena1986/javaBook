package com.wang.jvm.gc;


import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {

    private static List<String> a = new ArrayList<>();
    public static void main(String[] args){
        for(int i = 0 ; i < 1000000000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.add( String.valueOf(i));
        }
    }

}
