# 🪟 Sliding Window — Pattern Recognition

The **Sliding Window** technique is one of the most important patterns for solving **subarray and substring** problems efficiently.

The main idea is:

> Instead of repeatedly calculating every possible subarray/substring, maintain a **window** and slide it through the array/string.

A window is represented using two pointers:

```text id="slwin01"
left → [ WINDOW ] ← right
```

Usually:

```java
left = 0;
right = 0;
```

The `right` pointer expands the window.

The `left` pointer shrinks the window when a condition is violated.

---

# 🧠 How to Identify Sliding Window?

Don't look only for the words **"sliding window"**.

The question can be written in many different ways.

Look for:

```text id="slwin02"
Subarray
Substring
Contiguous
Continuous
Longest
Shortest / Minimum
Maximum
At most K
At least K
Exactly K
Without repeating
Distinct characters
Consecutive elements
Window of size K
```

These are strong signals that **Sliding Window** may be useful.

---

# 🎯 The Most Important Signal

Whenever you see:

```text id="slwin03"
CONTIGUOUS
```

think:

```text
🪟 SLIDING WINDOW
```

Because:

### Subarray

```text id="slwin04"
[2, 3, 4]
```

means the elements are next to each other.

### Subsequence

```text id="slwin05"
[2, 4]
```

does NOT necessarily have to be continuous.

So:

```text
SUBARRAY / SUBSTRING
        ↓
CONTIGUOUS
        ↓
Think Sliding Window
```

---

# 🔥 Two Main Types of Sliding Window

There are two major forms:

```text id="slwin06"
1. Fixed Size Window
2. Variable Size Window
```

You should learn to identify which one the question requires.

---

# 1️⃣ Fixed Size Sliding Window

The window size is given.

For example:

> Find the maximum sum of a subarray of size `k`.

If:

```text
k = 3
```

the window always contains exactly 3 elements.

Example:

```text id="slwin07"
[1, 2, 3, 4, 5, 6]
 └─────┘
  size 3
```

Then:

```text id="slwin08"
    [2, 3, 4]
```

Then:

```text id="slwin09"
       [3, 4, 5]
```

Then:

```text id="slwin10"
          [4, 5, 6]
```

The window moves one position at a time.

---

# 🧠 How to Recognize Fixed Window?

Look for:

```text id="slwin11"
Subarray of size K
Substring of length K
Window size K
Exactly K consecutive elements
Maximum sum of K elements
Minimum sum of K elements
Average of every K elements
```

The important clue is:

```text
SIZE = K
```

---

# 🧪 Example — Maximum Sum of Size K

Array:

```text id="slwin12"
[2, 1, 5, 1, 3, 2]
```

Given:

```text
k = 3
```

We need the maximum sum of any 3 consecutive elements.

---

## Window 1

```text id="slwin13"
[2, 1, 5]  1  3  2
```

Sum:

```text
2 + 1 + 5 = 8
```

---

## Slide Window

Instead of calculating:

```text
1 + 5 + 1
```

from scratch, remove the outgoing element and add the incoming element.

Remove:

```text
2
```

Add:

```text
1
```

So:

```text
8 - 2 + 1 = 7
```

Window:

```text
slwin14
2 [1, 5, 1] 3 2
```

---

## Slide Again

Remove:

```text
1
```

Add:

```text
3
```

```text
7 - 1 + 3 = 9
```

Window:

```text
2 1 [5, 1, 3] 2
```

---

## Slide Again

Remove:

```text
5
```

Add:

```text
2
```

```text
9 - 5 + 2 = 6
```

Window:

```text
2 1 5 [1, 3, 2]
```

Maximum:

```text
9
```

---

# 💻 Fixed Window Template

```java
int left = 0;
int sum = 0;
int maxSum = Integer.MIN_VALUE;

for (int right = 0; right < nums.length; right++) {

    sum += nums[right];

    if (right - left + 1 == k) {

        maxSum = Math.max(maxSum, sum);

        sum -= nums[left];
        left++;
    }
}

return maxSum;
```

The key condition is:

```java
right - left + 1 == k
```

This tells us:

> The window has reached size `k`.

---

# 🔑 Fixed Window Memory Trick

```text
ADD right
   ↓
Window reaches K?
   ↓
YES
   ↓
Calculate answer
   ↓
Remove left
   ↓
left++
```

Remember:

```text
ADD → CHECK → REMOVE → SLIDE
```

---

# 2️⃣ Variable Size Sliding Window

This is more important and slightly more difficult.

Here the window size is **not fixed**.

The window grows and shrinks depending on a condition.

Visual:

```text
left
 ↓
[        WINDOW        ]
                     ↑
                   right
```

Right expands:

