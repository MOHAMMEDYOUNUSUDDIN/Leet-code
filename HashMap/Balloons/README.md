# LeetCode 1189: Maximum Number of Balloons

## 1. Problem Statement

**Problem:** Maximum Number of Balloons
**LeetCode Number:** 1189
**Difficulty:** Easy
**Topic:** HashMap, String, Frequency Counting

Given a string `text`, return the maximum number of times the word `"balloon"` can be formed using the characters in `text`.

Each character in `text` can be used only once.

### Example 1

**Input:**

```text
text = "nlaebolko"
```

**Output:**

```text
1
```

**Explanation:**

We can form `"balloon"` once using the available characters.

### Example 2

**Input:**

```text
text = "loonbalxballpoon"
```

**Output:**

```text
2
```

**Explanation:**

We can form `"balloon"` twice using the available characters.

### Example 3

**Input:**

```text
text = "leetcode"
```

**Output:**

```text
0
```

**Explanation:**

The string does not contain all the characters required to form `"balloon"`.

---

## 2. Pattern Recognition

### Which pattern should we identify?

**Pattern: HashMap + Frequency Counting**

When a problem asks us to:

* Count how many times each character appears.
* Check whether enough characters are available.
* Construct a word using the available characters.
* Find the maximum number of times a word can be formed.

Think of **frequency counting using a HashMap**.

### How to recognize this pattern

| Problem Clue                | What It Means                                                    |
| --------------------------- | ---------------------------------------------------------------- |
| Count characters            | Store the frequency of each character                            |
| Construct a word            | Check the required character counts                              |
| Characters cannot be reused | Limit the number of words by available frequencies               |
| Find the maximum number     | Return the minimum possible word count among required characters |

### Important observation

The word `"balloon"` contains:

| Character | Required Frequency |
| --------- | -----------------: |
| b         |                  1 |
| a         |                  1 |
| l         |                  2 |
| o         |                  2 |
| n         |                  1 |

The characters `l` and `o` appear twice in the target word.

Therefore, their available frequencies must be divided by `2`.

The answer is the minimum of these five values:

```text
b
a
l / 2
o / 2
n
```

---

## 3. Java Solution Using HashMap

```java
import java.util.*;

class Solution {
    public int maxNumberOfBalloons(String text) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : text.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int b = map.getOrDefault('b', 0);
        int a = map.getOrDefault('a', 0);
        int l = map.getOrDefault('l', 0) / 2;
        int o = map.getOrDefault('o', 0) / 2;
        int n = map.getOrDefault('n', 0);

        return Math.min(b, Math.min(a,
               Math.min(l, Math.min(o, n))));
    }
}
```

---

## 4. Code Explanation

### Step 1: Create a HashMap

```java
HashMap<Character, Integer> map = new HashMap<>();
```

The HashMap stores each character and its frequency.

Example:

```text
Input: "balloon"

Map:
b = 1
a = 1
l = 2
o = 2
n = 1
```

### Step 2: Count character frequencies

```java
for (char ch : text.toCharArray()) {
    map.put(ch, map.getOrDefault(ch, 0) + 1);
}
```

Explanation:

* `text.toCharArray()` converts the string into a character array.
* `getOrDefault(ch, 0)` returns the existing frequency or `0` if the character is absent.
* `+ 1` increases the frequency.
* `map.put()` stores the updated frequency.

For example, when the character `l` appears for the first time, its frequency becomes `1`.

When `l` appears again, its frequency becomes `2`.

### Step 3: Get the required character frequencies

```java
int b = map.getOrDefault('b', 0);
int a = map.getOrDefault('a', 0);
int l = map.getOrDefault('l', 0) / 2;
int o = map.getOrDefault('o', 0) / 2;
int n = map.getOrDefault('n', 0);
```

We retrieve the frequency of every character needed to form `"balloon"`.

We divide the frequencies of `l` and `o` by `2` because each word requires two `l` characters and two `o` characters.

If a character does not exist in the map, its frequency is treated as `0`.

### Step 4: Return the minimum frequency

```java
return Math.min(b, Math.min(a,
       Math.min(l, Math.min(o, n))));
```

The minimum frequency determines how many complete `"balloon"` words can be formed.

For example:

```text
b = 3
a = 2
l = 4 / 2 = 2
o = 6 / 2 = 3
n = 5
```

The minimum is `2`.

Therefore, the answer is `2`.

---

## 5. Dry Run of the Code

Consider:

```text
text = "nlaebolko"
```

### Step 1: Build the HashMap

The loop processes each character.

