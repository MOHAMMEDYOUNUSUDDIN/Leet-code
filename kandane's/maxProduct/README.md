# 🔥 Maximum Product Subarray

## 🧩 Problem

Given an integer array `nums`, find the **contiguous subarray** that has the largest product and return that product.

### Example

```text
Input:
nums = [2,3,-2,4]

Output:
6
```

The subarray with the maximum product is:

```text
[2,3]
```

Product:

```text
2 × 3 = 6
```

---

# 💡 Core Idea

This problem looks similar to **Maximum Subarray**, but there is one major difference:

> With multiplication, a negative number can completely change the situation.

For example:

```text
minimum = -10
current = -2
```

Multiplying them:

```text
-10 × -2 = 20
```

The **minimum** became the **maximum**.

That's why we need to maintain **two values**:

```text
maxend → maximum product ending at current index

minend → minimum product ending at current index
```

---

# 🧠 Why Do We Need `minend`?

Suppose:

```text
nums = [-2, 3, -4]
```

At `3`:

```text
maxend = 3
minend = -6
```

Now we encounter `-4`.

The maximum could come from:

```text
maxend × -4
= 3 × -4
= -12
```

But the minimum could become:

```text
minend × -4
= -6 × -4
= 24
```

So:

```text
minend × negative = potentially maximum
```

This is the most important idea in this problem.

---

# 🔑 Three Possibilities

For every new number `nums[i]`, there are three possibilities.

### 1. Start a new subarray

```java
v1 = nums[i];
```

### 2. Extend the previous maximum product

```java
v2 = maxend * nums[i];
```

### 3. Extend the previous minimum product

```java
v3 = minend * nums[i];
```

Why do we consider `minend`?

Because:

```text
negative × negative = positive
```

---

# 💻 Code

```java
class Solution {
    public int maxProduct(int[] nums) {

        int minend = nums[0];
        int maxend = nums[0];
        int res = nums[0];

        for(int i = 1; i < nums.length; i++){

            int v1 = nums[i];
            int v2 = maxend * nums[i];
            int v3 = minend * nums[i];

            maxend = Math.max(v1, Math.max(v2, v3));

            minend = Math.min(v1, Math.min(v2, v3));

            res = Math.max(res, Math.max(minend, maxend));
        }

        return res;
    }
}
```

---

# 🧠 Understanding the Variables

## `maxend`

```java
int maxend = nums[0];
```

Means:

> Maximum product of a subarray that ends at the current index.

---

## `minend`

```java
int minend = nums[0];
```

Means:

> Minimum product of a subarray that ends at the current index.

This may look unnecessary, but it is extremely important because a future negative number can turn this minimum into a large positive number.

---

## `res`

```java
int res = nums[0];
```

Stores:

> Maximum product found anywhere in the array so far.

---

# 🧪 Complete Dry Run

Consider:

```text
nums = [2, 3, -2, 4]
```

Initially:

```text
maxend = 2
minend = 2
res = 2
```

---

## 🔹 Index 1

Current number:

```text
3
```

Calculate the three possibilities:

```text
v1 = 3

v2 = maxend × 3
   = 2 × 3
   = 6

v3 = minend × 3
   = 2 × 3
   = 6
```

Now:

```text
maxend = max(3, 6, 6)
       = 6
```

```text
minend = min(3, 6, 6)
       = 3
```

Update result:

```text
res = max(2, 6, 3)
    = 6
```

Current state:

```text
maxend = 6
minend = 3
res = 6
```

---

## 🔹 Index 2

Current:

```text
-2
```

Three possibilities:

```text
v1 = -2

v2 = maxend × -2
   = 6 × -2
   = -12

v3 = minend × -2
   = 3 × -2
   = -6
```

Therefore:

```text
maxend = max(-2, -12, -6)
       = -2
```

And:

```text
minend = min(-2, -12, -6)
       = -12
```

Result:

```text
res = max(6, -2, -12)
    = 6
```

Current state:

```text
maxend = -2
minend = -12
res = 6
```

---

## 🔹 Index 3

Current:

```text
4
```

Three possibilities:

```text
v1 = 4

v2 = maxend × 4
   = -2 × 4
   = -8

v3 = minend × 4
   = -12 × 4
   = -48
```

Therefore:

```text
maxend = max(4, -8, -48)
       = 4
```

```text
minend = min(4, -8, -48)
       = -48
```

Result:

```text
res = max(6, 4, -48)
    = 6
```

Final answer:

```text
6
```

---

# 📊 Dry Run Table

For:

```text
[2, 3, -2, 4]
```

| i | nums[i] | v1 | v2 = maxend × nums[i] | v3 = minend × nums[i] | maxend | minend | res |
| - | ------: | -: | --------------------: | --------------------: | -----: | -----: | --: |
| 0 |       2 |  — |                     — |                     — |      2 |      2 |   2 |
| 1 |       3 |  3 |                     6 |                     6 |      6 |      3 |   6 |
| 2 |      -2 | -2 |                   -12 |                    -6 |     -2 |    -12 |   6 |
| 3 |       4 |  4 |                    -8 |                   -48 |      4 |    -48 |   6 |

