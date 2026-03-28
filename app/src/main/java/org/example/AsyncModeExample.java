package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncModeExample {

    /**
     * 가상스레드에서는 비동기 모드가 사용됨
     */
    public static void main(String[] args) {
        try (
                //                ForkJoinPool forkJoinPool = new ForkJoinPool(4, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
                ExecutorService forkJoinPool = Executors.newWorkStealingPool(4)
        ) {

            for (int i = 0; i < 10; i++) {
                forkJoinPool.submit(new EventTask("Event " + i));
            }
        }
    }

    record EventTask(String eventName) implements Runnable {

        @Override
        public void run() {
            System.out.println("Processing " + eventName + " in thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Completed " + eventName + " in thread: " + Thread.currentThread().getName());
        }
    }
}
