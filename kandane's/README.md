# 📈 Kadane's Algorithm — Pattern Recognition

**Kadane's Algorithm** is one of the most important patterns for solving **maximum subarray sum** problems.

The main idea is:

> At every position, decide whether it is better to **continue the current subarray** or **start a new subarray from the current element**.

The core decision is:

```text
currentSum + currentElement
        VS
currentElement
```

We choose the larger one.

---

# 🧠 How to Identify Kadane's Algorithm?

Don't look only for the words **"Kadane's Algorithm"**.

The question may be written in many different ways.

Look for:

```text
Maximum subarray sum
Largest sum of a contiguous subarray
Maximum sum of consecutive elements
Best contiguous segment
Largest sum
Maximum profit from consecutive values
Maximum gain from a continuous range
```

The strongest signal is:

```text
CONTIGUOUS / CONSECUTIVE
+
MAXIMUM SUM
```

Then think:

```text
🔥 KADANE'S ALGORITHM
```

---

# 🎯 The Most Important Signal

If the question says:

> Find the maximum sum of a **contiguous subarray**.

Think:

```text
Kadane
```

Example:

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

The maximum-sum subarray is:

```text
[4, -1, 2, 1]
```

Sum:

```text
4 + (-1) + 2 + 1 = 6
```

Answer:

```text
6
```

---

# 🧠 What Does "Contiguous" Mean?

Contiguous means the elements must be next to each other.

For:

```text
[1, 2, 3, 4]
```

Valid subarrays:

```text
[1, 2]
[2, 3]
[3, 4]
[1, 2, 3]
[2, 3, 4]
```

But:

```text
[1, 3]
```

is NOT contiguous.

So:

```text
CONTIGUOUS SUBARRAY
        +
MAXIMUM SUM
        ↓
     KADANE
```

---

# 🔥 The Core Idea

At every element, we ask:

```text
Should I continue the previous subarray?

OR

Should I start a new subarray from here?
```

Mathematically:

```text
currentSum =
max(
    nums[i],
    currentSum + nums[i]
)
```

Then:

```text
maxSum =
max(
    maxSum,
    currentSum
)
```

---

# 🎯 Two Variables

Kadane usually uses two variables:

```java
int currentSum;
int maxSum;
```

### `currentSum`

Represents:

> Maximum sum of a subarray **ending at the current position**.

### `maxSum`

Represents:

> Maximum sum found **anywhere so far**.

This distinction is extremely important.

---

# 💻 Basic Kadane Template

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

# 🧪 Complete Dry Run

Consider:

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

We start:

```text
currentSum = -2
maxSum = -2
```

Now process each element.

---

## Step 1 — `1`

We compare:

```text
currentSum + 1
= -2 + 1
= -1
```

vs:

```text
1
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

Why?

Because continuing:

```text
[-2, 1]
```

gives `-1`.

Starting fresh:

```text
[1]
```

gives `1`.

So we start a new subarray.

---

## Step 2 — `-3`

Compare:

```text
1 + (-3) = -2
```

vs:

```text
-3
```

Choose:

```text
-2
```

So:

```text
currentSum = -2
maxSum = 1
```

---

## Step 3 — `4`

Compare:

```text
-2 + 4 = 2
```

vs:

```text
4
```

Choose:

```text
4
```

So:

```text
currentSum = 4
maxSum = 4
```

Start fresh again.

---

## Step 4 — `-1`

```text
4 + (-1) = 3
```

Compare:

```text
3 vs -1
```

Choose:

```text
3
```

Now:

```text
currentSum = 3
maxSum = 4
```

---

## Step 5 — `2`

```text
3 + 2 = 5
```

Choose:

```text
5
```

Now:

```text
currentSum = 5
maxSum = 5
```

---

## Step 6 — `1`

```text
5 + 1 = 6
```

Now:

```text
currentSum = 6
maxSum = 6
```

Our current subarray is:

```text
[4, -1, 2, 1]
```

Sum:

```text
6
```

---

## Step 7 — `-5`

```text
6 + (-5) = 1
```

So:

```text
currentSum = 1
maxSum = 6
```

The maximum remains `6`.

---

## Step 8 — `4`

```text
1 + 4 = 5
```

So:

```text
currentSum = 5
maxSum = 6
```

Final answer:

```text
6
```

---

# 📊 Dry Run Table

For:

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

| Element | Current Sum | Max Sum |
| ------: | ----------: | ------: |
|      -2 |          -2 |      -2 |
|       1 |           1 |       1 |
|      -3 |          -2 |       1 |
|       4 |           4 |       4 |
|      -1 |           3 |       4 |
|       2 |           5 |       5 |
|       1 |           6 |       6 |
|      -5 |           1 |       6 |
|       4 |           5 |       6 |

Final:

```text
Maximum Subarray Sum = 6
```

---

# 🧠 Why Do We Drop a Negative Sum?

This is the most important intuition behind Kadane.

Suppose:

```text
currentSum = -5
```

and next element is:

```text
10
```

Two choices:

```text
-5 + 10 = 5
```

or:

```text
10
```

Obviously:

```text
10 > 5
```

So carrying the previous negative sum only hurts us.

Therefore:

```text
Negative contribution
        ↓
