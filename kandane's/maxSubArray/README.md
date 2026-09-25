# 🔥 Maximum Subarray — Kadane's Algorithm

## 🧩 Problem

Given an integer array `nums`, find the **contiguous subarray** with the largest sum and return its sum.

### Example

```text
Input:
nums = [-2,1,-3,4,-1,2,1,-5,4]

Output:
6
```

The subarray with the maximum sum is:

```text
[4, -1, 2, 1]
```

Its sum is:

```text
4 + (-1) + 2 + 1 = 6
```

---

# 💡 Core Idea

This problem can be solved using **Kadane's Algorithm**.

At every index, we have two choices:

### Choice 1 — Start a new subarray

```text
nums[i]
```

This means the previous subarray is hurting our sum, so we start fresh from the current element.

### Choice 2 — Continue the previous subarray

```text
bestending + nums[i]
```

This means we include the current element in the subarray that ended at the previous index.

So:

```java
bestending = Math.max(nums[i], bestending + nums[i]);
```

`bestending` means:

> The maximum sum of a subarray that MUST end at the current index.

---

# 🧠 Understanding the Two Variables

## 1. `bestending`

```java
int bestending = nums[0];
```

It stores:

```text
Maximum subarray sum ending at the current index
```

It is updated at every iteration.

---

## 2. `res`

```java
int res = nums[0];
```

It stores:

```text
Maximum subarray sum found anywhere so far
```

So:

```java
res = Math.max(bestending, res);
```

updates the overall answer.

---

# 🔄 Algorithm

For every element starting from index `1`:

### Step 1

Take the current element:

```java
int v1 = nums[i];
```

This represents:

```text
Start a new subarray
```

### Step 2

Calculate:

```java
int v2 = bestending + nums[i];
```

This represents:

```text
Continue the previous subarray
```

### Step 3

Choose the better option:

```java
bestending = Math.max(v1, v2);
```

### Step 4

Update the overall maximum:

```java
res = Math.max(bestending, res);
```

---

# 💻 Code

```java
class Solution {
    public int maxSubArray(int[] nums) {

        int bestending = nums[0];
        int res = nums[0];

        for(int i = 1; i < nums.length; i++){

            int v1 = nums[i];
            int v2 = bestending + nums[i];

            bestending = Math.max(v1, v2);

            res = Math.max(bestending, res);
        }

        return res;
    }
}
```

---

# 🧪 Complete Dry Run

Consider:

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

Initially:

```text
bestending = -2
res        = -2
```

---

## Iteration 1

Current element:

```text
nums[1] = 1
```

Two choices:

```text
v1 = 1
v2 = bestending + nums[i]
   = -2 + 1
   = -1
```

Choose maximum:

```text
bestending = max(1, -1)
           = 1
```

Update result:

```text
res = max(1, -2)
    = 1
```

---

## Iteration 2

Current:

```text
nums[2] = -3
```

```text
v1 = -3
v2 = 1 + (-3)
   = -2
```

Choose:

```text
bestending = max(-3, -2)
           = -2
```

Result:

```text
res = max(-2, 1)
    = 1
```

---

## Iteration 3

Current:

```text
nums[3] = 4
```

```text
v1 = 4
v2 = -2 + 4
   = 2
```

Choose:

```text
bestending = max(4, 2)
           = 4
```

Result:

```text
res = max(4, 1)
    = 4
```

---

## Iteration 4

Current:

```text
nums[4] = -1
```

```text
v1 = -1
v2 = 4 + (-1)
   = 3
```

Choose:

```text
bestending = 3
```

Result:

```text
res = 4
```

---

## Iteration 5

Current:

```text
nums[5] = 2
```

```text
v1 = 2
v2 = 3 + 2
   = 5
```

Choose:

```text
bestending = 5
```

Result:

```text
res = 5
```

---

## Iteration 6

Current:

```text
nums[6] = 1
```

```text
v1 = 1
v2 = 5 + 1
   = 6
```

Choose:

```text
bestending = 6
```

Result:

```text
res = 6
```

---

## Iteration 7

Current:

```text
nums[7] = -5
```

```text
v1 = -5
v2 = 6 + (-5)
   = 1
```

Choose:

```text
bestending = 1
```

Result:

```text
res = 6
```

Notice something important:

Even though `bestending` dropped from `6` to `1`, we **don't lose 6** because `res` already stored it.

---

## Iteration 8

Current:

```text
nums[8] = 4
```

```text
v1 = 4
v2 = 1 + 4
   = 5
```

Choose:

```text
bestending = 5
```

Result:

```text
res = max(5, 6)
    = 6
```

---

# 📊 Dry Run Table

| i | nums[i] | `v1 = nums[i]` | `v2 = bestending + nums[i]` | `bestending` | `res` |
| - | ------: | -------------: | --------------------------: | -----------: | ----: |
| 0 |      -2 |              — |                           — |           -2 |    -2 |
| 1 |       1 |              1 |                          -1 |            1 |     1 |
| 2 |      -3 |             -3 |                          -2 |           -2 |     1 |
| 3 |       4 |              4 |                           2 |            4 |     4 |
| 4 |      -1 |             -1 |                           3 |            3 |     4 |
| 5 |       2 |              2 |                           5 |            5 |     5 |
| 6 |       1 |              1 |                           6 |            6 |     6 |
| 7 |      -5 |             -5 |                           1 |            1 |     6 |
| 8 |       4 |              4 |                           5 |            5 |     6 |

Therefore:

```text
Answer = 6
```

Maximum subarray:

```text
[4, -1, 2, 1]
```

---

# ⭐ The Most Important Concept

The main question at every element is:

```text
Should I continue the previous subarray
OR
should I start a new subarray here?
```

In code:

```java
Math.max(nums[i], bestending + nums[i])
```

Think of it like:

```text
             Current Element
                    |
          ┌─────────┴─────────┐
          ↓                   ↓
     Start Fresh          Continue
     nums[i]          bestending + nums[i]
          └─────────┬─────────┘
                    ↓
              Take Maximum
```

---

# 🧠 Why Does This Work?

Suppose we have:

```text
[-5, 4]
```

When we reach `4`:

```text
Continue previous:
-5 + 4 = -1

Start fresh:
4
```

Obviously:

```text
4 > -1
```

So we discard the previous negative contribution and start from `4`.

But if we have:

```text
[4, -1]
```

Then:

```text
Continue:
4 + (-1) = 3

Start fresh:
-1
```

So we continue:

```text
3 > -1
```

This decision at every index is the heart of Kadane's Algorithm.

---

# ⚠️ Important: Why Initialize With `nums[0]`?

We use:

```java
int bestending = nums[0];
int res = nums[0];
```

instead of:

```java
int bestending = 0;
int res = 0;
```

because the array can contain **all negative numbers**.

Example:

```text
nums = [-5, -2, -8]
```

The correct answer is:

```text
-2
```

because the subarray `[-2]` has the largest sum.

If we initialized `res = 0`, we would incorrectly return `0`.

---

# 🧪 All Negative Example

```text
nums = [-5, -2, -8]
```

Start:

```text
bestending = -5
res = -5
```

For `-2`:

```text
v1 = -2
v2 = -5 + (-2) = -7

bestending = max(-2, -7)
           = -2

res = max(-5, -2)
    = -2
```

For `-8`:

```text
v1 = -8
v2 = -2 + (-8) = -10

bestending = -8

res = max(-2, -8)
    = -2
```

Answer:

```text
-2
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the array only once.

### Space Complexity

```text
O(1)
```

We use only two variables:

```text
bestending
res
```

---

# 🎯 Pattern Recognition

When you see a question asking:

> Find the maximum sum of a contiguous subarray.

Think:

```text
Kadane's Algorithm
```

The key pattern is:

```java
current = Math.max(nums[i], current + nums[i]);
```

---

# 🔑 Key Takeaways

1. `bestending` = best sum of a subarray **ending at current index**.
2. At every element, decide:

   * Start new
   * Continue previous
3. `res` = maximum answer found so far.
4. Negative contribution can be discarded when starting fresh gives a better sum.
5. Works with all-negative arrays when initialized using `nums[0]`.
6. Time: `O(n)`
7. Space: `O(1)`

---

# 🚀 One-Line Mental Model

```text
"At every element, should I continue what I have or start fresh?"
```

That's **Kadane's Algorithm**.
