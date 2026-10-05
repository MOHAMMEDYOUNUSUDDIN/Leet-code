# LeetCode 974 — Subarray Sums Divisible by K

## 📝 Problem

Given an integer array `nums` and an integer `k`, return the number of **non-empty subarrays** whose sum is divisible by `k`.

### Example

```text
Input:
nums = [4,5,0,-2,-3,1]
k = 5

Output:
7
```

There are 7 subarrays whose sum is divisible by `5`.

---

# 💡 Main Concept

This problem uses:

* Prefix Sum
* Remainder
* HashMap

### Key Idea

If two prefix sums have the **same remainder when divided by `k`**, the subarray between them is divisible by `k`.

For example:

```text
Prefix Sum 1 = 4
Prefix Sum 2 = 9

4 % 5 = 4
9 % 5 = 4
```

Since the remainders are the same:

```text
9 - 4 = 5
```

And `5` is divisible by `5`.

Therefore, the subarray between those two prefix sums is valid.

---

# 🚀 Approach

We maintain a HashMap:

```text
remainder → frequency
```

It stores how many times each remainder has appeared.

Initially:

```java
prefixSum.put(0, 1);
```

Why?

Because before processing any element, the prefix sum is `0`.

If the current prefix sum itself is divisible by `k`, its remainder is `0`, and we need to count that subarray.

---

## Step 1: Calculate Prefix Sum

```java
sum += nums[i];
```

This keeps the sum of elements from the beginning up to the current index.

---

## Step 2: Calculate Remainder

```java
int rem = ((sum % k) + k) % k;
```

We calculate the remainder of the prefix sum.

The extra `+ k` handles negative numbers.

For example:

```text
-2 % 5 = -2
```

But we want the remainder in the range:

```text
0 to k-1
```

So:

```text
((-2 % 5) + 5) % 5
= (-2 + 5) % 5
= 3
```

---

## Step 3: Check Previous Same Remainder

```java
if (prefixSum.containsKey(rem)) {
    count += prefixSum.get(rem);
}
```

If the same remainder appeared before, every previous occurrence creates a valid subarray.

---

## Step 4: Store the Remainder

```java
prefixSum.put(rem, prefixSum.getOrDefault(rem, 0) + 1);
```

Increase the frequency of the current remainder.

---

# ❌ Why Don't We Use This Approach?

Your initial approach was:

```java
sum += nums[i];

if (sum % k == 0) {
    count++;
}

int diff = sum - k;

if (prefixSum.containsKey(diff)) {
    count += prefixSum.get(diff);
}
```

And you were storing:

```java
prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
```

### Problem 1: We need the same remainder, not `sum - k`

Suppose:

```text
k = 5

prefix sum = 14
previous prefix sum = 4
```

Then:

```text
14 - 4 = 10
```

`10` is divisible by `5`.

But:

```text
sum - k = 14 - 5 = 9
```

We don't need to search for `9`.

The important thing is:

```text
14 % 5 = 4
4 % 5 = 4
```

The remainders are equal.

Therefore, we should store **remainders**, not complete prefix sums.

---

### Problem 2: There can be many previous prefix sums with the same remainder

Example:

```text
k = 5
```

Suppose the prefix sum remainders are:

```text
4, 4, 4
```

When another `4` appears, it creates **3 different valid subarrays**.

A HashMap of remainders allows us to directly know:

```text
remainder 4 → appeared 3 times
```

So:

```java
count += 3;
```

---

### Problem 3: `sum % k == 0` is already handled by the HashMap

You don't need:

```java
if (sum % k == 0) {
    count++;
}
```

because we initially put:

```java
prefixSum.put(0, 1);
```

Suppose:

```text
sum = 5
k = 5
```

Then:

```text
rem = 0
```

HashMap already contains:

```text
0 → 1
```

So:

```java
count += prefixSum.get(0);
```

automatically counts the subarray.

Therefore, this separate condition is unnecessary.

---

# ✅ Java Code

