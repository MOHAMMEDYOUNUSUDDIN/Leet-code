# 🔹 LeetCode 724 — Find Pivot Index

## 🧩 Problem

Given an integer array `nums`, find the **pivot index**.

The **pivot index** is an index where:

```text
Sum of elements on the LEFT = Sum of elements on the RIGHT
```

Important:

* The current element `nums[i]` is **not included** in either side.
* If there are multiple pivot indexes, return the **leftmost** one.
* If no pivot index exists, return `-1`.

### Example

```text
nums = [1, 7, 3, 6, 5, 6]

              ↓
             index 3

Left side:   1 + 7 + 3 = 11
Right side:  5 + 6     = 11

So answer = 3
```

---

# 💡 Main Idea

We can solve this efficiently using **Total Sum + Left Sum**.

Instead of calculating the left and right sums again and again:

### Step 1: Find the total sum

```text
totalSum = sum of all elements
```

### Step 2: Traverse the array

For every index `i`:

```text
rightSum = totalSum - leftSum - nums[i]
```

Why?

Because:

```text
Total Sum
    ↓
Left Sum + Current Element + Right Sum
```

Therefore:

```text
Right Sum = Total Sum - Left Sum - Current Element
```

Then check:

```text
if(leftSum == rightSum)
```

If true:

```text
return i;
```

After checking the current index, add the current element to `leftSum`.

```text
leftSum += nums[i];
```

---

# 🧠 Code

```java
class Solution {
    public int pivotIndex(int[] nums) {

        int leftSum = 0;
        int totalsum = 0;

        // Calculate total sum
        for(int i : nums){
            totalsum += i;
        }

        // Find pivot index
        for(int i = 0; i < nums.length; i++){

            int rightSum = totalsum - leftSum - nums[i];

            if(leftSum == rightSum){
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }
}
```

---

# 🔍 Understanding the Flow

Suppose:

```text
nums = [1, 7, 3, 6, 5, 6]
```

First calculate:

```text
totalSum = 1 + 7 + 3 + 6 + 5 + 6
         = 28
```

Initially:

```text
leftSum = 0
```

Now start traversing.

---

# 🏃 Dry Run

### i = 0

```text
nums[0] = 1
leftSum = 0
totalSum = 28
```

Calculate:

```text
rightSum = 28 - 0 - 1
         = 27
```

Check:

```text
leftSum == rightSum
0 == 27 ❌
```

Update:

```text
leftSum = 0 + 1
        = 1
```

---

### i = 1

```text
nums[1] = 7
leftSum = 1
```

Calculate:

```text
rightSum = 28 - 1 - 7
         = 20
```

Check:

```text
1 == 20 ❌
```

Update:

```text
leftSum = 1 + 7
        = 8
```

---

### i = 2

```text
nums[2] = 3
leftSum = 8
```

Calculate:

```text
rightSum = 28 - 8 - 3
         = 17
```

Check:

```text
8 == 17 ❌
```

Update:

```text
leftSum = 8 + 3
        = 11
```

---

### i = 3 ⭐

```text
nums[3] = 6
leftSum = 11
```

Calculate:

```text
rightSum = 28 - 11 - 6
         = 11
```

Now:

```text
leftSum == rightSum

11 == 11 ✅
```

Therefore:

```text
return 3;
```

### Visual

```text
Index:      0   1   2   3   4   5
Array:      1   7   3   6   5   6
                        ↑
                      Pivot

Left:       1 + 7 + 3 = 11

Right:              5 + 6 = 11
```

Answer:

```text
3
```

---

# 🔄 Why `leftSum += nums[i]` Comes After the Check?

This is very important.

At index `i`, the current element should **not** belong to either side.

For example:

```text
[1, 7, 3, 6, 5, 6]
          ↑
       index 3
```

For index `3`:

```text
Left  = 1 + 7 + 3
Current = 6
Right = 5 + 6
```

So we first check:

```java
if(leftSum == rightSum)
```

and only after that:

```java
leftSum += nums[i];
```

This makes sure the current element is not included in the left side.

---

# 📊 Complete Dry Run Table

| i | nums[i] | leftSum | rightSum | Equal? | Update leftSum |
| - | ------: | ------: | -------: | :----: | -------------: |
| 0 |       1 |       0 |       27 |    ❌   |              1 |
| 1 |       7 |       1 |       20 |    ❌   |              8 |
| 2 |       3 |       8 |       17 |    ❌   |             11 |
| 3 |       6 |      11 |       11 |    ✅   |              — |

At `i = 3`:

```text
leftSum = 11
rightSum = 11
```

So:

```text
return 3
```

---

# 🧠 Pattern to Remember

This is a **Prefix Sum / Running Sum** pattern.

Whenever a problem asks something like:

```text
Find an index where left side satisfies some condition
AND
right side satisfies some condition
```

Think about:

```text
Total Sum
+
Running Left Sum
```

For this problem:

```text
rightSum = totalSum - leftSum - currentElement
```

Then:

```text
if(leftSum == rightSum)
    return index;
```

---

# ⏱️ Complexity

### Time Complexity

We traverse the array twice:

```text
O(n) + O(n)
= O(n)
```

So:

```text
Time = O(n)
```

### Space Complexity

We only use variables:

```text
leftSum
totalSum
rightSum
```

No extra array is used.

```text
Space = O(1)
```

---

# 🎯 Quick Interview Explanation

If the interviewer asks **"Explain your approach"**, say:

> First, I calculate the total sum of the array. Then I traverse the array while maintaining the sum of elements on the left. For every index, I calculate the right sum using `totalSum - leftSum - nums[i]`. If the left sum and right sum are equal, I return that index. After checking, I add the current element to the left sum.

### One-line formula

```text
RIGHT = TOTAL - LEFT - CURRENT
```

### Pattern

```text
Total Sum + Running Left Sum
        ↓
Calculate Right Sum
        ↓
Compare Left and Right
        ↓
Return Pivot Index
```

# 🔑 Remember

```text
Pivot Index
= Left Sum == Right Sum

Right Sum
= Total Sum - Left Sum - Current Element
```

**LeetCode:** #724 — Find Pivot Index
