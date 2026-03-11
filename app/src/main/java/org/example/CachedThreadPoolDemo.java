package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {

    /**
     * public static ExecutorService newCachedThreadPool() {
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     * }
     */
    public static void main(String[] args) {
        try (ExecutorService cachedThreadPool = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 20; i++) {
                cachedThreadPool.execute(() -> System.out.println(
                        Thread.currentThread().getName() + " is executing a task"
                ));
            }
        }
    }
}
