# Search a 2D Matrix | Binary Search | Java

## Problem: Search a 2D Matrix

**Pattern:** Binary Search on a Virtual 1D Array  
**Difficulty:** Medium  
**Time Complexity:** `O(log(m * n))`  
**Space Complexity:** `O(1)`

---

# Step 1: Question Explanation

## Problem Statement

You are given an `m x n` integer matrix with the following properties:

1. Integers in each row are sorted in ascending order.
2. The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if the target exists in the matrix. Otherwise, return `false`.

### Example

**Input:**

```text
matrix = [
    [1,  3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 60]
]

target = 34
```

**Output:**

```text
true
```

**Explanation:**

The target `34` exists at index `[2][2]` in the matrix.

### Matrix Representation

```text
         col
          0    1    2    3

row 0    [1]   [3]  [5]  [7]
row 1    [10] [11] [16] [20]
row 2    [23] [30] [34] [60]
```

The matrix contains `3` rows and `4` columns.

Therefore:

```text
n = number of rows = 3
m = number of columns = 4

Total elements = n * m
               = 3 * 4
               = 12
```

Instead of searching row by row, we can use binary search over all 12 elements.

### Brute Force Approach

We can traverse every element and compare it with the target.

```java
for (int i = 0; i < a.length; i++) {
    for (int j = 0; j < a[0].length; j++) {
        if (a[i][j] == target) {
            return true;
        }
    }
}

return false;
```

**Time Complexity:** `O(n * m)`

**Space Complexity:** `O(1)`

### Why Is Brute Force Not Optimal?

The matrix is already sorted.

We should use this information to eliminate unnecessary elements instead of checking every element.

**Optimization:** Apply binary search by treating the matrix as a virtual sorted 1D array.

---

# Step 2: Pattern Recognition

## How Do We Recognize Binary Search?

Whenever a problem has the following characteristics, consider binary search:

- The data is sorted.
- We need to find a particular target.
- We can eliminate half of the remaining search space after each comparison.
- We need better performance than linear search.

This problem satisfies all these conditions.

### Important Observations

**Observation 1: The matrix is globally sorted.**

Although the data is stored in a 2D matrix, the given properties ensure that reading the elements row by row produces a sorted sequence.

```text
1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60
```

**Observation 2: We do not need to create a new array.**

We can simulate a 1D array using index calculations.

**Observation 3: Binary search works on the virtual array.**

Instead of maintaining separate row and column boundaries, we maintain a single search range:

```java
low = 0;
high = n * m - 1;
```

**Observation 4: Each comparison eliminates half of the remaining search space.**

If the middle element is smaller than the target, search the right half.

If the middle element is greater than the target, search the left half.

### Pattern Identification

```text
Sorted 2D Matrix
       |
       v
Can it be represented as a sorted 1D array?
       |
       v
      Yes
       |
       v
Use Binary Search
       |
       v
Convert mid into row and column
       |
       v
Compare matrix[row][col] with target
```

**Final Pattern: Binary Search on a Virtual 1D Array.**

---

# Step 3: Core Concept, Monotonicity, and Index Mapping

## 3.1 Core Concept

The main idea is to apply binary search to the matrix without physically converting it into a 1D array.

Suppose the matrix is:

```text
[
    [1,  3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 60]
]
```

We can logically represent it as:

```text
Index:  0  1  2  3   4   5   6   7   8   9   10  11

Value:  1  3  5  7  10  11  16  20  23  30   34  60
```

We do not actually create this array.

Instead, we calculate the corresponding row and column whenever binary search produces a middle index.

This saves additional space.

## 3.2 Understanding the Monotonic Property

**Monotonicity means the elements follow a consistent order.**

In this problem, the matrix satisfies two conditions:

1. Every row is sorted in ascending order.
2. The first element of each row is greater than the last element of the preceding row.

For example:

```text
Row 0: 1 < 3 < 5 < 7

Row 1: 10 < 11 < 16 < 20

Row 2: 23 < 30 < 34 < 60
```

The row boundaries are also correctly ordered:

```text
7 < 10
20 < 23
```

Therefore, the complete sequence is sorted:

```text
1 < 3 < 5 < 7 < 10 < 11 < 16 < 20 < 23 < 30 < 34 < 60
```

