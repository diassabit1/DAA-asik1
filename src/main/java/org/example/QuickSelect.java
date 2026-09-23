package org.example;

import java.util.Random;

public class QuickSelect {
    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid array or k is out of bounds");
        }

        int[] copy = a.clone();

        metrics.startTimer();
        int result = quickSelect(copy, 0, copy.length - 1, k, metrics);
        metrics.stopTimer();

        return result;
    }

    private static int quickSelect(int[] a, int low, int high, int k, Metrics metrics) {
        while (low <= high) {
            int randomIndex = low + RANDOM.nextInt(high - low + 1);
            swap(a, low, randomIndex);

            int[] pivotRange = Partition.partition3Way(a, low, high, metrics);
            int lt = pivotRange[0];
            int gt = pivotRange[1];

            if (k < lt) {
                high = lt - 1;
            } else if (k > gt) {
                low = gt + 1;
            } else {
                return a[k];
            }
        }

        return a[low];
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}