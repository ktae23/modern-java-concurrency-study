package org.example.virtual;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MyVirtualThreadScheduler {

    public static final ThreadLocal<MyVirtualThread> CURRENT_VIRTUAL_THREAD = new ThreadLocal<>();

    public static final ScheduledExecutorService IO_EVENT_VIRTUAL_THREAD_SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    private final ExecutorService workStealingPool = Executors.newWorkStealingPool(2);

    public void schedule(MyVirtualThread virtualThread) {
        workStealingPool.submit(() -> {
            CURRENT_VIRTUAL_THREAD.set(virtualThread);
            virtualThread.run();
            CURRENT_VIRTUAL_THREAD.remove();
        });
    }
}
