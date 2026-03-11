package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class CallableExample {

    static final Map<Integer, Long> cache = new ConcurrentHashMap<>(Map.of(0, 0L, 1, 1L));

    public static void main(String[] args) {
        List<Future<Long>> futures = new ArrayList<>();
        List<Integer> fibonacciIndices = List.of(10, 20, 30, 40, 50);

        try (ExecutorService threadPool = Executors.newCachedThreadPool()) {
            for (int index : fibonacciIndices) {
                futures.add(threadPool.submit(() -> fibonacci(index)));
            }
            for (Future<Long> future : futures) {
                System.out.println("Fibonacci number: " + future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static Long fibonacci(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            long result = fibonacci(n - 1) + fibonacci(n - 2);
            cache.put(n, result);
            return result;
        }
    }

}
