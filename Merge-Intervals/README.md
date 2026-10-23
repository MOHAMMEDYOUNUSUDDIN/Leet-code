# Merge Intervals — Complete DSA Guide (Java)

## 1. What Is the Merge Intervals Pattern?

The Merge Intervals pattern is used when we are given ranges with start and end points and need to combine overlapping ranges.

An interval is represented as:

```text
[start, end]
```

For example:

```text
[1, 5]
```

This interval represents a range starting at `1` and ending at `5`.

### Real-World Example

Imagine three meetings scheduled during these times:

* Meeting A: 9:00 AM – 10:30 AM
* Meeting B: 10:00 AM – 11:00 AM
* Meeting C: 12:00 PM – 1:00 PM

The first two meetings overlap, so their combined time range is 9:00 AM – 11:00 AM.

The final ranges are:

```text
[9:00, 11:00]
[12:00, 13:00]
```

This is the basic idea behind merging intervals.

---

## 2. When Should You Use This Pattern?

Look for these clues in a problem statement:

* Merge overlapping intervals.
* Combine overlapping ranges.
* Find non-overlapping intervals.
* Combine meeting schedules.
* Insert a new interval into existing intervals.
* Find the intersection of two interval lists.
* Remove the minimum number of intervals to make them non-overlapping.

**Main identification question:**

Does the problem involve ranges represented by start and end points, and do I need to combine, compare, insert, or remove overlapping ranges?

If yes, consider the Merge Intervals pattern.

---

## 3. The Core Idea

The standard Merge Intervals algorithm follows three steps:

1. Sort intervals by their starting points.
2. Compare each interval with the last interval in the result.
3. Merge overlapping intervals or add a new interval when there is no overlap.

### Why Do We Sort First?

Consider:

```text
[[8,10], [1,3], [2,6], [15,18]]
```

The intervals are not arranged by their starting points.

After sorting:

```text
[[1,3], [2,6], [8,10], [15,18]]
```

Now, overlapping intervals are easier to process because we move from smaller starting points to larger starting points.

Sorting makes it possible to process the intervals in a single forward traversal.

---

## 4. How to Identify Overlapping Intervals

Suppose we have two intervals:

```text
Previous = [1,5]
Current  = [3,7]
```

The current interval starts before the previous interval ends.

```text
Previous: [1---------5]
Current:       [3---------7]
```

They overlap.

The merged interval becomes:

```text
[1,7]
```

### The Overlap Condition

After sorting by start time:

```java
currentStart <= previousEnd
```

If this condition is true, the intervals overlap.

### The Merge Formula

```java
mergedStart = previousStart;
mergedEnd = Math.max(previousEnd, currentEnd);
```

The start remains the start of the previous interval, and the end becomes the maximum of both end points.

### What If There Is No Overlap?

Previous interval:

```text
[1,3]
```

Current interval:

```text
[5,7]
```

Since:

```text
5 > 3
```

The intervals do not overlap.

Keep both intervals separately.

**Important:** In the standard closed-interval version of this problem, intervals sharing an endpoint are considered overlapping. For example, `[1,4]` and `[4,5]` merge into `[1,5]`.

---

## 5. Java Solution — Merge Intervals

### Problem

Given an array of intervals, merge all overlapping intervals and return the resulting non-overlapping intervals.

Example:

```text
Input:
[[1,3], [2,6], [8,10], [15,18]]

Output:
[[1,6], [8,10], [15,18]]
```

### Code

```java
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {

        // Step 1: Sort intervals by starting point
        Arrays.sort(intervals, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        // Step 2: Store merged intervals
        List<int[]> result = new ArrayList<>();

        // Step 3: Process each interval
        for (int[] current : intervals) {

            // If result is empty, or no overlap exists
            if (result.isEmpty() ||
                result.get(result.size() - 1)[1] < current[0]) {

                result.add(current);

            } else {

                // Merge overlapping intervals
                int[] previous =
                    result.get(result.size() - 1);

                previous[1] = Math.max(
                    previous[1],
                    current[1]
                );
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
```

---

## 6. Understand the Code Step by Step

### Step 1: Sort the intervals

```java
Arrays.sort(intervals, (a, b) ->
    Integer.compare(a[0], b[0])
);
```

Each interval is an array containing two values:

```text
interval[0] = start
interval[1] = end
```

We sort using `interval[0]`, so intervals with smaller starting points come first.

### Step 2: Create the result list

```java
List<int[]> result = new ArrayList<>();
```

This list stores the merged intervals.

Initially:

```text
result = []
```

### Step 3: Process each interval

```java
for (int[] current : intervals)
```

We visit the sorted intervals one by one.

### Step 4: Check whether merging is possible

```java
if (result.isEmpty() ||
    result.get(result.size() - 1)[1] < current[0])
```

There are two possibilities:

**Case A: No previous interval exists.**

Add the current interval.

**Case B: The current interval starts after the previous interval ends.**

The intervals do not overlap, so add the current interval separately.

Otherwise, merge the intervals.

### Step 5: Update the previous interval

```java
previous[1] = Math.max(
    previous[1],
    current[1]
);
```

We update the end of the previous interval to the greater of the two end points.

This is important because the current interval may be completely contained inside the previous interval.

For example:

```text
Previous = [1,10]
Current  = [3,5]
```

The result remains:

```text
[1,10]
```

Using `Math.max()` prevents the merged interval from becoming shorter.

---

## 7. Dry Run — Understand Every Step

