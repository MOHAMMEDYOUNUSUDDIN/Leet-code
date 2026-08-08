# LeetCode 209 — Minimum Size Subarray Sum

## Problem

Given an array of **positive integers** `nums` and a positive integer `target`, find the **minimum length of a contiguous subarray** whose sum is **greater than or equal to `target`**.

If no such subarray exists, return `0`.

### Example

```text
Input:
target = 7
nums = [2, 3, 1, 2, 4, 3]

Output:
2
```

The subarray `[4, 3]` has sum `7`, and its length is `2`.

---

## My Approach — Variable Sliding Window

I use the **Sliding Window** technique.

Here the window has two pointers:

- `left` → starting point of the window
- `right` → ending point of the window

We also maintain:

- `sum` → sum of elements currently inside the window
- `minLen` → minimum length found so far

### Main idea

We start with an empty window.

`right` keeps moving forward and adds elements to `sum`.

When the `sum` becomes greater than or equal to `target`, it means the current window is a valid answer.

Now we try to make the window smaller by moving `left` forward.

Why?

Because the question asks for the **minimum length**.

So:

1. Expand the window using `right`.
2. When `sum >= target`, the window is valid.
3. Store its length in `minLen`.
4. Remove `nums[left]`.
5. Move `left` forward.
6. Keep shrinking while the sum is still greater than or equal to the target.
7. Continue expanding using `right`.

This is called a **variable-size sliding window** because the window size keeps changing.

---

