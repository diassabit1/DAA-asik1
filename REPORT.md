# Assignment 1: Report - Divide and Conquer & Asymptotic Analysis

## 1. Introduction

This report presents the implementation and empirical evaluation of three algorithms: **Merge Sort**, **Quick Sort**, and **Quick Select**, developed in Java 17 as part of the Design and Analysis of Algorithms course.

The algorithms were evaluated using execution time, number of comparisons, and maximum recursion depth. The benchmark was performed for random, sorted, and duplicate-heavy input arrays with sizes of 1,000, 10,000, 100,000, and 1,000,000 elements.

## 2. Algorithmic Optimizations

### Merge Sort

Merge Sort uses one reusable auxiliary buffer allocated in the top-level call. The same buffer is passed through all recursive calls, so additional arrays are not created during merging. For subarrays containing 15 or fewer elements, Insertion Sort is used as a cutoff optimization.

The merge operation processes the two sorted halves in linear time.

### Quick Sort

Quick Sort chooses a pivot randomly and uses a 3-way partition that separates elements into less-than, equal-to, and greater-than groups.

The algorithm recursively processes the smaller partition first and processes the larger partition iteratively using a while loop. This keeps the recursion depth bounded by $O(\log n)$.

Randomized pivot selection improves the expected running time, but the theoretical worst case remains $O(n^2)$.

The 3-way partition is particularly effective for arrays containing many duplicate values because elements equal to the pivot are processed together.

### Quick Select

Quick Select uses the same 3-way partition method as Quick Sort. After partitioning, only the partition containing the required position $k$ is processed.

The algorithm is implemented iteratively and therefore does not create recursive calls during selection. Its expected running time is $O(n)$, while the worst case remains $O(n^2)$.

## 3. Asymptotic Bounds

| Algorithm | Best Case | Average Case | Worst Case | Reason / Notes |
| :--- | :--- | :--- | :--- | :--- |
| **Insertion Sort** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | An already sorted array requires linear work, while reverse-ordered input requires quadratic shifts. |
| **Merge Sort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | The array is divided into balanced halves and merging takes linear time. |
| **Quick Sort** | $\Theta(n)$ | $\Theta(n \log n)$ expected | $\Theta(n^2)$ | An all-equal input can be processed in linear time using 3-way partitioning; balanced partitions give $n \log n$, while repeatedly unbalanced partitions give quadratic time. |
| **Quick Select** | $\Theta(n)$ | $\Theta(n)$ expected | $\Theta(n^2)$ | A single partition can finish the operation, while balanced partitions give expected linear time and repeatedly unbalanced partitions give quadratic time. |

## 4. Recurrence Relations

### Merge Sort

The recurrence is:

$$
T(n) = 2T(n/2) + \Theta(n)
$$

Therefore:

- $a = 2$
- $b = 2$
- $f(n) = \Theta(n)$
- $n^{\log_b a} = n$

This is **Case 2** of the Master Theorem because:

$$
f(n) = \Theta(n^{\log_b a})
$$

Therefore:

$$
T(n) = \Theta(n \log n)
$$

The result is the same for the best, average, and worst cases.

### Quick Sort

For a balanced partition, the recurrence is:

$$
T(n) = 2T(n/2) + \Theta(n)
$$

Therefore:

- $a = 2$
- $b = 2$
- $f(n) = \Theta(n)$
- $n^{\log_b a} = n$

This is **Case 2** of the Master Theorem.

Therefore:

$$
T(n) = \Theta(n \log n)
$$

With a random pivot, balanced partitions are expected over a sequence of random pivot choices, giving an expected running time of $\Theta(n \log n)$.

However, randomized pivot selection does not eliminate the theoretical worst case. If the partitions repeatedly become highly unbalanced, the running time can reach:

$$
T(n) = \Theta(n^2)
$$

### Quick Select

For a balanced split, only one subproblem is processed:

$$
T(n) = T(n/2) + \Theta(n)
$$

Therefore:

- $a = 1$
- $b = 2$
- $f(n) = \Theta(n)$
- $n^{\log_b a} = 1$

This is **Case 3** of the Master Theorem because $f(n)$ grows polynomially faster than $n^{\log_b a}$.

Therefore:

$$
T(n) = \Theta(n)
$$

