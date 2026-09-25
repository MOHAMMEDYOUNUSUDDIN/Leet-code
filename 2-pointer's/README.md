# 👉 Two Pointers — Pattern Recognition

The **Two Pointers** technique is one of the most important patterns in DSA.

The main idea is to use **two indexes/pointers** to process an array or string instead of repeatedly checking elements.

The two pointers can move:

```text
left  → from beginning
right → from end
```

or:

```text
i → moves forward
j → moves forward
```

The exact movement depends on the problem.

---

# 🧠 How to Identify the Two Pointers Pattern?

Don't look only for the words **"two pointers"**.

The question can be written in many different ways.

Look for questions involving:

```text
Two elements
Pair
Target sum
Sorted array
Opposite ends
Left and right
Remove duplicates
Reverse
Palindrome
Container
Closest pair
Triplet
Partition
```

These are strong signals that **Two Pointers** may be useful.

---

# 🎯 The Core Idea

Instead of using nested loops:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        // check pair
    }
}
```

we try to use:

```text
left
  ↓
[1, 2, 3, 4, 5, 6]
                 ↑
                right
```

Then move the pointers intelligently.

---

# 🔥 Most Important Two Pointer Variations

There are mainly three forms you should recognize.

```text
1. Opposite Direction
2. Same Direction
3. Fast/Slow-style movement
```

For normal **Two Pointers**, the first two are especially important.

---

# 1️⃣ Opposite Direction Two Pointers

This is the most common form.

Initialize:

```java
int left = 0;
int right = nums.length - 1;
```

Visual:

```text
left                         right
 ↓                             ↓
[1, 2, 3, 4, 5, 6, 7, 8]
```

Then:

```text
left  → moves right
right → moves left
```

Eventually:

```text
          left
           ↓
[1, 2, 3, 4, 5, 6, 7, 8]
           ↑
          right
```

---

# 🧠 When Should I Think of Opposite Pointers?

Think of this pattern when:

```text
Array is sorted
+
Question involves a pair
+
Need to compare left/right
```

Especially when the question asks:

```text
Find two numbers whose sum is X
Find pair closest to target
Check palindrome
Container with most water
Reverse an array
Reverse a string
Move elements from both sides
```

---

# 🎯 Example — Two Sum II

Suppose:

```text
nums = [1, 2, 3, 4, 6]
target = 6
```

We want:

```text
a + b = target
```

Start:

```text
left = 0
right = 4
```

Visual:

```text
 L                    R
 ↓                    ↓
[1, 2, 3, 4, 6]

1 + 6 = 7
```

Since:

```text
7 > 6
```

we need a **smaller value**.

So move:

```text
right--
```

Now:

```text
 L                 R
 ↓                 ↓
[1, 2, 3, 4, 6]

1 + 4 = 5
```

Now:

```text
5 < 6
```

We need a **larger value**.

So:

```text
left++
```

Now:

```text
    L              R
    ↓              ↓
[1, 2, 3, 4, 6]

2 + 4 = 6
```

Found:

```text
2 + 4 = 6
```

---

# 💡 The Most Important Rule

For a **sorted array**:

```text
sum < target
    ↓
move LEFT forward

sum > target
    ↓
move RIGHT backward

sum == target
    ↓
ANSWER
```

Remember:

```text
Too Small → increase left
Too Large → decrease right
```

---

# 🧪 Two Sum II Dry Run

```text
nums = [1, 2, 3, 4, 6]
target = 6
```

| left | right | values | sum | Action    |
| ---: | ----: | ------ | --: | --------- |
|    0 |     4 | 1 + 6  |   7 | `right--` |
|    0 |     3 | 1 + 4  |   5 | `left++`  |
|    1 |     3 | 2 + 4  |   6 | Found     |

Answer:

```text
2 + 4 = 6
```

---

# 💻 Basic Opposite Pointer Template

```java
int left = 0;
int right = nums.length - 1;

