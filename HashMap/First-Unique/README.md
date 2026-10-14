# LeetCode 387: First Unique Character in a String

## 1. Problem Statement

**Problem:** Given a string `s`, find the first non-repeating character in it and return its index. If it does not exist, return `-1`.

### Example 1

**Input:**

```text
s = "leetcode"
```

**Output:**

```text
0
```

**Explanation:**

The character `'l'` appears only once and is the first non-repeating character. Its index is `0`.

### Example 2

**Input:**

```text
s = "loveleetcode"
```

**Output:**

```text
2
```

**Explanation:**

The character `'l'` appears multiple times. The character `'o'` also appears multiple times. The character `'v'` appears only once, and its index is `2`.

### Example 3

**Input:**

```text
s = "aabb"
```

**Output:**

```text
-1
```

**Explanation:**

Every character appears more than once, so there is no unique character.

---

## 2. Understanding the Question

We need to find the **first character that appears exactly once** in the string.

Remember:

* **Unique character:** A character that appears only once.
* **First unique character:** The first character from left to right that appears only once.
* **Index:** The position of a character in the string, starting from `0`.
* **Return `-1`:** If no unique character exists.

### Important Observation

We cannot simply return the first character we encounter because we need to check whether it appears again later.

For example:

```text
s = "aabbc"
```

Character frequencies:

* `a` → 2
* `b` → 2
* `c` → 1

The answer is `4`, because `'c'` is the first character that appears exactly once.

---

## 3. Pattern Recognition

### Pattern: HashMap + Frequency Counting

**When should you recognize this pattern?**

Look for these clues in a problem:

* You need to count how many times each character appears.
* You need to identify repeating or non-repeating elements.
* You need to find the first element satisfying a frequency condition.
* You need to check whether an element occurs exactly once.

### Pattern Identification Table

| Clue in the Question           | What It Suggests                       |
| ------------------------------ | -------------------------------------- |
| Find a non-repeating character | Count character frequencies            |
| Character appears exactly once | Frequency must equal `1`               |
| Find the first character       | Traverse the string from left to right |
| Return the character's index   | Return the current loop index          |
| No unique character exists     | Return `-1`                            |

### General Approach

1. Create a `HashMap<Character, Integer>`.
2. Traverse the string and count each character.
3. Traverse the string again from left to right.
4. Find the first character whose frequency is `1`.
5. Return its index.
6. If no such character exists, return `-1`.

**Memory Trick:**

**Count First → Search Second → Return Index**

---

## 4. Java Solution

```java
import java.util.HashMap;

class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Count the frequency of each character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Find the first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        // Step 3: No unique character found
        return -1;
    }
}
```

---

## 5. Code Explanation

### Step 1: Create a HashMap

```java
HashMap<Character, Integer> map = new HashMap<>();
```

The HashMap stores each character and its frequency.

Example:

```text
s = "aabbc"
```

The final HashMap contains:

```text
a = 2
b = 2
c = 1
```

### Step 2: Count Character Frequencies

```java
for (int i = 0; i < s.length(); i++) {
    char ch = s.charAt(i);
    map.put(ch, map.getOrDefault(ch, 0) + 1);
}
```

**Explanation:**

* `s.charAt(i)` gets the character at index `i`.
* `map.getOrDefault(ch, 0)` returns the existing frequency or `0` if the character is absent.
* `+ 1` increases the frequency.
* `map.put()` stores the updated frequency.

For example:

```java
map.put(ch, map.getOrDefault(ch, 0) + 1);
```

If `a` already has frequency `2`, its new frequency becomes `3`.

### Step 3: Find the First Unique Character

```java
for (int i = 0; i < s.length(); i++) {
    if (map.get(s.charAt(i)) == 1) {
        return i;
    }
}
```

**Explanation:**

* Traverse the string again from left to right.
* Get the frequency of each character.
* If its frequency is `1`, return its index immediately.
* Returning immediately ensures that we find the first unique character.

### Step 4: Return -1

```java
return -1;
```

If every character repeats, the second loop finishes without returning an index. Therefore, the method returns `-1`.

---

## 6. Code Dry Run

### Input

```text
s = "loveleetcode"
```

### Phase 1: Count Character Frequencies

| Index | Character | Previous Frequency | Updated Frequency |
| ----: | :-------: | -----------------: | ----------------: |
|     0 |     l     |                  0 |                 1 |
|     1 |     o     |                  0 |                 1 |
|     2 |     v     |                  0 |                 1 |
|     3 |     e     |                  0 |                 1 |
|     4 |     l     |                  1 |                 2 |
|     5 |     e     |                  1 |                 2 |
|     6 |     e     |                  2 |                 3 |
|     7 |     t     |                  0 |                 1 |
|     8 |     c     |                  0 |                 1 |
|     9 |     o     |                  1 |                 2 |
|    10 |     d     |                  0 |                 1 |
|    11 |     e     |                  3 |                 4 |

