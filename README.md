# DAA Assignment 1 - Divide & Conquer & Asymptotic Notations

## Author

- Name: Dias Sabit
- Group: SE-2526
- University: Astana IT University

## Project Structure

- `src/main/java/org/example/MergeSort.java` - Implementation of Merge Sort with a reusable buffer and Insertion Sort cutoff for subarrays of size 15 or less.
- `src/main/java/org/example/QuickSort.java` - Implementation of Quick Sort with random pivot selection, 3-way partitioning, and smaller-side-first recursion.
- `src/main/java/org/example/QuickSelect.java` - Implementation of Quick Select for finding the k-th smallest element.
- `src/main/java/org/example/Partition.java` - Shared 3-way partitioning method used by Quick Sort and Quick Select.
- `src/main/java/org/example/Metrics.java` - Metrics tracker for comparisons, recursion depth, and execution time.
- `src/main/java/org/example/BenchmarkRunner.java` - Benchmark runner that measures all algorithms and generates `results.csv`.
- `src/test/java/org/example/SortingTest.java` - JUnit 5 test suite covering correctness, edge cases, Quick Sort recursion depth, and Quick Select validation.
- `results.csv` - Benchmark results.
- `time_vs_n.png` - Execution time plot.
- `depth_vs_n.png` - Maximum recursion depth plot.
- `ratio_vs_n.png` - Asymptotic ratio plot.
- `REPORT.md` - Asymptotic analysis and discussion of the experimental results.

## Requirements

- Java JDK 17
- Maven

## Building the Project

To clean and compile the project:

```bash
mvn clean compile
