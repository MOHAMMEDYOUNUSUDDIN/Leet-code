# 📌 LeetCode 57 — Insert Interval

## 🧩 Question

You are given a list of non-overlapping intervals sorted by their starting time.

You are also given a `newInterval`.

Insert the new interval into the correct position and merge it with any overlapping intervals.

### Example

**Input:**

```text
intervals = [[1,3],[6,9]]
newInterval = [2,5]
```

**Output:**

```text
[[1,5],[6,9]]
```

### Why?

`[1,3]` overlaps with `[2,5]`.

So:

```text
[1,3] + [2,5] → [1,5]
```

`[6,9]` does not overlap, so it stays separate.

---

# 💡 Main Idea

There are **3 cases** for every interval:

### Case 1 — Current interval is BEFORE new interval

```text
currentEnd < newStart
```

Example:

```text
Current:    [1,2]
New:        [5,7]
```

No overlap.

👉 Directly add current interval.

---

### Case 2 — Current interval is AFTER new interval

```text
currentStart > newEnd
```

Example:

```text
New:        [2,5]
Current:    [7,9]
```

No overlap.

👉 First add `newInterval`, then add current interval.

---

### Case 3 — Intervals OVERLAP

If neither of the above conditions is true, they overlap.

Example:

```text
Current: [1,5]
New:     [2,7]
```

Merge them:

```text
[1,7]
```

Using:

```java
newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
```

---

# 🧠 Pattern Recognition

This is an **Intervals Pattern** problem.

When you see:

* Time ranges
* Meeting schedules
* `[start, end]`
* Insert a range
* Merge overlapping ranges

Think:

> **Before → Overlap → After**

The most important thing is recognizing the relationship between:

```text
Current interval
        ↓
New interval
```

### Pattern

```text
Current BEFORE New
       ↓
     ADD

Current OVERLAPS New
       ↓
     MERGE

Current AFTER New
       ↓
 ADD NEW → ADD CURRENT
```

---

# 💡 Hints

### Hint 1

The intervals are already sorted.

👉 You **do not need to sort** them again.

### Hint 2

Ask for every interval:

> Is it completely before the new interval?

```java
intervals[i][1] < newInterval[0]
```

### Hint 3

Ask:

> Is it completely after the new interval?

```java
intervals[i][0] > newInterval[1]
```

### Hint 4

If neither condition is true:

👉 The intervals overlap.

Merge them using:

```java
Math.min()
Math.max()
```

### Hint 5

What if you reach the end and haven't inserted `newInterval`?

👉 Add it after the loop.

---

