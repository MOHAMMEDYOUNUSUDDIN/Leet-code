# 🔗 LeetCode 876 — Middle of the Linked List

## 🧩 Problem

Given the head of a singly linked list, return the **middle node** of the linked list.

If there are two middle nodes, return the **second middle node**.

### Example 1

**Input:**

```text
1 → 2 → 3 → 4 → 5
```

**Output:**

```text
3 → 4 → 5
```

### Example 2

**Input:**

```text
1 → 2 → 3 → 4 → 5 → 6
```

**Output:**

```text
4 → 5 → 6
```

---

# 💡 Approach — Slow & Fast Pointer

We use two pointers:

* `slow` moves **one node at a time**.
* `fast` moves **two nodes at a time**.

When `fast` reaches the end of the linked list, `slow` will be pointing to the middle node.

### Why does this work?

Since `fast` moves twice as fast as `slow`, when `fast` has travelled the entire list, `slow` has travelled approximately half of it.

Therefore:

```text
Fast → reaches the end
Slow → reaches the middle
```

---

# 💻 Java Solution

```java
class Solution {
    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
```

---

# 🔍 Complete Dry Run

Consider:

```text
1 → 2 → 3 → 4 → 5 → null
```

## Step 1 — Initialization

```java
ListNode slow = head;
ListNode fast = head;
```

Both pointers start at `1`.

```text
1 → 2 → 3 → 4 → 5 → null
↑
S
↑
F
```

So:

```text
slow = 1
fast = 1
```

---

## Step 2 — First Iteration

Check the condition:

```java
fast != null && fast.next != null
```

Both are `true`.

Move:

```java
slow = slow.next;
fast = fast.next.next;
```

Now:

```text
1 → 2 → 3 → 4 → 5 → null
    ↑   ↑
    S   F
```

So:

```text
slow = 2
fast = 3
```

---

## Step 3 — Second Iteration

Condition:

```text
fast != null       → true
fast.next != null  → true
```

Move again:

```java
slow = slow.next;
fast = fast.next.next;
```

Now:

```text
1 → 2 → 3 → 4 → 5 → null
        ↑       ↑
        S       F
```

So:

```text
slow = 3
fast = 5
```

---

## Step 4 — Loop Stops

Check the condition again:

```text
fast != null       → true
fast.next != null  → false
```

Because:

```text
fast = 5
fast.next = null
```

Therefore, the loop stops.

Current list:

```text
1 → 2 → 3 → 4 → 5 → null
        ↑
       slow
```

`slow` is pointing to node `3`.

---

# 🎯 Return Value

The code says:

```java
return slow;
```

We are returning the **node**, not just its value.

The node containing `3` is still connected to the remaining list:

```text
slow
 ↓
3 → 4 → 5 → null
```

Therefore, the returned linked list is:

```text
3 → 4 → 5
```

This is why the LeetCode output is:

```text
[3,4,5]
```

and not simply:

```text
3
```

---

# 📊 Pointer Movement Summary

| Iteration | Slow  | Fast |
| --------- | ----- | ---- |
| Start     | 1     | 1    |
| 1         | 2     | 3    |
| 2         | 3     | 5    |
| Stop      | **3** | 5    |

Therefore:

```text
Answer = 3 → 4 → 5
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse approximately half the list with `slow` and the entire list with `fast`.

### Space Complexity

```text
O(1)
```

Only two pointers are used, so no extra data structure is required.

---

# 🧠 Pattern to Remember

This problem uses the:

## Slow & Fast Pointer Pattern

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

### Remember:

> **Slow moves 1 step, Fast moves 2 steps. When Fast reaches the end, Slow is at the middle.**

This pattern is useful for problems involving:

* Finding the middle of a linked list
* Detecting a cycle
* Finding the starting point of a cycle
* Finding the length of a cycle
* Other linked-list problems involving relative pointer movement