Input:

```text
[[1,3], [2,6], [8,10], [15,18]]
```

The input is already sorted.

### Initially

```text
result = []
```

### Iteration 1

Current interval:

```text
[1,3]
```

The result is empty, so add the interval.

```text
result = [[1,3]]
```

### Iteration 2

Current interval:

```text
[2,6]
```

Previous interval:

```text
[1,3]
```

Check:

```text
currentStart <= previousEnd
2 <= 3
```

The intervals overlap.

Update the previous interval's end:

```text
max(3,6) = 6
```

Result:

```text
[[1,6]]
```

### Iteration 3

Current interval:

```text
[8,10]
```

Previous merged interval:

```text
[1,6]
```

Check:

```text
8 <= 6
```

False.

The intervals do not overlap, so add the current interval.

```text
result = [[1,6], [8,10]]
```

### Iteration 4

Current interval:

```text
[15,18]
```

Previous interval:

```text
[8,10]
```

Check:

```text
15 <= 10
```

False.

Add the current interval.

```text
result = [[1,6], [8,10], [15,18]]
```

### Final Answer

```text
[[1,6], [8,10], [15,18]]
```

---

## 8. The Most Important Thinking Process

When solving an interval problem, ask yourself these questions.

### Question 1: Are the intervals sorted?

If not, can sorting by the starting point simplify the problem?

### Question 2: What should I compare?

Usually, compare the current interval's starting point with the last merged interval's ending point.

### Question 3: What happens when they overlap?

Extend the ending point using:

```java
Math.max(previousEnd, currentEnd)
```

### Question 4: What happens when they do not overlap?

Add the current interval as a new interval.

### Question 5: Do I need a different interval technique?

Not every interval problem requires merging. Some require insertion, intersection, sorting by end points, or a sweep-line algorithm.

---

## 9. Common Variations of Interval Problems

### A. Merge Intervals

**Goal:** Combine all overlapping intervals.

Main technique:

```text
Sort by start → Compare → Merge
```

Problem: [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

### B. Insert Interval

**Goal:** Insert a new interval into an existing sorted, non-overlapping list and merge where necessary.

Main technique:

1. Add intervals that finish before the new interval starts.
2. Merge all intervals that overlap the new interval.
3. Add the merged interval.
4. Add the remaining intervals.

Problem: [57. Insert Interval](https://leetcode.com/problems/insert-interval/)

### C. Non-overlapping Intervals

**Goal:** Remove the minimum number of intervals so that the remaining intervals do not overlap.

A common optimal approach is to sort by ending point and greedily keep intervals that finish as early as possible.

Problem: [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

### D. Interval List Intersections

**Goal:** Find the intersections between two sorted interval lists.

Main technique:

* Use two pointers.
* Calculate the intersection using the maximum starting point and minimum ending point.
* Advance the interval that ends first.

Problem: [986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)

### E. Meeting Rooms

**Goal:** Determine whether meeting schedules conflict.

Depending on the exact question, sort intervals and compare adjacent meetings, or use a sweep-line technique.

Problem: [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)

---

## 10. Merge Intervals vs Other Patterns

| Pattern         | Main purpose                                        |
| --------------- | --------------------------------------------------- |
| Merge Intervals | Combine overlapping ranges                          |
| Two Pointers    | Process elements using two moving positions         |
| Sliding Window  | Maintain a contiguous section of an array or string |
| Binary Search   | Search efficiently in sorted data                   |
| Prefix Sum      | Answer range-sum questions efficiently              |
| Sweep Line      | Process events ordered along a timeline             |

Merge Intervals often uses sorting and a forward scan. It can also be understood as a greedy algorithm because each interval is merged into the current result whenever possible.

---

## 11. Time and Space Complexity

Let `n` be the number of intervals.

### Time Complexity

Sorting takes:

```text
O(n log n)
```

Processing the intervals takes:

```text
O(n)
```

Therefore, total time complexity is:

```text
O(n log n)
```

### Space Complexity

The result list can store up to `n` intervals.

Therefore, auxiliary result space is:

```text
O(n)
```

The sorting implementation may also use additional space.

---

## 12. Common Mistakes

1. Forgetting to sort the intervals first.
2. Comparing the current start with the wrong interval's end.
3. Updating the merged end without using `Math.max()`.
4. Treating intervals that share an endpoint as non-overlapping when the problem defines them as closed intervals.
5. Adding overlapping intervals separately instead of merging them.
6. Assuming every interval problem requires the same sorting order.
7. Forgetting to add intervals that do not overlap.
8. Using a merge solution for a problem that actually requires finding intersections or removing intervals.

---

## 13. Practice Roadmap

Solve these in order:

1. [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
2. [57. Insert Interval](https://leetcode.com/problems/insert-interval/)
3. [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)
4. [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)
5. [986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)
6. [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)
7. [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

Some problems are premium or may have limited availability on LeetCode.

---

## 14. Final Revision Notes

Remember these four rules:

1. **Sort first** by the starting point for the standard Merge Intervals problem.
2. **Check overlap** using `currentStart <= previousEnd`.
3. **Merge** by updating the end to `Math.max(previousEnd, currentEnd)`.
4. **Otherwise, add** the current interval separately.

### Final Mental Model

Imagine walking along a timeline from left to right.

* If the next interval begins before the current merged interval ends, extend the current interval.
* If the next interval begins after the current merged interval ends, finish the current interval and start a new one.

That is the core logic of the Merge Intervals pattern.
