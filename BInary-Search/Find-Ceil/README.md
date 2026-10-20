# 🔍 Find Ceiling in a Sorted Array

## 📌 Problem Statement

Given a sorted array `arr[]` of distinct integers in ascending order and an integer `x`, find the **ceiling of `x`** in the array.

The ceiling of `x` is defined as the **smallest element in the array that is greater than or equal to `x`**.

Return the index of the ceiling element. If no such element exists, return `-1`.

### Examples

#### Example 1

**Input:**

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 5
```

**Output:**

```text
2
```

**Explanation:**

The smallest element greater than or equal to `5` is `8`.

The element `8` is present at index `2`.

Therefore, the answer is `2`.

#### Example 2

**Input:**

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 11
```

**Output:**

```text
4
```

**Explanation:**

The element `11` is equal to `x`.

Therefore, the ceiling element is `11`, and its index is `4`.

#### Example 3

**Input:**

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 20
```

**Output:**

```text
-1
```

**Explanation:**

All elements in the array are smaller than `20`.

Therefore, no ceiling exists, and the function returns `-1`.

---

## 💡 Approach: Binary Search

Since the array is sorted in ascending order, we can use Binary Search to find the ceiling efficiently.

The ceiling is the smallest element that is greater than or equal to `x`.

### Logic

1. Initialize `low = 0` and `high = arr.length - 1`.

2. Initialize `res = -1` to indicate that no ceiling has been found yet.

3. Calculate the middle index:

   ```java
   int mid = low + (high - low) / 2;
   ```

4. Compare `arr[mid]` with `x`:

   * **If `arr[mid] == x`:** The exact element is found, so return `mid`.
   * **If `arr[mid] < x`:** The current element is too small to be the ceiling. Search the right half by updating `low = mid + 1`.
   * **If `arr[mid] > x`:** The current element is a possible ceiling. Store its index in `res` and search the left half for a smaller valid ceiling by updating `high = mid - 1`.

5. Continue until `low > high`.

6. Return `res`.

### Why do we use `res`?

Whenever `arr[mid] > x`, we have found an element that could be the ceiling.

However, there might be a smaller element that is still greater than or equal to `x`.

Therefore, we:

* Store the current index in `res`.
* Continue searching the left half.
* Update `res` whenever we find a better candidate.

If no suitable element exists, `res` remains `-1`.

---

## 💻 Java Solution

```java
class Solution {
    public int findCeil(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                res = mid;
                high = mid - 1;
            }
        }

        return res;
    }
}
```

---

## 🔎 Dry Run 1: Ceiling Exists

### Input

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 5
```

### Initial Values

```text
low = 0
high = 6
res = -1
```

Array with indices:

| Index | 0 | 1 | 2 | 3  | 4  | 5  | 6  |
| ----- | - | - | - | -- | -- | -- | -- |
| Value | 1 | 2 | 8 | 10 | 11 | 12 | 19 |

### Iteration 1

Calculate the middle index:

```text
mid = low + (high - low) / 2
mid = 0 + (6 - 0) / 2
mid = 3
```

The middle element is:

```text
arr[mid] = arr[3] = 10
x = 5
```

Comparison:

```text
10 > 5
```

Since `10` is greater than `5`, it is a possible ceiling.

Update:

```text
res = 3
high = mid - 1
high = 2
```

Current values:

```text
low = 0
high = 2
res = 3
```

We continue searching the left half because a smaller ceiling might exist.

### Iteration 2

Calculate the middle index:

```text
mid = low + (high - low) / 2
mid = 0 + (2 - 0) / 2
mid = 1
```

The middle element is:

```text
arr[mid] = arr[1] = 2
x = 5
```

Comparison:

```text
2 < 5
```

Since `2` is smaller than `5`, it cannot be the ceiling.

Update:

```text
low = mid + 1
low = 2
```

Current values:

```text
low = 2
high = 2
res = 3
```

### Iteration 3

Calculate the middle index:

```text
mid = low + (high - low) / 2
mid = 2 + (2 - 2) / 2
mid = 2
```

The middle element is:

```text
arr[mid] = arr[2] = 8
x = 5
```

Comparison:

```text
8 > 5
```

Since `8` is greater than `5`, it is another possible ceiling.

Update:

```text
res = 2
high = mid - 1
high = 1
```

Now:

```text
low = 2
high = 1
```

Since `low > high`, the loop terminates.

Return:

```text
return res;
return 2;
```

### Final Output

```text
2
```

**Explanation:** The ceiling of `5` is `8`, which is present at index `2`.

---

## 📊 Dry Run Table

