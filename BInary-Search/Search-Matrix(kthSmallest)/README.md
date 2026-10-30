# Kth Smallest Element in a Sorted Matrix

## Step 1: Question Explanation

### Problem Statement

You are given an `n x m` matrix where:

- Each row is sorted in ascending order.
- Each column is sorted in ascending order.

Given an integer `k`, return the **kth smallest element** in the matrix.

The kth smallest element is the element that appears at position `k` when all matrix elements are arranged in ascending order.

**Important:** We are looking for the kth smallest value, not the element at a particular matrix index.

### Example 1

**Input:**

```text
a = [
  [ 1,  5,  9],
  [10, 11, 13],
  [12, 13, 15]
]

k = 8
```

**Output:**

```text
13
```

**Explanation:**

If we arrange the elements in ascending order:

```text
1, 5, 9, 10, 11, 12, 13, 13, 15
```

The 8th smallest element is `13`.

Notice that `13` appears twice. Both occurrences count toward the sorted order.

### Example 2

**Input:**

```text
a = [
  [1, 2],
  [3, 4]
]

k = 3
```

**Output:**

```text
3
```

**Explanation:**

The sorted elements are:

```text
1, 2, 3, 4
```

The 3rd smallest element is `3`.

### What Makes This Problem Interesting?

A straightforward approach would be to store all matrix elements in an array, sort them, and return the element at index `k - 1`.

However, that approach does not take full advantage of the sorted rows and columns.

Instead, we can use:

1. Binary search on the answer.
2. Monotonicity of the counting function.
3. Staircase traversal to count elements less than or equal to a guessed value.

This allows us to solve the problem without sorting the entire matrix.

---

## Step 2: Pattern Recognition

### Pattern: Binary Search on Answer + Monotonic Matrix Search

This problem combines two important patterns:

**Pattern 1: Binary Search on Answer**

Instead of searching for a matrix index, we search for the smallest value that has at least `k` elements less than or equal to it.

**Pattern 2: Monotonic Matrix Traversal**

For every guessed value, we efficiently count how many matrix elements are less than or equal to that value.

### How to Recognize Binary Search on Answer

Look for these clues:

- The answer lies within a known numerical range.
- We can guess a possible answer.
- We can determine whether the guess is too small or large enough.
- The decision changes monotonically as the guess increases.

In this problem:

```text
low  = smallest matrix element
high = largest matrix element
```

Every possible answer must lie between these two values.

For a guessed value `guess`, we calculate:

```java
int ans = fun(a, n, m, guess);
```

Here, `ans` represents the number of matrix elements less than or equal to `guess`.

Then we compare `ans` with `k`.

| Condition | Meaning | Action |
|---|---|---|
| `ans < k` | Fewer than `k` elements are at most `guess` | Search larger values |
| `ans >= k` | At least `k` elements are at most `guess` | Record the guess and search smaller values |

This is the central binary-search decision.

### Why Not Sort the Entire Matrix?

Sorting all `n * m` elements would take:

```text
O(n * m * log(n * m))
```

Your solution uses the matrix's sorted properties to count elements efficiently and then applies binary search over the value range.

---

## Step 3: Explain the Concept and Core Logic with Monotonicity

There are two important ideas in this solution.

1. Counting elements less than or equal to a guessed value.
2. Using that count to perform binary search on the answer.

Let's understand both.

### Concept 1: Count Elements Less Than or Equal to a Guess

Your helper method is:

```java
public int fun(int[][] a, int n, int m, int guess) {
    int row = n - 1;
    int col = 0;
    int count = 0;

    while (row >= 0 && col < m) {
        if (a[row][col] <= guess) {
            count += row + 1;
            col++;
        } else {
            row--;
        }
    }

    return count;
}
```

The helper starts from the **bottom-left corner**.

Why?

Because:

- Moving right increases or preserves the value.
- Moving up decreases or preserves the value.

These directions allow us to count multiple elements at once.

### Case 1: Current Element Is Less Than or Equal to Guess

Suppose the matrix is:

```text
 1   5   9
10  11  13
12  13  15
```

