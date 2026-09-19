package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] types = {"random", "sorted", "duplicates"};
        String[] algorithms = {"MergeSort", "QuickSort"};

        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.append("algorithm,input,n,time_ms,comparisons,max_depth\n");

            for (String algo : algorithms) {
                for (String type : types) {
                    for (int n : sizes) {
                        if (algo.equals("QuickSort") && n == 1000000 && type.equals("sorted")) {
                            continue;
                        }

                        double[] times = new double[5];
                        long comparisons = 0;
                        int maxDepth = 0;

                        for (int run = 0; run < 5; run++) {
                            int[] original = generateArray(n, type);
                            int[] array = original.clone();
                            Metrics metrics = new Metrics();

                            if (algo.equals("MergeSort")) {
                                MergeSort.sort(array, metrics);
                            } else if (algo.equals("QuickSort")) {
                                QuickSort.sort(array, metrics);
                            }

                            times[run] = metrics.getElapsedTimeMs();
                            if (run == 4) {
                                comparisons = metrics.getComparisons();
                                maxDepth = metrics.getMaxDepth();
                            }
                        }

                        Arrays.sort(times);
                        double medianTime = times[2];

                        writer.append(algo)
                                .append(",").append(type)
                                .append(",").append(String.valueOf(n))
                                .append(",").append(String.format("%.4f", medianTime))
                                .append(",").append(String.valueOf(comparisons))
                                .append(",").append(String.valueOf(maxDepth))
                                .append("\n");
                    }
                }
            }
            System.out.println("Benchmark completed. results.csv generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(int n, String type) {
        Random random = new Random(42);
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
        }
        return a;
    }
}