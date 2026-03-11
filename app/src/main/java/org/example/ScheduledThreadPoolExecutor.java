package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor {

    /**
     * public ScheduledThreadPoolExecutor(int corePoolSize) {
     * super(corePoolSize, Integer.MAX_VALUE,
     * DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
     * new DelayedWorkQueue());
     * }
     */
    public static void main(String[] args) {
        try (ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2)) {
            scheduledThreadPool.scheduleAtFixedRate(
                    () -> System.out.println(Thread.currentThread().getName() + " is running a scheduled task"),
                    0,
                    5,
                    TimeUnit.SECONDS
            );
        }
    }
}
