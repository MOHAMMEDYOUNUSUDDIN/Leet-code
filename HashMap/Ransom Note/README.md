# LeetCode 383: Ransom Note

## 1. Problem Explanation

**Problem:** Given two strings, `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed using characters from `magazine`. Otherwise, return `false`.

### Rules

* Each character in `magazine` can be used only once.
* Characters cannot be reused beyond their available frequency.
* The order of characters in `magazine` does not matter.
* Every character required by `ransomNote` must be available.

### Example 1

**Input:**

```text
ransomNote = "aa"
magazine = "aab"
```

**Output:**

```text
true
```

**Explanation:** The magazine contains two `'a'` characters, so we can construct `"aa"`.

### Example 2

**Input:**

```text
ransomNote = "aa"
magazine = "ab"
```

**Output:**

```text
false
```

**Explanation:** The magazine contains only one `'a'`, but the ransom note requires two.

### Example 3

**Input:**

```text
ransomNote = "a"
magazine = "b"
```

**Output:**

```text
false
```

**Explanation:** The required character `'a'` is not available.

---

## 2. Pattern Recognition

### Pattern: HashMap + Frequency Counting

**How to recognize this pattern:**

Look for problems involving:

* Counting how many times each character appears.
* Checking whether enough characters are available.
* Comparing character frequencies between strings.
* Making sure an element is not used more times than it appears.

### Why use a HashMap?

A HashMap stores each character and its frequency.

For example:

```text
magazine = "aab"
```

HashMap:

```text
a → 2
b → 1
```

We can quickly check whether a character is available and decrease its count whenever we use it.

### Algorithm

1. Create a `HashMap<Character, Integer>`.
2. Traverse `magazine` and count each character.
3. Traverse `ransomNote`.
4. Check whether each required character has a positive count.
5. If its count is zero or the character is absent, return `false`.
6. Otherwise, decrease its count by one.
7. If all characters are processed successfully, return `true`.

---

## 3. Java Code

```java
import java.util.HashMap;

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);

            if (map.getOrDefault(ch, 0) == 0) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);
        }

        return true;
    }
}
```

---

## 4. Important HashMap Methods

| Method                            | Meaning                                                                   | Example                    |
| --------------------------------- | ------------------------------------------------------------------------- | -------------------------- |
| `put(key, value)`                 | Inserts or updates a key-value pair                                       | `map.put('a', 2)`          |
| `get(key)`                        | Returns the value associated with a key                                   | `map.get('a')`             |
| `getOrDefault(key, defaultValue)` | Returns the value if the key exists; otherwise, returns the default value | `map.getOrDefault('a', 0)` |
| `containsKey(key)`                | Checks whether a key exists                                               | `map.containsKey('a')`     |

### Frequency Counting

```java
map.put(ch, map.getOrDefault(ch, 0) + 1);
```

* If the character is new, its count becomes `1`.
* If the character already exists, its count increases by `1`.

### Frequency Decreasing

```java
map.put(ch, map.get(ch) - 1);
```

Decreases the available count after using a character.

### Availability Check

```java
if (map.getOrDefault(ch, 0) == 0)
```

If the count is zero or the character does not exist, the required character is unavailable.

---

## 5. Dry Run of the Code

**Input:**

```text
ransomNote = "aa"
magazine = "aab"
```

### Phase 1: Count Characters in Magazine

Initially:

```text
map = {}
```

| Iteration | `i` | `ch`  | Previous Count | New Count | HashMap After Update |
| --------- | --: | ----- | -------------: | --------: | -------------------- |
| 1         |   0 | `'a'` |              0 |         1 | `{a=1}`              |
| 2         |   1 | `'a'` |              1 |         2 | `{a=2}`              |
| 3         |   2 | `'b'` |              0 |         1 | `{a=2, b=1}`         |

After the first loop:

```text
map = {a=2, b=1}
```

### Phase 2: Construct Ransom Note

The second loop checks each character in `"aa"`.

| Iteration | `i` | `ch`  | Available Count | Condition `count == 0` | Action         | HashMap After Update |
| --------- | --: | ----- | --------------: | ---------------------- | -------------- | -------------------- |
| 1         |   0 | `'a'` |               2 | False                  | Decrease count | `{a=1, b=1}`         |
| 2         |   1 | `'a'` |               1 | False                  | Decrease count | `{a=0, b=1}`         |

Both characters are available, so the loop completes.

The method executes:

```java
return true;
```

**Output: `true`**

---

## 6. Dry Run of a Failing Question

**Input:**

```text
ransomNote = "aa"
magazine = "ab"
```

### Phase 1: Count Characters

| Iteration | `i` | `ch`  | New Count | HashMap After Update |
| --------- | --: | ----- | --------: | -------------------- |
| 1         |   0 | `'a'` |         1 | `{a=1}`              |
| 2         |   1 | `'b'` |         1 | `{a=1, b=1}`         |

### Phase 2: Check Ransom Note

| Iteration | `i` | `ch`  | Available Count | Condition        | Result              |
| --------- | --: | ----- | --------------: | ---------------- | ------------------- |
| 1         |   0 | `'a'` |               1 | `1 == 0` → False | Decrease count to 0 |
| 2         |   1 | `'a'` |               0 | `0 == 0` → True  | Return `false`      |

**Output: `false`**

The second `'a'` cannot be used because its available count is already zero.

---

## 7. Time and Space Complexity

Let:

* `n` = length of `magazine`
* `m` = length of `ransomNote`

### Time Complexity: O(n + m)

* Counting characters in `magazine` takes O(n).
* Checking `ransomNote` takes O(m).
* HashMap operations take O(1) average time.

### Space Complexity: O(1)

The problem uses lowercase English letters, so the HashMap can contain at most 26 distinct characters.

---

## 8. Key Takeaways

1. Use `HashMap<Character, Integer>` to store character frequencies.
2. Use `getOrDefault()` to count characters conveniently.
3. Increase counts while traversing `magazine`.
4. Decrease counts while processing `ransomNote`.
5. Return `false` when a required character has no remaining count.
6. Return `true` when every required character is available.

### Pattern to Remember

**Count → Check → Decrease → Return**

Real-world example: If a recipe requires two eggs but you have only one egg, you cannot complete the recipe. Similarly, a ransom note cannot be constructed if the magazine does not contain enough copies of every required character.