while (left < right) {

    // calculate / compare

    if (condition) {
        left++;
    } else {
        right--;
    }
}
```

The important part is:

```text
left < right
```

because we normally want two different positions.

---

# 2️⃣ Two Pointers for Palindrome

Another common form is checking whether a string is a palindrome.

Example:

```text
"racecar"
```

Use:

```java
int left = 0;
int right = s.length() - 1;
```

Visual:

```text
left                         right
 ↓                             ↓
 r   a   c   e   c   a   r
```

Compare:

```text
s[left] == s[right]
```

If equal:

```text
left++
right--
```

Continue until:

```text
left >= right
```

---

# 🧪 Palindrome Dry Run

String:

```text
racecar
```

### Step 1

```text
r == r
```

Move:

```text
left++
right--
```

### Step 2

```text
a == a
```

Move again.

### Step 3

```text
c == c
```

Move again.

Now:

```text
left >= right
```

Therefore:

```text
Palindrome ✅
```

---

# 💻 Palindrome Template

```java
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
```

---

# 3️⃣ Reverse an Array

Two pointers can also be used to reverse an array.

Example:

```text
[1, 2, 3, 4, 5]
```

Start:

```text
left = 0
right = 4
```

Swap:

```text
1 ↔ 5
```

Array:

```text
[5, 2, 3, 4, 1]
```

Move:

```text
left++
right--
```

Swap:

```text
2 ↔ 4
```

Array:

```text
[5, 4, 3, 2, 1]
```

Done.

---

# 💻 Reverse Array Code

```java
int left = 0;
int right = nums.length - 1;

while (left < right) {

    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;

    left++;
    right--;
}
```

---

# 4️⃣ Same Direction Two Pointers

Not every Two Pointer problem uses:

```text
left ←       → right
```

Sometimes both pointers move in the **same direction**.

Example:

```text
i →
j →
[1, 1, 2, 2, 3, 3]
```

This is commonly used for:

```text
Remove duplicates
Modify array in-place
Partition elements
Merge-like problems
```

---

# 🎯 Example — Remove Duplicates from Sorted Array

Array:

```text
[1, 1, 2, 2, 3]
```

We want:

```text
[1, 2, 3]
```

Use two pointers:

```text
i = position of last unique element
j = scanning pointer
```

Initially:

```text
i = 0
j = 1
```

Visual:

```text
 i  j
 ↓  ↓
[1, 1, 2, 2, 3]
```

Compare:

```text
nums[j] == nums[i]
```

Duplicate.

Move:

```text
j++
```

Now:

```text
 i     j
 ↓     ↓
[1, 1, 2, 2, 3]
```

`2 != 1`

So:

```java
i++;
nums[i] = nums[j];
```

Array becomes logically:

```text
[1, 2, 2, 2, 3]
    ↑
    i
```

Continue.

Final unique portion:

```text
[1, 2, 3]
```

---

# 🧠 Same Direction Mental Model

Think:

```text
slow pointer → stores / builds answer
fast pointer → searches / scans
```

Example:

```text
slow
 ↓
[1, 1, 2, 2, 3, 3]
    ↑
   fast
```

Fast searches.

Slow maintains the valid portion.

This idea appears in:

```text
Remove duplicates
Move zeroes
Remove elements
Partition arrays
```

---

# 5️⃣ Move Zeroes

Example:

```text
[0, 1, 0, 3, 12]
```

Question:

> Move all zeroes to the end while maintaining the order of non-zero elements.

Expected:

```text
[1, 3, 12, 0, 0]
```

Use two pointers.

Think:

```text
slow → position where next non-zero should go
fast → scans the array
```

Start:

```text
slow = 0
fast = 0
```

When:

```text
nums[fast] != 0
```

put it at `slow`.

Then:

```text
slow++
```

`fast` always continues scanning.

---

# 🧪 Move Zeroes Dry Run

```text
[0, 1, 0, 3, 12]
```

### fast = 0

```text
0
```

Ignore.

### fast = 1

```text
1
```

Move `1` to position `slow`.

```text
[1, 0, 0, 3, 12]
 ↑