Drop it
        ↓
Start fresh
```

This is why:

```java
Math.max(nums[i], currentSum + nums[i])
```

works.

---

# 🔥 Easy Real-World Example

Imagine your daily profit/loss:

```text
[-10, +5, -2, +8, -1]
```

If your previous running business performance is:

```text
-10
```

and today gives:

```text
+5
```

You have two choices:

```text
Continue previous bad period:
-10 + 5 = -5

Start from today:
5
```

Obviously, starting from today is better.

Kadane does exactly this automatically.

---

# ⚠️ Important — All Negative Numbers

Consider:

```text
[-5, -2, -8, -1]
```

The answer should be:

```text
-1
```

because the maximum subarray is:

```text
[-1]
```

That's why we should initialize with:

```java
int currentSum = nums[0];
int maxSum = nums[0];
```

NOT:

```java
int maxSum = 0;
```

If we use `0`, we would incorrectly return `0`.

---

# 🧪 All Negative Dry Run

```text
[-5, -2, -8, -1]
```

Start:

```text
currentSum = -5
maxSum = -5
```

### `-2`

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

### `-8`

```text
max(-8, -2 + -8)
= max(-8, -10)
= -8
```

### `-1`

```text
max(-1, -8 + -1)
= max(-1, -9)
= -1
```

Final:

```text
-1
```

Correct.

---

# 🎯 How to Identify Kadane From Different Question Forms

The question may not directly say "maximum subarray sum."

### Question 1

> Find the largest sum of a contiguous subarray.

Think:

```text
KADANE
```

---

### Question 2

> Find the maximum sum of consecutive elements.

Think:

```text
KADANE
```

---

### Question 3

> Find the best continuous segment with maximum total.

Think:

```text
KADANE
```

---

### Question 4

> Find the maximum possible profit from a contiguous period.

Potentially:

```text
KADANE
```

if the problem reduces to maximum contiguous sum.

---

### Question 5

> Find the maximum sum subarray.

Immediately:

```text
KADANE
```

---

# 🧠 The Main Recognition Rule

```text
ARRAY
  +
CONTIGUOUS / CONSECUTIVE
  +
MAXIMUM SUM
        ↓
     KADANE
```

This is the most important thing to remember.

---

# 🔄 Kadane vs Sliding Window

These can sometimes look similar.

### Kadane

Used when:

```text
Maximum sum
+
Contiguous subarray
```

Example:

```text
[-2,1,-3,4,-1,2,1,-5,4]
```

We don't know the window size.

Kadane decides dynamically whether to:

```text
CONTINUE
or
RESTART
```

---

### Sliding Window

Usually has a window controlled by:

```text
K
or
a condition
```

Examples:

```text
Maximum sum of K elements
Longest substring
Minimum size subarray
At most K distinct
```

---

# 🔥 Important Difference

```text
MAXIMUM CONTIGUOUS SUM
        ↓
     KADANE
```

```text
FIXED K / WINDOW CONDITION
        ↓
SLIDING WINDOW
```

---

# 🎯 Kadane Pattern

The entire algorithm can be remembered as:

```text
              CURRENT ELEMENT
                    │
                    ↓
        ┌───────────┴───────────┐
        ↓                       ↓
Start new             Continue previous
subarray                  subarray
        │                       │
        ↓                       ↓
      nums[i]            currentSum + nums[i]
        │                       │
        └───────────┬───────────┘
                    ↓
                  MAX
                    ↓
             currentSum
                    │
                    ↓
             Update maxSum
```

---

# 💻 Simplest Kadane Code

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

# 🧠 One-Line Explanation of Each Line

```java
int currentSum = nums[0];
```

Start with the first element.

```java
currentSum = Math.max(nums[i], currentSum + nums[i]);
```

Ask:

> Start new or continue old?

```java
maxSum = Math.max(maxSum, currentSum);
```

Ask:

> Is this the best sum I've seen?

---

# 🚀 Finding the Actual Subarray

Sometimes the question asks for the **maximum sum**, but sometimes you may want the actual subarray.

Example:

```text
[-2,1,-3,4,-1,2,1,-5,4]
```

Answer:

```text
[4,-1,2,1]
```

We can maintain:

```java
int start = 0;
int end = 0;
int tempStart = 0;
```

When starting a new subarray:

```java
tempStart = i;
```

When finding a new maximum:

```java
start = tempStart;
end = i;
```

Concept:

```text
currentSum
     ↓
new start?
     ↓
remember starting index
     ↓
new max?
     ↓
