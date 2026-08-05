# Two Sum II - Input Array Is Sorted (LeetCode 167)

## 💡 Approach: Two Pointers

Since the array is **already sorted**, we can solve this problem using the **Two Pointer** technique in **O(n)** time.

---

## 🧠 Idea

We use two pointers:

- `i` → starts from the **beginning** of the array.
- `j` → starts from the **end** of the array.

At every step:

1. Find the sum of `nums[i] + nums[j]`.
2. If the sum equals the target, we found the answer.
3. If the sum is greater than the target, move the **right pointer (`j`)** left.
4. If the sum is smaller than the target, move the **left pointer (`i`)** right.

Because the array is sorted, moving pointers this way helps us reach the target efficiently.

---

# Code

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int sum = nums[i] + nums[j];

            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }

        return new int[]{-1, -1};
    }
}
```

---

# Step-by-Step Explanation

### Step 1: Initialize Two Pointers

```java
int i = 0;
int j = nums.length - 1;
```

- `i` points to the first element.
- `j` points to the last element.

Example:

```
Index : 0   1   2   3
Array : 2   7   11  15
        ↑           ↑
        i           j
```

---

### Step 2: Continue Until Both Pointers Meet

```java
while (i < j)
```

Keep checking pairs while the left pointer is before the right pointer.

---

### Step 3: Calculate the Current Sum

```java
int sum = nums[i] + nums[j];
```

Example:

```
nums[i] = 2
nums[j] = 15

sum = 2 + 15 = 17
```

---

### Step 4: Check if the Sum Equals the Target

```java
if (sum == target)
```

If the sum matches the target:

```java
return new int[]{i + 1, j + 1};
```

### Why `i + 1` and `j + 1`?

LeetCode 167 asks for **1-based indexing**, not 0-based indexing.

Example:

```
Array Index : 0  1  2  3
Return      : 1  2  3  4
```

---

### Step 5: If Sum is Too Large

```java
else if (sum > target) {
    j--;
}
```

Move the right pointer left because:

- The array is sorted.
- The right element is too large.
- Moving left gives a smaller number.

Example:

```
Target = 9

2   7   11   15
↑           ↑
i           j

Sum = 17 (Too Large)

Move j left

2   7   11   15
↑       ↑
i       j
```

---

### Step 6: If Sum is Too Small

```java
else {
    i++;
}
```

Move the left pointer right because:

- The current sum is too small.
- We need a larger number.
- Since the array is sorted, moving right increases the value.

Example:

```
Target = 18

2   7   11   15
↑   ↑
i

Sum = 2 + 15 = 17

Move i right

2   7   11   15
    ↑       ↑
    i       j
```

---

### Step 7: If No Pair Exists

```java
return new int[]{-1, -1};
```

This line runs only if no valid pair is found.

(For LeetCode 167, the problem guarantees exactly one solution, so this is just a safety return.)

---

# Dry Run

### Input

```
nums = [2, 7, 11, 15]
target = 9
```

### Initial State

```
i = 0
j = 3

2   7   11   15
↑           ↑
```

### Iteration 1

```
sum = 2 + 15 = 17

17 > 9

Move j left
```

```
2   7   11   15
↑       ↑
```

---

### Iteration 2

```
sum = 2 + 11 = 13

13 > 9

Move j left
```

```
2   7   11   15
↑   ↑
```

---

### Iteration 3

```
sum = 2 + 7 = 9

Target Found!
```

Return:

```
[1, 2]
```

---

# Why Does This Work?

Since the array is sorted:

- If the sum is too large, moving the right pointer left decreases the sum.
- If the sum is too small, moving the left pointer right increases the sum.

This allows us to find the answer without checking every pair.

---

# Time Complexity

```
O(n)
```

Each pointer moves at most `n` times.

---

# Space Complexity

```
O(1)
```

Only a few variables (`i`, `j`, and `sum`) are used.

---

# Key Points to Remember

- ✅ Array must be **sorted**.
- ✅ Use **two pointers**.
- ✅ If the sum is too large → move the **right pointer**.
- ✅ If the sum is too small → move the **left pointer**.
- ✅ Return **1-based indices** (`i + 1`, `j + 1`).
- ✅ Time Complexity: **O(n)**
- ✅ Space Complexity: **O(1)**

---

# Visualization

```
Target = 9

2   7   11   15
↑           ↑
2 + 15 = 17 ❌

Move right pointer

2   7   11   15
↑       ↑
2 + 11 = 13 ❌

Move right pointer

2   7   11   15
↑   ↑
2 + 7 = 9 ✅

Answer = [1, 2]
```