Final:

```text
Answer = 6
```

---

# 🔥 Important Dry Run: Where `minend` Becomes Important

Consider:

```text
nums = [-2, 3, -4]
```

Initially:

```text
maxend = -2
minend = -2
res = -2
```

### At `3`

```text
v1 = 3
v2 = -2 × 3 = -6
v3 = -2 × 3 = -6
```

Therefore:

```text
maxend = 3
minend = -6
res = 3
```

Now comes `-4`.

```text
v1 = -4

v2 = 3 × -4
   = -12

v3 = -6 × -4
   = 24
```

Look carefully:

```text
minend × current
= -6 × -4
= 24
```

So:

```text
maxend = 24
```

Answer:

```text
24
```

The maximum-product subarray is:

```text
[-2, 3, -4]
```

because:

```text
(-2) × 3 × (-4) = 24
```

---

# ⚠️ The Most Important Rule

With **Maximum Subarray**, we only need:

```text
maximum
```

But with **Maximum Product Subarray**, we need:

```text
maximum + minimum
```

Because:

```text
negative × negative = positive
```

Therefore:

```text
minend
```

can suddenly become the source of the maximum answer.

---

# 🧠 What Happens With a Negative Number?

Suppose:

```text
maxend = 10
minend = -20
nums[i] = -2
```

Then:

```text
maxend × -2 = -20
```

but:

```text
minend × -2 = 40
```

So the previous minimum becomes the new maximum.

That's why we calculate:

```java
int v2 = maxend * nums[i];
int v3 = minend * nums[i];
```

before updating either value.

---

# ⚠️ Why Calculate `v1`, `v2`, `v3` First?

This part is important:

```java
int v1 = nums[i];
int v2 = maxend * nums[i];
int v3 = minend * nums[i];
```

Then:

```java
maxend = Math.max(v1, Math.max(v2, v3));
minend = Math.min(v1, Math.min(v2, v3));
```

We calculate all three using the **old** `maxend` and `minend`.

If we changed `maxend` first and then used the new value to calculate `minend`, the calculation would be incorrect.

---

# 🧪 Zero Example

Consider:

```text
nums = [-2, 0, -1]
```

At `0`:

```text
v1 = 0
v2 = -2 × 0 = 0
v3 = -2 × 0 = 0
```

Therefore:

```text
maxend = 0
minend = 0
```

Zero effectively gives us a fresh starting point for the next subarray.

Then at `-1`:

```text
v1 = -1
v2 = 0 × -1 = 0
v3 = 0 × -1 = 0
```

So:

```text
maxend = 0
```

Overall answer remains:

```text
0
```

---

# 🧠 Pattern Recognition

When the question says:

> Find the maximum product of a contiguous subarray.

Think:

```text
Maximum Product Subarray
        ↓
Track MAX + MIN
        ↓
Because negative × negative = positive
```

The main formula is:

```java
maxend = max(nums[i], maxend * nums[i], minend * nums[i]);

minend = min(nums[i], maxend_old * nums[i], minend_old * nums[i]);
```

Your `v1`, `v2`, `v3` implementation makes this easier to understand.

---

# 🔄 Maximum Subarray vs Maximum Product Subarray

| Maximum Subarray           | Maximum Product Subarray              |
| -------------------------- | ------------------------------------- |
| Uses addition              | Uses multiplication                   |
| Track maximum              | Track maximum + minimum               |
| `current + nums[i]`        | `maxend * nums[i]`                    |
| Negative value reduces sum | Negative value can reverse the result |
| Kadane's Algorithm         | Modified Kadane's Algorithm           |

### Maximum Sum

```java
bestending = Math.max(nums[i],
                      bestending + nums[i]);
```

### Maximum Product

```java
maxend = Math.max(v1, Math.max(v2, v3));
minend = Math.min(v1, Math.min(v2, v3));
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We visit every element exactly once.

### Space Complexity

```text
O(1)
```

Only three variables are maintained:

```text
maxend
minend
res
```

---

# 🎯 Key Takeaways

### 1. `maxend`

Maximum product ending at current index.

### 2. `minend`

Minimum product ending at current index.

### 3. `res`

Maximum product found anywhere.

### 4. At every element, consider three choices:

```text
current element
maximum × current
minimum × current
```

### 5. Why minimum?

Because:

```text
negative × negative = positive
```

### 6. Zero

Zero can reset the product chain.

### 7. Negative numbers

Negative numbers are the main reason we need both `maxend` and `minend`.

---

# 🚀 One-Line Mental Model

> **"For every number, keep both the biggest and smallest product ending here, because the smallest negative product might become the biggest positive product when multiplied by another negative number."**

That's the key idea behind **Maximum Product Subarray**.
