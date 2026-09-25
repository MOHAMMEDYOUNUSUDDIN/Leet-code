# 🔗 Linked List — Slow & Fast Pointer Pattern

The **Slow & Fast Pointer** technique is one of the most important patterns for solving Linked List problems.

The basic idea is:

* `slow` moves **1 step**
* `fast` moves **2 steps**

```java
slow = slow.next;
fast = fast.next.next;
```

This pattern is mainly used for:

1. Finding a **cycle** in a Linked List
2. Finding the **starting point of a cycle**
3. Finding the **middle of a Linked List**

---

# 🧠 How to Identify the Pattern?

When a Linked List question asks something related to:

### 1️⃣ "Does the Linked List contain a cycle?"

Look for:

> Detect cycle / Loop / Circular Linked List / Repeated node

Use **Slow & Fast Pointers**.

---

### 2️⃣ "Find the starting point of the cycle"

Look for:

> Where does the cycle begin?
> Return the node where the cycle starts.

Use **Slow & Fast Pointers + Reset Pointer**.

---

### 3️⃣ "Find the middle of the Linked List"

Look for:

> Middle node
> Middle element
> Second half of Linked List
> Split Linked List into two halves

Use **Slow & Fast Pointers**.

---

# 🎯 The Core Idea

Think of the Linked List like this:

```text
1 → 2 → 3 → 4 → 5 → NULL
```

or:

```text
1 → 2 → 3 → 4 → 5
        ↑       ↓
        ← ← ← ←
```

The second one contains a cycle.

We use:

```text
slow → 1 step
fast → 2 steps
```

---

# 🚀 STEP 1 — Detect a Cycle

Initialize:

```java
ListNode slow = head;
ListNode fast = head;
```

Here:

* `head` = starting point of Linked List
* `slow` = starts at `head`
* `fast` = starts at `head`

Then:

```java
while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        return true;
    }
}

return false;
```

---

# ⚠️ Important Condition

Always remember:

```java
while (fast != null && fast.next != null)
```

Why?

Because `fast` moves:

```java
fast = fast.next.next;
```

So before accessing `fast.next.next`, we need to make sure:

```text
fast != null
fast.next != null
```

Otherwise, we can get a `NullPointerException`.

---

# 🔄 Why Does `slow == fast` Mean Cycle?

Imagine a race track.

If there is a straight road:

```text
1 → 2 → 3 → 4 → 5 → NULL
```

The fast pointer will eventually reach `NULL`.

But if there is a cycle:

```text
        ┌─────────┐
        ↓         │
1 → 2 → 3 → 4 → 5
        ↑         │
        └─────────┘
```

Both pointers enter the cycle.

`fast` moves faster than `slow`.

Eventually, `fast` catches `slow`.

Therefore:

```java
if (slow == fast)
```

means:

> A cycle exists.

---

# 🧪 Example — Cycle Detection

Consider:

```text
1 → 2 → 3 → 4 → 5
        ↑       ↓
        ← ← ← ←
```

The cycle starts at `3`.

Initial:

```text
slow = 1
fast = 1
```

### Iteration 1

```text
slow = 2
fast = 3
```

### Iteration 2

```text
slow = 3
fast = 5
```

### Iteration 3

```text
slow = 4
fast = 4
```

Now:

```java
slow == fast
```

Therefore:

```text
Cycle detected ✅
```

---

# 💡 Pattern #1 — Cycle Detection

### Question may be written in different ways:

* Detect cycle
* Detect loop
* Check whether Linked List is circular
* Determine if Linked List contains a cycle
* Return true if a cycle exists
* Find whether any node is repeated by traversal

All of these can indicate:

```text
SLOW + FAST POINTER
```

---

# 🎯 STEP 2 — Find Starting Point of Cycle

Sometimes the question is not:

> "Does a cycle exist?"

Instead, it asks:

> "Where does the cycle start?"

Example:

```text
1 → 2 → 3 → 4 → 5
        ↑       ↓
        ← ← ← ←
```

The answer is:

```text
3
```

---

