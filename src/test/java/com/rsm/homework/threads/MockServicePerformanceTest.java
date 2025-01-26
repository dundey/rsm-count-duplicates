package com.rsm.homework.threads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MockServicePerformanceTest {

    @Test
    void performanceTest() throws InterruptedException {
        int totalCalls = 1000;
        int concurrency = 100;
        MockService mockService = new MockService();

        ExecutorService executor = Executors.newFixedThreadPool(concurrency);
        long testStart = System.currentTimeMillis();

        List<Future<CallResult>> futures = new ArrayList<>(totalCalls);
        for (int i = 0; i < totalCalls; i++) {
            futures.add(executor.submit(() -> {
                long start = System.currentTimeMillis();
                boolean success = true;
                try {
                    mockService.execute();
                } catch (Exception e) {
                    success = false;
                }
                long end = System.currentTimeMillis();
                return new CallResult(success, end - start);
            }));
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);
        if (!finished) {
            throw new RuntimeException("Executor did not finish in 1 minute!");
        }

        int successCount = 0, overOneSecondCount = 0;
        for (Future<CallResult> future : futures) {
            try {
                CallResult result = future.get();
                if (result.success) successCount++;
                if (result.duration > 1000) overOneSecondCount++;
            } catch (ExecutionException ignored) {
            }
        }

        long totalTestDuration = System.currentTimeMillis() - testStart;
        double successRatio = (double) successCount / totalCalls;
        double overOneSecondRatio = (double) overOneSecondCount / totalCalls;

        System.out.println("=== Performance Test Results ===");
        System.out.println("Duration: " + totalTestDuration + " ms");
        System.out.printf("Success:  %d / %d (%.2f%%)\n", successCount, totalCalls, successRatio * 100);
        System.out.printf(">1s:      %d / %d (%.2f%%)\n", overOneSecondCount, totalCalls, overOneSecondRatio * 100);
        System.out.println("=================================");

        Assertions.assertTrue(totalTestDuration < 60_000, "Test took >= 1 minute");
        Assertions.assertTrue(successRatio >= 0.70, "Success ratio < 70%");
        Assertions.assertTrue(overOneSecondRatio <= 0.05, "Calls >1s > 5%");
    }

    record CallResult(boolean success, long duration) {}
}
