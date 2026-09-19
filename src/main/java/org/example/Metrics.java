package org.example;

public class Metrics {
    private long comparisons;
    private int maxDepth;
    private int currentDepth;
    private long startTime;
    private long elapsedTime;

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        currentDepth = 0;
        elapsedTime = 0;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void addComparisons(long count) {
        comparisons += count;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        elapsedTime = System.nanoTime() - startTime;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getElapsedTimeNano() {
        return elapsedTime;
    }

    public double getElapsedTimeMs() {
        return elapsedTime / 1_000_000.0;
    }
}