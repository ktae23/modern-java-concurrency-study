package org.example.virtual;

import jdk.internal.vm.Continuation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.example.virtual.MyVirtualThread.SCOPE;
import static org.example.virtual.MyVirtualThread.currentVirtualThread;

public class FileOperation {

    private final Random random = new Random();

    public void transfer(String filePath) {
        System.out.println("Start transferring file: " + filePath);
        MyVirtualThread myVirtualThread = currentVirtualThread();
        MyVirtualThreadScheduler.IO_EVENT_VIRTUAL_THREAD_SCHEDULER.schedule(
                () -> MyVirtualThread.VIRTUAL_THREAD_SCHEDULER.schedule(myVirtualThread),
                random.nextInt(1000),
                TimeUnit.MILLISECONDS
        );
        MyVirtualThreadScheduler.CURRENT_VIRTUAL_THREAD.remove();
        Continuation.yield(SCOPE);
        System.out.println("Transfer completed for file: " + filePath);
    }
}
