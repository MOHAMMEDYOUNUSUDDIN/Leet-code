# LeetCode 3 — Longest Substring Without Repeating Characters

## Problem

Given a string `s`, find the length of the **longest substring without repeating characters**.

### Example

```text
Input:  "abcabcbb"
Output: 3
```

The longest substring is:

```text
"abc"
```

It has no duplicate characters.

---

## Approach — Sliding Window

We maintain a window using two pointers:

```text
left
  ↓
[ a b c ]
        ↑
       right
```

We use a boolean array:

```java
boolean[] seen = new boolean[128];
```

It tells us whether a character is already inside the current window.

### Steps

1. Start `left = 0`.
2. Move `right` from left to right.
3. If the current character is already present:

   * Remove the character at `left`.
   * Move `left` forward.
4. Add the current character.
5. Calculate the current window length.
6. Store the maximum length.

---

## Java Code

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {

        int left = 0;
        int maxLength = 0;

        boolean[] seen = new boolean[128];

        for (int right = 0; right < s.length(); right++) {

            // Duplicate found
            while (seen[s.charAt(right)]) {
                seen[s.charAt(left)] = false;
                left++;
            }

            // Add current character
            seen[s.charAt(right)] = true;

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

---

## Dry Run

### Input

```text
s = "abcabcbb"
```

| right | Character | Window         | Action                      | Max |
| ----: | --------- | -------------- | --------------------------- | --: |
|     0 | a         | `a`            | Add `a`                     |   1 |
|     1 | b         | `ab`           | Add `b`                     |   2 |
|     2 | c         | `abc`          | Add `c`                     |   3 |
|     3 | a         | `abca`         | Duplicate `a` → move `left` |   3 |
|     4 | b         | `bca` → `bcab` | Duplicate `b` → move `left` |   3 |
|     5 | c         | `cab`          | Duplicate `c` → move `left` |   3 |

So the answer is:

```text
3
```

---

## Why `right - left + 1`?

Suppose:

```text
left = 2
right = 5
```

The window contains:

```text
index:   2  3  4  5
         a  b  c  d
```

Number of elements:

```text
5 - 2 + 1 = 4
```

Therefore:

```java
right - left + 1
```

gives the current window length.

---

## Important Part

This is the main logic:

```java
while (seen[s.charAt(right)]) {
    seen[s.charAt(left)] = false;
    left++;
}
```

If the new character already exists in the window, we keep removing characters from the left until the duplicate is gone.

Then:

```java
seen[s.charAt(right)] = true;
```

adds the new character to the window.

---

## Time Complexity

```text
O(n)
```

Each character is added and removed from the window at most once.

## Space Complexity

```text
O(1)
```

The boolean array has only 128 positions.

---

## Pattern

**Sliding Window → Longest Substring → No Repeating Characters**

This problem is one of the most important problems for learning the **variable-size sliding window** pattern.
