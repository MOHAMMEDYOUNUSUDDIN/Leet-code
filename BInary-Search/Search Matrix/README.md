# Search a 2D Matrix — Monotonic Matrix Search

## Step 1: Question Explanation

### Problem Statement

You are given an `n x m` matrix of integers where:

- Each row is sorted in ascending order from left to right.
- Each column is sorted in ascending order from top to bottom.

Given an integer `target`, return `true` if the target exists in the matrix; otherwise, return `false`.

### Example 1

**Input:**

```text
matrix = [
  [1,  4,  7, 11, 15],
  [2,  5,  8, 12, 19],
  [3,  6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

target = 5
```

**Output:**

```text
true
```

**Explanation:**

The target `5` exists in the matrix at index `[1][1]`.

### Example 2

**Input:**

```text
matrix = [
  [1,  4,  7, 11, 15],
  [2,  5,  8, 12, 19],
  [3,  6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

target = 20
```

**Output:**

```text
false
```

**Explanation:**

The target `20` does not exist in the matrix.

### Constraints and Observations

- The matrix may contain multiple rows and columns.
- Every row is sorted in ascending order.
- Every column is sorted in ascending order.
- The matrix is not necessarily sorted as one continuous sequence.
- We need to return a boolean value.
- The goal is to search efficiently without checking every element.

---

## Step 2: Pattern Recognition

### Pattern: Monotonic Matrix Search

This problem uses the **Monotonic Matrix Search** pattern.

Because each row and column is sorted, we can eliminate an entire row or column at every step by choosing the correct starting position.

### How to Recognize This Pattern

Look for these clues:

1. The input is a two-dimensional matrix.
2. Every row is sorted in ascending order.
3. Every column is sorted in ascending order.
4. The problem asks whether a target exists in the matrix.
5. A linear scan would work, but a more efficient solution is expected.

When these conditions appear together, consider searching from a matrix corner where one direction contains smaller values and the other contains larger values.

### Why Not Use Binary Search Directly?

Binary search works naturally on a sorted one-dimensional array.

However, this matrix is not necessarily sorted when flattened row by row.

For example:

```text
1   4   7  11
2   5   8  12
3   6   9  16
```

Although each row and column is sorted, the last element of one row can be greater than the first element of the next row.

Therefore, treating the entire matrix as one sorted array would be incorrect.

**Key takeaway:** Use the row and column ordering together instead of treating the matrix as a single sorted array.

---

## Step 3: Core Concept — Monotonicity

### What Is Monotonicity?

Monotonicity means that values change consistently in one direction.

In this problem:

- Moving right increases or preserves the value.
- Moving left decreases or preserves the value.
- Moving down increases or preserves the value.
- Moving up decreases or preserves the value.

This ordering allows us to eliminate unnecessary search regions.

### Why Start at the Top-Right Corner?

Consider this matrix:

```text
1   4   7  11  15
2   5   8  12  19
3   6   9  16  22
10 13  14  17  24
18 21  23  26  30
```

Start at the top-right element:

```text
15
```

At this position:

- Moving left leads to smaller values.
- Moving down leads to larger values.

This gives us two useful decisions.

### Case 1: Current Value Equals Target

```text
current == target
```

The target has been found.

Return:

```java
return true;
```

### Case 2: Current Value Is Greater Than Target

```text
current > target
```

Because the current value is already too large, every value below it in the same column must also be greater than the target.

Therefore, we can eliminate the entire column by moving left.

```java
col--;
```

### Case 3: Current Value Is Smaller Than Target

```text
current < target
```

Because the current value is too small, every value to its left in the same row must also be smaller than the target.

Therefore, we can eliminate the entire row by moving down.

```java
row++;
```

### Why Does This Work?

At every iteration, we eliminate either:

- One complete column, or
- One complete row.

We never need to revisit an eliminated region because the matrix's sorted properties prove that the target cannot be there.

Eventually:

1. We find the target and return `true`, or
2. We move outside the matrix boundaries and return `false`.

### Important Insight

The algorithm does not randomly move through the matrix.

Every movement is based on a comparison between the current value and the target.

**Monotonicity + strategic starting position = efficient matrix search.**

---

## Step 4: Code and Code Explanation

### Java Solution

```java
class Solution {
    public boolean searchMatrix(int[][] a, int target) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return false;
        }

        int n = a.length;
        int m = a[0].length;

        // Start from the top-right corner
        int row = 0;
        int col = m - 1;

        while (row < n && col >= 0) {
            if (a[row][col] == target) {
                return true; // Found the target
            } else if (a[row][col] > target) {
                col--; // Target is smaller, move left
            } else {
                row++; // Target is larger, move down
            }
        }

        return false; // Target not found
    }
}
```

### Code Explanation

#### 1. Handle Empty Input

