package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] types = {"random", "sorted", "duplicates"};
        String[] algorithms = {"MergeSort", "QuickSort", "QuickSelect"};

        Random random = new Random(42);

        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.append("algorithm,input,n,time_ms,comparisons,max_depth\n");

            for (String algorithm : algorithms) {
                for (String type : types) {
                    for (int n : sizes) {

                        double[] times = new double[5];
                        long comparisons = 0;
                        int maxDepth = 0;

                        for (int run = 0; run < 5; run++) {
                            int[] original = generateArray(n, type, random);
                            Metrics metrics = new Metrics();

                            if (algorithm.equals("MergeSort")) {
                                int[] array = original.clone();
                                MergeSort.sort(array, metrics);
                            } else if (algorithm.equals("QuickSort")) {
                                int[] array = original.clone();
                                QuickSort.sort(array, metrics);
                            } else {
                                QuickSelect.select(original, n / 2, metrics);
                            }

                            times[run] = metrics.getElapsedTimeMs();

                            if (run == 4) {
                                comparisons = metrics.getComparisons();
                                maxDepth = metrics.getMaxDepth();
                            }
                        }

                        Arrays.sort(times);
                        double medianTime = times[2];

                        String line = String.format(
                                Locale.US,
                                "%s,%s,%d,%.4f,%d,%d%n",
                                algorithm,
                                type,
                                n,
                                medianTime,
                                comparisons,
                                maxDepth
                        );

                        writer.append(line);
                    }
                }
            }

            System.out.println("Benchmark completed. results.csv generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(int n, String type, Random random) {
        int[] a = new int[n];

        switch (type) {
            case "random":
                for (int i = 0; i < n; i++) {
                    a[i] = random.nextInt();
                }
                break;

            case "sorted":
                for (int i = 0; i < n; i++) {
                    a[i] = i;
                }
                break;

            case "duplicates":
                for (int i = 0; i < n; i++) {
                    a[i] = random.nextInt(10);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown input type: " + type);
        }

        return a;
    }
}