## Code

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;

        int left = 0;

        int minLen = Integer.MAX_VALUE;

        int sum = 0;

        for (int right = 0; right < n; right++) {

            sum += nums[right];

            while (sum >= target) {

                minLen = Math.min(minLen, right - left + 1);

                sum -= nums[left];

                left++;
            }
        }

        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
}
```

---

## Understanding the Code

### 1. Array length

```java
int n = nums.length;
```

We store the length of the array in `n`.

---

### 2. Left pointer

```java
int left = 0;
```

`left` represents the starting index of our current window.

Initially, it starts from index `0`.

---

### 3. Minimum length

```java
int minLen = Integer.MAX_VALUE;
```

Initially, we don't know any valid subarray.

So we give `minLen` a very large value.

Whenever we find a valid window, we compare its length with `minLen`.

```java
minLen = Math.min(minLen, right - left + 1);
```

---

### 4. Current window sum

```java
int sum = 0;
```

This stores the sum of the elements currently inside the window.

---

### 5. Move `right`

```java
for (int right = 0; right < n; right++) {
```

`right` moves from left to right through the entire array.

For every new element:

```java
sum += nums[right];
```

We add that element to the current window.

---

## Why do we use `while`?

This is the most important part.

```java
while (sum >= target)
```

As soon as the current window's sum becomes greater than or equal to the target, we have a valid window.

But we don't stop immediately.

We try to make it **smaller**.

So we:

```java
minLen = Math.min(minLen, right - left + 1);
```

First store the current window length.

Then remove the leftmost element:

```java
sum -= nums[left];
```

And move `left` forward:

```java
left++;
```

We continue doing this as long as:

```text
sum >= target
```

Once the sum becomes smaller than the target, we stop shrinking.

Then `right` continues moving forward.

---

# Dry Run

Let's take:

```text
target = 7
nums = [2, 3, 1, 2, 4, 3]
```

Expected answer:

```text
2
```

The answer is `[4, 3]`.

---

## Initial State

```text
left = 0
sum = 0
minLen = Infinity
```

We start moving `right`.

---

## Step 1

```text
right = 0
nums[right] = 2
```

Add `2`:

```text
sum = 0 + 2 = 2
```

Now:

```text
sum < target
2 < 7
```

So we cannot shrink the window.

Current window:

```text
[2]
```

Length:

```text
1
```

No answer yet.

---

## Step 2

```text
right = 1
nums[right] = 3
```

Add `3`:

```text
sum = 2 + 3 = 5
```

Now:

```text
5 < 7
```

So we continue expanding.

Current window:

```text
[2, 3]
```

---

## Step 3

```text
right = 2
nums[right] = 1
```

Add `1`:

```text
sum = 5 + 1 = 6
```

Still:

```text
6 < 7
```

Continue expanding.

Current window:

```text
[2, 3, 1]
```

---

## Step 4

```text
right = 3
nums[right] = 2
```

Add `2`:

```text
sum = 6 + 2 = 8
```

Now:

```text
8 >= 7
```

So the window is valid.

Current window:

```text
[2, 3, 1, 2]
```

Length:

```text
right - left + 1
= 3 - 0 + 1
= 4
```

So:

```text
minLen = 4
```

### Now shrink the window

Remove `nums[left]`, which is `2`.

```text
sum = 8 - 2 = 6
left = 1
```

Now:

```text
sum = 6 < 7
```

So we stop shrinking.

Current window:

```text
[3, 1, 2]
```

---

## Step 5

```text
right = 4
nums[right] = 4
```

Add `4`:

```text
sum = 6 + 4 = 10
```

Now:

```text
10 >= 7
```

Current window:

```text
[3, 1, 2, 4]
```

Length:

```text
4
```

`minLen` is already `4`, so:

```text
minLen = 4
```

### Shrink again

Remove `nums[left] = 3`.

```text
sum = 10 - 3 = 7
left = 2
```

Still:

```text
sum >= target
7 >= 7
```

So we can shrink again.

Current window:

```text
[1, 2, 4]
```

Length:

```text
right - left + 1
= 4 - 2 + 1
= 3
```

Update:

```text
minLen = 3
```

### Shrink again

Remove `nums[left] = 1`.

```text
sum = 7 - 1 = 6
left = 3
```

Now:

```text
6 < 7
```

Stop shrinking.

Current window:

```text
[2, 4]
```

---

## Step 6

```text
right = 5
nums[right] = 3
```

Add `3`:

```text
sum = 6 + 3 = 9
```

Now:

```text
9 >= 7
```

Current window:

```text
[2, 4, 3]
```

Length:

```text
5 - 3 + 1 = 3
```

So:

```text
minLen = 3
```

### Shrink

Remove `nums[left] = 2`.

```text
sum = 9 - 2 = 7
left = 4
```

Still valid:

```text
7 >= 7
```

Current window:

```text
[4, 3]
```

Length:

```text
5 - 4 + 1 = 2
```

Update:

```text
minLen = 2
```

### Shrink again

Remove `nums[left] = 4`.

```text
sum = 7 - 4 = 3
left = 5
```

Now:

```text
3 < 7
```

Stop.

---

# Final Answer

```text
minLen = 2
```

Therefore:

```text
[4, 3]
```

is the smallest valid subarray.

```text
4 + 3 = 7
```

and its length is:

```text
2
```

So the answer is:

```text
2
```

---

# Dry Run Table

| `right` | Added | `sum` | Window | Action | `minLen` |
|---:|---:|---:|---|---|---:|
| 0 | 2 | 2 | `[2]` | Expand | ∞ |
| 1 | 3 | 5 | `[2,3]` | Expand | ∞ |
| 2 | 1 | 6 | `[2,3,1]` | Expand | ∞ |
| 3 | 2 | 8 | `[2,3,1,2]` | Valid → shrink | 4 |
| 4 | 4 | 10 | `[3,1,2,4]` | Valid → shrink | 4 |
| 4 | — | 7 | `[1,2,4]` | Valid → shrink | 3 |
| 5 | 3 | 9 | `[2,4,3]` | Valid → shrink | 3 |
| 5 | — | 7 | `[4,3]` | Valid → shrink | **2** |

---

# Important Point — Why `right - left + 1`?

Suppose:

```text
left = 4
right = 5
```

The window is:

```text
[4, 3]
```

There are two elements.

Formula:

```text
right - left + 1
```

becomes:

```text
5 - 4 + 1
= 2
```

The `+1` is necessary because both `left` and `right` are included.

---

# Why Do We Remove `nums[left]`?

Suppose:

```text
[2, 3, 1, 2]
```

has:

```text
sum = 8
target = 7
```

It is valid, but length is `4`.

We want a smaller valid subarray.

So we remove the leftmost element:

```text
[3, 1, 2]
```

Now sum becomes:

```text
6
```

Since `6 < 7`, we cannot remove anything else.

This is how we find the smallest possible window for that `right`.

---

# Why Is This Approach Efficient?

A brute-force solution would check many subarrays and can take:

```text
O(n²)
```

With sliding window, both pointers move only forward.

`right` moves from `0` to `n-1`.

`left` also moves forward and never moves backward.

Therefore, each element is added to the window once and removed from the window at most once.

So the total time is:

```text
O(n)
```

---

# Complexity

### Time Complexity

```text
O(n)
```

Even though there is a `while` loop inside the `for` loop, it is still `O(n)`.

Why?

Because `left` never moves backward. Across the entire program, `left` can move at most `n` times, and `right` also moves `n` times.

Therefore:

```text
O(n + n) = O(n)
```

### Space Complexity

```text
O(1)
```

We only use a few variables:

```text
left
right
sum
minLen
n
```

No extra array or data structure is created.

---

# Common Mistakes

### 1. Using `>` instead of `>=`

Wrong:

```java
while (sum > target)
```

Correct:

```java
while (sum >= target)
```

Because the problem allows the sum to be **equal to** the target.

For example:

```text
4 + 3 = 7
```

This is a valid answer.

---

### 2. Updating `minLen` after removing the left element

We should first record the current valid window:

```java
minLen = Math.min(minLen, right - left + 1);
```

Then remove:

```java
sum -= nums[left];
left++;
```

Otherwise, we could miss the current smallest valid window.

---

### 3. Forgetting `+1`

Wrong:

```java
right - left
```

Correct:

```java
right - left + 1
```

---

# Final Code

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;

        int left = 0;

        int minLen = Integer.MAX_VALUE;

        int sum = 0;

        for (int right = 0; right < n; right++) {

            sum += nums[right];

            while (sum >= target) {

                minLen = Math.min(minLen, right - left + 1);

                sum -= nums[left];

                left++;
            }
        }

        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
}
```

---

## One-Line Pattern to Remember

```text
Expand → sum >= target → store answer → shrink → repeat
```

Or simply:

```text
RIGHT  →  EXPAND
LEFT   →  SHRINK
```

> **For minimum-size sliding window:**  
> **Expand until the condition becomes true, then shrink as much as possible.**

## Problem Link

LeetCode 209 — Minimum Size Subarray Sum. The official problem asks for the minimum length of a contiguous subarray whose sum is at least `target`; the standard positive-integer constraint makes this sliding-window approach work in `O(n)` time. citeturn0search0
