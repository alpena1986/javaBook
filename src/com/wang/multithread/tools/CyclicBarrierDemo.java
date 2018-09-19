package com.wang.multithread.tools;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class CyclicBarrierDemo {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(
                    4,
                    10,
                    60,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadFactory() {
                        private final AtomicInteger threadNumber = new AtomicInteger(1);
                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "poooool-" + threadNumber );
                        }
                    });

    /**
     * 当拦截线程数达到4时，便优先执行barrierAction，然后再执行被拦截的线程。
     */
    private static final CyclicBarrier CB = new CyclicBarrier(4, () -> System.out.println("寝室四兄弟一起出发去球场"));

    public static void main(String[] args) {

        CompletionService<Object> completionService = new ExecutorCompletionService<>(THREAD_POOL_EXECUTOR);

        String[] str = {"李明", "王强", "刘凯", "赵杰"};
        for (int i = 0; i < 4; i++) {
            completionService.submit(new GoThread(str[i]) , new Object());
        }
        // 等待四个线程执行结束
        for (int i = 0; i < 4; i++){
            try {
                // CompletionService的特性是，只有当线程执行结束之后，Future才会进入completionService内部的存放Future的阻塞队列
                // 也就是take已经代表等待线程执行完成
                // get实际上是非阻塞的，因为线程已经执行完成
                completionService.take().get();
            } catch (InterruptedException e) {
                return;
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("四个人一起到达球场，现在开始打球");
    }

    private static class GoThread implements Runnable {
        private final String name;

        GoThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "开始从宿舍出发");
            try {
                CB.await();//拦截线程
                System.out.println(name + "从楼底下出发");
                System.out.println(name + "到达操场");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
