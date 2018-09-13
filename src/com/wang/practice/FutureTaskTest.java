package com.wang.practice;

import java.util.concurrent.*;

/**
 * 这个类确认一件事
 * FutureTask因为实现了Runnable接口，所以可以提交给xxxExetutor来执行。
 * 这是可以通过FutureTask的get()方法来取得返回值。
 * 那么submit()方法返回的Future的实例，也有get方法，也能取得返回值吗？
 * 结果：不能
 */
public class FutureTaskTest {

    public static void main(String[] args) {


        Callable<Integer> c = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };

        FutureTask ftOrig = new FutureTask(c);

        ExecutorService exec = Executors.newSingleThreadExecutor();

        Future ftReturn = exec.submit(ftOrig);

        try {
            System.out.println("原始的FutureTask的返回值"+ftOrig.get());
            System.out.println("被返回的FutureTask的返回值"+ftReturn.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
