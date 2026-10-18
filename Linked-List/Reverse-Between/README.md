# LeetCode 92: Reverse Linked List II

## 1. Problem Statement

**Problem:** Reverse Linked List II

**Difficulty:** Medium

**Pattern:** Linked List + In-place Reversal + Two Pointers

**LeetCode:** https://leetcode.com/problems/reverse-linked-list-ii/

### Question

Given the `head` of a singly linked list and two integers `left` and `right`, reverse the nodes of the list from position `left` to position `right`, and return the reversed list.

**Important:** Positions are 1-indexed.

### Example 1

**Input:**

```text
head = [1,2,3,4,5], left = 2, right = 4
```

**Output:**

```text
[1,4,3,2,5]
```

**Explanation:**

Reverse the sublist from position 2 to position 4.

Original:

```text
1 → 2 → 3 → 4 → 5 → null
```

Reversed:

```text
1 → 4 → 3 → 2 → 5 → null
```

### Example 2

**Input:**

```text
head = [5], left = 1, right = 1
```

**Output:**

```text
[5]
```

**Explanation:**

Since `left == right`, there is nothing to reverse.

---

## 2. Pattern Recognition

### When should you recognize this pattern?

Look for these clues in a problem statement:

* Reverse only a specific portion of a linked list.
* Reverse nodes between two given positions.
* Modify the links without creating a new linked list.
* Preserve the nodes outside the given range.

These clues indicate the **In-place Linked List Reversal** pattern.

### Pattern Used

**Linked List + Pointer Manipulation**

We use four main pointers:

| Pointer  | Purpose                                                                                                              |
| -------- | -------------------------------------------------------------------------------------------------------------------- |
| `before` | Points to the node immediately before the reversal section.                                                          |
| `last`   | Points to the original first node of the reversal section. After reversal, it becomes the last node of that section. |
| `cur`    | Points to the current node being reversed.                                                                           |
| `prev`   | Points to the previous node during reversal.                                                                         |

We also use a temporary pointer called `next` to save the next node before changing a link.

### Core Reversal Pattern

```java
ListNode next = cur.next;
cur.next = prev;
prev = cur;
cur = next;
```

**Remember the order:**

1. Save the next node.
2. Reverse the current link.
3. Move `prev` forward.
4. Move `cur` forward.

---

