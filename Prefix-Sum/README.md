📊 Prefix Sum — Pattern Recognition & Complete Guide

🧠 What Is Prefix Sum?

Prefix Sum is a technique used to calculate cumulative sums so that range-sum and subarray problems can be solved efficiently.

Instead of repeatedly adding elements, we store the sum of previous elements and reuse it.

Basic Idea

nums = [2, 4, 1, 3, 5]

prefix = [2, 6, 7, 10, 15]

Explanation:

prefix[0] = 2
prefix[1] = 2 + 4 = 6
prefix[2] = 2 + 4 + 1 = 7
prefix[3] = 2 + 4 + 1 + 3 = 10
prefix[4] = 2 + 4 + 1 + 3 + 5 = 15

Formula:

prefix[i] = prefix[i - 1] + nums[i]

---

🎯 How to Identify Prefix Sum?

Look for questions involving:

- Sum of elements between two indices
- Multiple range-sum queries
- Count subarrays with a given sum
- Find a subarray whose sum equals "K"
- Longest subarray with sum "K"
- Subarray sum divisible by "K"
- Equal sum on the left and right
- Running sum or cumulative sum
- Two-dimensional matrix range sums

Strong signals:

RANGE SUM
SUBARRAY SUM
CUMULATIVE SUM
EQUAL SUM
SUM EQUALS K
DIVISIBLE BY K

Think:

PREFIX SUM

Remember: Prefix Sum can be combined with a HashMap, a HashSet, a difference array, or other techniques depending on the question.

---

1️⃣ Basic Prefix Sum

Example

nums = [2, 4, 1, 3, 5]

Prefix Sum:

index:   0   1   2   3   4
nums:    2   4   1   3   5
prefix:  2   6   7  10  15

Java Code

int n = nums.length;
int[] prefix = new int[n];

prefix[0] = nums[0];

for (int i = 1; i < n; i++) {
    prefix[i] = prefix[i - 1] + nums[i];
}

Complexity

- Time: "O(n)"
- Space: "O(n)"

---

2️⃣ Range Sum Query

How to Identify?

The question asks:

- Find the sum between index "L" and index "R".
- Answer multiple range-sum queries.
- Calculate the sum from position "L" to position "R".

Formula

For an inclusive range "[L, R]":

sum(L, R) = prefix[R] - prefix[L - 1]

When "L == 0":

sum(0, R) = prefix[R]

Example

nums = [2, 4, 1, 3, 5]

Find the sum from index "1" to index "3".

nums[1...3] = [4, 1, 3]

Using Prefix Sum:

prefix[3] - prefix[0]
= 10 - 2
= 8

Answer:

8

Java Code

int rangeSum(int[] prefix, int left, int right) {
    if (left == 0) {
        return prefix[right];
    }

    return prefix[right] - prefix[left - 1];
}

Complexity:

- Preprocessing: "O(n)"
- Each query: "O(1)"
- Space: "O(n)"

---

3️⃣ Prefix Sum + HashMap

This is one of the most important Prefix Sum patterns.

How to Identify?

Look for:

- Count subarrays whose sum equals "K".
- Find the longest subarray with sum "K".
- Find subarrays containing positive and negative numbers.
- Count subarrays satisfying a sum condition.

When negative numbers are allowed, a normal Sliding Window based on sum generally cannot be relied upon.

Think:

PREFIX SUM + HASHMAP

Core Formula

Let:

currentPrefix = sum of elements from index 0 to i

The sum of a subarray from "j + 1" to "i" is:

currentPrefix - prefix[j]

If we want that sum to equal "K":

currentPrefix - prefix[j] = K

Rearrange:

prefix[j] = currentPrefix - K

Therefore, search for:

currentPrefix - K

in the HashMap.

---

4️⃣ Count Subarrays With Sum K

LeetCode 560 — Subarray Sum Equals K

Question:

«Count the number of continuous subarrays whose sum equals "K".»

Example:

nums = [1, 2, 3]
k = 3

Valid subarrays:

[1, 2] → sum = 3
[3]    → sum = 3

Answer:

2

Java Code

import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            prefixSum += num;

            count += map.getOrDefault(prefixSum - k, 0);

            map.put(
                prefixSum,
                map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }
}

Why "map.put(0, 1)"?