```text
right++
```

When the window becomes invalid:

```text
left++
```

until the window becomes valid again.

---

# 🧠 How to Recognize Variable Window?

Look for:

```text
Longest subarray
Longest substring
Minimum subarray
Smallest substring
At most K
At least K
Contains all characters
Without repeating characters
Maximum length satisfying condition
Minimum length satisfying condition
```

Especially:

```text
LONGEST
MINIMUM
AT MOST
AT LEAST
```

combined with:

```text
SUBARRAY / SUBSTRING
```

is a strong Sliding Window signal.

---

# 🎯 Example — Longest Substring Without Repeating Characters

Question:

> Find the length of the longest substring without repeating characters.

Example:

```text
abcabcbb
```

We need:

```text
abc
```

Length:

```text
3
```

---

# 🧠 Window Concept

Start:

```text
left = 0
right = 0
```

Window:

```text
[a]
```

Then:

```text
[ab]
```

Then:

```text
[abc]
```

All characters are unique.

Continue:

```text
[abca]
```

Now:

```text
a
```

is repeated.

The window is invalid.

So move `left`.

```text
a[bca]
```

Now the window is valid again.

---

# 🧪 Complete Dry Run

String:

```text
abcabcbb
```

We maintain a set of characters.

### Step 1

```text
a
```

Window:

```text
[a]
```

Length:

```text
1
```

Maximum:

```text
1
```

---

### Step 2

```text
ab
```

All unique.

```text
Length = 2
Maximum = 2
```

---

### Step 3

```text
abc
```

All unique.

```text
Length = 3
Maximum = 3
```

---

### Step 4

Add `a`:

```text
abca
```

`a` is repeated.

Window becomes invalid.

Move `left`:

```text
a[bca]
```

Now remove the old `a`.

Window:

```text
bca
```

Length:

```text
3
```

---

### Step 5

Add `b`:

```text
bcab
```

`b` is repeated.

Shrink:

```text
b[cab]
```

Window:

```text
cab
```

Length:

```text
3
```

---

Continue similarly.

Final answer:

```text
3
```

---

# 💻 Variable Window Template

A very important template:

```java
int left = 0;

for (int right = 0; right < nums.length; right++) {

    // Add nums[right] to window

    while (windowIsInvalid()) {

        // Remove nums[left] from window
        left++;
    }

    // Window is valid here

    // Update answer
}
```

The structure is:

```text
right++
   ↓
ADD new element
   ↓
Is window invalid?
   ↓
YES
   ↓
Shrink from left
   ↓
left++
   ↓
Window valid
   ↓
Update answer
```

---

# 🔥 Most Important Variable Window Pattern

```text
EXPAND → INVALID → SHRINK → VALID → ANSWER
```

Remember this.

---

# 🎯 Longest vs Minimum Window

This distinction is extremely important.

## Longest Valid Window

Question:

> Find the longest subarray satisfying a condition.

Usually:

```java
while (invalid) {
    left++;
}

answer = Math.max(answer, right - left + 1);
```

Why?

Because after shrinking, the window is valid.

Then we try to make it as **large as possible**.

---

## Minimum Valid Window

Question:

> Find the smallest subarray satisfying a condition.

Usually:

```java
while (valid) {

    answer = Math.min(answer, right - left + 1);

    left++;
}
```

Why?

Because while the window is valid, we try to **shrink it as much as possible**.

---

# 🧠 Very Important Difference

### Longest

```text
Expand
 ↓
Invalid?
 ↓
Shrink until valid
 ↓
Record maximum
```

### Minimum

```text
Expand
 ↓
Valid?
 ↓
Record minimum
 ↓
Shrink
 ↓
Try again
```

---

# 🪟 Sliding Window With Sum

Example:

```text
nums = [2, 3, 1, 2, 4, 3]
target = 7
```

Question:

> Find the minimum length subarray whose sum is at least `7`.

This is a classic variable window.

Start:

```text
left = 0
sum = 0
```

Expand right.

```text
[2]
sum = 2
```

Expand:

```text
[2,3]
sum = 5
```

Expand:

```text
[2,3,1]
sum = 6
```

Expand:

```text
[2,3,1,2]
sum = 8
```

Now:

```text
sum >= 7
```

Window is valid.

Length:

```text
4
```

Try shrinking from left.

Remove `2`:

```text
[3,1,2]
sum = 6
```

Now invalid.

So minimum currently:

```text
4
```

Continue expanding.

Eventually:

```text
[4,3]
```

has sum:

```text
7
```

Length:

```text
2
```

Answer:

```text
2
```

---

# 💻 Minimum Size Subarray Template

