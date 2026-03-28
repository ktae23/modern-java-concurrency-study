package org.example.virtual;

import java.time.Duration;

public class MyVirtualThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        FileOperation fileOperation = new FileOperation();
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            MyVirtualThread.start(() -> {
                System.out.println("Transfer: File_" + finalI + " Running in VirtualThread: " + MyVirtualThread.currentVirtualThread());

                fileOperation.transfer("File_" + finalI);

                System.out.println("Transfer: File_" + finalI + " Completed in  VirtualThread: " + MyVirtualThread.currentVirtualThread());
            });
        }

        Thread.sleep(Duration.ofSeconds(5));
    }
}