## 3. Java Code

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        ListNode before = null;
        ListNode last = head;

        for (int i = 1; i < left; i++) {
            before = last;
            last = last.next;
        }

        ListNode cur = last;
        ListNode prev = null;

        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        if (before != null) {
            before.next = prev;
        } else {
            head = prev;
        }

        last.next = cur;

        return head;
    }
}
```

---

## 4. Code Explanation

### Step 1: Handle the Base Case

```java
if (head == null || left == right) {
    return head;
}
```

* If the list is empty, return `head`.
* If `left == right`, only one position is involved, so no reversal is necessary.

### Step 2: Initialize the Pointers

```java
ListNode before = null;
ListNode last = head;
```

* `before` will point to the node immediately before position `left`.
* `last` will point to the node at position `left`.

Initially, `before` is `null` because the starting position might be the head.

### Step 3: Reach the Starting Position

```java
for (int i = 1; i < left; i++) {
    before = last;
    last = last.next;
}
```

This loop moves the pointers to their required positions.

For `left = 2`:

* `before` points to node `1`.
* `last` points to node `2`.

### Step 4: Initialize Reversal Pointers

```java
ListNode cur = last;
ListNode prev = null;
```

* `cur` starts at the first node to reverse.
* `prev` starts at `null` because the reversed sublist initially has no previous node.

### Step 5: Reverse the Required Nodes

```java
for (int i = 0; i < right - left + 1; i++) {
    ListNode next = cur.next;
    cur.next = prev;
    prev = cur;
    cur = next;
}
```

The number of nodes to reverse is:

`right - left + 1`

For `left = 2` and `right = 4`:

`4 - 2 + 1 = 3 nodes`

After reversal:

* `prev` points to node `4`, the new first node of the reversed section.
* `last` still points to node `2`, which is now the last node of that section.
* `cur` points to node `5`, the node immediately after the reversed section.

### Step 6: Reconnect the Beginning

```java
if (before != null) {
    before.next = prev;
} else {
    head = prev;
}
```

If `before` exists, connect it to the new first node of the reversed section.

If `before == null`, the reversal starts at position 1, so update `head`.

### Step 7: Reconnect the Ending

```java
last.next = cur;
```

The original first node of the reversed section is now its last node.

Connect it to `cur`, which points to the node after the reversed section.

### Step 8: Return the Head

```java
return head;
```

Return the head of the complete modified linked list.

---

## 5. Dry Run — Pointer Initialization

**Input:**

```text
head = [1,2,3,4,5]
left = 2
right = 4
```

Initial linked list:

```text
1 → 2 → 3 → 4 → 5 → null
```

### Pointer Movement

| Iteration | `i` | `before` | `last` |
| --------- | --: | -------- | ------ |
| Initial   |   — | null     | 1      |
| 1         |   1 | 1        | 2      |

The loop stops because `i < left` becomes false.

Now:

```text
before = 1
last = 2
cur = 2
prev = null
```

---

## 6. Dry Run — Reversal Loop

The loop runs `right - left + 1 = 3` times.

| Iteration | `cur` | `prev` Before | `next` | Link Reversed | `prev` After | `cur` After |
| --------- | ----: | ------------- | -----: | ------------- | -----------: | ----------: |
| 1         |     2 | null          |      3 | `2 → null`    |            2 |           3 |
| 2         |     3 | 2             |      4 | `3 → 2`       |            3 |           4 |
| 3         |     4 | 3             |      5 | `4 → 3`       |            4 |           5 |

After the reversal loop:

```text
prev = 4
last = 2
cur = 5
```

The reversed portion is:

```text
4 → 3 → 2 → null
```

The remaining node is:

```text
5 → null
```

We must reconnect these parts.

---

## 7. Dry Run — Reconnection

### Connection 1

```java
before.next = prev;
```

Here, `before = 1` and `prev = 4`.

This connects node `1` to node `4`.

### Connection 2

```java
last.next = cur;
```

Here, `last = 2` and `cur = 5`.

This connects node `2` to node `5`.

### Final Linked List

```text
1 → 4 → 3 → 2 → 5 → null
```

**Output:**

```text
[1,4,3,2,5]
```

---

## 8. Dry Run — Complete Example in One Table

**Input:** `head = [1,2,3,4,5], left = 2, right = 4`

| Step | Operation           | `before` | `last` | `prev` | `cur` |
| ---- | ------------------- | -------- | ------ | ------ | ----- |
| 1    | Initialize          | null     | 1      | —      | —     |
| 2    | Find reversal start | 1        | 2      | —      | —     |
| 3    | Initialize reversal | 1        | 2      | null   | 2     |
| 4    | Reverse node 2      | 1        | 2      | 2      | 3     |
| 5    | Reverse node 3      | 1        | 2      | 3      | 4     |
| 6    | Reverse node 4      | 1        | 2      | 4      | 5     |
| 7    | Connect beginning   | 1        | 2      | 4      | 5     |
| 8    | Connect ending      | 1        | 2      | 4      | 5     |
| 9    | Return head         | 1        | 2      | 4      | 5     |

**Final Output:** `[1,4,3,2,5]`

---

## 9. Complexity Analysis

### Time Complexity: O(n)

We traverse the list to reach position `left`, reverse the required sublist, and reconnect the nodes.

In the worst case, we process the entire list.

### Space Complexity: O(1)

We use only a constant number of pointer variables.

No additional linked list or array is created.

---

## 10. Important Edge Cases

| Case                    | Input                                  | Expected Output |
| ----------------------- | -------------------------------------- | --------------- |
| Single node             | `[5]`, `left = 1`, `right = 1`         | `[5]`           |
| Reverse entire list     | `[1,2,3]`, `left = 1`, `right = 3`     | `[3,2,1]`       |
| Reverse first two nodes | `[1,2,3,4]`, `left = 1`, `right = 2`   | `[2,1,3,4]`     |
| Reverse last two nodes  | `[1,2,3,4]`, `left = 3`, `right = 4`   | `[1,2,4,3]`     |
| Reverse middle nodes    | `[1,2,3,4,5]`, `left = 2`, `right = 4` | `[1,4,3,2,5]`   |

The code handles `left = 1` separately using the `before != null` condition.

---

## 11. Common Mistakes to Avoid

### Mistake 1: Incorrect Loop Bound

Incorrect:

```java
for (int i = 0; i < left; i++)
```

Correct:

```java
for (int i = 1; i < left; i++)
```

The correct loop stops `before` at position `left - 1`.

### Mistake 2: Losing the Remaining List

Always save the next node before reversing the current link.

```java
ListNode next = cur.next;
```

### Mistake 3: Forgetting Reconnection

After reversing, reconnect both sides:

```java
before.next = prev;
last.next = cur;
```

Remember to handle `before == null` when the reversal starts at the head.

### Mistake 4: Returning the Wrong Node

Return the head of the complete modified list:

```java
return head;
```

---

## 12. Final Revision Notes

**Problem Pattern:** Partial Linked List Reversal

**Main Formula:**

```text
Number of nodes to reverse = right - left + 1
```

**Pointer Roles:**

* `before`: Node before the reversal section.
* `last`: Original first node of the reversal section.
* `cur`: Current node being reversed.
* `prev`: Previous node during reversal.
* `next`: Temporarily stores the next node.

**Four Reversal Operations:**

```java
ListNode next = cur.next;
cur.next = prev;
prev = cur;
cur = next;
```

**Two Reconnection Operations:**

```java
before.next = prev;
last.next = cur;
```

The first reconnection requires a null check when `left == 1`.

**Key Learning:** Reverse the required portion using the standard linked list reversal pattern, then reconnect both ends without losing the rest of the list.