Assume:

```text
guess = 12
```

We start at the bottom-left element:

```text
12
```

Since:

```text
12 <= 12
```

The element qualifies.

More importantly, every element above `12` in the same column is also less than or equal to `12`.

That column contains:

```text
1
10
12
```

Therefore, we can count all three elements at once:

```java
count += row + 1;
```

Since `row = 2`:

```text
count += 2 + 1
count += 3
```

Then move right:

```java
col++;
```

**Key idea:** When `a[row][col] <= guess`, all elements above it in the current column also qualify.

### Case 2: Current Element Is Greater Than Guess

Suppose the current element is:

```text
15
```

and:

```text
guess = 12
```

Since:

```text
15 > 12
```

The element does not qualify.

Because the column is sorted, every element below `15` in that column is also greater than `12`.

Therefore, none of the elements below it can help us.

We move up:

```java
row--;
```

This eliminates the current row position from further consideration.

### Why Is the Count `row + 1`?

Java matrix indices start at zero.

If:

```text
row = 0
```

there is one element above or at the current position.

If:

```text
row = 1
```

there are two elements above or at the current position.

If:

```text
row = 2
```

there are three elements above or at the current position.

Therefore:

```java
count += row + 1;
```

counts all qualifying elements in that column segment.

### Concept 2: Binary Search on the Answer

Your main method uses:

```java
int low = a[0][0];
int high = a[n - 1][m - 1];
```

Because the rows and columns are sorted:

- `a[0][0]` is the smallest element.
- `a[n - 1][m - 1]` is the largest element.

Therefore, the kth smallest value must lie in this range.

We calculate the midpoint:

```java
int guess = low + (high - low) / 2;
```

Then count how many elements are less than or equal to it:

```java
int ans = fun(a, n, m, guess);
```

Now comes the most important part.

### Monotonicity of the Counting Function

Let:

```text
count(x) = number of matrix elements <= x
```

As `x` increases, `count(x)` can never decrease.

For example:

| Guess | Number of elements `<= guess` |
|---:|---:|
| 4 | 2 |
| 8 | 3 |
| 10 | 4 |
| 12 | 6 |
| 13 | 8 |
| 15 | 9 |

The count increases or stays the same as the guessed value increases.

This property is called **monotonicity**.

We need the smallest value `x` for which:

```text
count(x) >= k
```

### Case 1: `ans < k`

```java
if (ans < target) {
    low = guess + 1;
}
```

There are fewer than `k` elements less than or equal to the guessed value.

Therefore, the kth smallest element must be larger than the guess.

We eliminate the current guess and every smaller value:

```text
low = guess + 1
```

### Case 2: `ans >= k`

```java
else {
    res = guess;
    high = guess - 1;
}
```

At least `k` elements are less than or equal to the guess.

Therefore, the kth smallest value cannot be greater than the guess.

We record the guess as a candidate:

```java
res = guess;
```

Then search the smaller half:

```java
high = guess - 1;
```

Why continue searching?

Because the guess might be larger than the actual kth smallest element.

We want the **smallest value that satisfies `count(x) >= k`**.

### Why Does This Work with Duplicate Values?

Suppose:

```text
Sorted elements = [1, 2, 3, 3, 3, 7]
k = 4
```

The 4th smallest element is `3`.

At `guess = 3`:

```text
count(3) = 5
```

Since:

```text
5 >= 4
```

the guess is a valid candidate.

The algorithm searches for a smaller valid value. Since `2` has only two elements less than or equal to it, the search eventually returns `3`.

The counting method naturally handles duplicates because every occurrence is counted separately.

### Core Intuition

**The helper counts how many elements are at most a guessed value. Binary search then finds the smallest guessed value whose count reaches at least `k`.**

---

## Step 4: Code and Code Explanation

### Java Solution

