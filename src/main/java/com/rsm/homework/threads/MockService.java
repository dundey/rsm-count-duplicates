package com.rsm.homework.threads;

import java.util.Random;

public class MockService {
    private final Random random = new Random();

    public void execute() throws Exception {
        // ~3.45% chance of running 1001..1200 ms
        if (random.nextFloat() < 0.0345f) {
            Thread.sleep(1001 + random.nextInt(200));
        } else {
            Thread.sleep(1 + random.nextInt(1000));
        }

        // ~27.5% chance of failure
        if (random.nextFloat() < 0.275f) {
            throw new RuntimeException("MockService failed");
        }
    }
}
