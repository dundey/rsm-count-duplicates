package com.rsm.homework.selenium;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class RetryAnalyzer implements TestExecutionExceptionHandler {

    private static final int MAX_RETRY = 2;
    private int retryCount = 0;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (retryCount < MAX_RETRY) {
            retryCount++;
            System.out.println("Retrying test " + context.getDisplayName() + " - attempt " + retryCount);
            // Re-run the test method
            throw throwable;
        } else {
            throw throwable; // fail after max retries
        }
    }
}
