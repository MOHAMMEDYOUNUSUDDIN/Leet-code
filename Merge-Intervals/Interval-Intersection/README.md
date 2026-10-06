# 🔗 LeetCode 986 — Interval List Intersections

## 🧩 Question

You are given two lists of intervals:

```text
firstList
secondList
```

Each list is:

* Sorted by starting time
* Non-overlapping within itself

Find the **intersection/overlap** between the two lists.

### Example

**Input:**

```text
firstList =
[[0,2],[5,10],[13,23],[24,25]]

secondList =
[[1,5],[8,12],[15,24],[25,26]]
```

**Output:**

```text
[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```

---

# 💡 Main Idea

Use **Two Pointers**.

We maintain:

```text
i → firstList
j → secondList
```

At every step, compare:

```text
firstList[i]
secondList[j]
```

There are 3 important cases:

### 1. First interval ends before second starts

```java
first[1] < second[0]
```

No overlap.

Move:

```text
i++
```

---

### 2. Second interval ends before first starts

```java
second[1] < first[0]
```

No overlap.

Move:

```text
j++
```

---

### 3. They overlap

The intersection is:

```text
start = max(firstStart, secondStart)
end   = min(firstEnd, secondEnd)
```

So:

```java
int add1 = Math.max(first[0], second[0]);
int add2 = Math.min(first[1], second[1]);
```

Then add:

```text
[start, end]
```

---

# 🧠 Pattern Recognition

This is a combination of:

## **Intervals + Two Pointers**

When you see:

* Two sorted interval arrays
* Find common/overlapping ranges
* Find intersections
* Compare two lists
* Merge information from two sorted lists

Think:

> **Two Pointers + Compare Intervals**

### Core pattern

```text
first[i]  ↔  second[j]
     ↓
Do they overlap?
 ↙          ↘
YES          NO
 ↓            ↓
ADD          Move the
intersection interval that
             ends first
```

---

# 💡 Hints

### Hint 1

Both lists are already sorted.

👉 Don't sort them again.

### Hint 2

Use two pointers:

```java
int i = 0;
int j = 0;
```

### Hint 3

How do you know there is no overlap?

```text
firstEnd < secondStart
```

or

```text
secondEnd < firstStart
```

### Hint 4

If they overlap, what is the intersection?

Think:

```text
START → maximum of both starts
END   → minimum of both ends
```

### Hint 5

After finding an intersection, which pointer should move?

👉 Move the interval that **ends first**.

Why?

Because that interval cannot overlap with any future interval from the other list.

---

# 💻 Code

```java
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> list = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < firstList.length && j < secondList.length) {

            int[] first = firstList[i];
            int[] second = secondList[j];

            // No overlap
            if (first[1] < second[0]) {
                i++;
                continue;
            }

            // No overlap
            if (second[1] < first[0]) {
                j++;
                continue;
            }

            // Overlap
            int add1 = Math.max(first[0], second[0]);
            int add2 = Math.min(first[1], second[1]);

            list.add(new int[]{add1, add2});

            // Move the interval which ends first
            if (first[1] <= second[1]) {
                i++;
            } else {
                j++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
```

---

# 🔎 Code Explanation

## 1. Create result list

```java
List<int[]> list = new ArrayList<>();
```

This stores all the intersections.

---

## 2. Create two pointers

```java
int i = 0;
int j = 0;
```

`i` points to the current interval in `firstList`.

`j` points to the current interval in `secondList`.

Example:

```text
firstList:   [0,2] [5,10] [13,23]
               ↑
               i

secondList: [1,5] [8,12] [15,24]
             ↑
             j
```

---

# 3. Continue while both lists have intervals

```java
while (i < firstList.length && j < secondList.length)
```

We stop when either list has been completely processed.

---

# 4. Get current intervals

```java
int[] first = firstList[i];
int[] second = secondList[j];
```

Now we compare:

```text
first
second
```

---

# 5. First interval is completely before second

```java
if (first[1] < second[0]) {
    i++;
    continue;
}
```

Example:

```text
First:  [0,2]
Second:       [5,8]
```

Since:

```text
2 < 5
```

There is no possibility of overlap.

Move `i`.

---

# 6. Second interval is completely before first

```java
if (second[1] < first[0]) {
    j++;
    continue;
}
```

Example:

```text
First:        [5,8]
Second: [1,3]
```

Since:

```text
3 < 5
```

There is no overlap.

Move `j`.

---

# 7. Find the intersection

If neither condition is true, the intervals overlap.

```java
int add1 = Math.max(first[0], second[0]);
int add2 = Math.min(first[1], second[1]);
```

Why?

### Start

The intersection starts at the **later starting point**.

```text
max(firstStart, secondStart)
```

### End

The intersection ends at the **earlier ending point**.

```text
min(firstEnd, secondEnd)
```

---

## Example

```text
First:  [1,5]
Second: [3,7]
```

Start:

```text
max(1,3) = 3
```

End:

```text
min(5,7) = 5
```

Intersection:

```text
[3,5]
```

---

# 8. Add intersection

