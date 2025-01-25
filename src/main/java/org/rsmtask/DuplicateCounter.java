package org.rsmtask;

import java.util.Scanner;

/**
 * Counts the number of unique integers that appear at least twice in an array.
 * <p>
 * This utility adheres to the problem constraints where:
 * <ul>
 *   <li>Array size \( n \) is between 1 and 1000</li>
 *   <li>Each integer is between 1 and 1000 (inclusive)</li>
 * </ul>
 * </p>
 */
public class DuplicateCounter {

    private static final int MAX_ALLOWED_VALUE = 1000;

    /**
     * Counts the number of non-unique integers in the given array.
     *
     * @param numbers Array of integers to analyze. Must adhere to constraints:
     *                \( 1 \leq \text{numbers[i]} \leq 1000 \).
     * @return The count of integers appearing at least twice.
     */
    public static int countDuplicates(int[] numbers) {
        int[] counts = new int[MAX_ALLOWED_VALUE + 1]; // 0-1000 indexes
        for (int num : numbers) {
            counts[num]++;
        }

        int duplicates = 0;
        for (int count : counts) {
            if (count >= 2) duplicates++;
        }
        return duplicates;
    }

    /**
     * Reads input from stdin and prints the count of non-unique integers.
     * <p>
     * Input format:
     * <ol>
     *   <li>First line: Integer \( n \) (array size)</li>
     *   <li>Next \( n \) lines: Integers to populate the array</li>
     * </ol>
     * </p>
     * <p><b>Note:</b> Assumes valid input per problem constraints.</p>
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
            }
            System.out.println(countDuplicates(numbers));
        }
    }
}