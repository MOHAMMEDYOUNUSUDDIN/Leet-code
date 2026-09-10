# LeetCode 141 — Linked List Cycle.

## Problem

Given the `head` of a linked list, determine whether the linked list contains a cycle.

A **cycle** exists when a node's `next` pointer points back to a previous node instead of eventually pointing to `null`.

### Example

```text
1 → 2 → 3 → 4
    ↑       ↓
    └───────┘

Cycle exists → true
```

Without a cycle:

```text
1 → 2 → 3 → 4 → null

No cycle → false
```

---

# Approach — Slow and Fast Pointers

We use two pointers:

* **slow** moves one node at a time.
* **fast** moves two nodes at a time.

If a cycle exists, the fast pointer will eventually catch up with the slow pointer.

If there is no cycle, `fast` will eventually reach `null`.

This technique is called the **Floyd's Cycle Detection Algorithm** or **Tortoise and Hare Algorithm**.

---

# Code

```java
public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;

            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

---

# Code Explanation

## 1. Create the slow pointer

```java
ListNode slow = head;
```

`slow` starts from the first node.

It will move **one step at a time**.

Example:

```text
1 → 2 → 3 → 4

slow
 ↓
 1
```

---

## 2. Create the fast pointer

```java
ListNode fast = head;
```

`fast` also starts from the first node.

It will move **two steps at a time**.

Initially:

```text
slow
 ↓
1 → 2 → 3 → 4
 ↑
fast
```

---

# 3. The while condition

```java
while (fast != null && fast.next != null)
```

This checks whether `fast` can safely move two nodes.

We need both:

```text
fast != null
```

and

```text
fast.next != null
```

because we are going to execute:

```java
fast = fast.next.next;
```

### Why `&&`?

Both conditions must be true.

If `fast` is `null`, accessing:

```java
fast.next
```

would cause a `NullPointerException`.

If `fast.next` is `null`, then trying to access:

```java
fast.next.next
```

would also cause a `NullPointerException`.

Therefore:

```java
fast != null && fast.next != null
```

is required.

---

# 4. Move slow

```java
slow = slow.next;
```

Slow moves **one node**.

Example:

```text
1 → 2 → 3 → 4

slow
 ↓
1

After one move:

1 → 2 → 3 → 4
     ↑
    slow
```

---

# 5. Move fast

```java
fast = fast.next.next;
```

Fast moves **two nodes**.

Example:

```text
1 → 2 → 3 → 4

fast
 ↓
1

After two moves:

1 → 2 → 3 → 4
         ↑
        fast
```

---

# 6. Check whether they meet

```java
if (slow == fast) {
    return true;
}
```

This checks whether both pointers are pointing to the **same node**.

Important:

```java
slow == fast
```

means they refer to the same node.

We are NOT checking:

```java
slow.val == fast.val
```

because two different nodes can contain the same value.

If `slow` and `fast` meet, a cycle exists.

Therefore:

```java
return true;
```

---

# 7. Return false

```java
return false;
```

If the loop ends, it means `fast` reached the end of the linked list.

Therefore, there is no cycle.

---

# Dry Run 1 — No Cycle

Consider:

```text
1 → 2 → 3 → 4 → null
```

### Initial state

```text
slow = 1
fast = 1
```

### Iteration 1

Slow moves one step:

```text
slow = 2
```

Fast moves two steps:

```text
fast = 3
```

State:

```text
1 → 2 → 3 → 4 → null
    S   F
```

`slow != fast`

Continue.

---

### Iteration 2

Slow moves:

```text
slow = 3
```

Fast moves two steps:

```text
fast = null
```

Now:

```text
1 → 2 → 3 → 4 → null
        S
```

The while condition fails because:

```text
fast == null
```

So:

```java
return false;
```

### Result

```text
false
```

There is no cycle.

---

# Dry Run 2 — Cycle Exists

Consider:

```text
1 → 2 → 3 → 4
    ↑       ↓
    └───────┘
```

The `4` node points back to `2`.

### Initial state

```text
slow = 1
fast = 1
```

---

### Iteration 1

Slow:

```text
slow = 2
```

Fast:

```text
fast = 3
```

State:

```text
1 → 2 → 3 → 4
    S   F
```

They are different.

---

### Iteration 2

Slow moves:

```text
slow = 3
```

Fast moves two steps:

```text
fast = 2
```

State:

```text
1 → 2 → 3 → 4
    F   S
    ↑       ↓
    └───────┘
```

They are still different.

---

### Iteration 3

Slow moves:

```text
slow = 4
```

Fast moves:

```text
fast = 4
```

Now:

```text
slow == fast
```

Therefore:

```java
return true;
```

### Result

```text
true
```

A cycle exists.

---

# Why Does This Work?

Imagine two runners on a circular track.

* Slow runner moves 1 step.
* Fast runner moves 2 steps.

Because they are running around the same circle, the faster runner will eventually catch the slower runner.

The linked-list cycle works in the same way.

```text
          ┌──────────┐
          ↓          │
1 → 2 → 3 → 4 → 5 ──┘
    ↑
   cycle
```

If a cycle exists, `fast` cannot escape the cycle and eventually meets `slow`.

If there is no cycle, `fast` reaches `null`.

---

# Complexity

### Time Complexity

```text
O(n)
```

In the worst case, the pointers may travel through the linked list before detecting a cycle or reaching `null`.

### Space Complexity

```text
O(1)
```

Only two pointers are used.

No extra array, HashSet, or data structure is required.

---

# Key Things to Remember

```text
slow → 1 step
fast → 2 steps

fast != null
AND
fast.next != null

slow == fast
      ↓
cycle exists
```

### Pattern to recognize

Whenever you see a problem asking:

* Does a linked list contain a cycle?
* Detect a loop in a linked list
* Find whether nodes repeat

Think:

> **Slow + Fast Pointer**

---

# Common Mistakes

### Mistake 1 — Using `||`

Wrong:

```java
while (fast != null || fast.next != null)
```

Correct:

```java
while (fast != null && fast.next != null)
```

Both conditions must be safe before moving `fast` two steps.

---

### Mistake 2 — Moving fast only one step

Wrong:

```java
fast = fast.next;
```

Fast must move two steps:

```java
fast = fast.next.next;
```

---

### Mistake 3 — Comparing node values

Avoid:

```java
slow.val == fast.val
```

Use:

```java
slow == fast
```

We need to know whether the **same node** was reached.

---

# Final Mental Model

Before coding, think:

```text
1. Create slow and fast.
       ↓
2. Both start at head.
       ↓
3. While fast can move two steps:
       ↓
4. Slow moves 1.
       ↓
5. Fast moves 2.
       ↓
6. If they meet → true.
       ↓
7. If fast reaches null → false.
```

**LeetCode 141 — Linked List Cycle: DONE ✅**
