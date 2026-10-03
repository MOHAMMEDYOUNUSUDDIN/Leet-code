# LeetCode 525 - Contiguous Array

🔗 Problem: https://leetcode.com/problems/contiguous-array/description/

## Pattern

**Prefix Sum + HashMap**

---

## 1. Problem

Given a binary array containing only `0` and `1`, find the maximum length of a contiguous subarray that contains an equal number of `0`s and `1`s.

### Example

```text
Input:
[0, 1, 0, 1]

Output:
4
```

Explanation:

```text
0s = 2
1s = 2
```

The complete array has equal `0`s and `1`s, so the answer is `4`.

---

# 2. Main Idea

Maintain two counters:

```text
zero = number of 0s seen
one  = number of 1s seen
```

At every index calculate:

```text
diff = one - zero
```

The important observation is:

> If the same `diff` appears at two different indices, the elements between those indices contain an equal number of `0`s and `1`s.

---

# 3. Why Same Difference Works?

Example:

```text
[0, 1, 0, 1]
```

Track the difference:

```text
Index     Value     one     zero     diff
------------------------------------------
  0         0        0       1       -1
  1         1        1       1        0
  2         0        1       2       -1
  3         1        2       2        0
```

Notice:

```text
diff = -1
```

appears at index `0` and index `2`.

Also:

```text
diff = 0
```

appears at index `1` and index `3`.

When the same difference occurs again, the changes between those two positions cancel out.

Therefore:

```text
Same difference
       ↓
Equal number of 0s and 1s
       ↓
Balanced subarray
```

---

# 4. HashMap

We use:

```text
difference → first index
```

Example:

```text
{
    0  → -1,
   -1  → 0,
    1  → 4
}
```

We store only the **first occurrence** of each difference.

### Why?

Suppose:

```text
diff = -1
```

appears at:

```text
index 0
index 2
index 5
```

When we are at index `5`, we want:

```text
5 - 0 = 5
```

instead of:

```text
5 - 2 = 3
```

The first occurrence gives the longest possible subarray.

---

# 5. Why `map.put(0, -1)`?

We initialize:

```java
map.put(0, -1);
```

This means:

```text
difference = 0
index = -1
```

Think of `-1` as a position just before the array starts.

Example:

```text
[0, 1]
```

At index `1`:

```text
one = 1
zero = 1

diff = 0
```

The map contains:

```text
0 → -1
```

Therefore:

```text
length = currentIndex - firstIndex
       = 1 - (-1)
       = 2
```

So the balanced subarray has length `2`.

---

# 6. Algorithm

```text
1. Create a HashMap.
2. Put 0 → -1 in the HashMap.
3. Set one = 0.
4. Set zero = 0.
5. Set result = 0.
6. Traverse the array.
7. If nums[i] == 1, increment one.
8. Otherwise increment zero.
9. Calculate diff = one - zero.
10. If diff already exists:
       length = i - firstIndex
       update maximum length.
11. Otherwise:
       store diff → current index.
12. Return result.
```

---

# 7. Java Code

```java
import java.util.HashMap;

class Solution {
    public int findMaxLength(int[] nums) {

        int one = 0;
        int zero = 0;
        int res = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        // Difference 0 exists before the array starts
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                one++;
            } else {
                zero++;
            }

            int diff = one - zero;

            if (map.containsKey(diff)) {

                int idx = map.get(diff);
                int len = i - idx;

                res = Math.max(res, len);

            } else {

                // Store only the first occurrence
                map.put(diff, i);
            }
        }

        return res;
    }
}
```

---

# 8. Complete Dry Run

Example:

```text
nums = [0, 1, 0, 1, 1, 0]
```

Expected answer:

```text
6
```

Initial:

```text
one = 0
zero = 0
res = 0

map = {
    0 → -1
}
```

## Index 0

```text
nums[0] = 0

zero = 1
one = 0

diff = 0 - 1
     = -1
```

`-1` is not in the map.

Store:

```text
-1 → 0
```

---

## Index 1

```text
nums[1] = 1

zero = 1
one = 1

diff = 1 - 1
     = 0
```

`0` exists at index `-1`.

```text
length = 1 - (-1)
       = 2
```

```text
res = 2
```

---

## Index 2

```text
nums[2] = 0

zero = 2
one = 1

diff = 1 - 2
     = -1
```

`-1` exists at index `0`.

```text
length = 2 - 0
       = 2
```

```text
res = 2
```

---

## Index 3

```text
nums[3] = 1

zero = 2
one = 2

diff = 2 - 2
     = 0
```

`0` exists at index `-1`.

```text
length = 3 - (-1)
       = 4
```

```text
res = 4
```

Balanced subarray:

```text
[0, 1, 0, 1]
```

---

## Index 4

```text
nums[4] = 1

zero = 2
one = 3

diff = 3 - 2
     = 1
```

`1` does not exist.

Store:

```text
1 → 4
```

```text
res = 4
```

---

## Index 5

```text
nums[5] = 0

zero = 3
one = 3

diff = 3 - 3
     = 0
```

`0` exists at index `-1`.

```text
length = 5 - (-1)
       = 6
```

```text
res = 6
```

The entire array is balanced:

```text
[0, 1, 0, 1, 1, 0]

zeros = 3
ones  = 3
```

Final answer:

```text
6
```

---

# 9. Dry Run Table

| Index | Value | One | Zero | Diff | Action | Max |
|------:|------:|----:|-----:|-----:|--------|----:|
| -1 | - | 0 | 0 | 0 | `0 → -1` | 0 |
| 0 | 0 | 0 | 1 | -1 | `-1 → 0` | 0 |
| 1 | 1 | 1 | 1 | 0 | Found `0 → -1` | 2 |
| 2 | 0 | 1 | 2 | -1 | Found `-1 → 0` | 2 |
| 3 | 1 | 2 | 2 | 0 | Found `0 → -1` | 4 |
| 4 | 1 | 3 | 2 | 1 | `1 → 4` | 4 |
| 5 | 0 | 3 | 3 | 0 | Found `0 → -1` | **6** |

---

# 10. Visual Understanding

Think of `diff` as a balance.

```text
0 → -1
1 → +1
```

For:

```text
[0, 1, 0, 1]
```

The balance changes:

```text
Start
  ↓
  0
  ↓
 -1
  ↓
  0
  ↓
 -1
  ↓
  0
```

Whenever we reach the **same balance again**, the changes between those positions cancel out.

Therefore:

```text
Same balance
     ↓
Same difference
     ↓
Equal 0s and 1s
     ↓
Valid subarray
```

---

# 11. Complexity

### Time Complexity

```text
O(n)
```

We traverse the array once.

HashMap operations are approximately `O(1)`.

### Space Complexity

```text
O(n)
```

In the worst case, the HashMap can contain `n` different differences.

---

# 12. Pattern Recognition

When you see:

> Find the longest subarray with equal number of 0s and 1s

Think:

```text
Equal 0s and 1s
        ↓
Count difference
        ↓
one - zero
        ↓
Same difference appears again
        ↓
Balanced subarray
        ↓
HashMap
        ↓
Maximum length
```

---

# 13. Key Formula

```text
diff = one - zero
```

If the same `diff` appears again:

```text
length = currentIndex - firstIndex
```

Always store:

```text
difference → FIRST occurrence
```

---

# 14. One-Line Memory Trick

> Same prefix difference = balanced subarray between them.

---

## Pattern

**Prefix Sum + HashMap**

This pattern is useful for many problems involving:

- Longest subarray
- Equal counts
- Zero-sum subarrays
- Prefix sums
- Frequency differences