| Iteration | Character (`ch`) | Previous Frequency | Updated Frequency |
| --------: | ---------------- | -----------------: | ----------------: |
|         1 | n                |                  0 |                 1 |
|         2 | l                |                  0 |                 1 |
|         3 | a                |                  0 |                 1 |
|         4 | e                |                  0 |                 1 |
|         5 | b                |                  0 |                 1 |
|         6 | o                |                  0 |                 1 |
|         7 | l                |                  1 |                 2 |
|         8 | k                |                  0 |                 1 |
|         9 | o                |                  1 |                 2 |

After the loop, the HashMap contains:

```text
n = 1
l = 2
a = 1
e = 1
b = 1
o = 2
k = 1
```

### Step 2: Calculate the required frequencies

| Variable | Calculation          |     Value |
| -------- | -------------------- | --------: |
| b        | Frequency of `b`     |         1 |
| a        | Frequency of `a`     |         1 |
| l        | Frequency of `l` / 2 | 2 / 2 = 1 |
| o        | Frequency of `o` / 2 | 2 / 2 = 1 |
| n        | Frequency of `n`     |         1 |

### Step 3: Evaluate Math.min()

```java
Math.min(b, Math.min(a,
    Math.min(l, Math.min(o, n))));
```

Substitute the values:

```java
Math.min(1, Math.min(1,
    Math.min(1, Math.min(1, 1))));
```

Evaluation:

| Expression       | Result |
| ---------------- | -----: |
| `Math.min(o, n)` |      1 |
| `Math.min(l, 1)` |      1 |
| `Math.min(a, 1)` |      1 |
| `Math.min(b, 1)` |      1 |

**Final Output:**

```text
1
```

---

## 6. Dry Run of the Question

Consider:

```text
text = "loonbalxballpoon"
```

We want to find the maximum number of `"balloon"` words that can be formed.

### Step 1: Count the required characters

| Character | Frequency in `text` | Required per Word | Possible Words |
| --------- | ------------------: | ----------------: | -------------: |
| b         |                   2 |                 1 |              2 |
| a         |                   2 |                 1 |              2 |
| l         |                   4 |                 2 |              2 |
| o         |                   4 |                 2 |              2 |
| n         |                   2 |                 1 |              2 |

### Step 2: Find the minimum

```text
b = 2
a = 2
l = 4 / 2 = 2
o = 4 / 2 = 2
n = 2
```

Therefore:

```text
Answer = min(2, 2, 2, 2, 2)
       = 2
```

**Output:**

```text
2
```

We can form `"balloon"` twice.

---

## 7. Dry Run: When the Answer Is Zero

**Input:**

```text
text = "leetcode"
```

Required characters:

| Character | Frequency | Adjusted Frequency |
| --------- | --------: | -----------------: |
| b         |         0 |                  0 |
| a         |         0 |                  0 |
| l         |         1 |          1 / 2 = 0 |
| o         |         0 |                  0 |
| n         |         0 |                  0 |

The minimum frequency is `0`.

**Output:**

```text
0
```

We cannot form even one complete `"balloon"` word.

---

## 8. Complexity Analysis

Let `n` be the length of the input string.

### Time Complexity: O(n)

The loop visits each character once.

Retrieving the required frequencies takes a constant number of operations on average.

Therefore:

```text
Time Complexity = O(n)
```

### Space Complexity: O(1)

The HashMap stores character frequencies. Since the input is restricted to lowercase English letters, there are at most 26 distinct keys.

Therefore, the auxiliary space is constant:

```text
Space Complexity = O(1)
```

---

## 9. Common Mistakes

| Mistake                          | Correct Approach                                 |
| -------------------------------- | ------------------------------------------------ |
| Forgetting that `l` occurs twice | Divide its frequency by `2`                      |
| Forgetting that `o` occurs twice | Divide its frequency by `2`                      |
| Using `map.get('b')` directly    | Use `map.getOrDefault('b', 0)`                   |
| Returning the maximum frequency  | Return the minimum adjusted frequency            |
| Ignoring missing characters      | Treat missing characters as having frequency `0` |

---

## 10. Interview Explanation

If an interviewer asks you to explain your approach, you can say:

"I use a HashMap to count the frequency of each character in the input string. Since the target word is `balloon`, I retrieve the frequencies of `b`, `a`, `l`, `o`, and `n`. The characters `l` and `o` are required twice, so I divide their frequencies by two. Finally, I return the minimum of these five values because the character with the lowest available word count limits how many complete `balloon` words can be formed."

---

## 11. Quick Revision

Remember these four steps:

1. **Count:** Store character frequencies in a HashMap.
2. **Extract:** Get the frequencies of `b`, `a`, `l`, `o`, and `n`.
3. **Adjust:** Divide the frequencies of `l` and `o` by `2`.
4. **Return:** Find the minimum frequency using `Math.min()`.

### Pattern to Remember

**Character Frequency Counting + Minimum Frequency**

Use this approach when a problem asks you to construct a word from available characters and maximize the number of complete words.
