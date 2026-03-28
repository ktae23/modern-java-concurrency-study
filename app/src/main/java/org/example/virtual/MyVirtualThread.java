package org.example.virtual;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.util.concurrent.atomic.AtomicInteger;

public class MyVirtualThread {

    public static final MyVirtualThreadScheduler VIRTUAL_THREAD_SCHEDULER = new MyVirtualThreadScheduler();

    public static final AtomicInteger COUNTER = new AtomicInteger(1);

    public static final ContinuationScope SCOPE = new ContinuationScope("virtual-thread-scope");

    private final Continuation continuation;

    private final int vtid;

    private MyVirtualThread(Runnable runnable) {
        this.vtid = COUNTER.getAndIncrement();
        this.continuation = new Continuation(SCOPE, runnable);
    }

    public static void start(Runnable runnable) {
        var virtualThread = new MyVirtualThread(runnable);
        VIRTUAL_THREAD_SCHEDULER.schedule(virtualThread);
    }

    public void run() {
        continuation.run();
    }

    public static MyVirtualThread currentVirtualThread() {
        return MyVirtualThreadScheduler.CURRENT_VIRTUAL_THREAD.get();
    }

    @Override
    public String toString() {
        return "VirtualThread - " + vtid + "-" + Thread.currentThread().getName();
    }
}
