# LeetCode 503 — Next Greater Element II

## Problem Statement

Given a **circular integer array** `nums`, find the **Next Greater Element** for every element.

The Next Greater Element of an element is the first element to its right that is **strictly greater** than it.

If no greater element exists, return `-1`.

### Example

```text
Input:
nums = [1, 2, 1]

Output:
[2, -1, 2]
```

Explanation:

* For `1` → next greater element is `2`
* For `2` → no greater element exists → `-1`
* For the last `1` → because the array is circular, we go back to the beginning and find `2`

---

# Understanding the Circular Array

Normally:

```text
[1, 2, 1]
```

we move from left to right:

```text
1 → 2 → 1
```

But because the array is circular:

```text
1 → 2 → 1 → 1 → 2 → 1
```

So we can **imagine** creating an array with double the size.

For:

```text
nums = [1, 2, 1]
```

the hypothetical doubled array is:

```text
[1, 2, 1, 1, 2, 1]
```

We don't actually create this second array.

Instead, we use:

```java
nums[i % n]
```

This gives us the same effect.

For `n = 3`:

| `i` | `i % n` | `nums[i % n]` |
| --: | ------: | ------------: |
|   5 |       2 |             1 |
|   4 |       1 |             2 |
|   3 |       0 |             1 |
|   2 |       2 |             1 |
|   1 |       1 |             2 |
|   0 |       0 |             1 |

Therefore, `i % n` simulates the circular array.

---

# Approach

We use a **Monotonic Stack**.

The stack contains possible candidates for the Next Greater Element.

For every element:

1. Remove elements from the stack that are smaller than or equal to the current element.
2. If the stack is empty, the answer is `-1`.
3. Otherwise, the top of the stack is the Next Greater Element.
4. Push the current element into the stack.

The main condition is:

```java
stack.peek() <= cur
```

Why `<=`?

Because the Next Greater Element must be **strictly greater**.

For example:

```text
current = 5
stack top = 5
```

`5` is not greater than `5`, so it must be removed.

---

# Why Process `2 * n` Elements?

The array is circular.

Consider:

```text
nums = [1, 2, 1]
```

The last `1` needs to look at the beginning of the array:

```text
last 1 → first 1 → 2
```

Therefore, we need to give every element an opportunity to see elements from the beginning.

We simulate this by processing:

```java
2 * n
```

elements.

```java
for (int i = 2 * n - 1; i >= 0; i--)
```

---