This global ordering is what makes binary search possible.

### How Does Monotonicity Help?

Suppose:

```text
target = 34
mid element = 11
```

Since:

```text
11 < 34
```

Every element at or before the middle index is too small.

We can eliminate that entire portion and continue searching to the right.

Similarly, if:

```text
mid element = 30
target = 34
```

Since:

```text
30 < 34
```

We eliminate the left portion again.

**Important:** The binary-search decision is valid because the matrix is globally sorted when flattened row by row.

If the rows are individually sorted but the row-boundary condition does not hold, this particular approach is not necessarily valid.

## 3.3 Virtual 1D Array Index Mapping

This is the most important part of the solution.

Let:

```java
int n = a.length;
int m = a[0].length;
```

Where:

- `n` = number of rows.
- `m` = number of columns.

For a matrix with `n` rows and `m` columns:

```text
Total elements = n * m
```

The valid virtual 1D indices are:

```text
0 to n * m - 1
```

Therefore:

```java
int low = 0;
int high = n * m - 1;
```

For our example:

```text
n = 3
m = 4

low = 0
high = 3 * 4 - 1 = 11
```

### Formula 1: Convert a Virtual Index to a Row

```java
int row = mid / m;
```

Integer division gives the row number.

### Formula 2: Convert a Virtual Index to a Column

```java
int col = mid % m;
```

The remainder gives the column number.

### Why Do These Formulas Work?

Every row contains exactly `m` elements.

For `m = 4`, the virtual indices are grouped as follows:

```text
Virtual indices:     Row:

0, 1, 2, 3           0
4, 5, 6, 7           1
8, 9, 10, 11         2
```

Dividing the virtual index by `4` identifies its row.

Taking the remainder after division by `4` identifies its column.

### Index Mapping Table

| Virtual Index | Calculation | Row | Column | Matrix Value |
|---:|---|---:|---:|---:|
| 0 | 0 / 4, 0 % 4 | 0 | 0 | 1 |
| 1 | 1 / 4, 1 % 4 | 0 | 1 | 3 |
| 2 | 2 / 4, 2 % 4 | 0 | 2 | 5 |
| 3 | 3 / 4, 3 % 4 | 0 | 3 | 7 |
| 4 | 4 / 4, 4 % 4 | 1 | 0 | 10 |
| 5 | 5 / 4, 5 % 4 | 1 | 1 | 11 |
| 6 | 6 / 4, 6 % 4 | 1 | 2 | 16 |
| 7 | 7 / 4, 7 % 4 | 1 | 3 | 20 |
| 8 | 8 / 4, 8 % 4 | 2 | 0 | 23 |
| 9 | 9 / 4, 9 % 4 | 2 | 1 | 30 |
| 10 | 10 / 4, 10 % 4 | 2 | 2 | 34 |
| 11 | 11 / 4, 11 % 4 | 2 | 3 | 60 |

**Remember:**

```java
row = mid / numberOfColumns;
col = mid % numberOfColumns;
```

The divisor must be the number of columns, not the number of rows.

## 3.4 Binary Search Algorithm

The algorithm works as follows:

**Step 1:** Initialize the search boundaries.

```java
low = 0;
high = n * m - 1;
```

**Step 2:** Calculate the middle index.

```java
mid = low + (high - low) / 2;
```

This form avoids the potential overflow associated with `(low + high) / 2`.

**Step 3:** Convert the middle index into matrix coordinates.

```java
row = mid / m;
col = mid % m;
```

**Step 4:** Compare the middle element with the target.

If:

```java
a[row][col] == target
```

Return `true`.

If:

```java
a[row][col] < target
```

Search the right half:

```java
low = mid + 1;
```

Otherwise, search the left half:

```java
high = mid - 1;
```

**Step 5:** If the search range becomes empty, return `false`.

```java
return false;
```

### Binary Search Flow

```text
Initialize low and high
          |
          v
      low <= high?
       /       \
     Yes        No
      |          |
      v          v
 Calculate mid  Return false
      |
      v
 Convert mid to row and col
      |
      v
 Compare matrix[row][col] with target
      |
      +-------------------+
      |         |         |
      v         v         v
    Equal    Smaller    Greater
      |         |         |
      v         v         v
  Return true  Search    Search
              right      left
              half       half
```

