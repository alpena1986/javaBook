package com.wang.designpattern;

public class MySingletonClass {

    public int field;
    private MySingletonClass(){
        field = 19;
    };

    private static Object instance;

    public static Object getInstance(){
        if(instance == null) {
            synchronized (MySingletonClass.class) {
                if (instance == null) {
                    instance = new Object();
                }
            }
        }
        return instance;
    }




    public static void main(String[] args){
        MySingletonClass.getInstance();
    }

}
