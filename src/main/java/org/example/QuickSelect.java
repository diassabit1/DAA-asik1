package org.example;

import java.util.Random;

public class QuickSelect {
    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid array or k is out of bounds");
        }
        int[] copy = a.clone();
        return quickSelect(copy, 0, copy.length - 1, k, metrics);
    }

    private static int quickSelect(int[] a, int low, int high, int k, Metrics metrics) {
        while (low <= high) {
            metrics.enterRecursion();
            int randomIndex = low + RANDOM.nextInt(high - low + 1);
            swap(a, low, randomIndex);

            int[] pivotRange = partition3Way(a, low, high, metrics);
            int lt = pivotRange[0];
            int gt = pivotRange[1];

            metrics.exitRecursion();

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

    private static int[] partition3Way(int[] a, int low, int high, Metrics metrics) {
        int pivot = a[low];
        int lt = low;
        int gt = high;
        int i = low + 1;

        while (i <= gt) {
            metrics.incrementComparisons();
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else {
                metrics.incrementComparisons();
                if (a[i] > pivot) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }
        }
        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}