```java
int left = 0;
int sum = 0;
int minLength = Integer.MAX_VALUE;

for (int right = 0; right < nums.length; right++) {

    sum += nums[right];

    while (sum >= target) {

        minLength = Math.min(
            minLength,
            right - left + 1
        );

        sum -= nums[left];
        left++;
    }
}

return minLength == Integer.MAX_VALUE ? 0 : minLength;
```

---

# 🧠 Sliding Window + HashMap

Many substring problems require tracking frequencies.

Example:

```text
"aaabbc"
```

We can maintain:

```java
Map<Character, Integer> map;
```

When expanding:

```java
map.put(
    s.charAt(right),
    map.getOrDefault(s.charAt(right), 0) + 1
);
```

When shrinking:

```java
char ch = s.charAt(left);

map.put(ch, map.get(ch) - 1);

if (map.get(ch) == 0) {
    map.remove(ch);
}

left++;
```

So a common combination is:

```text
Sliding Window
+
HashMap / HashSet
```

---

# 🎯 Common Sliding Window Combinations

### Sliding Window + Set

Used for:

```text
Unique characters
No duplicates
```

Example:

```text
Longest Substring Without Repeating Characters
```

---

### Sliding Window + HashMap

Used for:

```text
Character frequency
Number frequency
Anagram
Required counts
```

Examples:

```text
Minimum Window Substring
Permutation in String
Find All Anagrams
```

---

### Sliding Window + Counter

Used for:

```text
At most K distinct
Exactly K distinct
Frequency conditions
```

---

# 🔥 "At Most K" Pattern

A very important phrase:

```text
AT MOST K
```

Usually think:

```text
Sliding Window
```

Example:

> Longest substring containing at most `K` distinct characters.

Concept:

```text
Expand right
    ↓
Add character
    ↓
Distinct > K?
    ↓
YES
    ↓
Shrink from left
    ↓
Distinct <= K
    ↓
Update maximum
```

Template:

```java
while (map.size() > k) {
    // remove s[left]
    left++;
}

answer = Math.max(
    answer,
    right - left + 1
);
```

---

# 🔥 "Exactly K" Pattern

Questions like:

```text
Subarrays with exactly K distinct elements
Substrings with exactly K distinct characters
```

can often be transformed using:

```text
Exactly K
=
At Most K
-
At Most (K - 1)
```

So:

```text
exactly(K)
=
atMost(K) - atMost(K-1)
```

This is a very important Sliding Window idea.

---

# 🧠 How to Identify Sliding Window From Different Question Forms

The same pattern may appear with completely different wording.

### Question 1

> Find the maximum sum of `k` consecutive elements.

Think:

```text
FIXED WINDOW
```

---

### Question 2

> Find the longest substring without repeating characters.

Think:

```text
VARIABLE WINDOW
+
SET / MAP
```

---

### Question 3

> Find the smallest subarray whose sum is at least target.

Think:

```text
VARIABLE WINDOW
+
SHRINK WHEN VALID
```

---

### Question 4

> Find the longest substring containing at most K distinct characters.

Think:

```text
VARIABLE WINDOW
+
HASHMAP
+
AT MOST K
```

---

### Question 5

> Find all anagrams of a pattern in a string.

Think:

```text
FIXED WINDOW
+
FREQUENCY MAP
```

---

# ⚠️ Sliding Window vs Two Pointers

This is very important.

Sliding Window technically uses **two pointers**, but not every Two Pointer problem is a Sliding Window problem.

### Two Pointers

Example:

```text
[1, 2, 3, 4, 5]
 ↑           ↑
left       right
```

You might compare two elements.

Example:

```text
Two Sum
Palindrome
Container With Most Water
```

---

### Sliding Window

The two pointers define a **continuous range**:

```text
[1, 2, 3, 4, 5, 6]
    ↑────────↑
   left     right
```

Everything between `left` and `right` is the current window.

Example:

```text
Substring
Subarray
Consecutive
Longest
Minimum
At most K
```

---

# 🧠 Easy Difference

Remember:

```text
TWO POINTERS
→ Two positions

SLIDING WINDOW
→ Two positions + CONTINUOUS RANGE
```

So:

```text
Two Pointers
      ↓
left/right are pointers

Sliding Window
      ↓
left/right define a window
```

---

# 🗺️ Pattern Recognition Cheat Sheet

| Question asks                | Think                    |
| ---------------------------- | ------------------------ |
| Subarray of size K           | Fixed Window             |
| Substring of size K          | Fixed Window             |
| Maximum sum of K consecutive | Fixed Window             |
| Minimum sum of K consecutive | Fixed Window             |
| Longest substring            | Variable Window          |
| Longest subarray             | Variable Window          |
| Smallest subarray            | Variable Window          |
| Minimum window               | Variable Window          |
| At most K                    | Variable Window          |
| At least K                   | Often Variable Window    |
| Without repeating characters | Window + Set/Map         |
| K distinct characters        | Window + HashMap         |
| Anagram in string            | Fixed Window + Frequency |
| Permutation in string        | Fixed Window + Frequency |
| Exactly K distinct           | AtMost(K) - AtMost(K-1)  |