---

# Step 4: Java Code and Code Explanation

## 4.1 Optimized Java Solution

```java
class Solution {
    public boolean searchMatrix(int[][] a, int target) {

        int n = a.length;
        int m = a[0].length;

        int low = 0;
        int high = n * m - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int col = mid % m;
            int row = mid / m;

            if (a[row][col] == target) {
                return true;
            }
            else if (a[row][col] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return false;
    }
}
```

## 4.2 Line-by-Line Explanation

### Step 1: Calculate the Matrix Dimensions

```java
int n = a.length;
int m = a[0].length;
```

`a.length` gives the number of rows.

`a[0].length` gives the number of columns.

For our example:

```text
n = 3
m = 4
```

### Step 2: Initialize the Search Space

```java
int low = 0;
int high = n * m - 1;
```

We treat the matrix as a virtual 1D array.

Since there are `12` elements:

```text
low = 0
high = 11
```

### Step 3: Start Binary Search

```java
while (low <= high)
```

Continue searching while the search interval contains at least one valid index.

When `low > high`, the search interval is empty and the target is absent.

### Step 4: Calculate the Middle Index

```java
int mid = low + (high - low) / 2;
```

The middle index divides the current search interval into two parts.

Using this formula also avoids the potential integer overflow of adding `low` and `high` directly.

### Step 5: Calculate the Column

```java
int col = mid % m;
```

The remainder determines the column index.

For example:

```text
mid = 5
m = 4

col = 5 % 4
col = 1
```

### Step 6: Calculate the Row

```java
int row = mid / m;
```

Integer division determines the row index.

For example:

```text
mid = 5
m = 4

row = 5 / 4
row = 1
```

Therefore:

```text
mid = 5

row = 1
col = 1

a[1][1] = 11
```

### Step 7: Check Whether the Target Is Found

```java
if (a[row][col] == target) {
    return true;
}
```

If the middle element equals the target, the search is complete.

### Step 8: Search the Right Half

```java
else if (a[row][col] < target) {
    low = mid + 1;
}
```

If the middle element is smaller than the target, all elements before or at the middle index can be eliminated.

We continue with the right half.

### Step 9: Search the Left Half

```java
else {
    high = mid - 1;
}
```

If the middle element is greater than the target, all elements after or at the middle index can be eliminated.

We continue with the left half.

### Step 10: Return False

```java
return false;
```

If the loop finishes without finding the target, the target does not exist in the matrix.

## 4.3 Complexity Analysis

Let:

```text
n = number of rows
m = number of columns
N = n * m
```

### Time Complexity

Binary search eliminates approximately half of the remaining elements in each iteration.

Therefore:

```text
N
N / 2
N / 4
N / 8
...
1
```

The number of iterations is logarithmic in the total number of elements.

**Time Complexity:**

```text
O(log(n * m))
```

### Space Complexity

We use only a constant number of variables:

```text
n, m, low, high, mid, row, col
```

No additional array or data structure is created.

**Auxiliary Space Complexity:**

```text
O(1)
```

---

# Step 5: Dry Run and Tabulated Dry Run

## 5.1 Dry Run Example

Consider the following matrix:

```text
a = [
    [1,  3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 60]
]

target = 34
```

Initial values:

```text
n = 3
m = 4

low = 0
high = 3 * 4 - 1 = 11
```

### Iteration 1

Calculate the middle index:

```text
mid = low + (high - low) / 2

mid = 0 + (11 - 0) / 2

mid = 5
```

Convert the virtual index:

```text
row = mid / m
row = 5 / 4
row = 1

col = mid % m
col = 5 % 4
col = 1
```

Access the matrix element:

```text
a[1][1] = 11
```

Compare:

```text
11 < 34
```

The target must be in the right half.

Update:

```text
low = mid + 1
low = 6
```

Current search range:

```text
low = 6
high = 11
```

### Iteration 2

Calculate the middle index:

```text
mid = 6 + (11 - 6) / 2

mid = 6 + 5 / 2

mid = 8
```

Convert the virtual index:

```text
row = 8 / 4 = 2
col = 8 % 4 = 0
```

Access the matrix element:

```text
a[2][0] = 23
```

Compare:

```text
23 < 34
```

The target must be in the right half.

Update:

