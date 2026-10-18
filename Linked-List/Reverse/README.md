# LeetCode 206: Reverse Linked List

## 1. Problem Statement

**Problem:** Reverse Linked List

**Difficulty:** Easy

**Pattern:** Linked List + In-place Reversal + Three Pointers

**LeetCode:** https://leetcode.com/problems/reverse-linked-list/

### Question

Given the `head` of a singly linked list, reverse the list and return the reversed list.

### Example 1

**Input:**

```text
head = [1,2,3,4,5]
```

**Output:**

```text
[5,4,3,2,1]
```

**Explanation:**

Original linked list:

```text
1 → 2 → 3 → 4 → 5 → null
```

After reversing:

```text
5 → 4 → 3 → 2 → 1 → null
```

### Example 2

**Input:**

```text
head = [1,2]
```

**Output:**

```text
[2,1]
```

### Example 3

**Input:**

```text
head = []
```

**Output:**

```text
[]
```

---

## 2. Pattern Recognition

### When should you recognize this pattern?

Look for these clues in a problem statement:

* Reverse a singly linked list.
* Change the direction of the links between nodes.
* Return the new head of the list.
* Perform the reversal without creating another linked list.

These clues indicate the **In-place Linked List Reversal** pattern.

### Pattern Used

**Three Pointers: `cur`, `prev`, and `next`**

| Pointer | Purpose                                                           |
| ------- | ----------------------------------------------------------------- |
| `cur`   | Points to the current node being processed.                       |
| `prev`  | Points to the previous node; initially `null`.                    |
| `next`  | Temporarily saves the next node before changing the current link. |

### Core Reversal Pattern

```java
ListNode next = cur.next;
cur.next = prev;
prev = cur;
cur = next;
```

**Remember the four steps:**

1. Save the next node.
2. Reverse the current link.
3. Move `prev` forward.
4. Move `cur` forward.

---

## 3. Java Code

```java
class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
```

---

## 4. Code Explanation

### Step 1: Initialize the Pointers

```java
ListNode cur = head;
ListNode prev = null;
```

* `cur` starts at the first node.
* `prev` starts at `null` because the new last node must point to `null`.

Initially:

```text
prev = null

cur
 ↓
1 → 2 → 3 → null
```

### Step 2: Check the Loop Condition

```java
while (cur != null)
```

The loop continues until `cur` reaches `null`.

This ensures every node is processed.

### Step 3: Save the Next Node

```java
ListNode next = cur.next;
```

Save the next node before changing the current link.

For example:

```text
cur
 ↓
1 → 2 → 3 → null
    ↑
   next
```

Here, `next` points to node `2`.

**Why is this important?**

If you reverse the current link without saving the next node, you may lose access to the remaining list.

### Step 4: Reverse the Link

```java
cur.next = prev;
```

Change the direction of the current node's link.

Initially:

```text
1 → 2
```

After reversing the link:

```text
1 → null
```

The current node now points backward.

### Step 5: Move `prev` Forward

```java
prev = cur;
```

Move `prev` to the current node.

After processing node `1`:

```text
prev
 ↓
1 → null
```

### Step 6: Move `cur` Forward

```java
cur = next;
```

Move `cur` to the node saved in `next`.

Now `cur` points to node `2`.

The process repeats until every link is reversed.

### Step 7: Return the New Head

```java
return prev;
```

When the loop finishes:

* `cur == null`
* `prev` points to the original last node.

The original last node is now the first node of the reversed list.

Therefore, return `prev`.

---

## 5. Dry Run — Initial Setup

**Input:**

```text
head = [1,2,3]
```

Original linked list:

```text
1 → 2 → 3 → null
```

Initial pointer values:

| Variable | Value               |
| -------- | ------------------- |
| `head`   | Node 1              |
| `cur`    | Node 1              |
| `prev`   | null                |
| `next`   | Not initialized yet |

---

## 6. Dry Run — First Iteration

### Before Execution

```text
prev = null

cur
 ↓
1 → 2 → 3 → null
```

**Operation 1: Save the next node**

```java
ListNode next = cur.next;
```

Now `next` points to node `2`.

**Operation 2: Reverse the link**

```java
cur.next = prev;
```

Node `1` now points to `null`.

**Operation 3: Move `prev`**

```java
prev = cur;
```

Now `prev` points to node `1`.

**Operation 4: Move `cur`**

```java
cur = next;
```

Now `cur` points to node `2`.

### After First Iteration

```text
prev
 ↓
1 → null

cur
 ↓
2 → 3 → null
```

---

## 7. Dry Run — Second Iteration

Before execution:

```text
prev
 ↓
1 → null

cur
 ↓
2 → 3 → null
```

| Operation      | Code              | Result             |
| -------------- | ----------------- | ------------------ |
| Save next node | `next = cur.next` | `next` points to 3 |
| Reverse link   | `cur.next = prev` | `2 → 1`            |
| Move previous  | `prev = cur`      | `prev` points to 2 |
| Move current   | `cur = next`      | `cur` points to 3  |

After the second iteration:

```text
prev
 ↓
2 → 1 → null

cur
 ↓
3 → null
```

---

## 8. Dry Run — Third Iteration

Before execution:

```text
prev
 ↓
2 → 1 → null

cur
 ↓
3 → null
```

| Operation      | Code              | Result             |
| -------------- | ----------------- | ------------------ |
| Save next node | `next = cur.next` | `next = null`      |
| Reverse link   | `cur.next = prev` | `3 → 2 → 1`        |
| Move previous  | `prev = cur`      | `prev` points to 3 |
| Move current   | `cur = next`      | `cur = null`       |

After the third iteration:

```text
prev
 ↓
3 → 2 → 1 → null

cur = null
```

The loop stops because `cur != null` is false.

**Final Output:**

```text
[3,2,1]
```

---

## 9. Complete Dry Run in One Table

**Input:** `head = [1,2,3]`

| Iteration | `cur` Before | `prev` Before | `next` | Link Reversed | `prev` After | `cur` After |
| --------- | ------------ | ------------- | ------ | ------------- | ------------ | ----------- |
| Initial   | 1            | null          | —      | —             | null         | 1           |
| 1         | 1            | null          | 2      | `1 → null`    | 1            | 2           |
| 2         | 2            | 1             | 3      | `2 → 1`       | 2            | 3           |
| 3         | 3            | 2             | null   | `3 → 2`       | 3            | null        |

After the loop:

```text
prev = 3
cur = null
```

Return `prev`.

**Final Output:** `[3,2,1]`

---

## 10. Dry Run Questions

Use these questions to test your understanding.

### Question 1

Given:

```text
head = [1,2,3,4]
```

What will the linked list look like after the first iteration?

**Answer:**

```text
1 → null
```

The remaining unprocessed list starts at node `2`.

### Question 2

Why do we write this statement first?

```java
ListNode next = cur.next;
```

**Answer:**

To save the next node before reversing the current link, preventing the remaining list from becoming inaccessible.

### Question 3

What does this statement do?

```java
cur.next = prev;
```

**Answer:**

It reverses the direction of the current node's link.

### Question 4

What will `prev` point to after the loop finishes?

**Answer:**

The original last node, which becomes the new head of the reversed linked list.

### Question 5

Why do we return `prev` instead of `head`?

**Answer:**

Because the original `head` still refers to the original first node, which becomes the last node after reversal. `prev` points to the new head.

### Question 6

What happens when the input list is empty?

```text
head = null
```

**Answer:**

`cur` is initially `null`, so the loop never executes. `prev` is also `null`, so the method returns `null`.

---

## 11. Complexity Analysis

### Time Complexity: O(n)

Each node is visited exactly once.

Here, `n` represents the number of nodes in the linked list.

### Space Complexity: O(1)

Only three pointer variables are needed: `cur`, `prev`, and `next`.

The algorithm reverses the list in place without creating another linked list.

---

## 12. Common Mistakes to Avoid

### Mistake 1: Not Saving the Next Node

Incorrect order:

```java
cur.next = prev;
ListNode next = cur.next;
```

After reversing the link, `cur.next` points backward, so the original next node may be lost.

Correct order:

```java
ListNode next = cur.next;
cur.next = prev;
```

### Mistake 2: Moving `cur` Incorrectly

Correct:

```java
cur = next;
```

This moves `cur` to the original next node.

### Mistake 3: Returning the Original Head

Incorrect:

```java
return head;
```

Correct:

```java
return prev;
```

After the reversal, `prev` points to the new head.

---

## 13. Final Revision Notes

**Problem:** Reverse Linked List

**LeetCode:** 206

**Pattern:** In-place Linked List Reversal

**Three Main Pointers:**

* `cur`: Current node.
* `prev`: Previous node.
* `next`: Saved next node.

**Four Essential Operations:**

```java
ListNode next = cur.next;
cur.next = prev;
prev = cur;
cur = next;
```

**Loop Condition:**

```java
while (cur != null)
```

**Return Statement:**

```java
return prev;
```

**Memory Trick:**

**Save → Reverse → Move Previous → Move Current**

Remember these four steps, and you can implement the iterative solution to Reverse Linked List without memorizing the entire code.
