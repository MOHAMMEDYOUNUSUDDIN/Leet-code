# LeetCode 409 — Longest Palindrome

## 1. Problem Statement

Given a string `s` containing lowercase and uppercase English letters, return the length of the longest palindrome that can be built using those letters.

A palindrome is a string that reads the same forward and backward.

**Important:** We can rearrange the characters in any order.

### Examples

**Example 1**

Input:

```text
s = "abccccdd"
```

Output:

```text
7
```

Explanation:

One possible palindrome is `"dccaccd"`, which has a length of 7.

**Example 2**

Input:

```text
s = "a"
```

Output:

```text
1
```

Explanation:

The single character itself is a palindrome.

**Example 3**

Input:

```text
s = "bb"
```

Output:

```text
2
```

Explanation:

We can form `"bb"`, which is a palindrome of length 2.

---

## 2. Pattern Recognition

**Pattern: HashMap + Frequency Counting + Greedy**

### How to recognize this pattern?

Look for these clues in a problem:

* The problem asks how many times each character appears.
* Characters can be rearranged.
* We need to construct a palindrome.
* We want to maximize the length.

### Core observation

A palindrome consists of:

* A left half.
* An optional single middle character.
* A right half that mirrors the left half.

For example:

```text
d c c a c c d
|---| | |---|
Left  Mid Right
```

Every character used in the left half must have a matching character in the right half.

Therefore:

* Even frequency: Use all occurrences.
* Odd frequency: Use the largest even number of occurrences.
* If any odd frequency exists, we can use one extra character in the center.

### Example

```text
Character    Frequency    Characters Used
a            1            1 (center)
b            1            0
c            4            4
d            2            2
```

Total length = `4 + 2 + 1 = 7`

---

## 3. Java Solution

```java
import java.util.HashMap;

class Solution {
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Count the frequency of each character
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int length = 0;
        boolean odd = false;

        // Step 2: Calculate the maximum palindrome length
        for (int count : map.values()) {
            length += (count / 2) * 2;

            if (count % 2 == 1) {
                odd = true;
            }
        }

        // Step 3: Add one character in the center if possible
        return odd ? length + 1 : length;
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
a -> 1
b -> 1
c -> 4
d -> 2
```

### Step 2: Count character frequencies

```java
for (char ch : s.toCharArray()) {
    map.put(ch, map.getOrDefault(ch, 0) + 1);
}
```

**Explanation:**

* `s.toCharArray()` converts the string into a character array.
* `map.getOrDefault(ch, 0)` returns the current frequency or `0` if the character is not present.
* `+ 1` increments the frequency.

For `"abccccdd"`:

```text
a = 1
b = 1
c = 4
d = 2
```

### Step 3: Initialize variables

```java
int length = 0;
boolean odd = false;
```

* `length`: Stores the total number of characters that can be used in pairs.
* `odd`: Tracks whether any character has an odd frequency.

### Step 4: Calculate the usable pairs

```java
for (int count : map.values()) {
    length += (count / 2) * 2;
}
```

The expression:

```java
(count / 2) * 2
```

Finds the largest even number less than or equal to `count`.

Examples:

| Count | Integer Division | Characters Used |
| ----: | ---------------: | --------------: |
|     1 |        1 / 2 = 0 |               0 |
|     2 |        2 / 2 = 1 |               2 |
|     3 |        3 / 2 = 1 |               2 |
|     4 |        4 / 2 = 2 |               4 |
|     5 |        5 / 2 = 2 |               4 |

**Why?**

Every character used in the left half needs a matching character in the right half.

### Step 5: Check for odd frequencies

```java
if (count % 2 == 1) {
    odd = true;
}
```

The modulo operator `%` returns the remainder.

* `count % 2 == 0`: Even frequency.
* `count % 2 == 1`: Odd frequency.

If any frequency is odd, we can place one character in the center.

### Step 6: Return the answer

```java
return odd ? length + 1 : length;
```

This is the ternary operator.

It means:

```java
if (odd) {
    return length + 1;
} else {
    return length;
}
```

We add only one extra character, even if multiple characters have odd frequencies.

---

## 5. Dry Run — Code Execution

### Input

```text
s = "abccccdd"
```

### Step 1: Build the HashMap

The first loop processes each character.

| Iteration | Character | Previous Frequency | New Frequency |
| --------: | :-------: | -----------------: | ------------: |
|         1 |     a     |                  0 |             1 |
|         2 |     b     |                  0 |             1 |
|         3 |     c     |                  0 |             1 |
|         4 |     c     |                  1 |             2 |
|         5 |     c     |                  2 |             3 |
|         6 |     c     |                  3 |             4 |
|         7 |     d     |                  0 |             1 |
|         8 |     d     |                  1 |             2 |

Final HashMap:

```text
{a=1, b=1, c=4, d=2}
```

