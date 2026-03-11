package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

    /**
     * public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
     * return new AutoShutdownDelegatedExecutorService
     * (new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>(),
     * threadFactory));
     * }
     */
    public static void main(String[] args) {
        try (ExecutorService singleThreadPool = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 20; i++) {
                singleThreadPool.execute(() -> System.out.println(
                        Thread.currentThread().getName() + " is executing a task"
                ));
            }
        }
    }

}
