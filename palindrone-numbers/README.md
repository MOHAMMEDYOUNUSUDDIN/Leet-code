# LeetCode 9 - Palindrome Number (Two Pointer Approach)

## Problem
Given an integer `x`, return `true` if it is a palindrome, otherwise return `false`.

A palindrome reads the same from left to right and right to left.

---

# Approach (Two Pointers)

Instead of reversing the number, convert it into a string and compare the characters from both ends.

- `left` starts at the beginning.
- `right` starts at the end.
- If the characters are different, return `false`.
- Otherwise, move `left++` and `right--`.
- If all characters match, return `true`.

---

# Java Code

```java
class Solution {
    public boolean isPalindrome(int x) {

        // Negative numbers are not palindrome
        if (x < 0) {
            return false;
        }

        String s = String.valueOf(x);

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

---

# Dry Run

### Input

```text
x = 12321
```

Convert to:

```text
s = "12321"
```

| Left Index | Right Index | Characters | Result |
|------------|-------------|------------|--------|
| 0 | 4 | 1 == 1 | Move both |
| 1 | 3 | 2 == 2 | Move both |
| 2 | 2 | Middle reached | Stop |

Return:

```text
true
```

---

# Time Complexity

```text
O(n)
```

where `n` is the number of digits.

---

# Space Complexity

```text
O(n)
```

because the integer is converted into a string.

---

# Key Points

- Negative numbers are never palindromes.
- Convert the number to a string.
- Use two pointers (`left` and `right`).
- Compare characters from both ends.
- If every pair matches, the number is a palindrome.