slow
```

### fast = 2

```text
0
```

Ignore.

### fast = 3

```text
3
```

Move:

```text
[1, 3, 0, 0, 12]
```

### fast = 4

```text
12
```

Move:

```text
[1, 3, 12, 0, 0]
```

Final:

```text
[1, 3, 12, 0, 0]
```

---

# 🧠 How to Recognize Two Pointer Questions

The question may be written differently.

You should train yourself to recognize the **underlying operation**, not the exact wording.

---

## 🔹 Pair / Target Questions

Look for:

```text
Find two numbers
Find a pair
Two elements whose sum is X
Two numbers closest to target
Find pair with given difference
```

Think:

```text
SORTED ARRAY?
     ↓
YES
     ↓
TWO POINTERS
```

---

## 🔹 Opposite Ends Questions

Look for:

```text
From both ends
Left and right
Compare first and last
Reverse
Palindrome
Container
Maximum area
```

Think:

```text
LEFT →        ← RIGHT
```

---

## 🔹 In-place Modification Questions

Look for:

```text
Modify without extra array
Remove duplicates
Remove elements
Move zeroes
Keep relative order
Compress array
```

Think:

```text
SLOW → stores answer
FAST → scans
```

---

# ⚠️ Important: Sorting Can Enable Two Pointers

Sometimes the array is **not sorted**.

Example:

```text
[3, 1, 5, 2, 4]
```

For pair-sum style problems, two pointers often become useful after sorting:

```text
[1, 2, 3, 4, 5]
```

Then:

```text
left = 1
right = 5
```

But remember:

> Sorting changes the original order.

So if the question asks for **original indexes**, you must consider whether sorting would lose the required information.

---

# 🔥 Two Pointers vs Sliding Window

These two patterns can look similar.

### Two Pointers

Usually:

```text
left       right
 ↓           ↓
[ elements ]
```

The pointers move according to a comparison or condition.

Common examples:

```text
Two Sum II
Palindrome
Container With Most Water
3Sum
Remove Duplicates
Move Zeroes
```

### Sliding Window

Usually:

```text
left → [ WINDOW ] ← right
```

You maintain a **continuous subarray/substring**.

Common signals:

```text
Longest substring
Shortest subarray
Maximum/minimum window
At most K
Exactly K
Without repeating characters
```

---

# 🗺️ Pattern Recognition Cheat Sheet

| Question Type             | Pointer Pattern            |
| ------------------------- | -------------------------- |
| Two Sum in sorted array   | Left + Right               |
| Pair with target          | Left + Right               |
| Palindrome                | Left + Right               |
| Reverse array             | Left + Right               |
| Reverse string            | Left + Right               |
| Container With Most Water | Left + Right               |
| 3Sum                      | Sort + Left + Right        |
| Remove duplicates         | Slow + Fast                |
| Remove elements           | Slow + Fast                |
| Move Zeroes               | Slow + Fast                |
| Partition array           | Slow + Fast / Left + Right |
| In-place modification     | Often Slow + Fast          |

---

# 🧠 The Main Mental Model

Whenever you see an array/string question, ask:

```text
                QUESTION
                    │
                    ↓
       Can I solve it using 2 positions?
                    │
             ┌──────┴──────┐
             ↓             ↓
       Opposite ends   Same direction
             │             │
             ↓             ↓
        left + right    slow + fast
             │             │
             ↓             ↓
       Pair / compare    Scan + build
       / reverse         answer
```

---

# 🔥 Three Main Templates

## Template 1 — Left + Right

```java
int left = 0;
int right = nums.length - 1;

while (left < right) {

    // compare / calculate

    if (condition) {
        left++;
    } else {
        right--;
    }
}
```

Use for:

```text
Pair sum
Palindrome
Reverse
Container
Sorted array problems
```

---

## Template 2 — Slow + Fast

```java
int slow = 0;

