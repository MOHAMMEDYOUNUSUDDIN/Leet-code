# LeetCode 560 — Subarray Sum Equals K

## 🧠 Pattern Used

**Prefix Sum + HashMap**

This pattern is useful when a question asks:

> **"How many subarrays have a sum equal to K?"**

Instead of checking every possible subarray, we use **Prefix Sum + HashMap** to solve it efficiently.

---

## 🔹 Problem

Given an integer array `nums` and an integer `k`, return the **total number of continuous subarrays whose sum is equal to `k`**.

### Example

```text
nums = [1, 2, 3]
k = 3
```

Subarrays with sum `3`:

```text
[1, 2]
[3]
```

Answer:

```text
2
```

---

# 🔹 Main Idea

The important formula is:

```text
currentPrefixSum - previousPrefixSum = k
```

Rearrange it:

```text
previousPrefixSum = currentPrefixSum - k
```

So while traversing the array:

1. Calculate the current prefix sum.
2. Calculate:

```text
diff = sum - k
```

3. Check whether `diff` already exists in the HashMap.
4. If it exists, its frequency tells us how many subarrays ending at the current index have sum `k`.
5. Store the current prefix sum in the HashMap.

---

# 🔹 Why HashMap?

The HashMap stores:

```text
prefixSum → frequency
```

For example:

```text
map = {
    0 → 1,
    3 → 2,
    5 → 1
}
```

This means:

```text
prefix sum 0 occurred 1 time
prefix sum 3 occurred 2 times
prefix sum 5 occurred 1 time
```

We store the **frequency**, not just whether the prefix sum exists.

Why?

Because the same prefix sum can occur multiple times, and each occurrence can create a different valid subarray.

---

# 🔹 Code

```java
class Solution {
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> prefixSum = new HashMap<>();

        int sum = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            if (sum == k) {
                count++;
            }

            int diff = sum - k;

            if (prefixSum.containsKey(diff)) {
                count += prefixSum.get(diff);
            }

            prefixSum.put(
                sum,
                prefixSum.getOrDefault(sum, 0) + 1
            );
        }

        return count;
    }
}
```

---

# 🔹 Understanding the Code Step-by-Step

### 1. Create HashMap

```java
Map<Integer,Integer> prefixSum = new HashMap<>();
```

It stores:

```text
prefix sum → frequency
```

---

### 2. Initialize variables

```java
int sum = 0;
int count = 0;
```

`sum` → current prefix sum

`count` → number of valid subarrays found

---

### 3. Traverse the array

```java
for(int i = 0; i < nums.length; i++)
```

We process the array from left to right.

---

### 4. Calculate Prefix Sum

```java
sum += nums[i];
```

Example:

```text
nums = [1, 2, 3]
```

Prefix sums become:

```text
1
3
6
```

---

### 5. Check `sum == k`

```java
if(sum == k){
    count++;
}
```

Suppose:

```text
k = 3
```

and current sum is:

```text
sum = 3
```

Then the subarray starting from index `0` has sum `3`.

So:

```text
count++
```

---

# 🔹 The Most Important Part

```java
int diff = sum - k;
```

Suppose:

```text
sum = 6
k = 3
```

Then:

```text
diff = 6 - 3
     = 3
```

Now we ask:

> Have we seen prefix sum `3` before?

If yes, then the elements between that previous prefix and the current index have sum `3`.

---

# 🔹 Why Does This Work?

Suppose:

```text
previousPrefixSum = 3
currentPrefixSum = 6
```

Then:

```text
currentPrefixSum - previousPrefixSum
= 6 - 3
= 3
```

Therefore, the subarray between those two positions has sum `3`.

In general:

```text
currentSum - previousSum = k
```

Therefore:

```text
previousSum = currentSum - k
```

That's exactly why we search for:

```java
sum - k
```

---

# 🔹 Dry Run

Let's take:

```text
nums = [1, 2, 3]
k = 3
```

Initially:

```text
sum = 0
count = 0
map = {}
```

---

## i = 0

```text
nums[0] = 1
```

Calculate:

```text
sum = 0 + 1
    = 1
```

Check:

```text
sum == k?
1 == 3 → No
```

Calculate:

```text
diff = sum - k
     = 1 - 3
     = -2
```

`-2` is not in the map.

Store:

```text
map = {
    1 → 1
}
```

---

## i = 1

```text
nums[1] = 2
```

Calculate:

```text
sum = 1 + 2
    = 3
```

Check:

```text
sum == k
3 == 3 → Yes
```

So:

```text
count = 1
```

Calculate:

```text
diff = 3 - 3
     = 0
```

`0` is not currently in the map.

Store:

```text
map = {
    1 → 1,
    3 → 1
}
```

---

## i = 2

```text
nums[2] = 3
```

Calculate:

```text
sum = 3 + 3
    = 6
```

Check:

```text
6 == 3 → No
```

Calculate:

```text
diff = 6 - 3
     = 3
```

Now:

```text
map contains 3
```

and:

```text
map.get(3) = 1
```

Therefore:

```text
count += 1
```

So:

```text
count = 2
```

Store current prefix sum:

```text
map = {
    1 → 1,
    3 → 1,
    6 → 1
}
```

Final answer:

```text
2
```

The two subarrays are:

```text
[1, 2]
[3]
```

---

# 🔥 Important Edge Case

A very important case is:

```text
nums = [1, 1, 1]
k = 2
```

The answer is:

```text
2
```

because:

```text
[1,1] → index 0-1
[1,1] → index 1-2
```

The HashMap frequency is important because the same prefix sum can occur multiple times.

---

# 🔹 Why `getOrDefault()`?

This line:

```java
prefixSum.put(
    sum,
    prefixSum.getOrDefault(sum, 0) + 1
);
```

means:

> If `sum` already exists, increase its frequency by 1. Otherwise, start its frequency at 1.

For example:

```text
map = {
    1 → 1
}
```

If we see prefix sum `1` again:

```text
1 → 2
```

So:

```text
map = {
    1 → 2
}
```

---

# 🔹 Why Do We Need Frequency?

Consider:

```text
nums = [0, 0, 0]
k = 0
```

There are multiple subarrays whose sum is `0`.

The prefix sum:

```text
0
```

appears multiple times.

Therefore, the map needs to remember:

```text
0 → 1
0 → 2
0 → 3
```

rather than simply storing:

```text
0 → true
```

The frequency tells us **how many previous positions can form a valid subarray**.

---

# 🔹 Important Initialization

You may see another version of this solution where the HashMap is initialized as:

```java
prefixSum.put(0, 1);
```

Then the code doesn't need:

```java
if(sum == k){
    count++;
}
```

because the initial prefix sum `0` already represents the empty prefix.

The alternative implementation is:

```java
class Solution {
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> prefixSum = new HashMap<>();

        prefixSum.put(0, 1);

        int sum = 0;
        int count = 0;

        for (int num : nums) {

            sum += num;

            int diff = sum - k;

            if (prefixSum.containsKey(diff)) {
                count += prefixSum.get(diff);
            }

            prefixSum.put(
                sum,
                prefixSum.getOrDefault(sum, 0) + 1
            );
        }

        return count;
    }
}
```

Both approaches work.

The second version is often easier to remember:

```text
1. Put 0 → 1
2. Calculate prefix sum
3. Find sum - k
4. Add its frequency
5. Store current sum
```

---

# 🔹 Pattern Recognition

When you see a problem asking:

```text
Count subarrays
+
Subarray sum equals K
```

Think:

```text
Prefix Sum + HashMap
```

The key formula is:

```text
previousSum = currentSum - k
```

And the HashMap stores:

```text
prefixSum → frequency
```

---

# 🔹 Brute Force vs Optimized

### Brute Force

Try every possible subarray.

```text
Time: O(n²)
Space: O(1)
```

### Prefix Sum + HashMap

Traverse the array once.

```text
Time: O(n)
Space: O(n)
```

So for a large array, the HashMap approach is much more efficient.

---

# 🔹 Complexity

### Time Complexity

```text
O(n)
```

We traverse the array once.

HashMap operations such as:

```text
containsKey()
get()
put()
```

are `O(1)` on average.

### Space Complexity

```text
O(n)
```

In the worst case, the HashMap can contain `n` different prefix sums.

---

# 🧠 Remember This

Don't try to memorize the whole code.

Remember these 4 steps:

```text
1. Calculate prefix sum
2. Find sum - k
3. Add its frequency to count
4. Store current prefix sum
```

The core idea:

```text
currentSum - previousSum = k
```

Therefore:

```text
previousSum = currentSum - k
```

That's the entire logic behind **Subarray Sum Equals K**.

---

## 🎯 Pattern

```text
Subarray
    ↓
Need to count
    ↓
Sum = K
    ↓
Prefix Sum
    ↓
Need previousSum
    ↓
currentSum - K
    ↓
HashMap
```

### Final Pattern:

**Prefix Sum + HashMap + Frequency**