For the input:

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 5
```

| Iteration | low | high | mid | arr[mid] | Condition    | res |
| --------- | --: | ---: | --: | -------: | ------------ | --: |
| 1         |   0 |    6 |   3 |       10 | `10 > 5`     |   3 |
| 2         |   0 |    2 |   1 |        2 | `2 < 5`      |   3 |
| 3         |   2 |    2 |   2 |        8 | `8 > 5`      |   2 |
| End       |   2 |    1 |   — |        — | `low > high` |   2 |

**Final Answer: `2`**

---

## 🔎 Dry Run 2: Exact Match Found

### Input

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 11
```

Initial values:

```text
low = 0
high = 6
res = -1
```

### Iteration 1

```text
mid = 0 + (6 - 0) / 2
mid = 3

arr[mid] = 10
```

Since `10 < 11`, search the right half.

```text
low = mid + 1
low = 4
```

### Iteration 2

```text
low = 4
high = 6

mid = 4 + (6 - 4) / 2
mid = 5

arr[mid] = 12
```

Since `12 > 11`, store this possible ceiling and search the left half.

```text
res = 5
high = mid - 1
high = 4
```

### Iteration 3

```text
low = 4
high = 4

mid = 4
arr[mid] = 11
```

Since `arr[mid] == x`, return the index immediately.

```text
return 4;
```

### Final Output

```text
4
```

**Explanation:** Since the target itself exists in the array, its index is the ceiling index.

---

## 🔎 Dry Run 3: Ceiling Does Not Exist

### Input

```text
arr = [1, 2, 8, 10, 11, 12, 19]
x = 20
```

Initial values:

```text
low = 0
high = 6
res = -1
```

Every element in the array is smaller than `20`.

During each iteration, the condition `arr[mid] < x` is true, so `low` moves to the right.

Eventually:

```text
low = 7
high = 6
res = -1
```

Since `low > high`, the loop terminates.

Return:

```text
return -1;
```

### Final Output

```text
-1
```

**Explanation:** No element in the array is greater than or equal to `20`.

---

## ⏱️ Complexity Analysis

### Time Complexity: O(log n)

In each iteration, Binary Search eliminates approximately half of the remaining search space.

Therefore, the time complexity is:

**O(log n)**

### Space Complexity: O(1)

The algorithm uses only a constant number of variables:

* `low`
* `high`
* `mid`
* `res`

No additional data structures are created.

Therefore, the auxiliary space complexity is:

**O(1)**

---

## 🧪 Edge Cases

| Test Case                            | Array               |  x | Expected Output |
| ------------------------------------ | ------------------- | -: | --------------: |
| Exact match at the beginning         | `[1, 2, 3, 4, 5]`   |  1 |               0 |
| Exact match in the middle            | `[1, 2, 3, 4, 5]`   |  3 |               2 |
| Exact match at the end               | `[1, 2, 3, 4, 5]`   |  5 |               4 |
| Ceiling between two elements         | `[1, 2, 8, 10, 11]` |  5 |               2 |
| Target smaller than all elements     | `[3, 5, 7, 9]`      |  1 |               0 |
| Target greater than all elements     | `[3, 5, 7, 9]`      | 10 |              -1 |
| Single-element array, match          | `[5]`               |  5 |               0 |
| Single-element array, ceiling exists | `[5]`               |  3 |               0 |
| Single-element array, no ceiling     | `[5]`               |  8 |              -1 |
| Empty array                          | `[]`                |  5 |              -1 |

---

## ⚠️ Important Points

* The array must be sorted in ascending order.
* The ceiling is the smallest element greater than or equal to `x`.
* This implementation returns the **index**, not the ceiling value.
* If the target exists, the method returns its index immediately.
* If `arr[mid] < x`, move right because the current element cannot be the ceiling.
* If `arr[mid] > x`, save its index and search left for a smaller valid candidate.
* Initialize `res = -1` to handle cases where no ceiling exists.
* If the array is empty, `high` becomes `-1`, so the loop never executes and the method returns `-1`.

---

## 🎯 Key Takeaways

1. Binary Search can efficiently find the ceiling in a sorted array.
2. The ceiling is the smallest element greater than or equal to the target.
3. The variable `res` stores the index of the best candidate found so far.
4. Searching the left half after finding a candidate helps find a smaller valid ceiling.
5. If an exact match is found, its index can be returned immediately.
6. The algorithm runs in **O(log n)** time and uses **O(1)** auxiliary space.

---

## 🚀 Conclusion

The Find Ceiling problem demonstrates how Binary Search can solve more than just exact-match searching.

By storing a candidate index whenever an element is greater than the target and continuing to search the left half, we can efficiently find the smallest element that satisfies the ceiling condition.

**Problem:** Find Ceiling in a Sorted Array
**Algorithm:** Binary Search
**Language:** Java
**Time Complexity:** O(log n)
**Auxiliary Space Complexity:** O(1)
