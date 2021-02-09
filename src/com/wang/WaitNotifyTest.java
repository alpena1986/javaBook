package com.wang;

public class WaitNotifyTest {
    public static void main(String[] args) {
        Object a = new Object();

        synchronized (a){
            try {
                a.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int c = 1;
        c++;
    }
}
