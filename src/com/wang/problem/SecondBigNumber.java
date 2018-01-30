package com.wang.problem;

public class SecondBigNumber {
    public int getSecondBig(int[] values){

        if(values == null || values.length < 2){
            throw new RuntimeException("wrong array passed");
        }

        int biggest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for(int v : values){
            if(v > biggest){
                second = biggest;
                biggest = v;
            } else {
                if(v > second){
                    second = v;
                }
            }
        }

        return second;
    }
}
