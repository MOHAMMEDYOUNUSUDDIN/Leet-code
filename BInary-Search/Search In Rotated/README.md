# Search in Rotated Sorted Array — LeetCode 33

## 1. Problem Statement

You are given an integer array `nums` that was originally sorted in ascending order but was rotated at some unknown position.

You are also given an integer `target`.

Your task is to search for the target in the array and return its index. If the target does not exist, return `-1`.

**Important:** You must solve the problem in `O(log n)` time complexity.

### Example 1

**Input:**

```text
nums = [4,5,6,7,0,1,2]
target = 0
```

**Output:**

```text
4
```

**Explanation:** The target `0` is present at index `4`.

### Example 2

**Input:**

```text
nums = [4,5,6,7,0,1,2]
target = 3
```

**Output:**

```text
-1
```

**Explanation:** The target `3` is not present in the array.

### Example 3

**Input:**

```text
nums = [1]
target = 0
```

**Output:**

```text
-1
```

**Explanation:** The target is not present in the array.

---

## 2. Pattern Used

**Binary Search on a Rotated Sorted Array**

This problem uses the Binary Search pattern with an additional condition to identify which half of the array is sorted.

Normally, binary search works on a completely sorted array. However, in this problem, the array is rotated, so we must determine which half is sorted before deciding where to search.

---

## 3. Intuition — How to Think About the Problem

Consider this array:

```text
[4, 5, 6, 7, 0, 1, 2]
```

The array was originally sorted, but it was rotated.

Even after rotation, **at least one half of the array will always be sorted** when we divide it using the middle index.

Our strategy is:

1. Find the middle index.
2. If the middle element equals the target, return its index.
3. Check whether the left half is sorted.
4. If the left half is sorted, check whether the target lies within that half.
5. Otherwise, search the right half.
6. If the left half is not sorted, the right half must be sorted. Check whether the target lies within the right half.
7. Repeat until the target is found or the search space becomes empty.

This approach eliminates half of the remaining search space in every iteration.

---

## 4. Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int guess = low + (high - low) / 2;

            if (nums[guess] == target) {
                return guess;
            }

            // Left half is sorted
            if (nums[low] <= nums[guess]) {
                if (nums[low] <= target && target < nums[guess]) {
                    high = guess - 1;
                } else {
                    low = guess + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[guess] < target && target <= nums[high]) {
                    low = guess + 1;
                } else {
                    high = guess - 1;
                }
            }
        }

        return -1;
    }
}
```

---

## 5. Code Explanation

### Step 1: Initialize Pointers

```java
int low = 0;
int high = nums.length - 1;
```

* `low` represents the starting index of the search space.
* `high` represents the ending index of the search space.

Initially, the entire array is considered.

### Step 2: Start the Binary Search

```java
while (low <= high)
```

The loop continues while the search space contains at least one element.

When `low > high`, the search space is empty, and the target cannot be found.

### Step 3: Calculate the Middle Index

```java
int guess = low + (high - low) / 2;
```

This calculates the middle index of the current search space.

Using this formula instead of `(low + high) / 2` helps avoid potential integer overflow.

### Step 4: Check the Middle Element

```java
if (nums[guess] == target) {
    return guess;
}
```

If the middle element equals the target, return its index immediately.

### Step 5: Check Whether the Left Half Is Sorted

```java
if (nums[low] <= nums[guess])
```

If the element at `low` is less than or equal to the middle element, the left half is sorted.

For example:

```text
[4, 5, 6, 7, 0, 1, 2]
     low     guess