It represents the empty prefix with sum zero.

It allows us to count a valid subarray that begins at index "0".

Complexity

- Time: "O(n)" average
- Space: "O(n)"

---

5️⃣ Longest Subarray With Sum K

How to Identify?

The question asks:

«Find the maximum length of a continuous subarray whose sum equals "K".»

The difference from LeetCode 560 is:

560 → Count valid subarrays
Longest subarray → Find maximum length

Core Idea

Store the first index at which each prefix sum appears.

When:

prefixSum - K

exists in the map:

length = currentIndex - previousIndex

Keep the maximum length.

Java Code

import java.util.HashMap;

class Solution {
    public int longestSubarraySumK(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {

            prefixSum += nums[i];

            if (map.containsKey(prefixSum - k)) {
                int length = i - map.get(prefixSum - k);
                maxLength = Math.max(maxLength, length);
            }

            map.putIfAbsent(prefixSum, i);
        }

        return maxLength;
    }
}

Important Difference

For counting subarrays:

Map value = frequency of prefix sum

For longest subarray:

Map value = first index of prefix sum

Do not overwrite the first index, because an earlier index gives a longer subarray.

---

6️⃣ Subarray Sums Divisible by K

LeetCode 974 — Subarray Sums Divisible by K

How to Identify?

The question asks:

- Count subarrays divisible by "K".
- Find a continuous segment whose sum is a multiple of "K".

Core Idea

If two prefix sums have the same remainder when divided by "K", their difference is divisible by "K".

prefix1 % K = prefix2 % K

Therefore:

(prefix2 - prefix1) % K = 0

Java Code

import java.util.HashMap;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            prefixSum += num;

            int remainder = prefixSum % k;

            if (remainder < 0) {
                remainder += k;
            }

            count += map.getOrDefault(remainder, 0);

            map.put(
                remainder,
                map.getOrDefault(remainder, 0) + 1
            );
        }

        return count;
    }
}

Important: In Java, a negative number can produce a negative remainder. Normalize it before using it as a HashMap key.

---

7️⃣ Continuous Subarray Sum

LeetCode 523 — Continuous Subarray Sum

The question asks whether a continuous subarray of length at least "2" has a sum divisible by "K".

Recognition

Subarray length >= 2
+
Sum divisible by K

Think:

PREFIX SUM + REMAINDER HASHMAP

Store the first index for each prefix remainder.

If the same remainder appears again and the index difference is at least "2", a valid subarray exists.

---

8️⃣ Two-Dimensional Prefix Sum

How to Identify?

Look for:

- Sum of a rectangular region in a matrix.
- Multiple queries over rows and columns.
- Sum between two coordinate positions.

Instead of a one-dimensional array, we maintain cumulative sums in a matrix.

Concept

prefix[i][j]

stores the sum of the rectangle from the top-left corner through cell "(i - 1, j - 1)" when using a padded prefix matrix.

For an inclusive rectangle from "(r1, c1)" to "(r2, c2)":

sum =
prefix[r2 + 1][c2 + 1]
- prefix[r1][c2 + 1]
- prefix[r2 + 1][c1]
+ prefix[r1][c1]

The final addition restores the top-left overlap, which was subtracted twice.

LeetCode 304 — Range Sum Query 2D - Immutable

---

9️⃣ Difference Array

Prefix Sum is also connected to the Difference Array technique.

How to Identify?

Look for:

- Apply many range updates.
- Add a value to every element in "[L, R]".
- Perform many range modifications and return the final array.

Instead of updating every element in a range, record changes at its boundaries.

For an update adding "value" to "[L, R]":

diff[L] += value
diff[R + 1] -= value

Then calculate the prefix sum of "diff" to reconstruct the final array.

This reduces each range update to "O(1)" time, with an additional "O(n)" pass to reconstruct the array.

---

🧠 Prefix Sum vs Sliding Window

Situation| Think
Running cumulative sum| Prefix Sum
Multiple range-sum queries| Prefix Sum
Count subarrays with sum K| Prefix Sum + HashMap
Longest subarray with sum K, including negative values| Prefix Sum + HashMap
Count subarrays divisible by K| Prefix Sum + Remainder Map
Maximum sum of K consecutive elements| Fixed Sliding Window
Positive numbers and minimum length with sum at least K| Often Sliding Window
Multiple range additions| Difference Array
Rectangle sum in a matrix| 2D Prefix Sum

