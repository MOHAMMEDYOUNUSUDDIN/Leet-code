# 📌 LeetCode 862 — Shortest Subarray with Sum at Least K

## 🧩 Question

Given an integer array `nums` and an integer `k`, find the **length of the shortest non-empty subarray** whose sum is at least `k`.

If no such subarray exists, return `-1`.

### Example

**Input:**

```text
nums = [2,-1,2]
k = 3
```

**Output:**

```text
3
```

### Why?

The entire array:

```text
[2,-1,2]
```

has sum:

```text
2 + (-1) + 2 = 3
```

So the shortest valid subarray has length `3`.

---

# 💡 Main Idea

This problem is tricky because the array can contain **negative numbers**.

A normal sliding window does not work reliably.

We use:

```text
Prefix Sum + Monotonic Deque
```

### Main idea

Convert subarray sum into a difference of prefix sums.

For a subarray from `i + 1` to `j`:

```text
sum = prefix[j] - prefix[i]
```

We need:

```text
prefix[j] - prefix[i] >= k
```

Therefore:

```text
prefix[j] - k >= prefix[i]
```

We use a deque to efficiently find the best previous prefix sum.

---

# 🧠 Pattern Recognition

This is a very important pattern:

## **Prefix Sum + Monotonic Deque**

When you see:

* Shortest subarray
* Sum at least `K`
* Negative numbers are present
* Need `O(n)` solution

Think:

> **Prefix Sum + Monotonic Increasing Deque**

### Why not normal Sliding Window?

For positive numbers, sliding window works well.

Example:

```text
[2,3,4,5]
```

But with negative numbers:

```text
[2,-5,6]
```

Removing or adding an element can unexpectedly increase or decrease the sum.

So the normal two-pointer/sliding-window approach breaks.

---

# 💡 Hints

### Hint 1

For a subarray:

```text
nums[i+1 ... j]
```

its sum can be written as:

```text
prefix[j] - prefix[i]
```

---

### Hint 2

We need:

```text
prefix[j] - prefix[i] >= k
```

So find an earlier prefix sum that makes this difference at least `k`.

---

### Hint 3

Which prefix sum is better?

Suppose:

```text
prefix[i] = 10
prefix[x] = 5
```

For the same current prefix:

```text
current = 15
```

`5` is better because:

```text
15 - 5 = 10
15 - 10 = 5
```

So we want the deque to maintain **increasing prefix sums**.

---

### Hint 4

If the current prefix sum makes a valid subarray with the front of the deque:

```java
cumulativeSum[j] - cumulativeSum[deq.peekFirst()] >= k
```

we found a valid subarray.

Try removing that index and check again for an even shorter one.

---

### Hint 5

If the current prefix sum is smaller than the prefix sum at the back:

```java
cumulativeSum[j] <= cumulativeSum[deq.peekLast()]
```

remove the back.

That old index is no longer useful.

---

# 💻 Code

```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int N = nums.length;

        Deque<Integer> deq = new LinkedList<>();
        long[] cumulativeSum = new long[N];

        int result = Integer.MAX_VALUE;
        int j = 0;

        while (j < N) {

            if (j == 0)
                cumulativeSum[j] = nums[j];
            else
                cumulativeSum[j] =
                    cumulativeSum[j - 1] + nums[j];

            // Subarray starting from index 0
            if (cumulativeSum[j] >= k)
                result = Math.min(result, j + 1);

            // Found a valid subarray
            while (!deq.isEmpty() &&
                   cumulativeSum[j] -
                   cumulativeSum[deq.peekFirst()] >= k) {

                result = Math.min(
                    result,
                    j - deq.peekFirst()
                );

                deq.pollFirst();
            }

            // Maintain increasing prefix sums
            while (!deq.isEmpty() &&
                   cumulativeSum[j] <=
                   cumulativeSum[deq.peekLast()]) {

                deq.pollLast();
            }

            // Add current index
            deq.offerLast(j);

            j++;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
```

---

# 🔎 Code Explanation

## 1. Store array length

```java
int N = nums.length;
```

