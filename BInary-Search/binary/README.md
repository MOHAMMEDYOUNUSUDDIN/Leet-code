# 🔍 Binary Search

## 📌 Problem Statement

Given an array of integers `nums` sorted in ascending order and an integer `target`, write a function to search for `target` in `nums`.

If the target exists in the array, return its index. Otherwise, return `-1`.

You must write an algorithm with **O(log n) runtime complexity**.

### Example 1

**Input:**

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9
```

**Output:**

```text
4
```

**Explanation:**

The target `9` is present in the array at index `4`. Therefore, the output is `4`.

### Example 2

**Input:**

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 2
```

**Output:**

```text
-1
```

**Explanation:**

The target `2` is not present in the array. Therefore, the function returns `-1`.

---

## 💡 Approach: Binary Search

Binary Search is an efficient searching algorithm used to find an element in a **sorted array**.

Instead of checking every element one by one, Binary Search repeatedly divides the search range into two halves.

At each step:

1. Find the middle index of the current search range.
2. If the middle element equals the target, return its index.
3. If the middle element is smaller than the target, search the right half.
4. If the middle element is greater than the target, search the left half.
5. Repeat until the target is found or the search range becomes empty.

If the target is not found, return `-1`.

### Why Binary Search?

In Linear Search, we may need to check every element, resulting in **O(n)** time complexity.

Binary Search eliminates half of the remaining search space in every iteration, resulting in **O(log n)** time complexity.

**Important:** Binary Search requires the array to be sorted in ascending order for this implementation to work correctly.

---

## 🧠 Algorithm

1. Initialize `low = 0` to represent the first index.

2. Initialize `high = nums.length - 1` to represent the last index.

3. Run a `while` loop as long as `low <= high`.

4. Calculate the middle index using:

   ```java
   int mid = low + (high - low) / 2;
   ```

5. Compare `nums[mid]` with `target`:

   * If `nums[mid] == target`, return `mid`.
   * If `nums[mid] < target`, update `low = mid + 1`.
   * Otherwise, update `high = mid - 1`.

6. If the loop finishes without finding the target, return `-1`.

---

## 💻 Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
```

---

## 🔎 Dry Run

Let's understand the algorithm step by step with an example.

### Input

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9
```

### Initial Values

```text
low = 0
high = 5
target = 9
```

Array with indices:

| Index | 0  | 1 | 2 | 3 | 4 | 5  |
| ----- | -- | - | - | - | - | -- |
| Value | -1 | 0 | 3 | 5 | 9 | 12 |

### Iteration 1

**Step 1:** Calculate the middle index.

```text
mid = low + (high - low) / 2
mid = 0 + (5 - 0) / 2
mid = 2
```

**Step 2:** Check the middle element.

```text
nums[mid] = nums[2] = 3
target = 9
```

Since `3 < 9`, the target must be in the right half of the array.

**Update:**

```text
low = mid + 1
low = 3
```

Current search range:

```text
[5, 9, 12]
```

### Iteration 2

Current values:

```text
low = 3
high = 5
```

**Step 1:** Calculate the middle index.

```text
mid = low + (high - low) / 2
mid = 3 + (5 - 3) / 2
mid = 4
```

**Step 2:** Check the middle element.

```text
nums[mid] = nums[4] = 9
target = 9
```

Since `nums[mid] == target`, the target is found.

**Return:**

```text
return 4;
```

### Final Output

```text
4
```

**Conclusion:** The target `9` is found at index `4` after only two iterations.

---

## 📊 Dry Run Table

| Iteration | low | high | mid | nums[mid] | Comparison | Action          |
| --------- | --: | ---: | --: | --------: | ---------- | --------------- |
| 1         |   0 |    5 |   2 |         3 | 3 < 9      | `low = mid + 1` |
| 2         |   3 |    5 |   4 |         9 | 9 == 9     | Return `4`      |

---

## 🧪 Dry Run When the Target Is Not Found