This recurrence describes the balanced case and explains the expected linear behavior of Quick Select.

If the pivot repeatedly produces highly unbalanced partitions, the worst-case running time becomes:

$$
T(n) = \Theta(n^2)
$$

## 5. Performance Plots

### Time vs n

![Time vs n](time_vs_n.png)

The plot compares the measured execution time of Merge Sort, Quick Sort, and Quick Select for random, sorted, and duplicate-heavy inputs.

### Max Recursion Depth vs n

![Depth vs n](depth_vs_n.png)

The plot shows the maximum recursion depth measured during the benchmark. Quick Sort is expected to maintain logarithmic recursion depth because it recursively processes the smaller partition and handles the larger partition iteratively. Merge Sort has logarithmic recursion depth due to its balanced recursive division. Quick Select is implemented iteratively.

### Ratio vs n

![Ratio vs n](ratio_vs_n.png)

For Merge Sort and Quick Sort, the ratio is calculated as:

$$
\frac{\text{comparisons}}{n\log_2 n}
$$

For Quick Select, the ratio is calculated as:

$$
\frac{\text{comparisons}}{n}
$$

These ratios are used to compare the measured number of comparisons with the expected asymptotic growth.

## 6. $\Theta$ Check

For Merge Sort and Quick Sort, the measured comparison ratio is:

$$
\frac{\text{comparisons}}{n\log_2 n}
$$

For Quick Select, the measured ratio is:

$$
\frac{\text{comparisons}}{n}
$$

The threshold used for the empirical comparison is:

$$
n_0 = 10000
$$

For each algorithm and input type, $c_1$ is estimated as the minimum measured ratio for $n \geq n_0$, while $c_2$ is estimated as the maximum measured ratio for $n \geq n_0$.

The $\Theta$ definition is:

$$
c_1 g(n) \leq f(n) \leq c_2 g(n)
$$

where:

- $g(n) = n\log_2 n$ for Merge Sort and Quick Sort;
- $g(n) = n$ for Quick Select.

The exact values of $c_1$ and $c_2$ are obtained from the final benchmark results.

For the final benchmark, the measured values should be reported in the following form:

| Algorithm | Input | $n_0$ | $c_1$ | $c_2$ |
| :--- | :--- | :---: | :---: | :---: |
| MergeSort | random | 10000 | [measured] | [measured] |
| MergeSort | sorted | 10000 | [measured] | [measured] |
| MergeSort | duplicates | 10000 | [measured] | [measured] |
| QuickSort | random | 10000 | [measured] | [measured] |
| QuickSort | sorted | 10000 | [measured] | [measured] |
| QuickSort | duplicates | 10000 | [measured] | [measured] |
| QuickSelect | random | 10000 | [measured] | [measured] |
| QuickSelect | sorted | 10000 | [measured] | [measured] |
| QuickSelect | duplicates | 10000 | [measured] | [measured] |

The ratio plots provide an empirical check of whether the measured comparison count follows the expected asymptotic growth.

## 7. Discussion & Empirical Analysis

The benchmark results can be compared with the theoretical complexity of the implemented algorithms. Merge Sort is expected to show $\Theta(n \log n)$ behavior because the array is divided into two balanced subproblems and each merge operation is linear.

Quick Sort is expected to approach $\Theta(n \log n)$ behavior on typical inputs when random pivot selection produces reasonably balanced partitions. Its 3-way partition is especially useful for duplicate-heavy inputs because all elements equal to the pivot are processed together. The theoretical worst case remains $\Theta(n^2)$ if partitions repeatedly become highly unbalanced.

Quick Select is expected to show linear growth in the number of comparisons because after each partition only one side is processed. Its worst-case complexity remains $\Theta(n^2)$ when the pivot repeatedly produces highly unbalanced partitions.

The recursion depth of Quick Sort remains bounded because only the smaller partition is processed recursively while the larger partition is handled iteratively. Merge Sort has logarithmic recursion depth because the input is divided into two approximately equal halves.

Differences between measured execution times can be caused by JVM warm-up, Just-In-Time compilation, garbage collection, CPU cache effects, and the Insertion Sort cutoff used by Merge Sort.

The measured results should therefore be interpreted together with the theoretical analysis rather than as exact mathematical constants.