```java
class Solution {
    public int fun(int[][] a, int n, int m, int guess) {
        int row = n - 1;
        int col = 0;
        int count = 0;

        while (row >= 0 && col < m) {
            if (a[row][col] <= guess) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }

        return count;
    }

    public int kthSmallest(int[][] a, int target) {
        int n = a.length;
        int m = a[0].length;

        int low = a[0][0];
        int high = a[n - 1][m - 1];
        int res = -1;

        while (low <= high) {
            int guess = low + (high - low) / 2;
            int ans = fun(a, n, m, guess);

            if (ans < target) {
                low = guess + 1;
            } else {
                res = guess;
                high = guess - 1;
            }
        }

        return res;
    }
}
```

### Part A: Helper Function

```java
public int fun(int[][] a, int n, int m, int guess)
```

This method counts the number of matrix elements less than or equal to `guess`.

Its return value is used to determine whether the current guess is too small or large enough.

#### Initialize the Position and Count

```java
int row = n - 1;
int col = 0;
int count = 0;
```

We begin at the bottom-left corner.

The initial count is zero because no elements have been counted yet.

#### Continue While the Position Is Valid

```java
while (row >= 0 && col < m)
```

The traversal continues while:

- `row >= 0`: We have not moved above the first row.
- `col < m`: We have not moved beyond the last column.

#### Count Qualifying Elements

```java
if (a[row][col] <= guess) {
    count += row + 1;
    col++;
}
```

When the current value is at most `guess`, all elements above it in the same column also qualify.

We count them together and move right.

#### Move Up When the Current Value Is Too Large

```java
else {
    row--;
}
```

When the current value exceeds `guess`, all elements below it in the same column are also too large.

We move up to check smaller values.

#### Return the Count

```java
return count;
```

The helper returns the total number of elements less than or equal to the guess.

**Helper complexity:**

```text
Time:  O(n + m)
Space: O(1)
```

At every iteration, either the row decreases or the column increases.

### Part B: Main Function

```java
public int kthSmallest(int[][] a, int target)
```

The variable `target` represents `k`, the desired position in sorted order.

#### Get Matrix Dimensions

```java
int n = a.length;
int m = a[0].length;
```

Here:

- `n` is the number of rows.
- `m` is the number of columns.

#### Establish the Search Range

```java
int low = a[0][0];
int high = a[n - 1][m - 1];
int res = -1;
```

The smallest element is the lower bound, and the largest element is the upper bound.

`res` stores the best candidate found so far.

The `-1` initialization assumes the problem's constraints make `-1` a suitable sentinel if no candidate is found. For a valid `k` and a non-empty matrix satisfying the stated conditions, a candidate will be found.

#### Calculate the Midpoint

```java
int guess = low + (high - low) / 2;
```

This calculates the midpoint without using the potentially overflowing expression `(low + high) / 2`.

#### Count Elements at Most the Guess

```java
int ans = fun(a, n, m, guess);
```

The helper returns how many elements are less than or equal to `guess`.

#### Search the Right Half

```java
if (ans < target) {
    low = guess + 1;
}
```

If fewer than `k` elements are at most the guess, the kth smallest value must be greater.

#### Record the Candidate and Search the Left Half

```java
else {
    res = guess;
    high = guess - 1;
}
```

If at least `k` elements are at most the guess, it is a valid upper-bound candidate.

We save it and search for a smaller valid value.

#### Return the Result

```java
return res;
```

After binary search finishes, `res` contains the smallest integer value satisfying:

```text
count(res) >= k
```

Since the matrix elements are integers and the search range contains the kth smallest element, this value is the kth smallest matrix element.

### Complexity Analysis

Let:

- `n` = number of rows.
- `m` = number of columns.
- `V = maxValue - minValue + 1` = size of the integer value range.

The helper takes:

```text
O(n + m)
```

The binary search takes:

```text
O(log V)
```

iterations.

Therefore:

**Time Complexity:**

```text
O((n + m) * log V)
```

where `V` is the size of the integer value range.

**Auxiliary Space Complexity:**

```text
O(1)
```

The algorithm uses only a constant number of additional variables.

---

## Step 5: Dry Run and Dry Run Table

Consider the following matrix:

```text
a = [
  [ 1,  5,  9],
  [10, 11, 13],
  [12, 13, 15]
]

k = 8
```

