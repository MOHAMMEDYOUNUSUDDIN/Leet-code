# Remove Duplicates from Sorted Array - Two Pointer Approach

## Problem Statement

Given a **sorted** integer array `nums`, remove the duplicates **in-place** such that each unique element appears only once.

Return the number of unique elements (`k`).

The first `k` elements of the array should contain the unique elements in their original order.

> **Note:** You must modify the input array in-place using **O(1)** extra space.

---

# Example 1

### Input

```text
nums = [1,1,2]
```

### Output

```text
k = 2

nums = [1,2,_]
```

Explanation:

* Unique elements are `1` and `2`.
* Return `2`.

---

# Example 2

### Input

```text
nums = [0,0,1,1,1,2,2,3,3,4]
```

### Output

```text
k = 5

nums = [0,1,2,3,4,_,_,_,_,_]
```

---

# Key Observation

The array is **already sorted**.

That means:

* Duplicate numbers are always next to each other.
* We only need to compare the current element with the last unique element.

Example

```text
1 1 2 2 3 3 4
```

All duplicates are adjacent.

---

# Intuition

Imagine writing unique numbers into the front of the array.

We use:

* `i` → Points to the last unique element.
* `j` → Scans the array looking for new unique elements.

Whenever `j` finds a new number, we place it after the last unique number.

---

# Two Pointer Approach

## Pointer `i`

Keeps track of the last unique element.

## Pointer `j`

Scans every element from left to right.

Initially

```text
i = 0
j = 1
```

---

# Dry Run

## Input

```text
nums = [0,0,1,1,1,2,2,3,3,4]
```

Initial

```text
Index : 0 1 2 3 4 5 6 7 8 9

Value : 0 0 1 1 1 2 2 3 3 4
        ↑ ↑
        i j
```

---

## Step 1

Compare

```text
nums[j] = 0
nums[i] = 0
```

Same value.

Move `j`.

```text
0 0 1 1 1 2 2 3 3 4
↑   ↑
i   j
```

---

## Step 2

Compare

```text
nums[j] = 1
nums[i] = 0
```

Different.

A new unique element is found.

Move `i`.

```text
i++
```

Copy

```text
nums[i] = nums[j]
```

Array

```text
0 1 1 1 1 2 2 3 3 4
  ↑ ↑
  i j
```

---

## Step 3

Compare

```text
nums[j] = 1
nums[i] = 1
```

Duplicate.

Move `j`.

---

## Step 4

Again

```text
nums[j] = 1
nums[i] = 1
```

Duplicate.

Move `j`.

---

## Step 5

Compare

```text
nums[j] = 2
nums[i] = 1
```

Different.

Move `i`.

Copy

```text
nums[i] = nums[j]
```

Array

```text
0 1 2 1 1 2 2 3 3 4
    ↑   ↑
    i   j
```

---

Continue the same process.

Final array becomes

```text
0 1 2 3 4 _ _ _ _ _
```

Return

```text
5
```

---

# Visualization

```text
Original

0 0 1 1 1 2 2 3 3 4
↑ ↑
i j

↓

Duplicate

Move j

↓

0 0 1 1 1 2 2 3 3 4
↑   ↑
i   j

↓

New unique found

Move i

Copy nums[j] to nums[i]

0 1 1 1 1 2 2 3 3 4
  ↑ ↑
  i j

↓

Continue until end
```

---

# Java Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int i = 0;

        for (int j = 1; j < nums.length; j++) {

            // New unique element found
            if (nums[j] != nums[i]) {

                i++;

                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
```

---

# Code Explanation

### Step 1

```java
int i = 0;
```

`i` points to the last unique element.

Initially, the first element is always unique.

---

### Step 2

```java
for (int j = 1; j < nums.length; j++)
```

`j` scans every remaining element.

---

### Step 3

```java
if (nums[j] != nums[i])
```

If the current element is different from the last unique element,

we found a new unique value.

---

### Step 4

```java
i++;
```

Move `i` to the next position.

---

### Step 5

```java
nums[i] = nums[j];
```

Store the new unique element.

---

### Step 6

```java
return i + 1;
```

`i` stores the **last unique index**.

The total number of unique elements is:

```text
last index + 1
```

---

# Why Does This Work?

Since the array is sorted:

* Duplicate values are always consecutive.
* Comparing `nums[j]` with `nums[i]` is enough.
* Every new unique value is copied to the next available position.
* The first part of the array always contains unique elements.

---

# Time Complexity

```text
O(n)
```

We scan the array only once.

---

# Space Complexity

```text
O(1)
```

No extra array is used.

---

# Key Takeaways

* The array **must be sorted**.
* Use **Two Pointers**.
* `i` stores the position of the last unique element.
* `j` scans the array.
* Copy only when a new unique value is found.
* Return `i + 1`.

---

# Pattern to Remember

```text
Initialize

i = 0

For every j from 1 to n-1

    Is nums[j] different from nums[i]?

        YES
            i++
            nums[i] = nums[j]

        NO
            Ignore duplicate

Return i + 1
```

This is a classic **Two Pointer** pattern where:

* One pointer (`j`) scans the array.
* The other pointer (`i`) keeps track of the position where the next unique element should be placed.
* This allows us to remove duplicates **in-place** using **O(1)** extra space.
