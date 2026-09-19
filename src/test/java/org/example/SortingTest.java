package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

    @Test
    public void testMergeSortCorrectness() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int n = 100 + random.nextInt(900);
            int[] original = new int[n];
            for (int j = 0; j < n; j++) {
                original[j] = random.nextInt(10000) - 5000;
            }

            int[] expected = original.clone();
            Arrays.sort(expected);

            int[] actual = original.clone();
            Metrics metrics = new Metrics();
            MergeSort.sort(actual, metrics);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testQuickSortCorrectness() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int n = 100 + random.nextInt(900);
            int[] original = new int[n];
            for (int j = 0; j < n; j++) {
                original[j] = random.nextInt(10000) - 5000;
            }

            int[] expected = original.clone();
            Arrays.sort(expected);

            int[] actual = original.clone();
            Metrics metrics = new Metrics();
            QuickSort.sort(actual, metrics);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testEdgeCases() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        MergeSort.sort(empty, metrics);
        QuickSort.sort(empty, metrics);
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        MergeSort.sort(single, metrics);
        QuickSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] duplicates = {5, 2, 5, 2, 5, 2, 1, 1};
        int[] expected = duplicates.clone();
        Arrays.sort(expected);

        int[] actualMerge = duplicates.clone();
        MergeSort.sort(actualMerge, metrics);
        assertArrayEquals(expected, actualMerge);

        int[] actualQuick = duplicates.clone();
        QuickSort.sort(actualQuick, metrics);
        assertArrayEquals(expected, actualQuick);
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
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int n = 50;
            int[] original = new int[n];
            for (int j = 0; j < n; j++) {
                original[j] = random.nextInt(1000);
            }

            int[] sorted = original.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(n);
            Metrics metrics = new Metrics();
            int result = QuickSelect.select(original, k, metrics);

            assertEquals(sorted[k], result);
        }
    }
}