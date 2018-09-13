package com.wang.algorism;

public class SecondBigNumber {


    /**
     * 得到一个整数数组的第二大的元素
     * @param values 整数数组
     * @return 第二大数字
     */
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
