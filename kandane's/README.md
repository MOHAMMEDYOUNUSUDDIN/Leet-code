# 🔥 Kadane's Algorithm — Pattern Recognition

Kadane's Algorithm is one of the most important patterns for solving **maximum subarray sum** problems efficiently.

The main idea is:

> At every element, decide whether to continue the previous subarray or start a new subarray from the current element.

---

# 🧠 What is Kadane's Algorithm?

Suppose we have:

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

We need to find the **maximum sum of a contiguous subarray**.

The answer is:

```text
[4, -1, 2, 1]
```

Sum:

```text
4 + (-1) + 2 + 1 = 6
```

Therefore:

```text
Answer = 6
```

Kadane solves this in:

```text
Time  → O(n)
Space → O(1)
```

---

# 🎯 Core Idea

At every element, we have two choices:

```text
1. Continue the previous subarray
2. Start a new subarray from the current element
```

Formula:

```java
currentSum = Math.max(
    nums[i],
    currentSum + nums[i]
);
```

Then update the maximum:

```java
maxSum = Math.max(maxSum, currentSum);
```

---

# 🧠 How to Identify Kadane's Algorithm?

Don't look for the words `"Kadane's Algorithm"`.

Instead, look for these signals:

```text
Maximum subarray sum
Maximum sum of a contiguous subarray
Largest sum of contiguous elements
Maximum sum segment
Best contiguous portion
Maximum sum of consecutive elements
Maximum possible sum from a continuous segment
```

The strongest signal is:

```text
CONTIGUOUS
+
MAXIMUM SUM
```

Think:

```text
🔥 KADANE'S ALGORITHM
```

---

# 🚨 Most Important Recognition Rule

Whenever you see:

```text
ARRAY
  ↓
CONTIGUOUS SUBARRAY
  ↓
MAXIMUM SUM
```

Think:

```text
🔥 KADANE
```

---

# 🧩 What Does Contiguous Mean?

Contiguous means the elements must be next to each other.

For:

```text
[1, 2, 3, 4]
```

This is a valid subarray:

```text
[2, 3]
```

But this is NOT:

```text
[1, 3]
```

because `1` and `3` are not next to each other.

Therefore:

```text
SUBARRAY
+
CONTIGUOUS
+
MAXIMUM SUM
```

is a strong Kadane signal.

---

# ⚠️ Subarray vs Subsequence

This is important.

## Subarray

Elements must be continuous.

```text
[1, 2, 3, 4]
```

Example:

```text
[2, 3]
```

Valid.

---

## Subsequence

Elements don't have to be continuous.

Example:

```text
[1, 2, 3, 4]
```

We can choose:

```text
[1, 3]
```

So don't automatically use Kadane for subsequence problems.

Kadane is primarily used for:

```text
CONTIGUOUS SUBARRAY
```

---

# 🔥 Main Kadane Pattern

The most important line is:

```java
currentSum = Math.max(
    nums[i],
    currentSum + nums[i]
);
```

Meaning:

```text
             nums[i]
                |
        ┌───────┴───────┐
        ↓               ↓
   Continue          Start New
        ↓               ↓
currentSum + nums[i]  nums[i]
        │               │
        └───────┬───────┘
                ↓
              MAX
                ↓
          currentSum
```

Then:

```java
maxSum = Math.max(maxSum, currentSum);
```

---

# 🧠 Real-World Example

Imagine your daily profit/loss:

```text
[-2, 3, -1, 5, -6, 4]
```

You want to find the best continuous period.

Every day you have two choices:

```text
Continue the previous period
```

or:

```text
Start a new period today
```

Kadane makes exactly this decision.

---

# 🧪 Example

Array:

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

Question:

> Find the maximum sum of a contiguous subarray.

Expected answer:

```text
6
```

Because:

```text
[4, -1, 2, 1]
```

has:

```text
4 + (-1) + 2 + 1 = 6
```

---

# 🚶 Complete Dry Run

Array:

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

Initialize:

```java
currentSum = nums[0];
maxSum = nums[0];
```

So:

```text
currentSum = -2
maxSum = -2
```

---

## Step 1 → `1`

Two choices:

```text
Start new:
1

Continue:
-2 + 1 = -1
```

Choose:

```text
1
```

Therefore:

```text
currentSum = 1
maxSum = 1
```

---

## Step 2 → `-3`

Choices:

