# LeetCode 1047 — Remove All Adjacent Duplicates In String

## Problem

Given a string `s`, repeatedly remove adjacent duplicate characters until no adjacent duplicates remain.

### Example

```text
Input:  "abbaca"
Output: "ca"
```

Explanation:

```text
abbaca
  ↓
aaca      remove "bb"
  ↓
ca        remove "aa"
```

---

## Approach — Stack

We use a **Stack** to keep track of characters.

For every character in the string:

1. If the stack is empty, push the character.
2. If the current character is the same as the stack's top character:

   * Remove the top character using `pop()`.
3. Otherwise, push the current character.
4. After processing the string, take all characters from the stack.
5. Since popping gives the characters in reverse order, reverse the result.

### Stack Operations Used

```text
push() → add an element
peek() → see the top element
pop()  → remove the top element
isEmpty() → check whether stack is empty
```

---

## Java Code

```java
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }

            if (s.charAt(i) == stack.peek()) {
                stack.pop();
                continue;
            }

            stack.push(s.charAt(i));
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }
}
```

---

## Dry Run

### Input

```text
s = "abbaca"
```

| Character | Stack   | Operation         |
| --------- | ------- | ----------------- |
| `a`       | `[a]`   | Push              |
| `b`       | `[a,b]` | Push              |
| `b`       | `[a]`   | Same as top → Pop |
| `a`       | `[]`    | Same as top → Pop |
| `c`       | `[c]`   | Push              |
| `a`       | `[c,a]` | Push              |

Now pop the stack:

```text
Stack: [c, a]

Pop → a
Pop → c
```

So:

```text
res = "ac"
```

Reverse it:

```text
"ac" → "ca"
```

### Final Answer

```text
"ca"
```

---

## Complexity

### Time Complexity

```text
O(n)
```

We process each character once.

### Space Complexity

```text
O(n)
```

The stack can contain up to `n` characters.

---

## Key Pattern

**Stack + Adjacent Duplicate Removal**

This pattern is useful whenever we need to remove elements based on the **previous/top element**.

### Real-world Example

Think of a stack of plates:

```text
A
B
B
```

When another `B` comes and matches the top `B`, both cancel/remove each other.

Similarly:

```text
"abbaca"
  BB → remove
  AA → remove
```

Result:

```text
"ca"
```