**Final HashMap:**

```text
l = 2
o = 2
v = 1
e = 4
t = 1
c = 1
d = 1
```

### Phase 2: Find the First Unique Character

| Index `i` | Character | Frequency | Condition `frequency == 1` | Action     |
| --------: | :-------: | --------: | :------------------------: | ---------- |
|         0 |     l     |         2 |            False           | Continue   |
|         1 |     o     |         2 |            False           | Continue   |
|         2 |     v     |         1 |            True            | Return `2` |

**Output:**

```text
2
```

**Why does the loop stop at index 2?**

The character `'v'` appears exactly once. Since it is the first unique character from left to right, the method immediately returns `2`.

---

## 7. Question Dry Run

### Example: `s = "aabbc"`

| Index | Character | Total Frequency | Is It Unique? | Decision   |
| ----: | :-------: | --------------: | :-----------: | ---------- |
|     0 |     a     |               2 |       No      | Skip       |
|     1 |     a     |               2 |       No      | Skip       |
|     2 |     b     |               2 |       No      | Skip       |
|     3 |     b     |               2 |       No      | Skip       |
|     4 |     c     |               1 |      Yes      | Return `4` |

**Output:**

```text
4
```

**Explanation:**

The characters `a` and `b` repeat. The character `c` appears only once, so its index `4` is returned.

### Example: `s = "aabb"`

| Index | Character | Total Frequency | Is It Unique? | Decision |
| ----: | :-------: | --------------: | :-----------: | -------- |
|     0 |     a     |               2 |       No      | Skip     |
|     1 |     a     |               2 |       No      | Skip     |
|     2 |     b     |               2 |       No      | Skip     |
|     3 |     b     |               2 |       No      | Skip     |

No character has a frequency of `1`.

**Output:**

```text
-1
```

---

## 8. Time and Space Complexity

Let `n` be the length of the string.

| Complexity       | Value  | Explanation                                                                                 |
| ---------------- | ------ | ------------------------------------------------------------------------------------------- |
| Time Complexity  | `O(n)` | Two traversals of the string take linear time on average.                                   |
| Space Complexity | `O(k)` | The HashMap stores the distinct characters, where `k` is the number of distinct characters. |

Since the character set is bounded for this problem, the HashMap uses constant auxiliary space with respect to `n` under the fixed-alphabet assumption.

---

## 9. Common Mistakes

### Mistake 1: Returning the First Character Immediately

Incorrect approach:

```java
return 0;
```

Why is it wrong?

The first character might appear multiple times.

### Mistake 2: Checking Frequencies During the Counting Loop

Why is it problematic?

The final frequency of a character is not known until the entire string has been processed.

**Solution:** Count all frequencies first, then search for the first unique character.

### Mistake 3: Returning the Character Instead of Its Index

Incorrect:

```java
return s.charAt(i);
```

Correct:

```java
return i;
```

The question asks for the index, not the character.

### Mistake 4: Forgetting `return -1`

If no unique character exists, the method must return `-1`.

---

## 10. Interview Revision

| Question                           | Answer                                                                    |
| ---------------------------------- | ------------------------------------------------------------------------- |
| Which pattern is used?             | HashMap + Frequency Counting                                              |
| Why do we use a HashMap?           | To store the frequency of each character                                  |
| Why are there two loops?           | The first counts frequencies; the second finds the first unique character |
| What does `getOrDefault()` do?     | Returns the existing value or a default value if the key is absent        |
| Why do we return `i`?              | The problem asks for the index                                            |
| Why do we return `-1`?             | No unique character exists                                                |
| Time complexity?                   | `O(n)`                                                                    |
| Space complexity?                  | `O(k)`, where `k` is the number of distinct characters                    |
| Can this be solved using an array? | Yes, if the character set is known and can be indexed                     |
| What is the key observation?       | Frequency counting must finish before checking the first unique character |

---

## 11. Final Revision Trick

Remember these three steps:

**1. COUNT**

```java
map.put(ch, map.getOrDefault(ch, 0) + 1);
```

**2. CHECK**

```java
if (map.get(s.charAt(i)) == 1)
```

**3. RETURN**

```java
return i;
```

If no unique character exists:

```java
return -1;
```

**Pattern to remember:** HashMap → Frequency Counting → First Matching Index.
