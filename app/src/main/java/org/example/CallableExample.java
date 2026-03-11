package org.example;

import java.util.Map;
import java.util.concurrent.*;

public class CallableExample {

    static final Map<Integer, Long> cache = new ConcurrentHashMap<>(Map.of(0, 0L, 1, 1L));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ExecutorService threadPool = Executors.newCachedThreadPool()) {
            Future<Long> fibonacciNumber = threadPool.submit(
                    () -> fibonacci(30)
            );
            final Long l = fibonacciNumber.get();
            System.out.println("l = " + l);
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
