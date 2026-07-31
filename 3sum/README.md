# 3Sum - LeetCode (Java)

## Problem Statement

Given an integer array `nums`, find **all unique triplets** such that:

```text
nums[i] + nums[j] + nums[k] = 0
```

### Conditions

- `i`, `j`, and `k` must be different indices.
- The answer should **not contain duplicate triplets**.

---

## Example

### Input

```text
nums = [-1,0,1,2,-1,-4]
```

### Output

```text
[
  [-1,-1,2],
  [-1,0,1]
]
```

---

# Approach

A brute-force solution would check every possible combination of three numbers.

There are three nested loops.

```text
for(i)
    for(j)
        for(k)
```

This takes **O(n³)** time, which is too slow for large inputs.

Instead, we use:

1. Sorting
2. Two Pointers

This reduces the time complexity to **O(n²)**.

---

# Step 1: Sort the Array

Before doing anything, sort the array.

Example:

Before sorting

```text
[-1,0,1,2,-1,-4]
```

After sorting

```text
[-4,-1,-1,0,1,2]
```

Sorting helps us:

- Use the Two Pointer technique.
- Easily avoid duplicate answers.

---

# Step 2: Fix One Number

We pick one element and assume it is the first number of the triplet.

```java
for(int i = 0; i < nums.length - 2; i++)
```

For every `i`, we now need to find **two numbers** after it whose sum equals:

```text
-target = nums[i]
```

---

# Step 3: Use Two Pointers

Create two pointers.

```java
int left = i + 1;
int right = nums.length - 1;
```

The pointers start like this:

```text
i    left             right
↓      ↓                ↓

[-4,-1,-1,0,1,2]
```

Now calculate:

```java
sum = nums[i] + nums[left] + nums[right];
```

---

# Step 4: Check the Sum

There are only three possibilities.

## Case 1

If

```text
sum == 0
```

We found a valid triplet.

Save it.

Move both pointers.

```java
left++;
right--;
```

---

## Case 2

If

```text
sum < 0
```

The sum is too small.

Move the left pointer.

```java
left++;
```

Why?

Because the array is sorted.

Moving left to the right increases the value.

---

## Case 3

If

```text
sum > 0
```

The sum is too large.

Move the right pointer.

```java
right--;
```

This decreases the total sum.

---

# Handling Duplicate Triplets

Suppose the array is

```text
[-2,-2,0,0,2,2]
```

Without checking duplicates, we would get

```text
[-2,0,2]
[-2,0,2]
[-2,0,2]
```

The same answer multiple times.

To avoid this:

Skip duplicate values of `i`.

```java
if(i > 0 && nums[i] == nums[i-1])
    continue;
```

Also skip duplicate values of `left`.

```java
while(left < right && nums[left] == nums[left-1])
    left++;
```

And duplicate values of `right`.

```java
while(left < right && nums[right] == nums[right+1])
    right--;
```

Now every triplet is added only once.

---

# Dry Run

Input

```text
[-1,0,1,2,-1,-4]
```

Sorted

```text
[-4,-1,-1,0,1,2]
```

---

### First Iteration

```text
i = -4

left = -1
right = 2
```

```text
-4 + (-1) + 2 = -3
```

Too small.

Move left.

Eventually, no triplet is found.

---

### Second Iteration

```text
i = -1

left = -1
right = 2
```

```text
-1 + (-1) + 2 = 0
```

Store

```text
[-1,-1,2]
```

Move both pointers.

Now

```text
left = 0
right = 1
```

Again

```text
-1 + 0 + 1 = 0
```

Store

```text
[-1,0,1]
```

Done.

---

# Java Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Fix one element
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            // Step 3: Two Pointer Search
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                }
                else if (sum < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        return ans;
    }
}
```

---

# Time Complexity

### Sorting

```text
O(n log n)
```

### Two Pointer Search

For every element, we scan the remaining array only once.

```text
O(n²)
```

### Overall

```text
O(n²)
```

---

# Space Complexity

```text
O(1)
```

Ignoring the output list.

---

# Why Two Pointers Work

Since the array is sorted:

- Moving the **left pointer** increases the sum.
- Moving the **right pointer** decreases the sum.

This allows us to find pairs efficiently without checking every combination.

---

# Key Takeaways

- Sort the array first.
- Fix one number.
- Use two pointers to find the remaining two numbers.
- Skip duplicates to avoid repeated answers.
- This approach reduces the complexity from **O(n³)** to **O(n²)**.

This problem is a classic example of combining **Sorting + Two Pointers**, a technique that is widely used in coding interviews.