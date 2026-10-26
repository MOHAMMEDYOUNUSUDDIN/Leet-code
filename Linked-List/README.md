# Linked List — Complete DSA Guide (Java)

## 1. What Is a Linked List?

A Linked List is a linear data structure in which elements are stored in nodes. Each node contains:
- **Data:** The value stored in the node.
- **Next:** A reference to the next node.

Example:

```text
10 → 20 → 30 → 40 → null
```

Unlike an array, linked-list nodes do not need to occupy consecutive memory locations.

### Real-World Example

Imagine a treasure hunt. Each clue tells you where to find the next clue. You follow the links one by one until you reach the end.

---

## 2. Types of Linked Lists

### Singly Linked List
Each node points to the next node.

```text
10 → 20 → 30 → null
```

### Doubly Linked List
Each node points to both the previous and next nodes.

```text
null ← 10 ⇄ 20 ⇄ 30 → null
```

### Circular Linked List
The last node points back to an earlier node, usually the head.

```text
10 → 20 → 30
↑         ↓
└─────────┘
```

---

## 3. Basic Java Structure

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
```

### Important Terms

| Term | Meaning |
|---|---|
| `head` | First node |
| `tail` | Last node |
| `next` | Reference to the next node |
| `null` | No next node |
| `current` | Node currently being processed |
| `previous` | Node before the current node |
| `temp` | Temporary reference used during traversal |

---

## 4. How to Traverse a Linked List

Traversal means visiting each node from the head until the end.

```java
ListNode current = head;

while (current != null) {
    System.out.println(current.val);
    current = current.next;
}
```

### How to Think

1. Start at `head`.
2. Process the current node.
3. Move to `current.next`.
4. Stop when `current == null`.

**Time Complexity:** O(n)  
**Auxiliary Space:** O(1)

---

## 5. How to Recognize Linked List Patterns

When reading a problem, ask what operation it requires.

| Problem requirement | Pattern to consider |
|---|---|
| Reverse node links | Iterative or recursive reversal |
| Find the middle node | Slow and fast pointers |
| Detect a cycle | Floyd's slow and fast pointers |
| Find the cycle's starting node | Floyd's cycle detection |
| Find the kth node from the end | Two pointers with a gap |
| Remove a node | Previous pointer and relinking |
| Merge sorted lists | Two pointers |
| Check whether a list is a palindrome | Find middle, reverse half, compare |
| Reorder nodes | Find middle, reverse second half, merge |
| Find the intersection of two lists | Two pointers or aligned lengths |
| Add numbers represented by lists | Digit-by-digit addition with carry |
| Sort a linked list | Merge sort |

---

## 6. Pattern 1: Reverse a Linked List

### Problem

Reverse the direction of the links.

Input:

```text
1 → 2 → 3 → 4 → null
```

Output:

```text
4 → 3 → 2 → 1 → null
```

### How to Think

You need to change each node's `next` reference to point backward.

Use three pointers:

- `prev`: Previous node.
- `curr`: Current node.
- `next`: Saves the next node before changing the link.

### Java Code

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
```

### Dry Run

| Current | Previous | Action |
|---|---|---|
| 1 | null | Make `1.next = null` |
| 2 | 1 | Make `2.next = 1` |
| 3 | 2 | Make `3.next = 2` |
| 4 | 3 | Make `4.next = 3` |

Final list:

```text
4 → 3 → 2 → 1 → null
```

**Time:** O(n)  
**Auxiliary Space:** O(1)

Problem: [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)

---

## 7. Pattern 2: Slow and Fast Pointers

### When to Use

Consider this pattern when you need to:
- Find the middle node.
- Detect a cycle.
- Find the starting point of a cycle.
- Divide the list into two halves.

### Core Idea

```java
slow = slow.next;
fast = fast.next.next;
```

The slow pointer moves one step, while the fast pointer moves two steps.

### Example: Find the Middle

```text
1 → 2 → 3 → 4 → 5
```

When `fast` reaches the end, `slow` reaches the middle.

### Java Code

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

For an even number of nodes, this implementation returns the second middle node.

**Time:** O(n)  
**Auxiliary Space:** O(1)

Problem: [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)

### Detect a Cycle

If a linked list contains a cycle, the fast pointer eventually catches the slow pointer.