Stores the number of elements.

---

# 2. Create Monotonic Deque

```java
Deque<Integer> deq = new LinkedList<>();
```

The deque stores **indices**, not prefix sums.

The prefix sums corresponding to those indices are maintained in increasing order.

Example:

```text
Deque indices:
[2, 5, 7]

Prefix sums:
[3, 8, 12]
```

They are increasing.

---

# 3. Prefix Sum Array

```java
long[] cumulativeSum = new long[N];
```

`long` is used because the cumulative sum can become larger than the `int` range.

Example:

```text
nums = [1000000000,1000000000,...]
```

The total can exceed `int`.

---

# 4. Calculate Prefix Sum

```java
if (j == 0)
    cumulativeSum[j] = nums[j];
else
    cumulativeSum[j] =
        cumulativeSum[j - 1] + nums[j];
```

Example:

```text
nums = [2,-1,2]
```

Prefix sums:

```text
index       0   1   2
nums        2  -1   2
prefix      2   1   3
```

---

# 5. Check Subarray Starting From Index 0

```java
if (cumulativeSum[j] >= k)
    result = Math.min(result, j + 1);
```

If:

```text
prefix[j] >= k
```

then:

```text
nums[0...j]
```

itself is a valid subarray.

Example:

```text
nums = [2,-1,2]
k = 3
```

At `j = 2`:

```text
prefix[2] = 3
```

Therefore:

```text
result = 3
```

---

# 6. Check Deque Front

```java
while (!deq.isEmpty() &&
       cumulativeSum[j] -
       cumulativeSum[deq.peekFirst()] >= k)
```

This checks whether the current prefix and the earliest useful prefix form a valid subarray.

Remember:

```text
Subarray Sum =
Current Prefix - Previous Prefix
```

So:

```text
cumulativeSum[j] -
cumulativeSum[i]
```

is the subarray sum.

---

# 7. Update Shortest Length

```java
result = Math.min(
    result,
    j - deq.peekFirst()
);
```

If the subarray is valid, calculate its length.

Example:

```text
j = 5
previous index = 2
```

Length:

```text
5 - 2 = 3
```

---

# 8. Remove the Front

```java
deq.pollFirst();
```

Why remove it?

Because we already found a valid subarray using that index.

Since the deque stores indices from oldest to newest, removing it allows us to check whether a shorter valid subarray exists.

---

# 9. Maintain Increasing Prefix Sums

```java
while (!deq.isEmpty() &&
       cumulativeSum[j] <=
       cumulativeSum[deq.peekLast()]) {

    deq.pollLast();
}
```

This is the **monotonic deque** part.

Suppose:

```text
Old prefix = 10
New prefix = 6
```

The old prefix `10` is useless.

Why?

For any future prefix `X`:

```text
X - 6 > X - 10
```

So prefix `6` is always better.

Therefore remove `10`.

---

# 10. Add Current Index

```java
deq.offerLast(j);
```

Store the current index for future subarrays.

---

# 🧪 Dry Run

### Input

```text
nums = [2,-1,2]
k = 3
```

Prefix sums:

```text
index:   0   1   2
nums:    2  -1   2
prefix:  2   1   3
```

Initial:

```text
result = ∞
deque = []
```

---

## Step 1 — j = 0

Prefix:

```text
prefix[0] = 2
```

Check:

```text
2 >= 3 ❌
```

Deque is empty.

Add index:

```text
deque = [0]
```

---

## Step 2 — j = 1

Prefix:

```text
prefix[1] = 1
```

Check:

```text
1 >= 3 ❌
```

Now compare with deque back:

```text
prefix[1] <= prefix[0]
1 <= 2 ✅
```

Remove index `0`.

```text
deque = []
```

Add index `1`:

```text
deque = [1]
```

### Why remove index 0?

Because prefix `1` is smaller than prefix `2`.

For future values, `1` will always produce a better/larger subarray sum.

---

## Step 3 — j = 2

Prefix:

```text
prefix[2] = 3
```

Check:

```text
3 >= 3 ✅
```

So:

```text
result = 3
```