The sorted order is:

```text
1, 5, 9, 10, 11, 12, 13, 13, 15
```

Therefore, the expected answer is:

```text
13
```

### Initial State

```text
low = 1
high = 15
res = -1
k = 8
```

### Binary Search Dry Run

| Step | Low | High | Guess | Count of Elements `<= Guess` | Condition | Action | Result |
|---|---:|---:|---:|---:|---|---|---:|
| 1 | 1 | 15 | 8 | 2 | `2 < 8` | Move right | -1 |
| 2 | 9 | 15 | 12 | 6 | `6 < 8` | Move right | -1 |
| 3 | 13 | 15 | 14 | 8 | `8 >= 8` | Save candidate, move left | 14 |
| 4 | 13 | 13 | 13 | 8 | `8 >= 8` | Save candidate, move left | 13 |

The search ends when:

```text
low = 13
high = 12
```

Since:

```text
low > high
```

the binary-search loop terminates.

The method returns:

```text
13
```

### Detailed Dry Run of the Helper Function

Let's understand how:

```java
fun(a, n, m, 12)
```

returns `6`.

Matrix:

```text
 1   5   9
10  11  13
12  13  15
```

Initial state:

```text
row = 2
col = 0
count = 0
guess = 12
```

| Step | Row | Col | Current Value | Comparison | Count | Action |
|---|---:|---:|---:|---|---:|---|
| 1 | 2 | 0 | 12 | `12 <= 12` | 3 | Count 3 elements, move right |
| 2 | 2 | 1 | 13 | `13 > 12` | 3 | Move up |
| 3 | 1 | 1 | 11 | `11 <= 12` | 5 | Count 2 more elements, move right |
| 4 | 1 | 2 | 13 | `13 > 12` | 5 | Move up |
| 5 | 0 | 2 | 9 | `9 <= 12` | 6 | Count 1 more element, move right |

Now:

```text
col = 3
m = 3
```

The condition `col < m` becomes false.

The helper returns:

```text
6
```

### Why Does the Helper Count Three Elements at Once?

At the first position:

```text
a[2][0] = 12
```

Because the first column is sorted:

```text
1
10
12
```

All three values are less than or equal to `12`.

Therefore:

```java
count += row + 1;
```

becomes:

```java
count += 2 + 1;
```

The helper counts three elements with a single comparison.

This is the key optimization that avoids scanning every matrix element.

### Final Dry Run Summary

| Variable | Final Value |
|---|---:|
| `k` | 8 |
| `res` | 13 |
| `low` | 13 |
| `high` | 12 |
| Returned Answer | 13 |

**Final output:**

```text
13
```

---

## Step 6: Interview Perspective

### 1. How Would You Explain the Approach in an Interview?

"I will use binary search on the answer instead of sorting the entire matrix.

The smallest possible answer is the top-left element, and the largest possible answer is the bottom-right element.

For each guessed value, I count the number of matrix elements less than or equal to it using a staircase traversal from the bottom-left corner.

If the count is smaller than `k`, the guess is too small, so I search the larger half.

Otherwise, the guess is a valid candidate. I save it and search the smaller half to find the smallest value whose count reaches at least `k`.

This works because the counting function is monotonic.

The time complexity is `O((n + m) * log V)`, where `V` is the size of the integer value range, and the auxiliary space complexity is `O(1)`."

### 2. Why Do We Use Binary Search on Values Instead of Indices?

We are looking for the kth smallest value, not a particular row or column.

The matrix's sorted properties allow us to count how many elements are at most a guessed value.

That counting function gives us a monotonic condition for binary search.

### 3. Why Does `count(x) >= k` Mean the Answer Is at Most `x`?

If at least `k` elements are less than or equal to `x`, the kth element in sorted order cannot be greater than `x`.

Therefore, `x` is a valid upper-bound candidate.

We continue searching smaller values to find the first one that satisfies this condition.

### 4. Why Do We Search Left When `ans >= k`?

Because we want the **smallest value for which the count is at least `k`**.

The current guess might be larger than necessary.