```

The left half contains elements in ascending order.

Now, check whether the target lies within the sorted left half.

```java
if (nums[low] <= target && target < nums[guess])
```

If the target is within this range, eliminate the right half:

```java
high = guess - 1;
```

Otherwise, eliminate the left half, including the middle element:

```java
low = guess + 1;
```

### Step 6: Check the Right Half

```java
else {
    if (nums[guess] < target && target <= nums[high]) {
        low = guess + 1;
    } else {
        high = guess - 1;
    }
}
```

If the left half is not sorted, the right half must be sorted.

Check whether the target lies between the middle element and the last element.

If it does, continue searching the right half:

```java
low = guess + 1;
```

Otherwise, continue searching the left half:

```java
high = guess - 1;
```

### Step 7: Return -1

```java
return -1;
```

If the loop finishes without finding the target, return `-1` because the target does not exist in the array.

---

## 6. Dry Run

**Input:**

```text
nums = [4, 5, 6, 7, 0, 1, 2]
target = 0
```

Initially:

```text
low = 0
high = 6
```

| Iteration | Low | High | Guess | nums[guess] | Decision                                                                                            |
| --------- | --: | ---: | ----: | ----------: | --------------------------------------------------------------------------------------------------- |
| 1         |   0 |    6 |     3 |           7 | Left half is sorted. Target 0 is not in the left-half range, so search right.                       |
| 2         |   4 |    6 |     5 |           1 | Left half is sorted. Target 0 is not in the left-half range, so search right.                       |
| 3         |   6 |    6 |     6 |           2 | Middle element is not the target. The target is not in the sorted right-half range, so search left. |

After the third iteration:

```text
low = 6
high = 5
```

Now `low > high`, so the loop stops.

**Correction to observe:** In the second iteration, when `low = 4` and `high = 6`, the middle index is `5`. The element at index `5` is `1`. Since the left half `[0, 1]` is sorted and the target `0` belongs to that half, the correct update is `high = 4`, not `low = 6`.

The correct dry run is shown below.

### Correct Dry Run

| Iteration | Low | High | Guess | nums[guess] | Decision                                                          |
| --------- | --: | ---: | ----: | ----------: | ----------------------------------------------------------------- |
| 1         |   0 |    6 |     3 |           7 | Left half is sorted. Target 0 is outside its range, so `low = 4`. |
| 2         |   4 |    6 |     5 |           1 | Left half is sorted. Target 0 is within its range, so `high = 4`. |
| 3         |   4 |    4 |     4 |           0 | Target found. Return index `4`.                                   |

**Output:**

```text
4
```

---

## 7. Important Conditions to Remember

### Condition 1: Is the Left Half Sorted?

```java
nums[low] <= nums[guess]
```

If true, the left half is sorted.

### Condition 2: Is the Target in the Left Half?

```java
nums[low] <= target && target < nums[guess]
```

If true, search the left half.

### Condition 3: Is the Target in the Right Half?

```java
nums[guess] < target && target <= nums[high]
```

If true, search the right half.

### Why Are the Comparisons Different?

The middle element has already been checked:

```java
if (nums[guess] == target)
```

Therefore, the target range excludes `nums[guess]` when checking either half.

* Left half: `target < nums[guess]`
* Right half: `target > nums[guess]`

This prevents the middle element from being included again.

---

## 8. Time and Space Complexity

### Time Complexity: O(log n)

In every iteration, we eliminate approximately half of the remaining search space.

For an array of size `n`, binary search requires at most logarithmically many iterations.

Therefore:

```text
Time Complexity: O(log n)
```

### Space Complexity: O(1)

The algorithm uses only a few variables, such as `low`, `high`, and `guess`.

It does not use additional arrays or recursion.

Therefore:

```text
Space Complexity: O(1)
```

---

## 9. Common Mistakes

1. **Using normal binary search directly:** The array is rotated, so the entire array is not necessarily sorted.
2. **Forgetting to identify the sorted half:** Always check which half is sorted before deciding where to search.
3. **Using incorrect boundaries:** Remember that the middle element has already been checked.
4. **Updating pointers incorrectly:** Use `guess - 1` or `guess + 1` to eliminate the middle element from the remaining search space.
5. **Using `low < high` instead of `low <= high`:** This can cause the algorithm to miss the last remaining element.
6. **Forgetting to return `-1`:** If the target is absent, the method must return `-1`.

---

## 10. How to Recognize This Pattern

Think about this approach when a problem has the following characteristics:

* The array was originally sorted.
* The array has been rotated.
* You need to find a target element.
* The required time complexity is `O(log n)`.

**Key idea:** Even though the entire array is not sorted, at least one half is sorted. Identify that half and use its boundaries to decide which part to search.

---

## 11. Key Takeaway

**Binary Search + Identify the Sorted Half = Search in a Rotated Sorted Array**

Instead of checking every element, use the sorted half to determine where the target can exist.

This reduces the search space in every iteration and achieves `O(log n)` time complexity.

**LeetCode Problem:** 33 — Search in Rotated Sorted Array

**Pattern:** Modified Binary Search

**Difficulty:** Medium