---

⚠️ Common Mistakes

1. Wrong range formula
   
   For an inclusive range "[L, R]", use:
   
   prefix[R] - prefix[L - 1]
   
   Handle "L == 0" separately.

2. Forgetting the initial zero prefix
   
   For counting subarrays with sum "K", initialize:
   
   map.put(0, 1);

3. Overwriting the first index
   
   For the longest subarray problem, use:
   
   map.putIfAbsent(prefixSum, i);

4. Using Sliding Window with negative numbers
   
   A standard sum-based Sliding Window relies on predictable changes when expanding and shrinking. Negative values can break that property.

5. Incorrect negative remainder
   
   Normalize the remainder in Java when solving divisibility problems.

6. Integer overflow
   
   Use "long" for prefix sums or totals when problem constraints require it.

---

🗺️ Pattern Recognition Cheat Sheet

Question asks| Pattern
Running Sum of 1D Array| Prefix Sum
Range Sum Query - Immutable| Prefix Sum
Range Sum Query 2D - Immutable| 2D Prefix Sum
Subarray Sum Equals K| Prefix Sum + Frequency Map
Maximum Size Subarray Sum Equals K| Prefix Sum + First Index Map
Subarray Sums Divisible by K| Prefix Sum + Remainder Map
Continuous Subarray Sum| Prefix Remainder + First Index
Range Addition| Difference Array + Prefix Sum
Longest subarray with sum K| Prefix Sum + HashMap

---

🔥 Final Mental Framework

                QUESTION
                    |
                    v
            Is it about SUM?
                    |
                    v
               PREFIX SUM
                    |
       +------------+------------+
       |            |            |
       v            v            v
   Range Sum    Subarray Sum   Range Update
       |            |            |
       v            v            v
    Prefix      Prefix +      Difference
   Difference    HashMap         Array
                    |
          +---------+---------+
          |         |         |
          v         v         v
        Count     Longest   Divisible
          |         |         |
          v         v         v
       Frequency  First     Remainder
         Map      Index       Map

🔑 Golden Rules

PREFIX SUM
= Cumulative total

Range Sum
= prefix[R] - prefix[L - 1]

Count Subarrays With Sum K
= Prefix Sum + Frequency Map

Longest Subarray With Sum K
= Prefix Sum + First Index Map

Subarray Sum Divisible by K
= Prefix Sum + Remainder Map

Range Updates
= Difference Array + Prefix Sum

---

🧩 LeetCode Practice List

Easy

- "1480. Running Sum of 1D Array" (https://leetcode.com/problems/running-sum-of-1d-array/)
- "303. Range Sum Query - Immutable" (https://leetcode.com/problems/range-sum-query-immutable/)
- "724. Find Pivot Index" (https://leetcode.com/problems/find-pivot-index/)
- "1732. Find the Highest Altitude" (https://leetcode.com/problems/find-the-highest-altitude/)

Medium

- "560. Subarray Sum Equals K" (https://leetcode.com/problems/subarray-sum-equals-k/)
- "525. Contiguous Array" (https://leetcode.com/problems/contiguous-array/)
- "523. Continuous Subarray Sum" (https://leetcode.com/problems/continuous-subarray-sum/)
- "974. Subarray Sums Divisible by K" (https://leetcode.com/problems/subarray-sums-divisible-by-k/)
- "304. Range Sum Query 2D - Immutable" (https://leetcode.com/problems/range-sum-query-2d-immutable/)
- "325. Maximum Size Subarray Sum Equals k" (https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/)
- "370. Range Addition" (https://leetcode.com/problems/range-addition/)

---

🏆 Final Recognition Rule

Whenever you see a question involving:

SUM
+
SUBARRAY / RANGE
+
REPEATED QUERIES / COUNT / LENGTH / DIVISIBILITY

consider Prefix Sum.

Then ask yourself:

1. Do I need a cumulative sum?
2. Do I need a range sum?
3. Do I need to count matching prefix sums?
4. Do I need the first index of a prefix sum?
5. Do I need to compare remainders?
6. Do I need to process multiple range updates?

The key is to identify what the question asks you to do with the prefix sum—not just memorize the formula.