```text
Start new:
-3

Continue:
1 + (-3) = -2
```

Choose:

```text
-2
```

Therefore:

```text
currentSum = -2
maxSum = 1
```

---

## Step 3 → `4`

Choices:

```text
Start new:
4

Continue:
-2 + 4 = 2
```

Choose:

```text
4
```

Therefore:

```text
currentSum = 4
maxSum = 4
```

---

## Step 4 → `-1`

Choices:

```text
Start new:
-1

Continue:
4 + (-1) = 3
```

Choose:

```text
3
```

Therefore:

```text
currentSum = 3
maxSum = 4
```

Current subarray:

```text
[4, -1]
```

---

## Step 5 → `2`

Choices:

```text
Start new:
2

Continue:
3 + 2 = 5
```

Choose:

```text
5
```

Therefore:

```text
currentSum = 5
maxSum = 5
```

Current subarray:

```text
[4, -1, 2]
```

---

## Step 6 → `1`

Choices:

```text
Start new:
1

Continue:
5 + 1 = 6
```

Choose:

```text
6
```

Therefore:

```text
currentSum = 6
maxSum = 6
```

Current subarray:

```text
[4, -1, 2, 1]
```

---

## Step 7 → `-5`

Choices:

```text
Start new:
-5

Continue:
6 + (-5) = 1
```

Choose:

```text
1
```

Therefore:

```text
currentSum = 1
maxSum = 6
```

---

## Step 8 → `4`

Choices:

```text
Start new:
4

Continue:
1 + 4 = 5
```

Choose:

```text
5
```

Maximum remains:

```text
6
```

---

# 📊 Complete Dry Run Table

```text
Array:
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

| Element | Continue | Start New | currentSum | maxSum |
| ------: | -------: | --------: | ---------: | -----: |
|      -2 |        - |        -2 |         -2 |     -2 |
|       1 |       -1 |         1 |          1 |      1 |
|      -3 |       -2 |        -3 |         -2 |      1 |
|       4 |        2 |         4 |          4 |      4 |
|      -1 |        3 |        -1 |          3 |      4 |
|       2 |        5 |         2 |          5 |      5 |
|       1 |        6 |         1 |          6 |      6 |
|      -5 |        1 |        -5 |          1 |      6 |
|       4 |        5 |         4 |          5 |      6 |

Final answer:

```text
6
```

Maximum subarray:

```text
[4, -1, 2, 1]
```

---

# 💻 Standard Kadane's Algorithm

```java
class Solution {
    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            currentSum = Math.max(
                nums[i],
                currentSum + nums[i]
            );

            maxSum = Math.max(
                maxSum,
                currentSum
            );
        }

        return maxSum;
    }
}
```

---

# 🧠 Understand the Two Variables

## 1. `currentSum`

`currentSum` means:

> Maximum sum of a subarray that ends at the current index.

Example:

```text
[4, -1, 2]
```

At `2`:

```text
currentSum = 5
```

---

## 2. `maxSum`

`maxSum` means:

> Maximum subarray sum found anywhere so far.

Example:

```text
[-2, 1, -3, 4, -1, 2, 1]
```

Best subarray:

```text
[4, -1, 2, 1]
```

Therefore:

```text
maxSum = 6
```

---

# 🔥 Most Important Concept

Remember:

```text
currentSum
    ↓
Best subarray ending HERE

maxSum
    ↓
Best subarray found ANYWHERE
```

---

# 🧠 Why Do We Restart?

Suppose:

```text
currentSum = -10
nums[i] = 5
```

Continue:

```text
-10 + 5 = -5
```

Start new:

```text
5
```

Obviously:

```text
5 > -5
```

So:

```text
currentSum = 5
```

The negative previous sum is hurting us.

Therefore:

```text
If the previous sum is harmful,
drop it and start again.
```

---

# 🔥 Easy Way to Remember

```text
If currentSum is helping:
    CONTINUE

If currentSum is hurting:
    RESTART
