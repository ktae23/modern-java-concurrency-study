package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    /**
     * public static ExecutorService newFixedThreadPool(int nThreads) {
     * return new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>());
     * }
     */
    public static void main(String[] args) {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors = " + availableProcessors);
        try (ExecutorService cachedThreadPool = Executors.newFixedThreadPool(availableProcessors)) {
            for (int i = 0; i < 20; i++) {
                cachedThreadPool.execute(() -> System.out.println(
                        Thread.currentThread().getName() + " is executing a task"
                ));
            }
        }
    }
}
