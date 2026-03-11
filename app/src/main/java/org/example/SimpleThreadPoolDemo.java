package org.example;

public class SimpleThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        try (var threadPool = new SimpleThreadPool(4, 100)) {
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                threadPool.submit(() -> runTask(finalI));
            }
        }

        Thread.sleep(10_000);
        System.out.println("Main thread finished");
    }

    private static void runTask(int id) {
        System.out.printf("Task %d on %s%n", id, Thread.currentThread().getName());

        try {
            Thread.sleep(100); // 작업 처리 시뮬레이션
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