```java
if (a == null || a.length == 0 || a[0].length == 0) {
    return false;
}
```

This checks whether:

- The matrix reference is `null`.
- The matrix has no rows.
- The first row has no columns.

If any condition is true, the target cannot be found, so we return `false`.

This guard assumes the matrix is rectangular and its rows are non-null, as is typical for this problem.

#### 2. Get Matrix Dimensions

```java
int n = a.length;
int m = a[0].length;
```

- `n` represents the number of rows.
- `m` represents the number of columns.

For an `n x m` matrix:

- Valid row indices range from `0` to `n - 1`.
- Valid column indices range from `0` to `m - 1`.

#### 3. Initialize the Starting Position

```java
int row = 0;
int col = m - 1;
```

We start at the top-right corner.

This position is useful because:

- Moving left gives smaller values.
- Moving down gives larger values.

These properties allow us to eliminate one dimension of the remaining search space at each step.

#### 4. Continue While the Position Is Valid

```java
while (row < n && col >= 0)
```

The loop continues while:

- `row < n`: We have not moved below the last row.
- `col >= 0`: We have not moved left of the first column.

If either boundary is crossed, the target is not present in the matrix.

#### 5. Check Whether the Target Is Found

```java
if (a[row][col] == target) {
    return true;
}
```

If the current element equals the target, return `true` immediately.

There is no need to continue searching.

#### 6. Move Left When the Current Value Is Too Large

```java
else if (a[row][col] > target) {
    col--;
}
```

If the current value exceeds the target, everything below it in that column is also too large.

We eliminate the current column and move left.

#### 7. Move Down When the Current Value Is Too Small

```java
else {
    row++;
}
```

If the current value is smaller than the target, everything to its left in that row is also too small.

We eliminate the current row and move down.

#### 8. Return False When the Search Ends

```java
return false;
```

If the loop finishes without finding the target, we have exhausted every position that could contain it.

Therefore, return `false`.

### Complexity Analysis

Let:

- `n` = number of rows.
- `m` = number of columns.

**Time Complexity: `O(n + m)`**

In every iteration, either `row` increases or `col` decreases.

The row index can increase at most `n` times, and the column index can decrease at most `m` times.

Therefore, the maximum number of iterations is proportional to `n + m`.

**Space Complexity: `O(1)`**

The algorithm uses only a few integer variables.

No additional data structures are required.

---

## Step 5: Dry Run and Dry Run Table

### Input

```text
a = [
  [1,  4,  7, 11, 15],
  [2,  5,  8, 12, 19],
  [3,  6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

target = 5
```

### Initial State

```text
row = 0
col = 4
target = 5
```

We start at `a[0][4]`, whose value is `15`.

### Step-by-Step Dry Run

| Step | Row | Col | Current Value | Comparison | Action |
|---|---:|---:|---:|---|---|
| 1 | 0 | 4 | 15 | 15 > 5 | Move left |
| 2 | 0 | 3 | 11 | 11 > 5 | Move left |
| 3 | 0 | 2 | 7 | 7 > 5 | Move left |
| 4 | 0 | 1 | 4 | 4 < 5 | Move down |
| 5 | 1 | 1 | 5 | 5 == 5 | Return `true` |

### Visual Representation

Initial position:

```text
 1   4   7  11 [15]
 2   5   8  12  19
 3   6   9  16  22
10  13  14  17  24
18  21  23  26  30
```

After moving left three times:

```text
 1 [4]  7  11  15
 2   5   8  12  19
 3   6   9  16  22
10  13  14  17  24
18  21  23  26  30
```

The current value is `4`, which is smaller than `5`.

Therefore, move down:

```text
 1   4   7  11  15
 2  [5]  8  12  19
 3   6   9  16  22
10  13  14  17  24
18  21  23  26  30
```

The current value equals the target.

```text
a[1][1] == 5
```

Therefore:

```java
return true;
```

### Dry Run for an Absent Target

Consider:

```text
target = 20
```

| Step | Row | Col | Current Value | Comparison | Action |
|---|---:|---:|---:|---|---|
| 1 | 0 | 4 | 15 | 15 < 20 | Move down |
| 2 | 1 | 4 | 19 | 19 < 20 | Move down |
| 3 | 2 | 4 | 22 | 22 > 20 | Move left |
| 4 | 2 | 3 | 16 | 16 < 20 | Move down |
| 5 | 3 | 3 | 17 | 17 < 20 | Move down |
| 6 | 4 | 3 | 26 | 26 > 20 | Move left |
| 7 | 4 | 2 | 23 | 23 > 20 | Move left |
| 8 | 4 | 1 | 21 | 21 > 20 | Move left |
| 9 | 4 | 0 | 18 | 18 < 20 | Move down |

Now:

