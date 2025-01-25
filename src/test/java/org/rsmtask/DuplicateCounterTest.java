package org.rsmtask;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive tests for {@link DuplicateCounter}.
 * <p>
 * Covers: functional requirements, boundary value analysis, invalid input handling,
 * and system input validation.
 * </p>
 */
class DuplicateCounterTest {
    private InputStream originalSystemIn;

    @BeforeEach
    void preserveOriginalSystemIn() {
        originalSystemIn = System.in;
    }

    @AfterEach
    void restoreOriginalSystemIn() {
        System.setIn(originalSystemIn);
    }

    // ========================================================================
    // Functional Requirements (Core Logic)
    // ========================================================================
    @Test
    void countDuplicates_WithSampleInput0_ReturnsTwoDuplicates() {
        int[] numbers = {1, 3, 1, 4, 5, 6, 3, 2};
        assertEquals(2, DuplicateCounter.countDuplicates(numbers),
                "Two duplicates (1, 3) expected for Sample Input 0");
    }

    @Test
    void countDuplicates_WithSampleInput1_ReturnsTwoDuplicates() {
        int[] numbers = {1, 1, 2, 2, 2, 3};
        assertEquals(2, DuplicateCounter.countDuplicates(numbers),
                "Two duplicates (1, 2) expected for Sample Input 1");
    }

    // ========================================================================
    // Boundary Value Analysis (Valid Input Edges)
    // ========================================================================
    @Test
    void countDuplicates_WithSingleElement_ReturnsZero() {
        int[] numbers = {5};
        assertEquals(0, DuplicateCounter.countDuplicates(numbers),
                "Single-element arrays cannot have duplicates");
    }

    @Test
    void countDuplicates_WithMaximumAllowedValue_ReturnsOneDuplicate() {
        int[] numbers = {1000, 1000, 5};
        assertEquals(1, DuplicateCounter.countDuplicates(numbers),
                "Maximum allowed value (1000) should be counted as valid");
    }

    @Test
    void countDuplicates_WithMaximumArraySize_ReturnsOneDuplicate() {
        int[] numbers = new int[1000];
        Arrays.fill(numbers, 42);
        assertEquals(1, DuplicateCounter.countDuplicates(numbers),
                "Maximum array size (n=1000) should be processed correctly");
    }

    @Test
    void countDuplicates_WithMinimumAllowedValue_ReturnsOneDuplicate() {
        int[] numbers = {1, 1, 2, 3};
        assertEquals(1, DuplicateCounter.countDuplicates(numbers),
                "Minimum allowed value (1) should be counted as valid");
    }

    @Test
    void countDuplicates_WithExactDuplicateThreshold_ReturnsCount() {
        int[] numbers = {8, 8, 9, 9};
        assertEquals(2, DuplicateCounter.countDuplicates(numbers),
                "Elements with exactly 2 occurrences should be counted");
    }

    @Test
    void countDuplicates_WithBothBoundaryValues_ReturnsTwoDuplicates() {
        int[] numbers = {1, 1, 1000, 1000};
        assertEquals(2, DuplicateCounter.countDuplicates(numbers),
                "Both min (1) and max (1000) values should be counted");
    }

    @Test
    void countDuplicates_WithMaxSizeAllUnique_ReturnsZero() {
        int[] numbers = new int[1000];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        assertEquals(0, DuplicateCounter.countDuplicates(numbers),
                "Max-sized array with all unique values should return 0");
    }

    // ========================================================================
    // Invalid Input Handling (Constraint Violations)
    // ========================================================================
    @Test
    void countDuplicates_WithNullArray_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> DuplicateCounter.countDuplicates(null),
                "Null input must throw NullPointerException");
    }

    @Test
    void countDuplicates_WithNegativeNumber_ThrowsIndexOutOfBounds() {
        int[] numbers = {-5};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> DuplicateCounter.countDuplicates(numbers),
                "Negative values should cause ArrayIndexOutOfBounds");
    }

    @Test
    void countDuplicates_WithValueExceedingMax_ThrowsIndexOutOfBounds() {
        int[] numbers = {1001};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> DuplicateCounter.countDuplicates(numbers),
                "Values >1000 should cause ArrayIndexOutOfBounds");
    }

    // ========================================================================
    // System Input Validation (Main Method Behavior)
    // ========================================================================
    @Test
    void main_WithNonIntegerInput_ThrowsInputMismatch() {
        System.setIn(new ByteArrayInputStream("3\n1\nabc\n2".getBytes()));
        assertThrows(java.util.InputMismatchException.class,
                () -> DuplicateCounter.main(new String[]{}),
                "Non-integer input should throw InputMismatchException");
    }

    @Test
    void main_WithNegativeArraySize_ThrowsNegativeArraySize() {
        System.setIn(new ByteArrayInputStream("-5".getBytes()));
        assertThrows(NegativeArraySizeException.class,
                () -> DuplicateCounter.main(new String[]{}),
                "Negative array size should throw NegativeArraySizeException");
    }

    @Test
    void main_WithFloatingPointInput_ThrowsInputMismatch() {
        System.setIn(new ByteArrayInputStream("3.14".getBytes()));
        assertThrows(java.util.InputMismatchException.class,
                () -> DuplicateCounter.main(new String[]{}),
                "Floating-point input should throw InputMismatchException");
    }
}