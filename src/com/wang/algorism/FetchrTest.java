package com.wang.algorism;

public class FetchrTest {
    public static void main(String[] args){
//        int[] values = args[0];
//        int target = args[1];
//        System.out.println(findTarget(values, target));
    }

    public int findTarget(int[] values, int target){

        int startIndex = 0, endIndex = values.length - 1;
        int index = -1;
        while(true){
            if(startIndex == endIndex && values[startIndex] == target){
                index = startIndex;
                break;
            }
            int middleIndex = (startIndex + endIndex) /2;
            if(values[middleIndex] != target){
                if(inRange(values[startIndex], values[middleIndex], target)){
                    endIndex = middleIndex;
                } else if(inRange(values[middleIndex + 1], values[endIndex], target)){
                    startIndex = middleIndex;
                } else {
                    break;
                }
            }
        }

        return index;
    }

    public boolean inRange(int left, int right, int target){
        boolean result = false;
        if(left <= right){
            if(target>=left && target<= right){
                result = true;
            }
        } else {
            if(target >= left || target <= right){
                result = true;
            }
        }
        return true;
    }
}