Now check deque:

```text
3 - prefix[1] >= 3?
```

```text
3 - 1 = 2
```

Not enough.

So no more removal.

Add index `2`:

```text
deque = [1,2]
```

---

# ✅ Final Answer

```text
3
```

The shortest valid subarray is:

```text
[2,-1,2]
```

Sum:

```text
2 + (-1) + 2 = 3
```

Length:

```text
3
```

---

# 🧪 Important Dry Run With Negative Numbers

Consider:

```text
nums = [84,-37,32,40,95]
k = 167
```

Prefix sums:

```text
84
47
79
119
214
```

At the final position:

```text
214 - 47 = 167
```

So the subarray is:

```text
[-37,32,40,95]
```

Length:

```text
4
```

The algorithm can find this efficiently even though the array contains negative numbers.

---

# 🌍 Real-World Example

Imagine daily profit/loss:

```text
Day 1: +₹50
Day 2: -₹20
Day 3: +₹40
Day 4: +₹100
```

Suppose you want the **shortest consecutive set of days** where total profit is at least:

```text
₹120
```

You cannot simply use a normal sliding window because some days can have losses.

Prefix Sum + Monotonic Deque helps find the shortest profitable period efficiently.

---

# ❌ Why Normal Sliding Window Doesn't Work

Normal sliding window works well when all numbers are positive.

Example:

```text
[2,3,4,5]
```

Increasing the window always increases the sum.

But with negative numbers:

```text
[5,-10,20]
```

Adding an element can decrease the sum.

Removing an element can increase the sum.

Therefore:

> **Negative numbers break the normal sliding-window assumption.**

That's why we use:

```text
Prefix Sum + Monotonic Deque
```

---

# 🔥 The Most Important Concept

Suppose we have:

```text
prefix[i] = 10
prefix[j] = 6
```

Since:

```text
6 < 10
```

index `i` is dominated by index `j`.

Why?

For any future prefix `X`:

```text
X - 6 > X - 10
```

Therefore, the smaller prefix is always more useful.

So we remove the larger prefix from the back.

This creates an:

## **Increasing Monotonic Deque**

```text
Small Prefix
     ↓
[ 2, 5, 9, 14 ]
 ↑
Smallest
```

---

# 🎯 Pattern Recognition Cheat Sheet

### Normal Sliding Window

Use when:

```text
Positive numbers
+
Find subarray
+
Sum/product/window condition
```

---

### Prefix Sum

Use when:

```text
Subarray sum
Range sum
Sum between two indices
```

Formula:

```text
sum(i...j) = prefix[j] - prefix[i]
```

---

### Prefix Sum + HashMap

Common for:

```text
Subarray Sum Equals K
```

Example:

**LeetCode 560**

---

### Prefix Sum + Monotonic Deque

Use when:

```text
Shortest subarray
+
Sum >= K
+
Negative numbers
```

Example:

**LeetCode 862**

---

# 🧠 Pattern Recognition Flow

```text
Subarray Problem
       ↓
Is sum involved?
       ↓
     YES
       ↓
Can numbers be negative?
    ↙       ↘
  NO         YES
  ↓           ↓
Sliding     Prefix Sum
Window         +
             Deque
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Each index is:

* Added to deque once
* Removed from deque at most once

Therefore, total operations are linear.

### Space Complexity

```text
O(n)
```

Because of:

```text
prefix sum array
+
deque
```

---

# 📝 One-Line Revision

> **LeetCode 862 = Prefix Sum + Increasing Monotonic Deque to find the shortest subarray with sum ≥ K, even when negative numbers exist.**

### ⭐ Remember these 3 ideas

```text
1. Subarray Sum = Current Prefix - Previous Prefix

2. Maintain increasing prefix sums

3. Move/remove from front when sum >= K
```

### Core Formula

```java
currentPrefix - oldPrefix >= k
```

### Core Pattern

```text
PREFIX SUM
     ↓
MONOTONIC INCREASING DEQUE
     ↓
CHECK SUM >= K
     ↓
UPDATE SHORTEST LENGTH
```
