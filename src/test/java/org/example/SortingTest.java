package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

    @Test
    public void testMergeSortCorrectness() {
        Random random = new Random(42);

        for (int i = 0; i < 100; i++) {
            int n = 100 + random.nextInt(900);
            int[] original = new int[n];

            for (int j = 0; j < n; j++) {
                original[j] = random.nextInt(10000) - 5000;
            }

            int[] expected = original.clone();
            Arrays.sort(expected);

            int[] actual = original.clone();

            MergeSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testQuickSortCorrectness() {
        Random random = new Random(42);

        for (int i = 0; i < 100; i++) {
            int n = 100 + random.nextInt(900);
            int[] original = new int[n];

            for (int j = 0; j < n; j++) {
                original[j] = random.nextInt(10000) - 5000;
            }

            int[] expected = original.clone();
            Arrays.sort(expected);

            int[] actual = original.clone();

            QuickSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testEdgeCases() {
        int[] empty = {};
        MergeSort.sort(empty, new Metrics());
        QuickSort.sort(empty, new Metrics());
        assertArrayEquals(new int[]{}, empty);

        int[] singleMerge = {42};
        int[] singleQuick = {42};

        MergeSort.sort(singleMerge, new Metrics());
        QuickSort.sort(singleQuick, new Metrics());

        assertArrayEquals(new int[]{42}, singleMerge);
        assertArrayEquals(new int[]{42}, singleQuick);

        int[] equalMerge = {5, 5, 5, 5, 5, 5};
        int[] equalQuick = equalMerge.clone();

        MergeSort.sort(equalMerge, new Metrics());
        QuickSort.sort(equalQuick, new Metrics());

        assertArrayEquals(new int[]{5, 5, 5, 5, 5, 5}, equalMerge);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5, 5}, equalQuick);

        int[] sortedMerge = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] sortedQuick = sortedMerge.clone();

        MergeSort.sort(sortedMerge, new Metrics());
        QuickSort.sort(sortedQuick, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, sortedMerge);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, sortedQuick);
    }

    @Test
    public void testQuickSortDepth() {
        int n = 100000;
        int[] sorted = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = i;
        }

        Metrics metrics = new Metrics();

        QuickSort.sort(sorted, metrics);

        int maxAllowedDepth = (int) (2 * Math.log(n) / Math.log(2));

        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth);
    }

    @Test
    public void testQuickSelect() {
        Random random = new Random(42);

        for (int i = 0; i < 100; i++) {
            int n = 50;
            int[] original = new int[n];

            for (int j = 0; j < n; j++) {
                original[j] = random.nextInt(1000);
            }

            int[] sorted = original.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(n);

            int result = QuickSelect.select(
                    original,
                    k,
                    new Metrics()
            );

            assertEquals(sorted[k], result);
        }
    }

    @Test
    public void testQuickSelectInvalidInput() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, -1, new Metrics())
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, 3, new Metrics())
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{}, 0, new Metrics())
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(null, 0, new Metrics())
        );
    }
}