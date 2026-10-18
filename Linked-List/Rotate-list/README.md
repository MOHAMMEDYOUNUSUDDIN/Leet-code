# LeetCode 61: Rotate List

## 1. Problem Statement

**Problem:** Rotate List
**LeetCode Number:** 61
**Difficulty:** Medium
**Pattern:** Linked List + Two Pointers + Circular Linked List

### Question

Given the head of a singly linked list, rotate the list to the right by `k` positions.

### Example 1

**Input:**

```text
head = [1, 2, 3, 4, 5]
k = 2
```

**Output:**

```text
[4, 5, 1, 2, 3]
```

**Explanation:**

After rotating right once:

```text
1 → 2 → 3 → 4 → 5
                  ↓
5 → 1 → 2 → 3 → 4
```

After rotating right twice:

```text
4 → 5 → 1 → 2 → 3
```

The last two nodes move to the beginning of the list.

### Example 2

**Input:**

```text
head = [0, 1, 2]
k = 4
```

**Output:**

```text
[2, 0, 1]
```

**Explanation:**

The list contains three nodes.

Rotating four times is equivalent to rotating once because:

```text
4 % 3 = 1
```

Therefore, the final list is:

```text
2 → 0 → 1
```

---

## 2. How to Think About This Problem

Before writing code, understand what the problem is asking.

Suppose we have:

```text
1 → 2 → 3 → 4 → 5
```

We need to rotate the list to the right by two positions.

Ask yourself these questions:

1. How many nodes are present in the list?
2. Which nodes must move to the beginning?
3. How can I avoid unnecessary rotations?
4. How can I reconnect the nodes without creating a separate list?
5. Where should I break the linked list?

### Key Observation

For the list:

```text
1 → 2 → 3 → 4 → 5
```

When `k = 2`, the last two nodes become the first two nodes.

The result is:

```text
4 → 5 → 1 → 2 → 3
```

Instead of moving nodes one by one, we can find the correct breaking point and reconnect the list.

**Important idea:** Connect the last node to the head to form a circular linked list. Then find the new last node and break the circle.

---

## 3. Pattern Recognition

### When should you recognize this pattern?

Look for these clues in a problem:

* The problem involves rotating a linked list.
* Nodes must be moved from the end to the beginning.
* The relative order of the nodes must remain unchanged.
* The problem asks you to modify links rather than create a new list.

These clues suggest using linked-list pointers and a circular linked list.

### Patterns Used

| Pattern               | Purpose                                       |
| --------------------- | --------------------------------------------- |
| Linked List Traversal | Find the length and last node                 |
| Modulo (`%`)          | Eliminate unnecessary rotations               |
| Circular Linked List  | Connect the last node to the head             |
| Pointer Manipulation  | Find the new last node and reconnect the list |

---

## 4. Step-by-Step Approach

### Step 1: Handle Edge Cases

```java
if (head == null || head.next == null || k == 0) {
    return head;
}
```

Why?

* `head == null`: The list is empty.
* `head.next == null`: The list contains only one node.
* `k == 0`: No rotation is required.

In these cases, the original list remains unchanged.

### Step 2: Find the Length and Last Node

```java
ListNode last = head;
int n = 1;

while (last.next != null) {
    last = last.next;
    n++;
}
```

We use two variables:

* `last`: Points to the last node.
* `n`: Stores the number of nodes.

Example:

```text
1 → 2 → 3 → 4 → 5 → null
```

After traversal:

```text
n = 5
last = node 5
```

Notice that we check `last.next != null` instead of `last != null`.

This allows us to stop at the last node instead of moving beyond it.

### Step 3: Reduce the Number of Rotations

```java
k = k % n;
```

Why use modulo?

If a list contains five nodes, rotating it five times returns it to its original position.

For example:

```text
n = 5
k = 12

k = 12 % 5
k = 2
```

Therefore, rotating twelve times is equivalent to rotating twice.

### Step 4: Check Whether Rotation Is Necessary

```java
if (k == 0) {
    return head;
}
```

If `k` becomes zero after modulo, the list does not need to change.

### Step 5: Make the List Circular

```java
last.next = head;
```

Initially:

```text
1 → 2 → 3 → 4 → 5 → null
```

After connecting the last node to the head:

