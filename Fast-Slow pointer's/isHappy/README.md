# LeetCode 202 — Happy Number

## Problem

Write an algorithm to determine if a number `n` is a **Happy Number**.

A Happy Number is a number defined by the following process:

1. Start with any positive integer.
2. Replace the number by the sum of the squares of its digits.
3. Repeat the process.
4. If the process eventually reaches `1`, the number is a Happy Number.
5. If the process enters a cycle that does not contain `1`, the number is not a Happy Number.

---

## Example 1

### Input

```text
n = 19
```

### Process

```text
19
↓
1² + 9² = 82
↓
8² + 2² = 68
↓
6² + 8² = 100
↓
1² + 0² + 0² = 1
```

Since the sequence reaches `1`:

```text
Output: true
```

---

## Example 2

### Input

```text
n = 2
```

### Process

```text
2
↓
4
↓
16
↓
37
↓
58
↓
89
↓
145
↓
42
↓
20
↓
4
↓
16
↓
37
↓
...
```

The sequence repeats:

```text
4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4
```

It has entered a cycle and never reaches `1`.

Therefore:

```text
Output: false
```

---

# Main Idea

The important observation is that the process can have only two outcomes:

```text
Starting Number
      |
      v
Generate next number
      |
      +----------------+
      |                |
      v                v
     1              Cycle
      |                |
      v                v
    true             false
```

Because the sequence can enter a cycle, we can use **Floyd's Cycle Detection Algorithm**, also called the **Slow and Fast Pointer technique**.

This is the same pattern used in Linked List Cycle problems.

---

# Why Slow and Fast Pointers?

We use two variables:

```text
slow
fast
```

Initially:

```text
slow = n
fast = n
```

Then:

```text
slow → moves 1 step
fast → moves 2 steps
```

In code:

```java
slow = getNext(slow);
fast = getNext(getNext(fast));
```

If the number is happy, one of them eventually reaches `1`.

If the number is unhappy, the sequence enters a cycle.

Inside a cycle, the fast pointer moves faster than the slow pointer, so eventually:

```text
slow == fast
```

Therefore, we can detect the cycle.

---

# How to Calculate the Next Number

For every number, we need to calculate the sum of the squares of its digits.

For example:

```text
n = 19
```

### Step 1

Get the last digit:

```text
19 % 10 = 9
```

Square it:

```text
9 × 9 = 81
```

Remove the last digit:

```text
19 / 10 = 1
```

### Step 2

Get the last digit:

```text
1 % 10 = 1
```

Square it:

```text
1 × 1 = 1
```

Remove the last digit:

```text
1 / 10 = 0
```

Now:

```text
sum = 81 + 1
    = 82
```

Therefore:

```text
getNext(19) = 82
```

---

# getNext() Function

The function calculates the sum of squared digits.

```java
private int getNext(int n) {

    int sum = 0;

    while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n = n / 10;
    }

    return sum;
}
```

### Important Operations

```java
n % 10
```

Gets the last digit.

Example:

```text
19 % 10 = 9
```

---

```java
n / 10
```

Removes the last digit.

Example:

```text
19 / 10 = 1
```

---

```java
digit * digit
```

Calculates the square of the digit.

Example:

```text
9 * 9 = 81
```

---

# Complete Algorithm

1. Set `slow = n`.
2. Set `fast = n`.
3. Move `slow` one step.
4. Move `fast` two steps.
5. If either pointer reaches `1`, return `true`.
6. If `slow == fast`, a cycle has been detected.
7. Return `false`.

---

# Complete Code

```java
class Solution {

    public boolean isHappy(int n) {

        int slow = n;
        int fast = n;

        while (true) {

            slow = getNext(slow);
            fast = getNext(getNext(fast));

            if (fast == 1 || slow == 1) {
                return true;
            }

            if (slow == fast) {
                return false;
            }
        }
    }

    private int getNext(int n) {

        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n / 10;
        }

        return sum;
    }
}
```

---

# Dry Run 1 — Happy Number

## Input

```text
n = 19
```

Initially:

```text
slow = 19
fast = 19
```

### Iteration 1

Slow moves one step:

```text
slow = getNext(19)
     = 82
```

Fast moves two steps:

```text
fast = getNext(getNext(19))

getNext(19) = 82
getNext(82) = 68

fast = 68
```

Current state:

```text
slow = 82
fast = 68
```

Neither is `1`.

Also:

```text
82 != 68
```

Continue.

---

### Iteration 2

Slow:

```text
slow = getNext(82)
     = 68
```

Fast:

```text
fast = getNext(getNext(68))

getNext(68) = 100
getNext(100) = 1

fast = 1
```

Current state:

```text
slow = 68
fast = 1
```

Condition:

```java
if (fast == 1 || slow == 1)
```

is true.

Therefore:

```text
return true
```

### Result

```text
19 → 82 → 68 → 100 → 1

Output: true
```

---

# Dry Run 2 — Unhappy Number

## Input

```text
n = 2
```

The generated sequence is:

```text
2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 → ...
```

Initially:

```text
slow = 2
fast = 2
```

| Step  | Slow | Fast |
| ----- | ---- | ---- |
| Start | 2    | 2    |
| 1     | 4    | 16   |
| 2     | 16   | 58   |
| 3     | 37   | 145  |
| 4     | 58   | 20   |
| 5     | 89   | 16   |
| 6     | 145  | 89   |
| 7     | 42   | 42   |

At step 7:

```text
slow = 42
fast = 42
```

Therefore:

```java
if (slow == fast)
```

becomes true.

So:

```text
return false
```

### Result

```text
2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 → ...

Output: false
```

---

# Understanding the Cycle

The cycle is:

```text
4
↓
16
↓
37
↓
58
↓
89
↓
145
↓
42
↓
20
└──────→ 4
```

The number keeps repeating these values.

Since `1` is not part of this cycle, the number is not happy.

---

# Why Will Slow and Fast Meet?

Suppose both pointers are inside a cycle.

```text
slow → moves 1 position
fast → moves 2 positions
```

Every iteration, `fast` gains one position relative to `slow`.

Because the cycle has a finite number of positions, `fast` must eventually catch `slow`.

Therefore:

```text
slow == fast
```

This detects the cycle.

---

# Important Pattern

This problem teaches an important DSA pattern:

## Cycle Detection

Whenever a problem repeatedly transforms a value:

```text
value
 ↓
new value
 ↓
new value
 ↓
new value
 ↓
...
```

and the sequence may repeat, think about:

```text
Slow Pointer + Fast Pointer
```

This same concept is used in:

* LeetCode 141 — Linked List Cycle
* LeetCode 142 — Linked List Cycle II
* LeetCode 202 — Happy Number

---

# Complexity

Let `d` be the number of digits in the number.

### Time Complexity

Each `getNext()` operation processes all digits:

```text
O(d)
```

Cycle detection requires a bounded number of generated states, so the overall complexity is effectively:

```text
O(d)
```

for the standard Happy Number process.

### Space Complexity

We only use:

```text
slow
fast
sum
digit
```

No extra set or array is required.

Therefore:

```text
O(1)
```

space.

---

# Key Takeaways

1. `% 10` extracts the last digit.
2. `/ 10` removes the last digit.
3. `digit * digit` calculates the square.
4. `getNext()` generates the next number.
5. Happy number → eventually reaches `1`.
6. Unhappy number → enters a cycle.
7. Slow/Fast pointers detect that cycle.
8. If `slow == fast` before reaching `1`, return `false`.
9. This is a **cycle detection pattern**, not just a Happy Number trick.

## Pattern to Remember

> Repeated transformation + possible cycle = Think about Cycle Detection.
