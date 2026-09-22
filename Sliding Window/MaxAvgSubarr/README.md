# LeetCode 643 — Maximum Average Subarray I

## Problem

Given an integer array `nums` and an integer `k`, find the **maximum average value** of any contiguous subarray of length `k`.

### Example

```text
Input:
nums = [1,12,-5,-6,50,3]
k = 4

Output:
12.75
```

The subarray with maximum average is:

```text
[12, -5, -6, 50]
```

Sum:

```text
12 + (-5) + (-6) + 50 = 51
```

Average:

```text
51 / 4 = 12.75
```

---

## Approach — Fixed Size Sliding Window

We need to check every subarray of size `k`.

Instead of calculating the sum again and again, we use a **Sliding Window**.

### Main Idea

First calculate the sum of the first `k` elements.

Then move the window one position at a time.

When the window moves:

```text
Remove the old element
+
Add the new element
```

So:

```java
currentSum += nums[i] - nums[i - k];
```

This makes the solution much faster.

---

## Java Code

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int currentSum = 0;

        // Calculate sum of first k elements
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        int maxSum = currentSum;

        // Slide the window
        for (int i = k; i < nums.length; i++) {

            currentSum += nums[i] - nums[i - k];

            maxSum = Math.max(maxSum, currentSum);
        }

        // Convert to double before division
        return (double) maxSum / k;
    }
}
```

---

## Dry Run

### Input

```text
nums = [1, 12, -5, -6, 50, 3]
k = 4
```

### Step 1 — First Window

Take first 4 elements:

```text
[1, 12, -5, -6]
```

Sum:

```text
1 + 12 - 5 - 6 = 2
```

So:

```text
currentSum = 2
maxSum = 2
```

---

### Step 2 — Move Window

Now `i = 4`.

Current window:

```text
[1, 12, -5, -6]
```

We remove:

```text
1
```

and add:

```text
50
```

So:

```text
currentSum = 2 + 50 - 1
           = 51
```

Window becomes:

```text
[12, -5, -6, 50]
```

Update:

```text
maxSum = 51
```

---

### Step 3 — Move Again

Now `i = 5`.

Remove:

```text
12
```

Add:

```text
3
```

Therefore:

```text
currentSum = 51 + 3 - 12
           = 42
```

Window:

```text
[-5, -6, 50, 3]
```

`maxSum` is still:

```text
51
```

---

## Final Answer

```text
maxSum = 51
k = 4
```

Average:

```text
51 / 4 = 12.75
```

Therefore:

```text
Output = 12.75
```

---

## Why `nums[i] - nums[i-k]`?

This is the most important line:

```java
currentSum += nums[i] - nums[i - k];
```

Suppose:

```text
[1, 12, -5, -6]
```

moves to:

```text
[12, -5, -6, 50]
```

We don't need to calculate the entire sum again.

Just:

```text
Old Sum = 2

Remove 1
Add 50

New Sum = 2 - 1 + 50
        = 51
```

That's why Sliding Window makes the solution efficient.

---

## Why `(double) maxSum / k`?

If we write:

```java
return maxSum / k;
```

both are integers, so Java performs **integer division**.

For example:

```text
51 / 4 = 12
```

But we need:

```text
12.75
```

Therefore:

```java
return (double) maxSum / k;
```

Now Java performs decimal division.

---

## Time Complexity

```text
O(n)
```

We go through the array only once after calculating the first window.

## Space Complexity

```text
O(1)
```

We only use a few variables.

---

## Pattern

**Sliding Window → Fixed Size Window**

### Remember this formula:

```java
currentSum += nums[i] - nums[i - k];
```

**Add the new element and remove the element that left the window.**

This pattern is useful for many **fixed-size subarray** problems.
