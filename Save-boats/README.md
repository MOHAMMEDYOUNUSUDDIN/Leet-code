# Move Zeroes - Two Pointer Approach

## Problem Statement

Given an integer array `nums`, move all the `0`s to the end of the array while maintaining the **relative order of the non-zero elements**.

**Note:**

* You must do this **in-place** (without creating another array).
* Try to solve it in **O(n)** time.

### Example

**Input**

```text
nums = [0,1,0,3,12]
```

**Output**

```text
[1,3,12,0,0]
```

---

# Intuition

Imagine arranging students in a line.

* Students with **non-zero values** should stand at the front.
* Students with **zero values** should move to the end.
* The order of the non-zero students must not change.

Instead of moving every zero to the end, we do something simpler:

> Whenever we find a non-zero element, place it in the next available position at the front.

---

# Two Pointer Approach

We use two pointers.

### Pointer `i`

* Points to the position where the next non-zero element should be placed.

### Pointer `j`

* Scans the entire array from left to right.

Initially

```text
i = 0
j = 0
```

---

# Dry Run

## Input

```text
nums = [0,1,0,3,12]
```

Initial state

```text
Index : 0 1 2 3 4

Value : 0 1 0 3 12
        ↑
        i
        ↑
        j
```

---

## Step 1

`j` points to `0`

```text
nums[j] == 0
```

Do nothing.

Move only `j`.

```text
0 1 0 3 12
↑
i
  ↑
  j
```

---

## Step 2

`j` points to `1`

```text
nums[j] != 0
```

Swap `nums[i]` and `nums[j]`

```text
Before

0 1 0 3 12

After

1 0 0 3 12
```

Move both pointers.

```text
1 0 0 3 12
  ↑
  i
    ↑
    j
```

---

## Step 3

`j` points to `0`

Do nothing.

Move only `j`.

```text
1 0 0 3 12
  ↑
  i
      ↑
      j
```

---

## Step 4

`j` points to `3`

Swap

```text
Before

1 0 0 3 12

After

1 3 0 0 12
```

Move both pointers.

```text
1 3 0 0 12
    ↑
    i
        ↑
        j
```

---

## Step 5

`j` points to `12`

Swap

```text
Before

1 3 0 0 12

After

1 3 12 0 0
```

Move both pointers.

```text
1 3 12 0 0
```

Finished!

---

# Visualization

```text
Array

0 1 0 3 12
↑
i
↑
j

↓

Skip zero

0 1 0 3 12
↑
i
  ↑
  j

↓

Found non-zero

Swap

1 0 0 3 12
  ↑
  i
    ↑
    j

↓

Skip zero

1 0 0 3 12
  ↑
  i
      ↑
      j

↓

Found non-zero

Swap

1 3 0 0 12
    ↑
    i
        ↑
        j

↓

Found non-zero

Swap

1 3 12 0 0
```

---

# Java Solution

```java
class Solution {
    public void moveZeroes(int[] nums) {

        int i = 0;

        for (int j = 0; j < nums.length; j++) {

            if (nums[j] != 0) {

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
            }
        }
    }
}
```

---

# Code Explanation

```java
int i = 0;
```

`i` points to the position where the next non-zero element should be placed.

---

```java
for (int j = 0; j < nums.length; j++)
```

`j` scans every element in the array.

---

```java
if (nums[j] != 0)
```

If the current element is non-zero, we place it at index `i`.

---

```java
int temp = nums[i];
nums[i] = nums[j];
nums[j] = temp;
```

Swap the current non-zero element with the element at `i`.

---

```java
i++;
```

Move `i` forward because one correct position has been filled.

---

# Why Does This Work?

* `j` visits every element exactly once.
* `i` always points to the next position where a non-zero element belongs.
* Every non-zero element is moved to the front in its original order.
* Zeros naturally end up at the end.

---

# Complexity Analysis

### Time Complexity

```text
O(n)
```

We scan the array only once.

### Space Complexity

```text
O(1)
```

No extra array is used.

---

# Key Takeaways

* Sort **is not needed**.
* Use **two pointers**:

  * `i` → next position for a non-zero element.
  * `j` → scans the array.
* Swap only when `nums[j]` is non-zero.
* This preserves the order of non-zero elements.
* Runs in **O(n)** time and **O(1)** extra space.

---

# Pattern to Remember

```text
Initialize:

i = 0

For every j from left to right:

Is nums[j] non-zero?

YES
    Swap(nums[i], nums[j])
    i++

NO
    Continue

End
```

This is a classic **Two Pointer** pattern where one pointer scans the array and the other tracks the next correct position for the desired elements.