Therefore, we save it:

```java
res = guess;
```

Then reduce the upper bound:

```java
high = guess - 1;
```

### 5. Why Do We Search Right When `ans < k`?

If fewer than `k` elements are at most the guess, the kth smallest element must be larger.

Therefore:

```java
low = guess + 1;
```

### 6. Why Is the Counting Function Monotonic?

If:

```text
x <= y
```

then every element that is less than or equal to `x` is also less than or equal to `y`.

Therefore:

```text
count(x) <= count(y)
```

As the guess increases, the count cannot decrease.

This monotonicity makes binary search possible.

### 7. Why Start From the Bottom-Left Corner?

At the bottom-left corner:

- Moving right increases the values.
- Moving up decreases the values.

If the current value is less than or equal to the guess, all elements above it in that column qualify.

If the current value exceeds the guess, all elements below it in that column are too large.

This lets us count elements efficiently.

### 8. What Is the Time Complexity?

Let:

```text
V = maxValue - minValue + 1
```

Each call to the counting function takes `O(n + m)` time.

Binary search requires `O(log V)` iterations.

Therefore:

```text
O((n + m) * log V)
```

This complexity depends on the numerical range of matrix values, not directly on the number of elements alone.

### 9. What Is the Space Complexity?

```text
O(1)
```

The solution does not allocate an additional array, sort the matrix, or use recursion.

Only a constant number of variables are required.

### 10. What Happens If the Matrix Contains Duplicates?

Duplicates are counted as separate occurrences.

For example:

```text
1, 2, 3, 3, 3, 7
```

The 4th smallest element is `3`.

The counting function counts every occurrence of `3` because it checks:

```java
a[row][col] <= guess
```

It does not count only distinct values.

### 11. Why Is the Result Variable Necessary?

```java
int res = -1;
```

Whenever a guess satisfies:

```text
count(guess) >= k
```

we save that guess as a candidate.

The search then continues toward smaller values.

Without `res`, we would not retain the best valid candidate while narrowing the search range.

For valid inputs, the final candidate is the kth smallest element.

### 12. Can We Use a Min-Heap Instead?

Yes.

A min-heap can be used to repeatedly extract the smallest remaining element until the kth element is reached.

For example, inserting the first element of each row and adding the next element from a row after removing its current minimum gives an efficient heap-based solution.

A common heap-based approach takes approximately:

```text
Time:  O(k log(min(k, n)))
Space: O(min(k, n))
```

The exact complexity depends on the heap initialization and implementation.

The binary-search solution is particularly attractive when the matrix's value range is manageable and constant auxiliary space is desirable.

### 13. What Is the Difference Between This Problem and Searching for a Target?

**Searching for a target:**

We are given a specific value and must determine whether it exists.

**Finding the kth smallest element:**

We do not know the answer beforehand. We search for a value whose count of elements less than or equal to it reaches at least `k`.

The first problem searches for a known value, while the second searches for the value at a given sorted position.

### 14. What Are the Main Interview Takeaways?

- Recognize binary search on answer.
- Identify the monotonic counting function.
- Use staircase traversal to count qualifying elements.
- Count an entire column segment using `row + 1`.
- Search right when the count is too small.
- Record the candidate and search left when the count is sufficient.
- Handle duplicates correctly.
- Explain why the smallest valid value is the kth smallest element.
- State the complexity in terms of the value range.

---

## Final Summary

| Property | Details |
|---|---|
| Problem | Find the kth smallest matrix element |
| Main Pattern | Binary Search on Answer |
| Supporting Pattern | Monotonic Matrix Traversal |
| Search Range | Smallest to largest matrix value |
| Counting Method | Count elements `<= guess` |
| Traversal Start | Bottom-left corner |
| If `count < k` | Search right |
| If `count >= k` | Save candidate and search left |
| Time Complexity | `O((n + m) * log V)` |
| Auxiliary Space | `O(1)` |

### One-Line Intuition

**Binary-search the value range, count how many elements are less than or equal to each guess using staircase traversal, and find the smallest value whose count is at least `k`.**