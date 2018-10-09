package com.wang.algorism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AlibabaFeizhuTest {

    private static List<Object> listA = new ArrayList<>();

    private static List<Object> listB = new ArrayList<>();

    private static List<Object> listC = new ArrayList<>();

    private static List<Object> listD = new ArrayList<>();

    private static AtomicInteger aSize = new AtomicInteger();

    private static AtomicInteger bSize = new AtomicInteger();

    private static AtomicInteger cSize = new AtomicInteger();

    private static AtomicInteger dSize = new AtomicInteger();

    private static float A_WEIGHT = 1;

    private static float B_WEIGHT = 2;

    private static float C_WEIGHT = 3;

    private static float D_WEIGHT = 4;

    /**
     * 得到应该插入的集合
     */
    private static List<Object> getShouldInsertCollection(){
        float smallest = (float)aSize.get() / A_WEIGHT;
        List<Object> resultCollection = listA;
        if((float)bSize.get()/ B_WEIGHT <= smallest){
            smallest = bSize.get()/ B_WEIGHT;
            resultCollection = listB;
        }
        if((float)cSize.get()/ C_WEIGHT <= smallest){
            smallest = cSize.get()/ C_WEIGHT;
            resultCollection = listC;
        }
        if((float)dSize.get()/ D_WEIGHT <= smallest){
            smallest = dSize.get()/ D_WEIGHT;
            resultCollection = listD;
        }
        return resultCollection;
    }

    /**
     * 向A,B,C,D集合中按权重插入元素
     */
    private static void insert(Object a){
        if(a == null) {
            throw new NullPointerException();
        }

        // 按权重拿到目标集合
        List<Object> targetCollection = getShouldInsertCollection();

        if(targetCollection == listA){
            aSize.incrementAndGet();
        } else if(targetCollection == listB){
            bSize.incrementAndGet();
        } else if(targetCollection == listC){
            cSize.incrementAndGet();
        } else if(targetCollection == listD){
            dSize.incrementAndGet();
        } else{
            throw new RuntimeException("unexpected targetCollection or null targetCollection");
        }

        // 因为使用的是ArrayList，无奈只能加锁
        synchronized (targetCollection){
            targetCollection.add(a);
        }

    }

    public static void main(String[] args){
        for(int i = 0; i< 30023; i++){
            insert(new Object());
        }

        System.out.println("size of listA:" + aSize);
        System.out.println("size of listB:" + bSize);
        System.out.println("size of listC:" + cSize);
        System.out.println("size of listD:" + dSize);
    }
}
