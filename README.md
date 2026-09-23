# DAA Assignment 1 - Divide & Conquer & Asymptotic Notations

## Author
* Name: Dias Sabit
* Group: SE-2526
* University: Astana IT University

## Project Structure
* `src/main/java/org/example/MergeSort.java` - Implementation of Merge Sort with reusable buffer and Insertion Sort cutoff ($\le 15$).
* `src/main/java/org/example/QuickSort.java` - Implementation of Quick Sort with random pivot, 3-way partition, and smaller-side first recursion.
* `src/main/java/org/example/QuickSelect.java` - Implementation of Quick Select for finding the $k$-th smallest element.
* `src/main/java/org/example/Metrics.java` - Metrics tracker for comparisons, recursion depth, and execution time.
* `src/main/java/org/example/BenchmarkRunner.java` - Benchmark runner that generates `results.csv`.
* `src/test/java/org/example/SortingTest.java` - JUnit 5 test suite covering correctness, edge cases, and depth bounds.

## How to Build and Run

### Prerequisites
* Java JDK 17
* Maven

### Running Tests (JUnit 5)
```bash
mvn test

### Running Benchmark

```bash
mvn clean compile
java -cp target/classes org.example.BenchmarkRunner