```text
low = mid + 1
low = 9
```

Current search range:

```text
low = 9
high = 11
```

### Iteration 3

Calculate the middle index:

```text
mid = 9 + (11 - 9) / 2

mid = 9 + 2 / 2

mid = 10
```

Convert the virtual index:

```text
row = 10 / 4 = 2
col = 10 % 4 = 2
```

Access the matrix element:

```text
a[2][2] = 34
```

Compare:

```text
34 == 34
```

The target is found.

```java
return true;
```

**Final Output:**

```text
true
```

## 5.2 Tabulated Dry Run

Input:

```text
target = 34
```

| Iteration | Low | High | Mid | Row = Mid / 4 | Col = Mid % 4 | Element | Comparison | Action |
|---:|---:|---:|---:|---:|---:|---:|---|---|
| 1 | 0 | 11 | 5 | 1 | 1 | 11 | 11 < 34 | `low = 6` |
| 2 | 6 | 11 | 8 | 2 | 0 | 23 | 23 < 34 | `low = 9` |
| 3 | 9 | 11 | 10 | 2 | 2 | 34 | 34 == 34 | Return `true` |

### Search Space Visualization

Initially:

```text
Virtual array:

[1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]

 low = 0                                  high = 11
```

After iteration 1:

```text
Eliminated: [1, 3, 5, 7, 10, 11]

Remaining:  [16, 20, 23, 30, 34, 60]

              low = 6            high = 11
```

After iteration 2:

```text
Eliminated: [16, 20, 23]

Remaining:  [30, 34, 60]

             low = 9   high = 11
```

After iteration 3:

```text
Remaining: [30, 34, 60]
                ^
              target

mid = 10
a[2][2] = 34

Target found!
```

**Key takeaway:** Each iteration eliminates approximately half of the remaining search space, which makes binary search much faster than linear search for large matrices.

---

# Step 6: Interview Point of View

## 6.1 How to Explain This Solution in an Interview

You can explain the approach like this:

"The matrix is sorted both within each row and across row boundaries. Therefore, if we traverse it row by row, all elements form a globally sorted sequence.

Instead of creating a separate 1D array, I simulate one using virtual indices from `0` to `n * m - 1`.

I apply binary search on this range. For every middle index, I calculate the corresponding row using `mid / m` and the column using `mid % m`.

If the middle element equals the target, I return true. If it is smaller, I search the right half. Otherwise, I search the left half.

This approach takes `O(log(n * m))` time and `O(1)` auxiliary space."

## 6.2 Frequently Asked Interview Questions

### Q1. Why can we apply binary search to a 2D matrix?

Because the matrix satisfies both conditions:

- Each row is sorted in ascending order.
- The first element of each row is greater than the last element of the previous row.

Together, these conditions guarantee that the complete matrix is sorted when read row by row.

### Q2. Why is `high = n * m - 1`?

Because a matrix with `n` rows and `m` columns contains `n * m` elements.

The virtual 1D indices start at `0`.

Therefore, the final index is:

```text
n * m - 1
```

For a matrix containing 12 elements:

```text
low = 0
high = 11
```

### Q3. Why do we use `mid / m` to find the row?

Each row contains exactly `m` elements.

Integer division by `m` identifies the row containing the virtual index.

```java
row = mid / m;
```

### Q4. Why do we use `mid % m` to find the column?

The remainder gives the position within the row.

```java
col = mid % m;
```

For example:

```text
mid = 10
m = 4

row = 10 / 4 = 2
col = 10 % 4 = 2
```

Therefore:

```java
a[2][2] == 34
```

### Q5. Why not use `mid / n` and `mid % n`?

Because `n` represents the number of rows, whereas the number of elements in each row is `m`.

The correct formulas are:

```java
row = mid / m;
col = mid % m;
```

Using `n` instead of `m` would produce incorrect coordinates for general rectangular matrices.

### Q6. Why do we use `low <= high`?

The condition ensures that the search continues while at least one candidate index remains.

When:

```text
low > high
```

The search interval is empty, so the target has not been found.

### Q7. Why do we use `low = mid + 1`?

If the middle element is smaller than the target, the middle element and every preceding element can be discarded.

The next possible candidate is `mid + 1`.

### Q8. Why do we use `high = mid - 1`?