for (int fast = 0; fast < nums.length; fast++) {

    if (condition) {

        nums[slow] = nums[fast];
        slow++;
    }
}
```

Use for:

```text
Remove duplicates
Remove elements
Move zeroes
In-place filtering
```

---

## Template 3 — Sort + Two Pointers

For problems involving:

```text
Pairs
Triplets
Closest sum
Duplicate combinations
```

Often:

```text
Step 1 → Sort
Step 2 → Fix one element
Step 3 → Use left + right
```

Example:

```text
3Sum
```

Concept:

```text
[-1, 0, 1, 2, -1, -4]

       Sort
        ↓

[-4, -1, -1, 0, 1, 2]

Fix one element
        ↓
Use left + right
```

---

# 🚨 Common Mistakes

### Mistake 1 — Moving the wrong pointer

For sorted pair sum:

```text
sum < target
```

You need a larger value:

```text
left++
```

For:

```text
sum > target
```

you need a smaller value:

```text
right--
```

---

### Mistake 2 — Using `left <= right`

For pair problems, usually:

```java
while (left < right)
```

because we need two different positions.

---

### Mistake 3 — Forgetting that sorting changes indexes

If the problem asks:

```text
Return original indexes
```

be careful before sorting.

---

### Mistake 4 — Confusing Two Pointers with Sliding Window

If the problem specifically deals with:

```text
continuous subarray
continuous substring
longest/shortest window
```

consider **Sliding Window**.

---

# ⭐ Final Memory Framework

When you see a question:

```text
ARRAY / STRING
      │
      ↓
Does it involve two positions?
      │
 ┌────┴────┐
 ↓         ↓
YES        NO
 │
 ↓
Can pointers start
from opposite ends?
 │
 ├── YES ──→ LEFT + RIGHT
 │
 │           ↓
 │      Pair / Palindrome /
 │      Reverse / Container
 │
 └── NO ───→ SLOW + FAST
             ↓
       Scan + Build Answer
             ↓
       Remove / Move /
       Modify / Filter
```

---

# 🔑 One-Line Memory Tricks

```text
LEFT + RIGHT
→ Compare / Pair / Reverse
```

```text
SLOW + FAST
→ Scan / Build / Modify
```

```text
SORT + LEFT + RIGHT
→ Pair / Triplet / Target
```

---

# 🧩 Important LeetCode Problems

### Basic

* **167. Two Sum II** → Left + Right
* **125. Valid Palindrome** → Left + Right
* **344. Reverse String** → Left + Right
* **283. Move Zeroes** → Slow + Fast
* **26. Remove Duplicates from Sorted Array** → Slow + Fast
* **27. Remove Element** → Slow + Fast

### Intermediate

* **11. Container With Most Water** → Left + Right
* **15. 3Sum** → Sort + Left + Right
* **16. 3Sum Closest** → Sort + Left + Right
* **75. Sort Colors** → Multiple pointers / partition
* **18. 4Sum** → Sorting + Multiple pointers

---

# 🏆 Final Pattern Recognition

Don't memorize every problem separately.

Instead remember:

```text
              TWO POINTERS
                    │
          ┌─────────┴─────────┐
          ↓                   ↓
   OPPOSITE DIRECTION   SAME DIRECTION
          │                   │
          ↓                   ↓
    left + right          slow + fast
          │                   │
          ↓                   ↓
   Pair / Compare        Scan / Build
   Reverse               Modify
   Palindrome            Remove
   Container             Move
```

### 🔥 The Golden Rule

```text
Question asks about TWO ELEMENTS
        ↓
Think Two Pointers

Question asks about FIRST + LAST
        ↓
Think Left + Right

Question asks to SCAN + MODIFY
        ↓
Think Slow + Fast

Question asks about PAIRS/TRIPLETS
in a SORTED array
        ↓
Think Sort + Two Pointers
```

**The goal is not to memorize "Two Pointer problems." The goal is to recognize the pointer movement from the question.**
