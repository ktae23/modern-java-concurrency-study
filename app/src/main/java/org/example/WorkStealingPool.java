package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPool {

    /**
     * public static ExecutorService newWorkStealingPool() {
     * return new ForkJoinPool
     * (Runtime.getRuntime().availableProcessors(),
     * ForkJoinPool.defaultForkJoinWorkerThreadFactory,
     * null, true);
     * }
     *
     */
    public static void main(String[] args) {
        try (ExecutorService workStealingPool = Executors.newWorkStealingPool()) {
            for (int i = 0; i < 10; i++) {
                workStealingPool.submit(() -> System.out.println(Thread.currentThread().getName() + " is executing a task"));
            }
        }
    }
}