# 🔥 Floyd's Cycle Detection Algorithm

We use two phases.

## Phase 1 — Find Meeting Point

First:

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        break;
    }
}
```

When:

```text
slow == fast
```

we know that a cycle exists.

But this node is **NOT necessarily the starting node of the cycle**.

It is only the **meeting point**.

---

# Phase 2 — Find Starting Point

Now create another pointer:

```java
ListNode start = head;
```

Then move both pointers one step at a time:

```java
while (start != slow) {

    start = start.next;
    slow = slow.next;
}
```

When:

```java
start == slow
```

that node is the:

> **Starting point of the cycle**

---

# 🧠 Why Does This Work?

Suppose:

```text
head
 ↓
1 → 2 → 3 → 4 → 5
        ↑       ↓
        ← ← ← ←
```

Let:

```text
distance from head to cycle start = a
distance from cycle start to meeting point = b
cycle length = c
```

When `slow` and `fast` meet, mathematical properties of the cycle guarantee that:

```text
distance(head → cycle start)
=
distance(meeting point → cycle start)
```

when moving one step at a time around the cycle.

Therefore:

```text
head pointer
      ↓
      1 → 2 → 3
              ↑
              │
           cycle start

meeting point
      ↓
      4 → 5 → 3
```

Move both one step at a time.

They meet at:

```text
3
```

which is the cycle's starting point.

---

# 🧪 Complete Example

```text
1 → 2 → 3 → 4 → 5
        ↑       ↓
        ← ← ← ←
```

### Phase 1

```text
slow → 1
fast → 1
```

Move:

```text
slow → 2
fast → 3
```

Move:

```text
slow → 3
fast → 5
```

Move:

```text
slow → 4
fast → 4
```

Meeting point:

```text
slow = 4
fast = 4
```

---

### Phase 2

Reset:

```text
start = head = 1
slow = 4
```

Move both:

```text
start → 2
slow  → 5
```

Again:

```text
start → 3
slow  → 3
```

Now:

```text
start == slow
```

Therefore:

```text
Cycle starts at node 3 ✅
```

---

# 💻 Complete Code — Find Cycle Starting Point

```java
public ListNode detectCycle(ListNode head) {

    ListNode slow = head;
    ListNode fast = head;

    // Phase 1: Detect cycle
    while (fast != null && fast.next != null) {

        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            break;
        }
    }

    // No cycle
    if (fast == null || fast.next == null) {
        return null;
    }

    // Phase 2: Find cycle starting point
    ListNode start = head;

    while (start != slow) {

        start = start.next;
        slow = slow.next;
    }

    return start;
}
```

---

# 🎯 Pattern #2 — Cycle Starting Point

If the question asks:

```text
Where does the cycle start?
Find the node where the loop begins.
Return the beginning of the cycle.
```

Think:

```text
SLOW + FAST
      ↓
Find meeting point
      ↓
Reset one pointer to HEAD
      ↓
Move both by 1
      ↓
Meeting point = cycle start
```

---

# 🎯 STEP 3 — Find Middle of Linked List

Another common Slow & Fast Pointer problem is:

> Find the middle node of a Linked List.

Example:

```text
1 → 2 → 3 → 4 → 5
```

Expected:

```text
3 → 4 → 5
```

Use:

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;
}

return slow;
```

---

# 🧪 Middle of Linked List — Dry Run

Linked List:

```text
1 → 2 → 3 → 4 → 5
```

Initial:

```text
slow = 1
fast = 1
```

### Iteration 1

```text
slow = 2
fast = 3
```

### Iteration 2

```text
slow = 3
fast = 5
```

Next condition:

```java
fast != null && fast.next != null
```

Here:

```text
fast = 5
fast.next = null
```

Condition becomes false.

Therefore:

```text
slow = 3
```

Answer:

```text
3 → 4 → 5
```

---

# ⚠️ Even Number of Nodes

Consider:

```text
1 → 2 → 3 → 4 → 5 → 6
```

Using:

```java
while (fast != null && fast.next != null)
```

`slow` will stop at:

```text
4
```