### Step 2: Execute the second loop

Initial values:

```java
length = 0;
odd = false;
```

The loop processes the frequency values `1, 1, 4, 2`.

| Iteration | Count | `(count / 2) * 2` | Length Before | Length After | `count % 2 == 1` |  Odd |
| --------: | ----: | ----------------: | ------------: | -----------: | :--------------: | :--: |
|         1 |     1 |                 0 |             0 |            0 |       true       | true |
|         2 |     1 |                 0 |             0 |            0 |       true       | true |
|         3 |     4 |                 4 |             0 |            4 |       false      | true |
|         4 |     2 |                 2 |             4 |            6 |       false      | true |

**Note:** The iteration order of `map.values()` is not guaranteed. The table uses the order `1, 1, 4, 2` for explanation. The final result remains the same.

### Iteration 1: Count = 1

```java
length += (1 / 2) * 2;
```

Integer division:

```text
1 / 2 = 0
0 * 2 = 0
```

Therefore:

```text
length = 0
```

Check the odd frequency:

```java
1 % 2 == 1
```

Result:

```text
odd = true
```

### Iteration 2: Count = 1

```text
(1 / 2) * 2 = 0
length = 0 + 0 = 0
```

The frequency is odd, so `odd` remains `true`.

### Iteration 3: Count = 4

```text
(4 / 2) * 2 = 4
length = 0 + 4 = 4
```

All four `c` characters can be used.

### Iteration 4: Count = 2

```text
(2 / 2) * 2 = 2
length = 4 + 2 = 6
```

Both `d` characters can be used.

Final values:

```text
length = 6
odd = true
```

### Step 3: Execute the return statement

```java
return odd ? length + 1 : length;
```

Since `odd = true`:

```text
return 6 + 1;
return 7;
```

**Final Answer: 7**

---

## 6. Dry Run — Problem Understanding

Input:

```text
s = "abccccdd"
```

| Character | Frequency | Pair Count | Characters Used in Pairs | Odd Frequency? |
| :-------: | --------: | ---------: | -----------------------: | :------------: |
|     a     |         1 |          0 |                        0 |       Yes      |
|     b     |         1 |          0 |                        0 |       Yes      |
|     c     |         4 |          2 |                        4 |       No       |
|     d     |         2 |          1 |                        2 |       No       |
| **Total** |     **8** |      **3** |                    **6** |     **Yes**    |

We use six characters in pairs:

```text
c c d d c c
```

One possible arrangement is:

```text
d c c a c c d
```

The character `a` is placed in the center.

Final palindrome:

```text
dccaccd
```

Check:

```text
Forward:  dccaccd
Backward: dccaccd
```

Therefore, the maximum palindrome length is:

```text
6 + 1 = 7
```

**Important:** We do not need to construct the palindrome. The problem asks only for its maximum length.

---

## 7. Complexity Analysis

Let `n` be the length of the string and `k` be the number of distinct characters.

### Time Complexity: O(n)

* Counting frequencies takes `O(n)`.
* Processing the frequency values takes `O(k)`.
* Since `k <= n`, the total time complexity is `O(n)`.

### Space Complexity: O(k)

The HashMap stores the frequency of each distinct character.

Because the problem contains English letters, the number of distinct characters is bounded by a constant of 52.

Therefore, for this specific problem, the auxiliary space is effectively `O(1)`.

---

## 8. Interview Revision

**Q1. Which pattern is used?**

HashMap + Frequency Counting + Greedy.

**Q2. Why do we count frequencies?**

To determine how many matching character pairs are available.

**Q3. Why use `(count / 2) * 2`?**

It calculates the largest even number of occurrences that can be used in matching pairs.

**Q4. Why can we add only one extra character?**

A palindrome can have at most one unpaired character, and that character must be in the center.

**Q5. Why use a boolean variable?**

To remember whether any character has an odd frequency.

**Q6. What happens if all frequencies are even?**

We return `length` without adding anything.

**Q7. What happens if multiple frequencies are odd?**

We use pairs from all frequencies and add only one extra character.

**Q8. What is the time complexity?**

`O(n)`.

**Q9. Is constructing the palindrome necessary?**

No. Only the maximum possible length is required.

**Q10. Can this problem be solved without a HashMap?**

Yes. An integer array of size 128 or 256 can count characters using their character codes. A frequency array is also a valid approach.

---

## 9. Quick Revision Formula

Remember these three steps:

```text
1. Count the frequency of every character.

2. Add (count / 2) * 2 for every frequency.

3. If any frequency is odd, add 1.
```

Formula:

```text
Answer = Sum of largest even counts + (Any odd frequency ? 1 : 0)
```

**Pattern to remember:** Frequency Counting + Greedy Pairing.

**LeetCode:** 409 — Longest Palindrome
