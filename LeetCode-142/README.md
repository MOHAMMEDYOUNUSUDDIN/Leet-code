# LeetCode 142 — Linked List Cycle II

## Problem

Given the head of a linked list, determine whether the linked list contains a cycle.

If a cycle exists, return the **node where the cycle begins**.

If there is no cycle, return `null`.

### Example

```text
Input:
3 → 2 → 0 → -4
    ↑         ↓
    └─────────┘

Output:
Node with value 2
```

---

# Approach

We use **Floyd's Cycle Detection Algorithm**, also known as the **Tortoise and Hare Algorithm**.

The solution has two phases:

### Phase 1: Detect the Cycle

Use two pointers:

* `slow` moves **one step** at a time.
* `fast` moves **two steps** at a time.

If there is a cycle, `slow` and `fast` will eventually meet.

```java
slow = slow.next;
fast = fast.next.next;
```

If:

```java
slow == fast
```

then a cycle exists.

---

### Phase 2: Find the Starting Node

Finding the meeting point is not enough because the meeting point may not be the beginning of the cycle.

After the pointers meet:

```java
slow = head;
```

Now:

* `slow` starts from `head`.
* `fast` remains at the meeting point.
* Both pointers move **one step at a time**.

```java
while(slow != fast){
    slow = slow.next;
    fast = fast.next;
}
```

The point where they meet again is the **starting node of the cycle**.

---

# Code

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect cycle
        while(fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){

                // Phase 2: Find cycle starting point
                slow = head;

                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }
}
```

---

# Code Explanation

### 1. Initialize the pointers

```java
ListNode slow = head;
ListNode fast = head;
```

Both pointers initially point to the head of the linked list.

---

### 2. Move the pointers

```java
while(fast != null && fast.next != null){
    slow = slow.next;
    fast = fast.next.next;
```

`slow` moves one node.

`fast` moves two nodes.

The condition:

```java
fast != null && fast.next != null
```

prevents accessing a `null` node.

---

### 3. Check whether they meet

```java
if(slow == fast)
```

If both pointers point to the same node, a cycle exists.

---

### 4. Reset `slow`

```java
slow = head;
```

We move `slow` back to the beginning of the linked list.

`fast` stays at the meeting point.

---

### 5. Move both pointers one step

```java
while(slow != fast){
    slow = slow.next;
    fast = fast.next;
}
```

Both pointers now move at the same speed.

They will meet at the **beginning of the cycle**.

---

### 6. Return the cycle starting node

```java
return slow;
```

Since `slow == fast`, either pointer can be returned.

---

### 7. No cycle

If the `while` loop ends because `fast` reaches `null`, there is no cycle.

```java
return null;
```

---

# Dry Run

Consider:

```text
3 → 2 → 0 → -4
    ↑         ↓
    └─────────┘
```

The cycle starts at node `2`.

So the linked list is:

```text
3 → 2 → 0 → -4
    ↑         |
    └─────────┘
```

### Phase 1: Find the Meeting Point

Initially:

```text
slow = 3
fast = 3
```

### Iteration 1

```text
slow = 2
fast = 0
```

```text
3 → 2 → 0 → -4
    S    F
```

`slow != fast`

---

### Iteration 2

```text
slow = 0
fast = 2
```

`slow != fast`

---

### Iteration 3

```text
slow = -4
fast = -4
```

Now:

```text
slow == fast
```

A cycle is detected.

The meeting point is `-4`.

**Important:** `-4` is NOT the cycle starting point.

---

# Phase 2: Find the Cycle Start

Reset:

```java
slow = head;
```

Now:

```text
slow = 3
fast = -4
```

Move both one step.

### Iteration 1

```text
slow = 2
fast = 2
```

They meet at node `2`.

Therefore:

```text
Cycle starts at 2
```

Return:

```java
return slow;
```

### Final Output

```text
2
```

---

# Why Does Phase 2 Work?

Let:

* `x` = distance from head to cycle start
* `y` = distance from cycle start to meeting point
* `C` = length of the cycle

When `slow` and `fast` meet, Floyd's algorithm guarantees that resetting one pointer to `head` and moving both pointers one step at a time will make them meet exactly at the cycle's starting node.

This is the key idea behind the second phase.

---

# Complexity

### Time Complexity

```text
O(n)
```

The linked list is traversed a constant number of times.

### Space Complexity

```text
O(1)
```

Only two pointers are used, so no extra data structure is required.

---

# Key Takeaway

Remember the solution as:

```text
Phase 1:
slow → 1 step
fast → 2 steps
       ↓
    They meet
       ↓
Phase 2:
slow → head
fast → meeting point
       ↓
both move 1 step
       ↓
They meet again
       ↓
Cycle starting node
```

**Floyd's Cycle Detection = Detect Meeting Point → Reset One Pointer → Find Cycle Start**
