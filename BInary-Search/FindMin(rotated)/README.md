# LeetCode 153: Find Minimum in Rotated Sorted Array

## 1. Problem Statement

Given an array of unique elements that was originally sorted in ascending order but has been rotated between 1 and `n` times, find the minimum element in the array.

You must solve the problem in `O(log n)` time complexity.

### Example 1

**Input:**

```text
nums = [3,4,5,1,2]
```

**Output:**

```text
1
```

**Explanation:**

The original sorted array was `[1,2,3,4,5]`. After rotation, it becomes `[3,4,5,1,2]`. The minimum element is `1`.

### Example 2

**Input:**

```text
nums = [4,5,6,7,0,1,2]
```

**Output:**

```text
0
```

**Explanation:**

The minimum element in the rotated array is `0`.

### Example 3

**Input:**

```text
nums = [11,13,15,17]
```

**Output:**

```text
11
```

**Explanation:**

The array is already sorted, so the first element is the minimum.

---

## 2. Pattern Used

**Binary Search**

Binary Search is used because the array was originally sorted. We can use comparisons to eliminate half of the search space in each iteration.

## 3. Approach

We use three variables:

* `left`: Starting index of the search range.
* `right`: Ending index of the search range.
* `mid`: Middle index of the search range.

Initially:

```java
int left = 0;
int right = nums.length - 1;
```

Calculate the middle index:

```java
int mid = left + (right - left) / 2;
```

Compare `nums[mid]` with `nums[right]`.

### Case 1: `nums[mid] > nums[right]`

The minimum element must be to the right of `mid`.

Update:

```java
left = mid + 1;
```

### Case 2: `nums[mid] < nums[right]`

The minimum element is either at `mid` or to its left.

Update:

```java
right = mid;
```

We use `right = mid` because the middle element itself could be the minimum.

Repeat until `left == right`.

The remaining element is the minimum.

---

## 4. Java Solution

```java
class Solution {
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
```

---

## 5. Dry Run

**Input:**

```text
nums = [4,5,6,7,0,1,2]
```

| Step | Left | Right | Mid | nums[mid] | nums[right] | Condition     | Action             |
| ---- | ---: | ----: | --: | --------: | ----------: | ------------- | ------------------ |
| 1    |    0 |     6 |   3 |         7 |           2 | 7 > 2         | left = mid + 1 = 4 |
| 2    |    4 |     6 |   5 |         1 |           2 | 1 < 2         | right = mid = 5    |
| 3    |    4 |     5 |   4 |         0 |           1 | 0 < 1         | right = mid = 4    |
| 4    |    4 |     4 |   - |         - |           - | left == right | Loop stops         |

**Final Answer:**

```text
0
```

The minimum element is at index `4`.

---

## 6. Understanding the Dry Run

### Iteration 1

```text
Array: [4,5,6,7,0,1,2]
                ↑     ↑
               mid   right
```

* `nums[mid] = 7`
* `nums[right] = 2`
* Since `7 > 2`, the minimum is on the right side.

Update:

```java
left = mid + 1;
```

Now `left = 4`.

### Iteration 2

The search range is from index `4` to index `6`.

* `mid = 5`
* `nums[mid] = 1`
* `nums[right] = 2`
* Since `1 < 2`, the minimum is at `mid` or to its left.

Update:

```java
right = mid;
```

Now `right = 5`.

### Iteration 3

The search range is from index `4` to index `5`.

* `mid = 4`
* `nums[mid] = 0`
* `nums[right] = 1`
* Since `0 < 1`, the minimum is at `mid` or to its left.

Update:

```java
right = mid;
```

Now `right = 4`.

Since `left == right`, the loop stops.

Return:

```java
return nums[left];
```

The answer is `0`.

---

## 7. How to Identify This Pattern

Ask yourself these questions when solving a problem:

1. Is the array originally sorted?
2. Has the array been rotated?
3. Can I use comparisons to eliminate half of the search space?
4. Can I find the answer without checking every element?

If the answer is yes, Binary Search may be suitable.

The key observation is that the rotation creates two sorted sections, and comparing the middle element with the rightmost element helps us determine which section contains the minimum.

---

## 8. Important Observations

* The array contains unique elements.
* The array was originally sorted in ascending order.
* The minimum may be at any index.
* The array may not have been rotated.
* We compare `nums[mid]` with `nums[right]`.
* We use `right = mid` to avoid eliminating a possible minimum.
* The loop stops when `left == right`.

### Important Code

```java
if (nums[mid] > nums[right]) {
    left = mid + 1;
} else {
    right = mid;
}
```

Remember this logic.

---

## 9. Complexity Analysis

### Time Complexity: O(log n)

In every iteration, we eliminate approximately half of the search space.

For example:

```text
16 -> 8 -> 4 -> 2 -> 1
```

Therefore, the time complexity is `O(log n)`.

### Space Complexity: O(1)

We use only three variables: `left`, `right`, and `mid`.

No additional array or data structure is required.

Therefore, the space complexity is `O(1)`.

---

## 10. Common Mistakes

### Mistake 1: Using `right = mid - 1`

Incorrect for this approach:

```java
right = mid - 1;
```

The middle element itself might be the minimum.

Correct:

```java
right = mid;
```

### Mistake 2: Using `left <= right`

For this implementation, use:

```java
while (left < right)
```

When `left == right`, the minimum has been found.

### Mistake 3: Searching for a Particular Value

Do not assume the minimum is `0` or `1`.

Instead, compare:

```java
nums[mid] > nums[right]
```

The algorithm works regardless of the actual values in the array.

---

## 11. Key Takeaway

**LeetCode 153 teaches Binary Search on a rotated sorted array.**

The most important logic is:

* If `nums[mid] > nums[right]`, search the right half.
* Otherwise, search the left half, including `mid`.
* Stop when `left == right`.
* Return `nums[left]`.

**Related Problems:**

* LeetCode 33: Search in Rotated Sorted Array
* LeetCode 154: Find Minimum in Rotated Sorted Array II

**Pattern:** Binary Search

**Difficulty:** Medium