```java
class Solution {
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

Compare node references using `==`, not their values.

**Time:** O(n)  
**Auxiliary Space:** O(1)

Problem: [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

---

## 8. Pattern 3: Find the Kth Node from the End

### Core Idea

Keep two pointers separated by `k` nodes.

1. Move `fast` forward by `k` nodes.
2. Move both pointers one step at a time.
3. When `fast` reaches the end, `slow` points to the kth node from the end.

For problems that remove the kth node from the end, a dummy node simplifies handling the head.

Problem: [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

---

## 9. Pattern 4: Merge Two Sorted Linked Lists

### Problem

Merge two sorted linked lists into one sorted list.

Input:

```text
List 1: 1 → 3 → 5
List 2: 2 → 4 → 6
```

Output:

```text
1 → 2 → 3 → 4 → 5 → 6
```

### How to Think

1. Compare the current nodes.
2. Attach the smaller node to the result.
3. Advance the pointer belonging to that list.
4. Continue until one list is empty.
5. Attach the remaining nodes.

A dummy node makes it easier to build the result.

Problem: [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

---

## 10. Pattern 5: Palindrome Linked List

### Problem

Determine whether the linked-list values read the same forward and backward.

Example:

```text
1 → 2 → 2 → 1
```

Output:

```text
true
```

### Common Approach

1. Find the middle using slow and fast pointers.
2. Reverse the second half.
3. Compare the first half with the reversed second half.
4. If needed, restore the original list.

Problem: [234. Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)

**Time:** O(n)  
**Auxiliary Space:** O(1) for the standard in-place approach.

---

## 11. Pattern 6: Reorder a Linked List

### Problem

Rearrange:

```text
L0 → L1 → L2 → L3 → L4
```

Into:

```text
L0 → L4 → L1 → L3 → L2
```

### Approach

1. Find the middle.
2. Reverse the second half.
3. Alternately merge nodes from both halves.

Problem: [143. Reorder List](https://leetcode.com/problems/reorder-list/)

---

## 12. Pattern 7: Intersection of Two Linked Lists

Two linked lists intersect when they share the **same node in memory**, not merely nodes with equal values.

### Two-Pointer Approach

1. Start one pointer at each list's head.
2. Move each pointer one step at a time.
3. When a pointer reaches the end of its list, redirect it to the other list's head.
4. If the lists intersect, the pointers eventually meet at the intersection node.
5. If they do not intersect, both eventually become `null`.

Problem: [160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/)

**Time:** O(m + n)  
**Auxiliary Space:** O(1)

---

## 13. Pattern 8: Add Two Numbers

Two linked lists represent numbers, with digits stored in reverse order.

Example:

```text
List 1: 2 → 4 → 3
List 2: 5 → 6 → 4
```

These represent `342` and `465`.

Output:

```text
7 → 0 → 8
```

This represents `807`.

### How to Think

1. Add the current digits and carry.
2. Store `sum % 10` in the result node.
3. Update carry using `sum / 10`.
4. Continue while either list has nodes or a carry remains.

Problem: [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

---

## 14. Dummy Node Technique

A dummy node is a temporary node placed before the head.

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
```

### Why Use It?

Removing or inserting nodes at the head can require special handling. A dummy node provides a consistent node before the first real node.

It is particularly useful for:
- Removing nodes.
- Merging lists.
- Building a new list.
- Handling edge cases where the original head changes.

When finished, return:

```java
return dummy.next;
```

---

## 15. Common Linked List Mistakes

1. Losing the remaining list when changing `next`.
2. Forgetting to save `curr.next` before reversing a link.
3. Accessing `fast.next` before checking whether `fast` is `null`.
4. Forgetting to update the head after reversal or deletion.
5. Comparing node values instead of references when checking intersection or cycles.
6. Mishandling an empty list or a single-node list.
7. Forgetting to connect the remaining nodes when merging.
8. Creating a cycle accidentally while changing links.
9. Forgetting to handle carry when adding numbers.
10. Returning the dummy node instead of `dummy.next`.

---

## 16. Time Complexity Reference

Let `n` be the number of nodes.

| Operation | Typical time complexity |
|---|---|
| Traverse the list | O(n) |
| Search for a value | O(n) |
| Insert at the head | O(1) |
| Delete the head | O(1) |
| Reverse the list | O(n) |
| Find the middle | O(n) |
| Detect a cycle | O(n) |
| Merge two sorted lists | O(m + n) |
| Find the intersection | O(m + n) |

Insertion or deletion is O(1) when the required node or its predecessor is already known. Finding that position can take O(n).

---

## 17. Practice Roadmap

### Beginner
1. [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)
2. [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)
3. [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
4. [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
5. [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

### Intermediate
6. [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
7. [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
8. [160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/)
9. [234. Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)
10. [143. Reorder List](https://leetcode.com/problems/reorder-list/)
11. [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
12. [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)

### Advanced
13. [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)
14. [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
15. [148. Sort List](https://leetcode.com/problems/sort-list/)

---

## 18. Final Revision Checklist

Before solving a linked-list problem, ask:

1. Do I need to traverse, reverse, insert, or delete nodes?
2. Can slow and fast pointers simplify the problem?
3. Do I need a dummy node?
4. Should I save `next` before changing a link?
5. Does the problem require comparing node values or node references?
6. What happens for an empty list or a single node?
7. Can I solve it in O(n) time and O(1) auxiliary space?

### Final Mental Model

A linked-list problem usually becomes easier when you identify which references must change.

**Understand the pointers first. Then write the code.**
