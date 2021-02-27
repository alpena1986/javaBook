package com.wang.algorism.orderedprint;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static AtomicInteger cnt = new AtomicInteger(0);
    private final static int flag = 3000000;

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread TA = new Thread(new Runnable(){
            @Override
            public void run() {
                while (cnt.get()<= flag) {

                    if(cnt.get() % 3 == 0){
                        //System.out.println(cnt.get() + "A");
                        cnt.incrementAndGet();
                    }
                }
            }
        });

        Thread TB = new Thread(new Runnable(){
            @Override
            public void run() {
                while (cnt.get() <= flag) {

                    if(cnt.get() % 3 == 1){
                        //System.out.println(cnt.get() + "B");
                        cnt.incrementAndGet();
                    }

                }
            }
        });

        Thread TC = new Thread(new Runnable(){
            @Override
            public void run() {
                while (cnt.get() <= flag) {
                    if(cnt.get() % 3 == 2){
                        //System.out.println(cnt.get() + "C");
                        cnt.incrementAndGet();
                    }
                }
            }
        });


        TA.start();
        TB.start();
        TC.start();

        TA.join();
        TB.join();
        TC.join();

        System.out.println(System.currentTimeMillis() - start);
    }
}