If the middle element is greater than the target, the middle element and every following element can be discarded.

The previous possible candidate is `mid - 1`.

### Q9. What happens if the target does not exist?

Binary search continues reducing the search range until:

```text
low > high
```

The loop terminates and the method returns:

```java
return false;
```

### Q10. What is the time complexity?

The matrix contains `n * m` elements.

Binary search takes logarithmic time in the size of the search space.

Therefore:

```text
O(log(n * m))
```

This can also be expressed as:

```text
O(log n + log m)
```

because:

```text
log(n * m) = log n + log m
```

### Q11. What is the space complexity?

The algorithm uses a fixed number of variables and does not create another array.

Therefore, the auxiliary space complexity is:

```text
O(1)
```

### Q12. Is it necessary to convert the matrix into a 1D array?

No.

Creating a new array would require `O(n * m)` additional space.

Virtual index mapping lets us access the same elements directly from the original matrix, keeping auxiliary space at `O(1)`.

### Q13. What if the matrix is empty?

The provided solution assumes the matrix contains at least one row and one column.

If empty matrices are allowed, add a guard before accessing `a[0]`:

```java
if (a == null || a.length == 0 || a[0].length == 0) {
    return false;
}
```

This prevents invalid access for an empty matrix or an empty first row.

### Q14. What if each row is sorted, but the entire matrix is not globally sorted?

The virtual 1D binary-search approach is not guaranteed to work.

For example:

```text
[
    [1,  4,  7],
    [2,  5,  8],
    [3,  6,  9]
]
```

Each row is sorted, but the first element of the second row is not greater than the last element of the first row.

Flattening this matrix row by row does not produce a sorted sequence.

For a matrix with rows and columns sorted independently, a different technique, such as staircase search, may be appropriate.

### Q15. Can we solve this problem using staircase search?

The standard staircase-search approach works when each row and each column is sorted in ascending order.

However, this problem provides a stronger property: the first element of each row is greater than the last element of the previous row.

That stronger property allows us to treat the entire matrix as one sorted sequence and use binary search.

## 6.3 Common Mistakes to Avoid

**Mistake 1: Using the number of rows instead of columns in index mapping.**

Incorrect:

```java
row = mid / n;
col = mid % n;
```

Correct:

```java
row = mid / m;
col = mid % m;
```

**Mistake 2: Setting the upper boundary incorrectly.**

Incorrect:

```java
high = n * m;
```

Correct:

```java
high = n * m - 1;
```

The last valid index is one less than the total number of elements.

**Mistake 3: Updating the boundaries incorrectly.**

Incorrect:

```java
low = mid;
high = mid;
```

These updates may cause an infinite loop.

Correct:

```java
low = mid + 1;
high = mid - 1;
```

**Mistake 4: Assuming every row-sorted matrix can be flattened into a sorted array.**

The row-boundary condition is essential.

Without it, the virtual 1D array might not be globally sorted.

**Mistake 5: Forgetting empty-matrix handling when required.**

The original code assumes a non-empty matrix. If empty input is permitted, validate the input before accessing `a[0]`.

---

# Final Summary

| Concept | Explanation |
|---|---|
| Problem | Search for a target in a sorted 2D matrix |
| Pattern | Binary Search |
| Approach | Treat the matrix as a virtual 1D array |
| Search boundaries | `low = 0`, `high = n * m - 1` |
| Middle index | `mid = low + (high - low) / 2` |
| Row mapping | `row = mid / m` |
| Column mapping | `col = mid % m` |
| If element < target | `low = mid + 1` |
| If element > target | `high = mid - 1` |
| If element == target | Return `true` |
| If target is absent | Return `false` |
| Time complexity | `O(log(n * m))` |
| Auxiliary space | `O(1)` |

## The Most Important Things to Remember

1. **Recognize the global sorted order** from the matrix properties.
2. **Apply binary search** to the virtual index range `0` through `n * m - 1`.
3. **Convert indices correctly:** `row = mid / m` and `col = mid % m`.
4. **Eliminate half the search space** after every comparison.
5. **Avoid creating a separate array** to maintain `O(1)` auxiliary space.

**One-line interview takeaway:**

> Use binary search on a virtual 1D representation of the matrix, mapping each middle index back to its 2D coordinates using division and modulo.