### Input

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 2
```

### Step 1

```text
low = 0
high = 5
mid = 2
nums[mid] = 3
```

Since `3 > 2`, search the left half.

```text
high = mid - 1
high = 1
```

### Step 2

```text
low = 0
high = 1
mid = 0
nums[mid] = -1
```

Since `-1 < 2`, search the right half.

```text
low = mid + 1
low = 1
```

### Step 3

```text
low = 1
high = 1
mid = 1
nums[mid] = 0
```

Since `0 < 2`, update:

```text
low = mid + 1
low = 2
```

Now:

```text
low = 2
high = 1
```

Since `low > high`, the loop terminates.

The target is not found, so the function returns:

```text
-1
```

### Dry Run Table

| Iteration | low | high | mid | nums[mid] | Comparison   | Action           |
| --------- | --: | ---: | --: | --------: | ------------ | ---------------- |
| 1         |   0 |    5 |   2 |         3 | 3 > 2        | `high = mid - 1` |
| 2         |   0 |    1 |   0 |        -1 | -1 < 2       | `low = mid + 1`  |
| 3         |   1 |    1 |   1 |         0 | 0 < 2        | `low = mid + 1`  |
| End       |   2 |    1 |   — |         — | `low > high` | Return `-1`      |

---

## ⏱️ Complexity Analysis

### Time Complexity: O(log n)

In each iteration, Binary Search eliminates approximately half of the remaining search space.

For an array containing `n` elements, the number of iterations grows logarithmically with the size of the array.

Therefore, the time complexity is:

**O(log n)**

### Space Complexity: O(1)

The algorithm uses only a few additional variables:

* `low`
* `high`
* `mid`

No additional array or data structure is created.

Therefore, the auxiliary space complexity is:

**O(1)**

---

## ⚠️ Important Points

* The array must be sorted in ascending order.
* Array indexing starts from `0` in Java.
* `low` represents the starting index of the search range.
* `high` represents the ending index of the search range.
* `mid` represents the middle index of the current search range.
* The condition `low <= high` ensures that the remaining search range is checked, even when it contains just one element.
* When `nums[mid] < target`, move right using `low = mid + 1`.
* When `nums[mid] > target`, move left using `high = mid - 1`.
* Return `-1` when the target does not exist in the array.
* The expression `low + (high - low) / 2` calculates the middle index while avoiding the potential integer overflow associated with `(low + high) / 2`.

---

## 🧩 Edge Cases

| Test Case                           | Input Array       | Target | Expected Output |
| ----------------------------------- | ----------------- | -----: | --------------: |
| Target at the beginning             | `[1, 3, 5, 7, 9]` |    `1` |             `0` |
| Target at the end                   | `[1, 3, 5, 7, 9]` |    `9` |             `4` |
| Target in the middle                | `[1, 3, 5, 7, 9]` |    `5` |             `2` |
| Target not present                  | `[1, 3, 5, 7, 9]` |    `4` |            `-1` |
| Single-element array, target found  | `[5]`             |    `5` |             `0` |
| Single-element array, target absent | `[5]`             |    `2` |            `-1` |
| Empty array                         | `[]`              |    `5` |            `-1` |

---

## 🎯 Key Takeaways

1. Binary Search is an efficient algorithm for searching sorted arrays.
2. It reduces the search space by half in each iteration.
3. It uses two pointers: `low` and `high`.
4. The middle index is calculated using `low + (high - low) / 2`.
5. Its time complexity is **O(log n)**.
6. Its auxiliary space complexity is **O(1)**.
7. If the target is found, return its index; otherwise, return `-1`.

---

## 🚀 Conclusion

Binary Search is one of the most important searching algorithms in Data Structures and Algorithms (DSA).

By repeatedly dividing the search space into two halves, it finds elements efficiently without checking every element individually.

Understanding how `low`, `high`, and `mid` change during each iteration is the key to mastering Binary Search.

**Problem Type:** Searching
**Algorithm:** Binary Search
**Programming Language:** Java
**Time Complexity:** O(log n)
**Space Complexity:** O(1)

