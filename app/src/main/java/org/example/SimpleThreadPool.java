package org.example;

import java.util.concurrent.LinkedBlockingDeque;

public class SimpleThreadPool implements AutoCloseable {

    private final LinkedBlockingDeque<Runnable> queue;

    private final ThreadGroup threadGroup;

    private volatile boolean running = true;

    public SimpleThreadPool(int poolSize, int queueSize) {
        Worker[] threads = new Worker[poolSize];
        this.queue = new LinkedBlockingDeque<>(queueSize);
        this.threadGroup = new ThreadGroup("SimpleThreadPool");

        for (int i = 0; i < poolSize; i++) {
            threads[i] = new Worker(threadGroup, "Worker-" + i);
            threads[i].start();
        }
    }

    public void submit(Runnable task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        running = false;
        threadGroup.interrupt();
    }

    @Override
    public void close() {
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        shutdown();
    }

    class Worker extends Thread {

        public Worker(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Runnable task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