```

Formula:

```java
currentSum = Math.max(
    nums[i],
    currentSum + nums[i]
);
```

---

# ⚠️ Edge Case: All Negative Numbers

Consider:

```text
[-5, -2, -8, -1]
```

Correct answer:

```text
-1
```

Because:

```text
[-1]
```

is the maximum subarray.

Do NOT initialize like this:

```java
int currentSum = 0;
int maxSum = 0;
```

That would incorrectly return:

```text
0
```

Instead:

```java
int currentSum = nums[0];
int maxSum = nums[0];
```

This correctly handles all-negative arrays.

---

# 🧪 All Negative Example

Array:

```text
[-5, -2, -8, -1]
```

Start:

```text
currentSum = -5
maxSum = -5
```

At `-2`:

```text
max(-2, -5 + -2)
= max(-2, -7)
= -2
```

Now:

```text
currentSum = -2
maxSum = -2
```

At `-8`:

```text
max(-8, -2 + -8)
= max(-8, -10)
= -8
```

Maximum remains:

```text
-2
```

At `-1`:

```text
max(-1, -8 + -1)
= max(-1, -9)
= -1
```

Final:

```text
maxSum = -1
```

---

# ⏱️ Complexity

Kadane's Algorithm:

```text
Time Complexity:
O(n)
```

Why?

We visit every element exactly once.

```text
Space Complexity:
O(1)
```

Why?

We only use a few variables:

```text
currentSum
maxSum
```

---

# 🚨 Brute Force vs Kadane

A brute-force approach may check every possible subarray.

Number of subarrays:

```text
n(n + 1) / 2
```

So it can take:

```text
O(n²)
```

or worse depending on how sums are calculated.

Kadane:

```text
O(n)
```

Instead of:

```text
Try every subarray
        ↓
Calculate every sum
```

we do:

```text
One pass
   ↓
Continue OR Restart
   ↓
Track maximum
```

---

# 🔍 How to Identify Kadane in a New Question

When reading a problem, ask:

```text
1. Is it an ARRAY?

2. Am I looking for a SUBARRAY?

3. Does the subarray have to be CONTIGUOUS?

4. Am I dealing with a SUM?

5. Am I asked for the MAXIMUM?
```

If most answers are YES:

```text
🔥 Think Kadane
```

---

# 🧩 Question Identification Examples

## Example 1

> Find the maximum sum of a contiguous subarray.

Think:

```text
Contiguous
+
Maximum Sum
        ↓
Kadane
```

---

## Example 2

> Find the largest possible sum from consecutive elements.

Think:

```text
Consecutive
+
Largest Sum
        ↓
Kadane
```

---

## Example 3

> Find the maximum subarray.

Think:

```text
Maximum Subarray
        ↓
Kadane
```

---

## Example 4

> Find the maximum sum of any continuous segment.

Think:

```text
Continuous Segment
+
Maximum Sum
        ↓
Kadane
```

---

# ⚠️ When NOT to Immediately Use Kadane

Don't use Kadane just because the question contains:

```text
maximum
```

For example:

> Find the maximum element.

This does NOT require Kadane.

Simply track:

```java
max = Math.max(max, nums[i]);
```

Also:

> Find maximum subsequence sum.

may require a different approach because a subsequence doesn't have to be contiguous.

Always look for:

```text
CONTIGUOUS SUBARRAY
+
MAXIMUM SUM
```

---

# 🔥 Kadane + Tracking the Actual Subarray

Sometimes the question asks for the actual subarray, not only the sum.

Then track:

```text
start
end
tempStart
```

Example:

```java
class Solution {
    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return maxSum;
    }
}
```

For:

```text
[-2,1,-3,4,-1,2,1,-5,4]
```

the maximum subarray is:

```text
[4,-1,2,1]
```

with:

```text
sum = 6
```

---

# 🔥 Kadane vs Sliding Window

Both can deal with subarrays, but the thinking is different.

## Sliding Window

Usually:

```text
CONTIGUOUS
+
WINDOW CONDITION
```

Examples:

```text
Longest substring
At most K
Minimum window
Fixed size K
```

Think:

```text
LEFT + RIGHT
```

---

## Kadane

Usually:

```text
CONTIGUOUS SUBARRAY
+
MAXIMUM SUM
```

Think:

```text
CURRENT SUM
+
MAX SUM
```

Easy difference:

```text
Sliding Window
→ Maintain a window based on a condition

