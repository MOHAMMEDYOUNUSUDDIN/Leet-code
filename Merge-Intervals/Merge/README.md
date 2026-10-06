# 🔗 LeetCode 56 — Merge Intervals

## 🧩 Question

Given an array of intervals where:

```text
intervals[i] = [start, end]
```

Merge all overlapping intervals and return an array of the non-overlapping intervals that cover all the intervals.

### Example

**Input:**

```text
[[1,3],[2,6],[8,10],[15,18]]
```

**Output:**

```text
[[1,6],[8,10],[15,18]]
```

### Simple Explanation

`[1,3]` and `[2,6]` overlap because `2 <= 3`.

So they become:

```text
[1,6]
```

But `[6,8]` does not overlap, so `[1,6]` remains separate from `[8,10]`.

---

# 💡 Main Idea

The easiest way to solve this problem is:

1. **Sort intervals by starting point.**
2. Keep track of the current interval using:

   * `start`
   * `end`
3. Compare the current interval with the next interval.
4. If they overlap, extend `end`.
5. If they don't overlap, store the current interval and start a new one.
6. Finally, add the last interval.

---

# 🧠 Pattern Recognition

This problem belongs to the:

## **Intervals / Merge Intervals Pattern**

When you see questions involving:

* `[start, end]`
* Time ranges
* Meeting schedules
* Overlapping ranges
* Merging ranges
* Removing overlapping intervals

Think:

> **Sort by start → Compare end with next start → Merge if overlapping**

### Important condition

```java
if (end >= nextStart)
```

This means the current interval and next interval overlap.

Example:

```text
Current: [1,5]
Next:    [3,7]
```

Since:

```text
5 >= 3
```

They overlap.

Therefore:

```text
[1,7]
```

---

# 🔍 Hint

Before looking at the solution, try thinking about these questions:

### Hint 1

How can you make it easier to detect overlapping intervals?

👉 Sort them by their starting point.

### Hint 2

After sorting, what should you compare?

👉 Current interval's `end` with next interval's `start`.

### Hint 3

If they overlap, what should happen?

👉 Extend the current `end`.

```java
end = Math.max(end, nextEnd);
```

### Hint 4

What if they don't overlap?

👉 Save the current interval and start processing the next interval.

---

# 💻 Code

```java
class Solution {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if (end >= nextStart) {
                end = Math.max(end, nextEnd);
            } 
            else {
                res.add(new int[]{start, end});

                start = nextStart;
                end = nextEnd;
            }
        }

        res.add(new int[]{start, end});

        return res.toArray(new int[res.size()][]);
    }
}
```

---

# 🔎 Code Explanation

## 1. Sort the intervals

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
```

Sort intervals based on their starting value.

Example:

```text
Before:
[[8,10],[1,3],[15,18],[2,6]]

After:
[[1,3],[2,6],[8,10],[15,18]]
```

Now we can process them from left to right.

---

## 2. Create result list

```java
List<int[]> res = new ArrayList<>();
```

This stores the final merged intervals.

---

## 3. Start with the first interval

```java
int start = intervals[0][0];
int end = intervals[0][1];
```

For:

```text
[[1,3], ...]
```

We have:

```text
start = 1
end = 3
```

---

## 4. Traverse remaining intervals

```java
for(int i = 1; i < intervals.length; i++)
```

We start from index `1` because index `0` is already stored in `start` and `end`.

---

## 5. Get the next interval

```java
int nextStart = intervals[i][0];
int nextEnd = intervals[i][1];
```

For:

```text
[2,6]
```

we get:

```text
nextStart = 2
nextEnd = 6
```

---

## 6. Check for overlap

```java
if(end >= nextStart)
```

If:

```text
Current: [1,3]
Next:    [2,6]
```

Then:

```text
3 >= 2
```

So they overlap.

---

## 7. Merge overlapping intervals

```java
end = Math.max(end, nextEnd);
```

For:

```text
Current: [1,3]
Next:    [2,6]
```

We get:

```text
end = max(3,6)
end = 6
```

Current merged interval becomes:

```text
[1,6]
```

---

## 8. Handle non-overlapping intervals

```java
else {
    res.add(new int[]{start,end});

    start = nextStart;
    end = nextEnd;
}
```

Example:

```text
Current: [1,6]
Next:    [8,10]
```

Since:

```text
6 < 8
```

They don't overlap.

So store:

```text
[1,6]
```

Then start processing:

```text
[8,10]
```

---

## 9. Add the final interval

```java
res.add(new int[]{start,end});
```

The last interval won't automatically be added inside the `else`, so we add it after the loop.

---

## 10. Convert List to Array

```java
return res.toArray(new int[res.size()][]);
```

The problem expects:

```text
int[][]
```

So we convert the `List<int[]>` into a 2D array.

---

# 🧪 Dry Run

### Input

```text
[[1,3],[2,6],[8,10],[15,18]]
```

### Step 1 — Sort

Already sorted:

```text
[1,3]
[2,6]
[8,10]
[15,18]
```

Initial:

```text
start = 1
end = 3
res = []
```

### Step 2 — `[2,6]`

Check:

```text
end >= nextStart
3 >= 2 ✅
```

Merge:

```text
end = max(3,6)
end = 6
```

Current:

```text
[1,6]
```

---

### Step 3 — `[8,10]`

Check:

```text
6 >= 8 ❌
```

No overlap.

Add:

```text
res = [[1,6]]
```

Start new interval:

```text
start = 8
end = 10
```

---

### Step 4 — `[15,18]`

Check:

```text
10 >= 15 ❌
```

No overlap.

Add:

```text
res = [[1,6],[8,10]]
```

Start:

```text
start = 15
end = 18
```

---

### Step 5 — Add final interval

After the loop:

```text
res = [[1,6],[8,10],[15,18]]
```

### Final Output

```text
[[1,6],[8,10],[15,18]]
```

---

# 🌍 Real-World Example

Imagine meeting schedules:

```text
Meeting 1: 10:00 - 11:00
Meeting 2: 10:30 - 12:00
Meeting 3: 14:00 - 15:00
```

Meeting 1 and Meeting 2 overlap:

```text
10:00 - 12:00
```

So they can be represented as one continuous time interval.

Final:

```text
10:00 - 12:00
14:00 - 15:00
```

This is exactly what **Merge Intervals** does.

---

# ⏱️ Complexity

### Time Complexity

Sorting takes:

```text
O(n log n)
```

Traversing intervals takes:

```text
O(n)
```

Therefore:

```text
O(n log n)
```

### Space Complexity

Result list requires:

```text
O(n)
```

So:

```text
O(n)
```

---

# 🎯 Pattern to Remember

Whenever you see:

```text
[start, end]
[start, end]
[start, end]
```

and the question asks about **overlapping ranges**, remember:

```text
SORT
  ↓
COMPARE
  ↓
OVERLAP?
 ↙     ↘
YES     NO
 ↓       ↓
MERGE   STORE
```

### Core condition

```java
if (end >= nextStart)
```

### Core merge

```java
end = Math.max(end, nextEnd);
```

### Core pattern

> **Sort → Compare → Merge → Store**

---

# 📝 One-Line Revision

**Merge Intervals = Sort by start time, then merge whenever `currentEnd >= nextStart`.**