save start and end
```

---

# 💻 Kadane With Start and End Index

```java
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
```

For:

```text
[-2,1,-3,4,-1,2,1,-5,4]
```

we get:

```text
start = 3
end = 6
```

So:

```text
nums[3...6]
```

is:

```text
[4,-1,2,1]
```

---

# 🔥 Kadane Variations

Once you understand basic Kadane, you will see variations.

## 1. Maximum Subarray Sum

```text
Maximum contiguous sum
        ↓
Basic Kadane
```

---

## 2. Maximum Subarray With Indices

```text
Maximum sum
+
Need actual subarray
        ↓
Kadane + start/end indexes
```

---

## 3. Maximum Circular Subarray

Example:

```text
[5,-3,5]
```

Sometimes the maximum subarray wraps around the end.

Concept:

```text
Maximum Circular Sum
=
max(
    normal Kadane,
    totalSum - minimumSubarraySum
)
```

This uses:

```text
Maximum Kadane
+
Minimum Kadane
```

---

# ⚠️ Circular Array Important Case

For:

```text
[-3,-2,-5]
```

If all numbers are negative:

```text
totalSum - minSum
```

can become incorrect.

So handle:

```text
if maxSum < 0
```

and return the largest element.

---

# 🧩 Important LeetCode Problems

### 🟢 Basic

* **53. Maximum Subarray** → Basic Kadane
* **121. Best Time to Buy and Sell Stock** → Related running-best idea

### 🟡 Intermediate

* **918. Maximum Sum Circular Subarray** → Kadane + Minimum Kadane
* **152. Maximum Product Subarray** → Similar idea but requires tracking max and min
* **1191. K-Concatenation Maximum Sum** → Kadane variation

### 🔴 Advanced

* Maximum subarray variants with:

  * circular arrays
  * multiple concatenations
  * constraints
  * modified sums

---

# 🗺️ Pattern Recognition Cheat Sheet

| Question asks                      | Think                           |
| ---------------------------------- | ------------------------------- |
| Maximum subarray sum               | Kadane                          |
| Largest contiguous sum             | Kadane                          |
| Maximum consecutive sum            | Kadane                          |
| Best continuous segment            | Kadane                          |
| Maximum sum of contiguous elements | Kadane                          |
| Maximum circular subarray          | Kadane + Min Kadane             |
| Need actual maximum subarray       | Kadane + indices                |
| Maximum product subarray           | Kadane-style + max/min tracking |

---

# 🚨 Common Mistakes

### Mistake 1 — Initializing with `0`

Wrong:

```java
int maxSum = 0;
```

This fails for:

```text
[-5,-2,-8]
```

Correct:

```java
int maxSum = nums[0];
```

---

### Mistake 2 — Confusing subarray with subsequence

Kadane works for:

```text
CONTIGUOUS SUBARRAY
```

Not arbitrary subsequences.

---

### Mistake 3 — Forgetting the restart decision

The heart of Kadane is:

```java
Math.max(
    nums[i],
    currentSum + nums[i]
)
```

Always ask:

```text
Continue?
OR
Restart?
```

---

# 🔑 One-Line Memory Trick

```text
CURRENT SUM
    ↓
Is previous sum helping?
    │
 ┌──┴──┐
 ↓     ↓
YES    NO
 ↓     ↓
KEEP   RESTART
```

Or simply:

```text
currentSum = max(current element,
                 currentSum + current element)
```

---

# 🏆 Final Mental Framework

Whenever you see:

```text
              ARRAY
                │
                ↓
        CONTIGUOUS / CONSECUTIVE?
                │
               YES
                ↓
          MAXIMUM SUM?
                │
               YES
                ↓
             KADANE
                │
                ↓
       ┌────────┴────────┐
       ↓                 ↓
   Continue            Restart
       │                 │
       ↓                 ↓
 currentSum + x          x
       │                 │
       └────────┬────────┘
                ↓
               MAX
                ↓
          currentSum
                │
                ↓
        update maxSum
```

# 🔥 Final Memory Rules

```text
CONTIGUOUS + MAXIMUM SUM
        ↓
      KADANE
```

```text
currentSum + nums[i]
        VS
     nums[i]
        ↓
      MAX
```

```text
currentSum
→ best sum ending HERE
```

```text
maxSum
→ best sum found ANYWHERE
```

```text
Negative running contribution
        ↓
     DROP IT
        ↓
     START NEW
```

### The 4 Questions to Ask

Whenever you see a new problem:

```text
1. Is it CONTIGUOUS?

2. Are we looking for MAXIMUM SUM?

3. Can I decide between CONTINUE or RESTART?

4. Do I only need the best sum ending at the current position?
```

If the answer is **yes**, think:

```text
🔥 KADANE'S ALGORITHM
```

**The goal is not to memorize Kadane's code. Understand the decision: `CONTINUE` the previous subarray or `START NEW`.**