Kadane
→ Maintain the best subarray sum
```

---

# 🔥 Kadane vs Prefix Sum

## Prefix Sum

Useful when:

```text
Range sum queries
Subarray sum equals K
Need sum of arbitrary ranges
```

## Kadane

Best fit when:

```text
Maximum contiguous subarray sum
```

---

# 🧩 Kadane Variations

Kadane's core idea can be extended to several problems.

Common examples:

```text
1. Maximum Subarray Sum
2. Maximum Circular Subarray
3. Maximum Product Subarray
4. Best Time to Buy and Sell Stock
5. Maximum Subarray with indices
```

The exact implementation may change, but the main idea remains:

```text
Maintain the best state while scanning the array.
```

---

# 🏆 Important LeetCode Problems

## 🟢 Basic

### 53. Maximum Subarray

```text
Pattern:
Kadane's Algorithm

Goal:
Find maximum sum of a contiguous subarray.
```

This is the most important Kadane problem.

---

## 🟡 Intermediate

### 918. Maximum Sum Circular Subarray

```text
Pattern:
Kadane + Circular Array
```

Important idea:

```text
Maximum Normal Subarray
```

and:

```text
Total Sum - Minimum Subarray
```

are used.

---

### 152. Maximum Product Subarray

```text
Pattern:
Kadane-style DP
```

Unlike normal Kadane, we track:

```text
maximum product
minimum product
```

because:

```text
negative × negative = positive
```

---

# 🗺️ Kadane Pattern Cheat Sheet

| Question Pattern                    | Think                     |
| ----------------------------------- | ------------------------- |
| Maximum subarray sum                | Kadane                    |
| Maximum contiguous sum              | Kadane                    |
| Largest sum segment                 | Kadane                    |
| Maximum sum of consecutive elements | Kadane                    |
| Best continuous segment             | Kadane                    |
| Maximum subarray                    | Kadane                    |
| All negative numbers possible       | Initialize with `nums[0]` |
| Need actual subarray                | Kadane + indices          |
| Circular maximum subarray           | Kadane variation          |
| Maximum product subarray            | Kadane-style variation    |

---

# 💻 Universal Kadane Template

```java
int currentSum = nums[0];
int maxSum = nums[0];

for (int i = 1; i < nums.length; i++) {

    currentSum = Math.max(
        nums[i],
        currentSum + nums[i]
    );

    maxSum = Math.max(
        maxSum,
        currentSum
    );
}

return maxSum;
```

---

# 🔑 Kadane Memory Trick

Remember:

```text
CURRENT + NEW
      OR
    NEW ONLY
```

Take the maximum.

Then:

```text
CURRENT SUM
     ↓
MAXIMUM SO FAR
```

Code:

```java
currentSum = Math.max(
    nums[i],
    currentSum + nums[i]
);

maxSum = Math.max(
    maxSum,
    currentSum
);
```

---

# 🧠 Real-World Mental Model

Imagine you are carrying money.

If your current balance is positive:

```text
Keep carrying it.
```

If your current balance is negative:

```text
Drop it and start fresh.
```

So:

```text
Positive contribution
        ↓
Continue

Negative contribution
        ↓
Restart
```

---

# 🧠 Final Mental Framework

When you see:

```text
ARRAY
  ↓
SUBARRAY?
  ↓
CONTIGUOUS?
  ↓
YES
  ↓
SUM?
  ↓
MAXIMUM?
  ↓
YES
  ↓
🔥 KADANE
```

Then think:

```text
currentSum
     ↓
Continue or Restart?
     ↓
Update maxSum
```

---

# 🏆 Final Golden Rules

```text
CONTIGUOUS SUBARRAY
        +
MAXIMUM SUM
        ↓
🔥 KADANE
```

```text
currentSum
=
maximum sum ending at current index
```

```text
maxSum
=
maximum sum found anywhere
```

```text
currentSum + nums[i]
        OR
nums[i]
        ↓
Take MAX
```

```text
Previous sum is helping
        ↓
CONTINUE
```

```text
Previous sum is hurting
        ↓
RESTART
```

```text
Time  → O(n)
Space → O(1)
```

---

# ⭐ One-Line Memory Trick

```text
KADANE =
CONTIGUOUS SUBARRAY
+
MAXIMUM SUM
+
CONTINUE OR RESTART
```

---

# 🚀 Final Goal

Don't memorize Kadane's code.

Understand this question:

> "Is the previous subarray helping me or hurting me?"

If it is helping:

```text
CONTINUE
```

If it is hurting:

```text
RESTART
```

Then maintain:

```text
currentSum
+
maxSum
```

Once you see:

```text
SUBARRAY
+
CONTIGUOUS
+
MAXIMUM SUM
```

immediately think:

```text
🔥 KADANE'S ALGORITHM
```