```text
row = 5
n = 5
```

The loop condition `row < n` becomes false.

The search ends, and the method returns:

```java
return false;
```

**Conclusion:** The algorithm correctly identifies both present and absent targets without scanning every matrix element.

---

## Step 6: Interview Perspective

### 1. How Would You Explain the Approach in an Interview?

"I will use a monotonic matrix search starting from the top-right corner.

Because every row and column is sorted in ascending order, the current element helps me eliminate either a row or a column.

If the current value equals the target, I return `true`.

If the current value is greater than the target, I move left because all values below it in the current column are too large.

If the current value is smaller than the target, I move down because all values to its left in the current row are too small.

I repeat this process until I find the target or move outside the matrix boundaries.

This gives me `O(n + m)` time complexity and `O(1)` auxiliary space."

### 2. Why Do We Start From the Top-Right Corner?

The top-right corner provides two useful directions:

- Left decreases the values.
- Down increases the values.

This lets us eliminate an entire row or column after each comparison.

Starting from an arbitrary position would not provide the same straightforward elimination strategy.

### 3. Can We Start From the Bottom-Left Corner?

Yes.

The bottom-left corner also works because:

- Moving right increases the values.
- Moving up decreases the values.

The same elimination logic applies, with the movement directions reversed.

### 4. Why Not Start From the Top-Left Corner?

At the top-left corner:

- Moving right increases the values.
- Moving down also increases the values.

If the current value is greater than the target, both directions lead to larger values, but the target could still be in either the remaining row or the remaining column.

There is no equally simple decision that eliminates one dimension in every case.

Therefore, the top-left corner is not the natural starting point for this algorithm.

### 5. What Is the Time Complexity?

```text
O(n + m)
```

The row index moves only downward, at most `n` times.

The column index moves only leftward, at most `m` times.

The total number of iterations is therefore bounded by `n + m`.

### 6. What Is the Space Complexity?

```text
O(1)
```

Only a constant number of variables are used.

The algorithm does not create another matrix, use recursion, or allocate an additional data structure proportional to the input size.

### 7. What Happens If the Target Does Not Exist?

The algorithm keeps eliminating rows or columns until either:

- `row == n`, meaning we have moved below the matrix, or
- `col < 0`, meaning we have moved left of the matrix.

At that point, the loop terminates, and the method returns `false`.

### 8. What If the Matrix Contains Duplicate Values?

The algorithm still works.

The problem does not require finding a unique occurrence or returning an index. It only asks whether the target exists.

If the target appears multiple times, finding any occurrence is sufficient to return `true`.

### 9. What If the Matrix Contains Only One Row or One Column?

The algorithm still works.

**Single row:** The row index remains `0`, and the column index moves left as necessary.

**Single column:** The column index remains `0` unless the current value is too large, in which case it becomes `-1` and the search ends.

Both cases follow the same logic without requiring separate algorithms.

### 10. Can We Solve This Using Binary Search?

Yes, but the exact approach depends on the matrix's ordering.

If the matrix is sorted as a single sequence in row-major order, binary search can achieve:

```text
Time: O(log(n * m))
Space: O(1)
```

However, in this problem, only the individual rows and columns are guaranteed to be sorted.

The matrix is not necessarily sorted as one continuous sequence.

Therefore, the top-right monotonic search is a natural solution with:

```text
Time: O(n + m)
Space: O(1)
```

### 11. What Is the Main Difference Between This Problem and a Fully Sorted Matrix Problem?

**Fully sorted matrix:**

Each element is greater than or equal to the preceding element in row-major order, so the matrix can be treated as a sorted one-dimensional array.

**Row-and-column sorted matrix:**

Each row and column is sorted individually, but row-major order is not guaranteed to be sorted.

Recognizing this difference helps determine which search algorithm is appropriate.

### 12. What Are the Most Important Interview Takeaways?

- Recognize the monotonic matrix search pattern.
- Use the sorted properties of both rows and columns.
- Start from the top-right or bottom-left corner.
- Eliminate one row or column at every step.
- Maintain valid matrix boundaries.
- Avoid unnecessary nested loops.
- Explain why each movement is safe.
- Analyze the solution as `O(n + m)` time and `O(1)` auxiliary space.

---

## Final Summary

| Property | Details |
|---|---|
| Algorithm | Monotonic Matrix Search |
| Starting Position | Top-right corner |
| Comparison | Current value versus target |
| If equal | Return `true` |
| If current value is greater | Move left |
| If current value is smaller | Move down |
| Time Complexity | `O(n + m)` |
| Auxiliary Space | `O(1)` |
| Main Advantage | Eliminates an entire row or column per step |

### One-Line Intuition

**Start at the top-right corner, compare the current element with the target, and eliminate one row or column at each step until the target is found or the search space becomes empty.**