So for an even-sized Linked List, this standard implementation returns the **second middle node**.

```text
1 → 2 → 3 → 4 → 5 → 6
          ↑     ↑
        first  second
       middle  middle

                 ↑
              returned
```

---

# 🧠 The Main Mental Model

Whenever you see a Linked List question involving:

```text
Cycle
Loop
Repeated node
Cycle starting point
Middle
Split into halves
Fast/slow movement
```

Immediately think:

```text
        SLOW + FAST
             │
       ┌─────┴─────┐
       ↓           ↓
    1 step      2 steps
```

---

# 📌 Three Most Important Patterns

## 1. Detect Cycle

```java
slow = head;
fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        return true;
    }
}

return false;
```

### Remember:

```text
slow == fast
     ↓
Cycle exists
```

---

## 2. Find Cycle Starting Point

```text
Phase 1:
slow + fast
     ↓
Find meeting point

Phase 2:
one pointer → head
other pointer → meeting point
     ↓
Move both 1 step
     ↓
Meeting point = cycle start
```

---

## 3. Find Middle

```java
slow = head;
fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;
}

return slow;
```

### Remember:

```text
fast reaches the end
        ↓
slow reaches the middle
```

---

# 🔑 One-Line Memory Trick

```text
FAST = 2X SPEED
SLOW = 1X SPEED
```

Therefore:

### No cycle

```text
FAST → NULL
```

### Cycle

```text
FAST catches SLOW
```

### Middle

```text
FAST → END
SLOW → MIDDLE
```

### Cycle Start

```text
FAST catches SLOW
        ↓
Reset one pointer to HEAD
        ↓
Move both equally
        ↓
They meet at cycle START
```

---

# 🗺️ Pattern Recognition Cheat Sheet

| Question asks                  | Pattern             |
| ------------------------------ | ------------------- |
| Does Linked List have a cycle? | Slow + Fast         |
| Detect loop                    | Slow + Fast         |
| Find cycle                     | Slow + Fast         |
| Find starting node of cycle    | Slow + Fast + Reset |
| Find beginning of loop         | Slow + Fast + Reset |
| Find middle node               | Slow + Fast         |
| Find second middle             | Slow + Fast         |
| Split Linked List              | Slow + Fast         |
| Find first half / second half  | Slow + Fast         |

---

# 🚨 Important Distinction

Don't confuse these two:

### Detect cycle

```text
slow == fast
```

means:

```text
Cycle exists
```

### Find cycle start

```text
slow == fast
```

is only the **meeting point**.

Then:

```java
start = head;

while (start != slow) {
    start = start.next;
    slow = slow.next;
}
```

The new meeting point is:

```text
CYCLE START
```

---

# 🧩 Related Problems

These concepts appear in many LeetCode problems:

* **141. Linked List Cycle** → Detect cycle
* **142. Linked List Cycle II** → Find cycle starting point
* **876. Middle of the Linked List** → Find middle
* **234. Palindrome Linked List** → Find middle + reverse second half
* **143. Reorder List** → Find middle + reverse + merge
* **287. Find the Duplicate Number** → Same slow/fast cycle idea, but using array values as pointers

---

# ⭐ Final Mental Framework

Whenever you see a question involving a Linked List:

```text
             QUESTION
                 │
                 ↓
       Is it related to position,
       cycle, loop or middle?
                 │
                 ↓
        Try SLOW + FAST
                 │
        ┌────────┼────────┐
        ↓        ↓        ↓
      Cycle    Middle   Cycle Start
        │        │        │
        ↓        ↓        ↓
   slow==fast   slow     Reset
                         ↓
                    Move equally
                         ↓
                    Cycle Start
```

## 🔥 Remember This

```text
Slow → 1 step
Fast → 2 steps

Fast reaches NULL
→ No cycle

Slow meets Fast
→ Cycle exists

Slow meets Fast
+ Reset one pointer to Head
+ Move both one step
→ Cycle starting point

Fast reaches the end
→ Slow is at the middle
```

**This is the core Slow–Fast Pointer pattern.**
