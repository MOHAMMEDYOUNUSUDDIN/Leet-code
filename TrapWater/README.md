# Trapping Rain Water — LeetCode 42

## Problem

Given an array `height[]` where each element represents the height of a vertical bar, calculate how much rainwater can be trapped between the bars.

### Example

```text
Input:
height = [0,1,0,2,1,0,1,3,2,1,2,1]

Output:
6
```

---

## Approach: Two Pointers

This solution uses the **Two Pointer technique**.

We use two pointers:

```java
left = 0
right = height.length - 1
```

We also maintain:

```java
leftMax  // maximum height found from the left
rightMax // maximum height found from the right
```

The variable:

```java
water
```

stores the total amount of trapped rainwater.

---

## Why Two Pointers?

The amount of water that can be stored at a position is:

```text
min(leftMax, rightMax) - height[i]
```

Normally, we could calculate `leftMax` and `rightMax` arrays, but that would require `O(n)` extra space.

The two-pointer approach calculates the required maximum values while moving toward the center.

Therefore:

```text
Time Complexity  = O(n)
Space Complexity = O(1)
```

This is the optimal approach.

---

## Java Code

```java
class Solution {

    public int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {

                water += leftMax - height[left];
                left++;

            } else {

                water += rightMax - height[right];
                right--;
            }
        }

        return water;
    }
}
```

---

# How the Algorithm Works

Initially:

```text
left = 0
right = n - 1

leftMax = 0
rightMax = 0
water = 0
```

At every iteration:

### Step 1 — Update maximum heights

```java
leftMax = Math.max(leftMax, height[left]);
rightMax = Math.max(rightMax, height[right]);
```

This keeps track of the highest bar seen from each side.

### Step 2 — Compare the maximum heights

```java
if (leftMax < rightMax)
```

If `leftMax` is smaller, we process the left side.

Why?

Because the smaller boundary determines how much water can be trapped.

Otherwise, we process the right side.

### Step 3 — Calculate water

For the left side:

```java
water += leftMax - height[left];
```

For the right side:

```java
water += rightMax - height[right];
```

---

# Dry Run

Consider:

```text
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

We start with:

```text
left = 0
right = 11
leftMax = 0
rightMax = 0
water = 0
```

## Iteration 1

```text
height[left]  = 0
height[right] = 1

leftMax = max(0, 0) = 0
rightMax = max(0, 1) = 1
```

Comparison:

```text
0 < 1 → true
```

Process left:

```text
water += 0 - 0
water = 0
```

Move:

```text
left = 1
```

---

## Iteration 2

```text
left = 1
right = 11

height[left] = 1
height[right] = 1

leftMax = max(0,1) = 1
rightMax = max(1,1) = 1
```

Comparison:

```text
1 < 1 → false
```

Process right:

```text
water += 1 - 1
water = 0
```

Move:

```text
right = 10
```

---

## Iteration 3

```text
left = 1
right = 10

height[left] = 1
height[right] = 2

leftMax = 1
rightMax = 2
```

Since:

```text
1 < 2
```

process left:

```text
water += 1 - 1
water = 0
```

Move:

```text
left = 2
```

---

## Iteration 4

```text
left = 2
right = 10

height[left] = 0
height[right] = 2

leftMax = 1
rightMax = 2
```

Since:

```text
1 < 2
```

process left:

```text
water += 1 - 0
water += 1
```

So:

```text
water = 1
```

Move:

```text
left = 3
```

This `0` height bar traps **1 unit** of water.

---

## Remaining Important Steps

The same process continues:

```text
Step     leftMax     rightMax     water
------------------------------------------------
1           0           1           0
2           1           1           0
3           1           2           0
4           1           2           1
5           2           2           1
6           2           2           2
7           2           2           2
8           2           3           2
9           2           3           3
10          2           3           5
11          2           3           6
```

Finally:

```text
water = 6
```

So the answer is:

```text
Output: 6
```

---

# Visual Understanding

The input:

```text
[0,1,0,2,1,0,1,3,2,1,2,1]
```

contains several valleys.

The water can be visualized approximately as:

```text
        |
    |   |       |
    |~~~|~~~~~~~|
|~~~|~~~|~~~|~~~|
-----------------
```

The bars on both sides act as boundaries, and the lower boundary determines how much water can stay between them.

---

# Why Is This Optimal?

### Brute Force

For every position, find the tallest bar on the left and right.

```text
Time  = O(n²)
Space = O(1)
```

Too slow for large inputs.

### Prefix/Suffix Arrays

Precalculate:

```text
leftMax[]
rightMax[]
```

Complexity:

```text
Time  = O(n)
Space = O(n)
```

Better time, but uses extra memory.

### Two Pointers — This Solution

Uses only:

```text
left
right
leftMax
rightMax
water
```

Complexity:

```text
Time  = O(n)
Space = O(1)
```

Therefore, the **two-pointer solution is optimal** for this problem.

---

# Key Pattern to Remember

When you see a problem involving:

* Water between bars
* Left and right boundaries
* Maximum values from both sides
* Need for `O(1)` extra space

Think about the **Two Pointer technique**.

The most important condition is:

```java
if (leftMax < rightMax)
```

### If:

```text
leftMax < rightMax
```

Process the **left side**.

### Otherwise:

```text
leftMax >= rightMax
```

Process the **right side**.

That is the main trick behind the optimal solution.