```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {

        int sum = 0;
        int count = 0;

        Map<Integer, Integer> prefixSum = new HashMap<>();

        // Remainder 0 exists before starting
        prefixSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            // Handle negative remainders
            int rem = ((sum % k) + k) % k;

            // Same remainder means the subarray is divisible by k
            if (prefixSum.containsKey(rem)) {
                count += prefixSum.get(rem);
            }

            // Store/update frequency of remainder
            prefixSum.put(rem, prefixSum.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}
```

---

# 🔍 Dry Run

### Input

```text
nums = [4, 5, 0, -2, -3, 1]
k = 5
```

Initially:

```text
sum = 0
count = 0

HashMap:
{0=1}
```

---

### i = 0

```text
num = 4

sum = 0 + 4 = 4

rem = 4 % 5 = 4
```

HashMap contains `4`?

```text
No
```

Count:

```text
0
```

Store:

```text
{0=1, 4=1}
```

---

### i = 1

```text
num = 5

sum = 4 + 5 = 9

rem = 9 % 5 = 4
```

HashMap contains `4`?

```text
Yes → frequency = 1
```

So:

```text
count = 0 + 1 = 1
```

Store:

```text
{0=1, 4=2}
```

The valid subarray is:

```text
[5]
```

because:

```text
5 % 5 = 0
```

---

### i = 2

```text
num = 0

sum = 9 + 0 = 9

rem = 4
```

HashMap:

```text
4 → 2
```

So:

```text
count = 1 + 2 = 3
```

HashMap becomes:

```text
{0=1, 4=3}
```

---

### i = 3

```text
num = -2

sum = 9 - 2 = 7

rem = 7 % 5 = 2
```

`2` is not present.

```text
count = 3
```

Store:

```text
{0=1, 4=3, 2=1}
```

---

### i = 4

```text
num = -3

sum = 7 - 3 = 4

rem = 4
```

HashMap:

```text
4 → 3
```

So:

```text
count = 3 + 3 = 6
```

Store:

```text
{0=1, 4=4, 2=1}
```

---

### i = 5

```text
num = 1

sum = 4 + 1 = 5

rem = 5 % 5 = 0
```

HashMap:

```text
0 → 1
```

So:

```text
count = 6 + 1 = 7
```

Update:

```text
{0=2, 4=4, 2=1}
```

---

# 🎯 Final Answer

```text
count = 7
```

Therefore:

```text
Output = 7
```

# DryRun Table

| i | num | sum | remainder | count added | total count | HashMap           |
| - | --: | --: | --------: | ----------: | ----------: | ----------------- |
| 0 |   4 |   4 |         4 |           0 |           0 | `{0=1, 4=1}`      |
| 1 |   5 |   9 |         4 |           1 |           1 | `{0=1, 4=2}`      |
| 2 |   0 |   9 |         4 |           2 |           3 | `{0=1, 4=3}`      |
| 3 |  -2 |   7 |         2 |           0 |           3 | `{0=1, 4=3, 2=1}` |
| 4 |  -3 |   4 |         4 |           3 |           6 | `{0=1, 4=4, 2=1}` |
| 5 |   1 |   5 |         0 |           1 |       **7** | `{0=2, 4=4, 2=1}` |






---

# 🧠 Pattern Recognition

Whenever you see:

* "Number of subarrays"
* "Sum divisible by K"
* "Sum is a multiple of K"
* "Subarray sum % K == 0"

Think:

```text
Prefix Sum
     ↓
Remainder
     ↓
HashMap
     ↓
Count previous same remainders
```

### Formula

If:

```text
prefixSum[j] % k == prefixSum[i] % k
```

then:

```text
(prefixSum[j] - prefixSum[i]) % k == 0
```

Therefore, the subarray between them is divisible by `k`.

---

# ⏱️ Complexity

### Time

```text
O(n)
```

We traverse the array once.

### Space

```text
O(k)
```

At most `k` different remainders (`0` to `k-1`) are stored.

---

# 🔑 Remember

> **Same remainder = divisible subarray**

Don't search for:

```text
sum - k
```

Instead, search for:

```text
same remainder
```

And initialize:

```java
prefixSum.put(0, 1);
```

This Single initialization allows the algorithm to automatically handle subarrays whose sum itself is divisible by `k`.