---

# 💻 Universal Sliding Window Template

## Fixed Size

```java
int left = 0;

for (int right = 0; right < n; right++) {

    // Add right element

    if (right - left + 1 == k) {

        // Calculate answer

        // Remove left element
        left++;
    }
}
```

---

## Variable Size

```java
int left = 0;

for (int right = 0; right < n; right++) {

    // Add right element

    while (windowIsInvalid()) {

        // Remove left element
        left++;
    }

    // Window is valid
    // Update answer
}
```

---

# 🔥 Final Mental Framework

When you see:

```text
ARRAY / STRING
      │
      ↓
Is it CONTIGUOUS?
      │
      ├── NO → Maybe not Sliding Window
      │
      └── YES
            ↓
      SUBARRAY / SUBSTRING
            ↓
       Sliding Window
            │
       ┌────┴────┐
       ↓         ↓
   Fixed K    Variable
       │         │
       ↓         ↓
   size = K   condition
       │         │
       ↓         ↓
   Add right   Expand
   Calculate      ↓
   Remove left  Invalid?
                ↓
              Shrink
                ↓
              Valid
                ↓
             Answer
```

---

# 🔑 Golden Rules

```text
CONTIGUOUS
    ↓
Think Sliding Window
```

```text
SIZE = K
    ↓
Think Fixed Window
```

```text
LONGEST
    ↓
Expand + shrink when invalid
    ↓
MAXIMUM answer
```

```text
MINIMUM / SMALLEST
    ↓
Expand until valid
    ↓
Shrink while valid
```

```text
AT MOST K
    ↓
Sliding Window
```

```text
UNIQUE / FREQUENCY / DISTINCT
    ↓
Sliding Window + Set/HashMap
```

```text
EXACTLY K
    ↓
Often:
AtMost(K) - AtMost(K-1)
```

---

# 🧩 Important LeetCode Problems

## 🟢 Basic

* **643. Maximum Average Subarray I** → Fixed Window
* **1456. Maximum Number of Vowels in a Substring of Given Length** → Fixed Window
* **121. Best Time to Buy and Sell Stock** → Sliding Window / Two Pointer idea

## 🟡 Intermediate

* **3. Longest Substring Without Repeating Characters** → Variable Window + Set
* **209. Minimum Size Subarray Sum** → Variable Window
* **567. Permutation in String** → Fixed Window + Frequency
* **438. Find All Anagrams in a String** → Fixed Window + Frequency
* **904. Fruit Into Baskets** → At Most 2 Distinct
* **1004. Max Consecutive Ones III** → At Most K
* **424. Longest Repeating Character Replacement** → Variable Window + Frequency

## 🔴 Advanced

* **76. Minimum Window Substring** → Variable Window + HashMap
* **992. Subarrays with K Different Integers** → Exactly K
* **239. Sliding Window Maximum** → Sliding Window + Monotonic Deque

---

# 🏆 Final Pattern Recognition

Don't memorize every Sliding Window problem separately.

Remember this:

```text
                ARRAY / STRING
                      │
                      ↓
               CONTIGUOUS?
                      │
                     YES
                      ↓
              SLIDING WINDOW
                      │
             ┌────────┴────────┐
             ↓                 ↓
          FIXED             VARIABLE
             │                 │
             ↓                 ↓
          SIZE K          CONDITION BASED
             │                 │
             ↓                 ↓
       ADD → CALCULATE    EXPAND RIGHT
       → REMOVE LEFT           ↓
                            INVALID?
                              ↓
                           SHRINK LEFT
                              ↓
                            VALID
                              ↓
                           ANSWER
```

# ⭐ One-Line Memory Trick

```text
SLIDING WINDOW =
CONTIGUOUS RANGE
+
LEFT & RIGHT POINTERS
+
EXPAND / SHRINK
```

### And the most important recognition rule:

```text
SUBARRAY / SUBSTRING
        +
CONTIGUOUS
        +
LONGEST / MINIMUM / K / DISTINCT
        ↓
   🪟 SLIDING WINDOW
```

**Your goal is not to memorize "Sliding Window problems." Your goal is to recognize:**

```text
"What is my window?"
"How do I expand it?"
"When does it become invalid?"
"How do I shrink it?"
"When do I update my answer?"
```

Once you can answer those four questions, you can solve most Sliding Window Problems.