```java
list.add(new int[]{add1, add2});
```

The intersection is stored in the result.

---

# 9. Move the interval that ends first

```java
if (first[1] <= second[1]) {
    i++;
} else {
    j++;
}
```

This is the **most important part** of the solution.

Example:

```text
First:  [1,5]
Second: [3,7]
```

First ends at `5`.

Second ends at `7`.

So:

```text
5 < 7
```

Move `i`.

Why?

Because `[1,5]` is finished. It cannot intersect with future intervals after `5`.

---

# 🧪 Dry Run

### Input

```text
firstList =
[[0,2],[5,10],[13,23],[24,25]]

secondList =
[[1,5],[8,12],[15,24],[25,26]]
```

Initially:

```text
i = 0
j = 0
```

---

## Step 1

```text
First  = [0,2]
Second = [1,5]
```

Overlap.

Start:

```text
max(0,1) = 1
```

End:

```text
min(2,5) = 2
```

Add:

```text
[1,2]
```

First ends first:

```text
2 < 5
```

So:

```text
i++
```

Now:

```text
i = 1
j = 0
```

Result:

```text
[[1,2]]
```

---

## Step 2

```text
First  = [5,10]
Second = [1,5]
```

Second ends before first starts?

```text
5 < 5 ❌
```

They touch at `5`, so they overlap.

Intersection:

```text
start = max(5,1) = 5
end   = min(10,5) = 5
```

Add:

```text
[5,5]
```

Second ends first:

```text
5 <= 10
```

So:

```text
j++
```

Result:

```text
[[1,2],[5,5]]
```

---

## Step 3

```text
First  = [5,10]
Second = [8,12]
```

Overlap.

Start:

```text
max(5,8) = 8
```

End:

```text
min(10,12) = 10
```

Add:

```text
[8,10]
```

First ends first:

```text
10 <= 12
```

So:

```text
i++
```

Result:

```text
[[1,2],[5,5],[8,10]]
```

---

## Step 4

```text
First  = [13,23]
Second = [8,12]
```

Second ends before first starts:

```text
12 < 13 ✅
```

So:

```text
j++
```

---

## Step 5

```text
First  = [13,23]
Second = [15,24]
```

Overlap.

Start:

```text
max(13,15) = 15
```

End:

```text
min(23,24) = 23
```

Add:

```text
[15,23]
```

First ends first.

```text
i++
```

---

## Step 6

```text
First  = [24,25]
Second = [15,24]
```

They touch at `24`.

Intersection:

```text
[24,24]
```

Second ends first:

```text
24 <= 25
```

So:

```text
j++
```

---

## Step 7

```text
First  = [24,25]
Second = [25,26]
```

They touch at `25`.

Intersection:

```text
[25,25]
```

Add:

```text
[25,25]
```

Then:

```text
i++
```

---

# ✅ Final Output

```text
[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```

---

# 🌍 Real-World Example

Imagine two people's free time.

### Person A

```text
10:00 - 12:00
14:00 - 16:00
```

### Person B

```text
11:00 - 13:00
15:00 - 17:00
```

Common/free time:

```text
11:00 - 12:00
15:00 - 16:00
```

Those common periods are the **interval intersections**.

---

# 🔥 Important Concept: Why Move the Smaller End?

Suppose:

```text
A = [1,5]
B = [3,10]
```

Intersection:

```text
[3,5]
```

A ends at `5`, B ends at `10`.

A is finished first.

So move A:

```text
i++
```

If we move B instead, we might miss possible intersections.

### Rule

> **After finding an intersection, move the pointer whose interval ends first.**

This is the key to the Two-Pointer approach.

---

# ⏱️ Complexity

Let:

```text
n = firstList.length
m = secondList.length
```

Each pointer only moves forward.

### Time Complexity

```text
O(n + m)
```

### Space Complexity

```text
O(k)
```

where `k` is the number of intersections stored in the result.

---

# 🎯 Pattern Recognition Cheat Sheet

## Pattern 1 — Merge Intervals

**LeetCode 56**

```text
Unsorted intervals
       ↓
Sort
       ↓
Merge overlapping
```

---

## Pattern 2 — Insert Interval

**LeetCode 57**

```text
Sorted intervals
       ↓
BEFORE → OVERLAP → AFTER
```

---

## Pattern 3 — Interval Intersection

**LeetCode 986**

```text
Two sorted interval lists
       ↓
Two Pointers
       ↓
Find overlap
       ↓
Move interval ending first
```

---

# 📝 One-Line Revision

> **Interval Intersection = Use two pointers, find overlap using `max(start)` and `min(end)`, then move the interval that ends first.**

### ⭐ Remember these 3 lines

```java
int start = Math.max(first[0], second[0]);
int end = Math.min(first[1], second[1]);

if (first[1] <= second[1]) i++;
else j++;
```

### 🧠 Pattern

```text
TWO SORTED INTERVAL LISTS
          ↓
    TWO POINTERS
          ↓
     FIND OVERLAP
          ↓
    MOVE SMALLER END
```