```text
1 → 2 → 3 → 4 → 5
↑                   |
|___________________|
```

Now the list is circular.

This makes it possible to move the last nodes to the beginning without individually moving them.

### Step 6: Calculate the Number of Steps

```java
int steps = n - k - 1;
```

Why?

For a list of length `n`, the new last node must be at position:

```text
n - k
```

Using zero-based indexing, its index is:

```text
n - k - 1
```

Example:

```text
n = 5
k = 2

steps = 5 - 2 - 1
steps = 2
```

The new last node will be node `3`.

### Step 7: Find the New Last Node

```java
ListNode newLast = head;

for (int i = 0; i < steps; i++) {
    newLast = newLast.next;
}
```

We start from the original head and move forward `steps` times.

For our example:

```text
Index:  0   1   2   3   4
Node:   1 → 2 → 3 → 4 → 5
```

The new last node is:

```text
newLast = node 3
```

### Step 8: Find the New Head

```java
ListNode newHead = newLast.next;
```

The node after the new last node becomes the new head.

Therefore:

```text
newLast = node 3
newHead = node 4
```

### Step 9: Break the Circle

```java
newLast.next = null;
```

This disconnects node `3` from node `4`.

The final list becomes:

```text
4 → 5 → 1 → 2 → 3 → null
```

### Step 10: Return the New Head

```java
return newHead;
```

**Important:** Return `newHead`, not `head`.

The original `head` still points to node `1`, but the rotated list begins at node `4`.

---

## 5. Complete Java Code

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        // Step 1: Handle edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 2: Find the length and last node
        ListNode last = head;
        int n = 1;

        while (last.next != null) {
            last = last.next;
            n++;
        }

        // Step 3: Remove unnecessary rotations
        k = k % n;

        if (k == 0) {
            return head;
        }

        // Step 4: Make the list circular
        last.next = head;

        // Step 5: Find the new last node
        int steps = n - k - 1;
        ListNode newLast = head;

        for (int i = 0; i < steps; i++) {
            newLast = newLast.next;
        }

        // Step 6: Find the new head
        ListNode newHead = newLast.next;

        // Step 7: Break the circle
        newLast.next = null;

        // Step 8: Return the rotated list
        return newHead;
    }
}
```

---

## 6. Dry Run of the Code

### Input

```text
head = [1, 2, 3, 4, 5]
k = 2
```

### Step 1: Find the Length

Initially:

```text
last = 1
n = 1
```

Traverse the list:

| Iteration | `last` | `n` |
| --------- | ------ | --: |
| Initial   | 1      |   1 |
| 1         | 2      |   2 |
| 2         | 3      |   3 |
| 3         | 4      |   4 |
| 4         | 5      |   5 |

The loop stops when `last.next == null`.

Final values:

```text
last = 5
n = 5
```

### Step 2: Calculate Effective Rotations

```java
k = k % n;
```

Calculation:

```text
k = 2 % 5
k = 2
```

### Step 3: Make the List Circular

```java
last.next = head;
```

The list becomes circular:

```text
1 → 2 → 3 → 4 → 5
↑                   |
|___________________|
```

### Step 4: Calculate Steps

```java
int steps = n - k - 1;
```

Calculation:

```text
steps = 5 - 2 - 1
steps = 2
```

### Step 5: Find the New Last Node

Initially:

```text
newLast = 1
```

| Iteration | `newLast` |
| --------- | --------- |
| Initial   | 1         |
| 1         | 2         |
| 2         | 3         |

Therefore:

```text
newLast = 3
```

### Step 6: Find the New Head

```java
ListNode newHead = newLast.next;
```

Since node `3` points to node `4`:

```text
newHead = 4
```

### Step 7: Break the Circle

```java
newLast.next = null;
```

Now the list is:

```text
4 → 5 → 1 → 2 → 3 → null
```

### Final Output

```text
[4, 5, 1, 2, 3]
```

---

## 7. Important Pointer Variables

| Variable  | Meaning                                           |
| --------- | ------------------------------------------------- |
| `head`    | Original first node                               |
| `last`    | Original last node                                |
| `n`       | Length of the linked list                         |
| `k`       | Effective number of rotations after modulo        |
| `steps`   | Number of moves needed to reach the new last node |
| `newLast` | Last node of the rotated list                     |
| `newHead` | First node of the rotated list                    |

---

## 8. Common Mistakes

### Mistake 1: Incorrect Length Calculation

Incorrect:

```java
int n = 1;