# Code

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;

        for (int i = 2 * n - 1; i >= 0; i--) {

            int cur = nums[i % n];

            while (!stack.isEmpty() && stack.peek() <= cur) {
                stack.pop();
            }

            if (i < n) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(cur);
        }

        return res;
    }
}
```

---

# Code Explanation

## 1. Result Array

```java
int[] res = new int[nums.length];
```

Stores the answer for every element.

Initially:

```text
res = [0, 0, 0]
```

---

## 2. Stack

```java
Stack<Integer> stack = new Stack<>();
```

Used to store possible greater elements.

It behaves as a **monotonic decreasing stack**.

---

## 3. Store Array Length

```java
int n = nums.length;
```

For:

```text
nums = [1, 2, 1]
```

we have:

```text
n = 3
```

---

## 4. Traverse Twice

```java
for (int i = 2 * n - 1; i >= 0; i--)
```

For `n = 3`:

```text
i = 5
i = 4
i = 3
i = 2
i = 1
i = 0
```

We traverse from right to left because the stack represents elements on the right side.

---

## 5. Get Current Element

```java
int cur = nums[i % n];
```

This is the trick that handles the circular array.

Instead of creating:

```text
[1, 2, 1, 1, 2, 1]
```

we use:

```text
nums[i % n]
```

---

## 6. Remove Invalid Candidates

```java
while (!stack.isEmpty() && stack.peek() <= cur) {
    stack.pop();
}
```

If the stack top is smaller than or equal to the current element, it cannot be the answer.

Example:

```text
current = 5
stack = [8, 6, 3, 2]
```

Remove:

```text
2 → because 2 <= 5
3 → because 3 <= 5
```

Now:

```text
stack = [8, 6]
```

The top `6` is greater than `5`, so it can be the answer.

---

## 7. Store Answer Only for Original Array

```java
if (i < n) {
    res[i] = stack.isEmpty() ? -1 : stack.peek();
}
```

We process `2 * n` positions, but we only need answers for the original `n` positions.

Therefore:

```text
i < n
```

ensures that answers are stored only for the original array.

If the stack is empty:

```text
-1
```

Otherwise:

```text
stack.peek()
```

is the Next Greater Element.

---

## 8. Push Current Element

```java
stack.push(cur);
```

The current element can become the Next Greater Element for an element processed later.

---

# Dry Run

Consider:

```text
nums = [1, 2, 1]
```

Hypothetical doubled array:

```text
[1, 2, 1, 1, 2, 1]
```

Initial:

```text
stack = []
res = [0, 0, 0]
```

| `i` | `cur` | Stack Before | Popped | Answer | Stack After |
| --: | ----: | ------------ | ------ | -----: | ----------- |
|   5 |     1 | `[]`         | None   |      — | `[1]`       |
|   4 |     2 | `[1]`        | `1`    |      — | `[2]`       |
|   3 |     1 | `[2]`        | None   |      — | `[2,1]`     |
|   2 |     1 | `[2,1]`      | `1`    |    `2` | `[2,1]`     |
|   1 |     2 | `[2,1]`      | `1,2`  |   `-1` | `[2]`       |
|   0 |     1 | `[2]`        | None   |    `2` | `[2,1]`     |

Final:

```text
res = [2, -1, 2]
```

---

# Step-by-Step Example

### For index `2`

```text
nums[2] = 1
```

Stack:

```text
[2, 1]
```

Remove `1` because:

```text
1 <= 1
```

Now:

```text
[2]
```

`2 > 1`

Therefore:

```text
res[2] = 2
```

---

### For index `1`

```text
nums[1] = 2
```

Stack:

```text
[2, 1]
```

Remove:

```text
1 <= 2
2 <= 2
```

Stack becomes empty.

Therefore:

```text
res[1] = -1
```

---

### For index `0`

```text
nums[0] = 1
```

Stack:

```text
[2]
```

Since:

```text
2 > 1
```

we get:

```text
res[0] = 2
```

Final result:

```text
[2, -1, 2]
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Although we iterate `2 * n` times, `2n` is still `O(n)`.

Each element is pushed into the stack and removed at most once.

Therefore:

```text
Time = O(n)
```

## Space Complexity

The stack can contain up to `n` elements.

The result array also contains `n` elements.

Therefore:

```text
Space = O(n)
```

---

# Important DSA Pattern

This problem combines two important concepts:

### 1. Monotonic Stack

Used for problems like:

* Next Greater Element
* Next Smaller Element
* Previous Greater Element
* Previous Smaller Element
* Daily Temperatures
* Stock Span

### 2. Circular Array

For circular arrays, a common trick is:

```java
i % n
```

and process:

```java
2 * n
```

positions.

---

# Pattern to Remember

For **Next Greater Element II**:

```text
Circular Array
      ↓
Process 2*n elements
      ↓
Use i % n
      ↓
Monotonic Stack
      ↓
Remove elements <= current
      ↓
Stack top = Next Greater Element
      ↓
Empty Stack = -1
```

## Final Result

For:

```text
nums = [1, 2, 1]
```

Output:

```text
[2, -1, 2]
```

## Key Lines

### Circular array:

```java
int cur = nums[i % n];
```

### Remove smaller/equal elements:

```java
while (!stack.isEmpty() && stack.peek() <= cur) {
    stack.pop();
}
```

### Find answer:

```java
res[i] = stack.isEmpty() ? -1 : stack.peek();
```

### Process twice:

```java
for (int i = 2 * n - 1; i >= 0; i--)
```