# 💻 Code

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();

        boolean insert = false;

        for (int i = 0; i < intervals.length; i++) {

            // Current interval comes before new interval
            if (intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
            }

            // Current interval comes after new interval
            else if (intervals[i][0] > newInterval[1]) {

                if (!insert) {
                    res.add(newInterval);
                    insert = true;
                }

                res.add(intervals[i]);
            }

            // Overlapping intervals
            else {
                newInterval[0] =
                    Math.min(newInterval[0], intervals[i][0]);

                newInterval[1] =
                    Math.max(newInterval[1], intervals[i][1]);
            }
        }

        // If new interval was never inserted
        if (!insert) {
            res.add(newInterval);
        }

        return res.toArray(new int[res.size()][]);
    }
}
```

---

# 🔎 Code Explanation

## 1. Create result list

```java
List<int[]> res = new ArrayList<>();
```

This stores the final intervals.

---

## 2. Track whether new interval was inserted

```java
boolean insert = false;
```

This helps us know whether `[newStart, newEnd]` has already been added to the result.

---

# 3. Traverse the intervals

```java
for (int i = 0; i < intervals.length; i++)
```

We check every interval one by one.

---

# 4. Current interval comes BEFORE new interval

```java
if (intervals[i][1] < newInterval[0]) {
    res.add(intervals[i]);
}
```

Example:

```text
Current: [1,2]
New:     [5,7]
```

Since:

```text
2 < 5
```

There is no overlap.

So:

```text
res = [[1,2]]
```

---

# 5. Current interval comes AFTER new interval

```java
else if (intervals[i][0] > newInterval[1])
```

Example:

```text
New:     [5,7]
Current: [10,12]
```

Since:

```text
10 > 7
```

The new interval should come first.

So:

```java
res.add(newInterval);
```

Then:

```java
res.add(intervals[i]);
```

---

# 6. Overlapping intervals

If the interval is neither before nor after, it must overlap.

```java
else {
    newInterval[0] =
        Math.min(newInterval[0], intervals[i][0]);

    newInterval[1] =
        Math.max(newInterval[1], intervals[i][1]);
}
```

Example:

```text
New:     [3,5]
Current: [2,6]
```

Start:

```text
min(3,2) = 2
```

End:

```text
max(5,6) = 6
```

Merged interval:

```text
[2,6]
```

---

# 7. Add new interval at the end if necessary

```java
if (!insert) {
    res.add(newInterval);
}
```

This handles cases where the new interval belongs at the end or overlaps until the end.

Example:

```text
intervals = [[1,3],[6,9]]
newInterval = [10,12]
```

After processing:

```text
res = [[1,3],[6,9]]
```

`newInterval` hasn't been inserted.

So we add:

```text
[10,12]
```

Final:

```text
[[1,3],[6,9],[10,12]]
```

---

# 🧪 Dry Run

## Input

```text
intervals = [[1,3],[6,9]]
newInterval = [2,5]
```

Initial:

```text
res = []
newInterval = [2,5]
insert = false
```

---

### Step 1 — `[1,3]`

Check:

```text
3 < 2 ❌
```

Not before.

Check:

```text
1 > 5 ❌
```

Not after.

Therefore, overlap.

Merge:

```text
start = min(2,1) = 1
end   = max(5,3) = 5
```

Now:

```text
newInterval = [1,5]
```

---

### Step 2 — `[6,9]`

Check:

```text
9 < 1 ❌
```

Not before.

Check:

```text
6 > 5 ✅
```

Current interval is after the new interval.

So add:

```text
res = [[1,5]]
```

Then add current interval:

```text
res = [[1,5],[6,9]]
```

---

### Final Output

```text
[[1,5],[6,9]]
```

---

# 🧪 Dry Run 2 — New Interval at Beginning

### Input

```text
intervals = [[5,7],[9,12]]
newInterval = [1,3]
```

First interval:

```text
[5,7]
```

Check:

```text
5 > 3 ✅
```

So add new interval first:

```text
res = [[1,3]]
```

Then add:

```text
[5,7]
```

Next:

```text
[9,12]
```

Again after new interval:

```text
res = [[1,3],[5,7],[9,12]]
```

---

# 🧪 Dry Run 3 — New Interval at End

### Input

```text
intervals = [[1,2],[4,5]]
newInterval = [7,9]
```

`[1,2]`:

```text
2 < 7 ✅
```

Add it.

`[4,5]`:

```text
5 < 7 ✅
```

Add it.

Loop ends.

`newInterval` was never inserted.

So:

```text
res.add(newInterval)
```

Final:

```text
[[1,2],[4,5],[7,9]]
```

---

# 🌍 Real-World Example

Imagine your college timetable:

```text
Existing classes:
10:00 - 11:00
12:00 - 1:00
```

You want to add:

```text
10:30 - 12:30
```

The new class overlaps both existing time slots.

So they become one continuous interval:

```text
10:00 - 1:00
```

This is exactly what **Insert Interval** solves.

---

# 🔥 Difference Between LeetCode 56 and 57

### LeetCode 56 — Merge Intervals

You receive multiple intervals:

```text
[[1,3],[2,6],[8,10]]
```

You need to merge all overlapping intervals.

👉 Usually:

```text
SORT → MERGE
```

---

### LeetCode 57 — Insert Interval

The intervals are **already sorted and non-overlapping**.

You receive one new interval.

```text
[[1,3],[6,9]]
new = [2,5]
```

👉 Usually:

```text
BEFORE → OVERLAP → AFTER
```

No sorting is required.

---

# ⏱️ Complexity

Let `n` be the number of existing intervals.

### Time Complexity

```text
O(n)
```

We visit each interval once.

### Space Complexity

```text
O(n)
```

For storing the result.

---

# 🎯 Pattern Recognition Cheat Sheet

When you see an **interval problem**, ask:

### 1. Are intervals unsorted?

Think:

```text
SORT → MERGE
```

Example:

**LeetCode 56 — Merge Intervals**

---

### 2. Are intervals already sorted and non-overlapping?

Think:

```text
BEFORE → OVERLAP → AFTER
```

Example:

**LeetCode 57 — Insert Interval**

---

### 3. Do you need to find overlapping meetings?

Think:

```text
INTERVAL PATTERN
```

---

# 📝 One-Line Revision

> **Insert Interval = Add intervals before the new one, merge overlapping intervals, then add intervals after it.**

### Core conditions

```java
// Before
intervals[i][1] < newInterval[0]

// After
intervals[i][0] > newInterval[1]

// Otherwise
// Overlap → Merge
```

### Core pattern

```text
BEFORE  → ADD
OVERLAP → MERGE
AFTER   → ADD NEW + ADD CURRENT
```
