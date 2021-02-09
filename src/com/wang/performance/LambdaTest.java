package com.wang.performance;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LambdaTest {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Dog> list = createList();
        List<String> dogNameList = new ArrayList<>();
        List<Integer> dogNumList = new ArrayList<>();
        for (Dog dog : list) {
            dogNameList.add(dog.name);
            dogNumList.add(dog.num);
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(elapsed);
    }



    public static class Dog {
        private Integer num;
        private String name;
    }

    private static List<Dog> createList(){
        List<Dog> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Dog dog = new Dog();
            dog.num = i;
            dog.name = "maomao" + i;
            result.add(dog);
        }
        return result;
    }
}