while (last != null) {
    n++;
    last = last.next;
}
```

This counts incorrectly because `n` starts at `1` and the loop visits every node.

Correct:

```java
int n = 1;

while (last.next != null) {
    last = last.next;
    n++;
}
```

### Mistake 2: Forgetting Modulo

Without modulo, a very large value of `k` may cause unnecessary operations.

Correct:

```java
k = k % n;
```

### Mistake 3: Using the Wrong Number of Steps

Incorrect:

```java
int steps = n - k;
```

Correct:

```java
int steps = n - k - 1;
```

We subtract one because the new last node's zero-based index is `n - k - 1`.

### Mistake 4: Forgetting to Break the Circle

If we forget:

```java
newLast.next = null;
```

the linked list remains circular, potentially causing infinite traversal.

### Mistake 5: Returning the Original Head

Incorrect:

```java
return head;
```

Correct:

```java
return newHead;
```

The rotated list begins at the new head.

---

## 9. Time and Space Complexity

### Time Complexity: O(n)

We traverse the linked list to find its length and then traverse it again to find the new last node.

Both operations take linear time.

Therefore:

```text
O(n) + O(n) = O(n)
```

### Space Complexity: O(1)

We use only a fixed number of pointer and integer variables.

We do not create another linked list or use an additional data structure.

Therefore:

```text
Space Complexity = O(1)
```

---

## 10. How an Interviewer May Ask This Question

An interviewer may present the same problem in different ways.

### Question 1: Basic Rotation

**Interviewer:** Given a linked list, rotate it right by `k` positions.

**What they expect:**

* Correct handling of empty and single-node lists.
* Efficient pointer manipulation.
* Correct output for different values of `k`.

### Question 2: Very Large `k`

**Interviewer:** What if `k` is much larger than the length of the list?

**Answer:**

Use modulo to remove unnecessary rotations.

```java
k = k % n;
```

For example:

```text
n = 5
k = 100

100 % 5 = 0
```

The list remains unchanged.

### Question 3: Can You Solve It Without Creating Another List?

**Answer:**

Yes. Connect the last node to the head, forming a circular linked list. Find the new last node and break the circle.

This requires O(1) auxiliary space.

### Question 4: What Is the Time Complexity?

**Answer:**

O(n), because the algorithm traverses the linked list a constant number of times.

### Question 5: Why Do You Use `n - k - 1`?

**Answer:**

After rotating right by `k` positions, the new last node is at zero-based index `n - k - 1`.

For example, if `n = 5` and `k = 2`:

```text
New last index = 5 - 2 - 1 = 2
```

Index `2` contains node `3`.

### Question 6: Why Do You Return `newHead`?

**Answer:**

The original head no longer represents the first node of the rotated list. The node after `newLast` becomes the new head.

### Question 7: What Happens If `k = 0`?

**Answer:**

No rotation is needed, so we return the original head.

### Question 8: What Happens If the List Contains Only One Node?

**Answer:**

Rotating a single-node list does not change it. We return the original head.

---

## 11. Interview Follow-Up Questions

Practice answering these without looking at the solutions.

1. How would you rotate a linked list to the left?
2. Can you solve this problem without forming a circular linked list?
3. What happens when `k` is a multiple of the list length?
4. Why is the space complexity O(1)?
5. Why must you save `newHead` before breaking the circle?
6. What would happen if you forgot to set `newLast.next = null`?
7. Can you explain the algorithm using a list of six nodes and `k = 3`?
8. How would you handle a list containing zero or one node?

---

## 12. Quick Revision Notes

Before an interview or coding test, remember these steps:

1. Handle edge cases.
2. Find the length and last node.
3. Calculate `k % n`.
4. Return the original head if effective `k` is zero.
5. Connect the last node to the head.
6. Calculate `steps = n - k - 1`.
7. Find the new last node.
8. Save `newHead = newLast.next`.
9. Break the circle using `newLast.next = null`.
10. Return `newHead`.

### One-Line Summary

**Find the length → Apply modulo → Create a circle → Find the new last node → Break the circle → Return the new head.**

### Final Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```
