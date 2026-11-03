# LeetCode 215 – Kth Largest Element in an Array

**Difficulty:** Medium  
**Topic:** Heap / Priority Queue  
**Pattern:** Top K Elements  
**Approach:** Min Heap of Size K  
**Time Complexity:** O(N log K)  
**Space Complexity:** O(K)

---

# Step 1: Question Explanation

### Problem Statement

Given an integer array `nums` and an integer `k`, return the `kth` largest element in the array.

Important points:
- We need to find the Kth largest element.
- The array may contain duplicate elements.
- We need to find the Kth largest element in sorted order.
- We don't need to find the Kth distinct largest element.
- We should not necessarily sort the entire array.

### Example 1

**Input:**
```java
nums = [3, 2, 1, 5, 6, 4]
k = 2
```

**Output:**
```java
5
```

**Explanation:**

Elements in descending order:

`[6, 5, 4, 3, 2, 1]`

The 2nd largest element is `5`.

### Example 2

**Input:**
```java
nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]
k = 4
```

**Output:**
```java
4
```

**Explanation:**

Elements in descending order:

`[6, 5, 5, 4, 3, 3, 2, 2, 1]`

The 4th largest element is `4`.

---

# Step 2: Pattern Recognition

### How to Identify the Pattern?

Whenever a question contains keywords like:

- Kth Largest
- Kth Smallest
- Top K Elements
- K Most Frequent Elements
- Find the smallest/largest K elements

Think about the **Heap / Priority Queue pattern**.

### Why Heap?

A Heap helps us efficiently maintain the largest or smallest elements without sorting the entire array.

For this problem:

We need the Kth largest element.

Instead of sorting the complete array, we can maintain only the K largest elements using a Min Heap.

### Pattern Identified

**Top K Elements → Min Heap of Size K**

---

# Step 3: Explain the Concept and Core Logic

### 1. What is a Heap?

A Heap is a special data structure that follows the heap property.

There are two types:

**Min Heap:**
- Smallest element is always at the top.
- `peek()` returns the smallest element.
- `poll()` removes the smallest element.

**Max Heap:**
- Largest element is always at the top.
- `peek()` returns the largest element.
- `poll()` removes the largest element.

### 2. Why Min Heap for Kth Largest?

Our goal is to find the Kth largest element.

Suppose:

```java
nums = [3, 2, 1, 5, 6, 4]
k = 2
```

We need to maintain the 2 largest elements.

The Min Heap will always keep the smallest element among those K largest elements at its root.

Example:

```text
Min Heap = [5, 6]

       5
      /
     6
```

Here:
- 6 is the largest element.
- 5 is the second largest element.
- 5 is at the root.

Therefore:

`pq.peek()` gives us the Kth largest element.

### 3. Core Algorithm

**Step 1:** Create a Min Heap.

**Step 2:** Insert the first K elements into the heap.

**Step 3:** Traverse the remaining elements.

**Step 4:** Compare the current element with the heap's root.

- If the current element is smaller than or equal to the root, ignore it.
- If the current element is greater than the root:
  - Remove the root using `poll()`.
  - Insert the current element using `offer()`.

**Step 5:** After processing all elements, return `pq.peek()`.

### Why Does This Work?

The heap always maintains the K largest elements encountered so far.

The smallest among these K elements is the Kth largest element.

---

# Step 4: Code and Code Explanation

### Java Solution

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {

        // Create Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Insert first K elements
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        // Process remaining elements
        for (int i = k; i < nums.length; i++) {

            // If current element is smaller than or equal
            // to the smallest element in the heap, ignore it
            if (nums[i] <= pq.peek()) {
                continue;
            }

            // Remove smallest element
            pq.poll();

            // Insert current element
            pq.offer(nums[i]);
        }

        // Root contains Kth largest element
        return pq.peek();
    }
}
```

### Line-by-Line Explanation

**1. Create Min Heap**

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

Java's default PriorityQueue implements a Min Heap.

**2. Insert First K Elements**

```java
for (int i = 0; i < k; i++) {
    pq.offer(nums[i]);
}
```

Initially, we insert K elements into the heap.

**3. Traverse Remaining Elements**

```java
for (int i = k; i < nums.length; i++)
```

We process the remaining elements one by one.

**4. Compare with Root**

```java
if (nums[i] <= pq.peek()) {
    continue;
}
```

If the current element is smaller than or equal to the root, it cannot enter the K largest elements.

Therefore, skip it.

**5. Remove and Insert**

```java
pq.poll();
pq.offer(nums[i]);
```

If the current element is greater than the root:

- Remove the smallest element.
- Insert the current element.

This ensures that the heap always maintains the K largest elements processed so far.

**6. Return Answer**

```java
return pq.peek();
```

The root contains the Kth largest element.

### Complexity Analysis

Let:
- N = Number of elements.
- K = Required position.

| Operation | Time Complexity |
|---|---|
| Insert first K elements | O(K log K) |
| Process remaining N-K elements | O((N-K) log K) |
| Peek | O(1) |

**Overall Time Complexity:**

`O(N log K)`

**Space Complexity:**

`O(K)`

We maintain only K elements in the heap.

---

# Step 5: Dry Run and Tabulated Dry Run

### Input

```java
nums = [3, 2, 1, 5, 6, 4]
k = 2
```

Expected Output:

```java
5
```

### Phase 1: Insert First K Elements

We insert the first two elements: 3 and 2.

| Operation | Element | Min Heap | Peek |
|---|---|---|---|
| Insert | 3 | [3] | 3 |
| Insert | 2 | [2, 3] | 2 |

Now the heap contains:

`[2, 3]`

Heap size = K = 2.

### Phase 2: Process Remaining Elements

Remaining elements:

`[1, 5, 6, 4]`

| i | nums[i] | pq.peek() | Condition nums[i] <= pq.peek() | Operation | Heap After Operation |
|---|---|---|---|---|---|
| 2 | 1 | 2 | true | Skip | [2, 3] |
| 3 | 5 | 2 | false | Poll 2, Offer 5 | [3, 5] |
| 4 | 6 | 3 | false | Poll 3, Offer 6 | [5, 6] |
| 5 | 4 | 5 | true | Skip | [5, 6] |

### Final Heap

```text
       5
      /
     6
```

Heap elements:

`[5, 6]`

Now:

```java
return pq.peek();
```

Output:

`5`

### Final Answer: 5

### Important Dry Run Observation

At every iteration:

1. Heap size remains K.
2. Heap contains the K largest elements processed so far.
3. Root contains the smallest element among those K elements.
4. At the end, root gives the Kth largest element.

---

# Step 6: Interview Point of View

### 1. Why did you choose Min Heap instead of Max Heap?

Because we need to maintain the K largest elements.

A Min Heap allows us to remove the smallest element among those K elements in O(log K).

The root ultimately represents the Kth largest element.

### 2. Can we solve this using Max Heap?

Yes.

We can insert all elements into a Max Heap and remove the root K-1 times.

The remaining root will be the Kth largest element.

However, this approach takes O(N + K log N) time and O(N) space.

### 3. Why not use Sorting?

Sorting takes O(N log N) time.

A Min Heap of size K takes O(N log K) time and O(K) space.

When K is much smaller than N, the heap approach can be more efficient.

### 4. What happens if duplicate elements exist?

Duplicates are counted.

For example:

```java
nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]
k = 4
```

Sorted descending:

`[6, 5, 5, 4, 3, 3, 2, 2, 1]`

Answer = 4.

We are finding the Kth largest element, not the Kth distinct largest element.

### 5. What is the difference between Min Heap and Max Heap?

| Operation | Min Heap | Max Heap |
|---|---|---|
| Root | Smallest | Largest |
| offer() | O(log N) | O(log N) |
| poll() | O(log N) | O(log N) |
| peek() | O(1) | O(1) |
| Java Declaration | `new PriorityQueue<>()` | `new PriorityQueue<>(Collections.reverseOrder())` |

### 6. Important Java Heap Syntax

```java
// Min Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());

// Insert
pq.offer(10);

// Remove top element
pq.poll();

// Get top element
pq.peek();

// Check empty
pq.isEmpty();

// Get size
pq.size();
```

---

# Final Revision Notes

| Question | Heap Pattern |
|---|---|
| Kth Largest Element | Min Heap of Size K |
| Kth Smallest Element | Max Heap of Size K |
| Top K Frequent Elements | Priority Queue |
| Merge K Sorted Lists | Min Heap |
| Find Median from Data Stream | Two Heaps |
| K Closest Points to Origin | Max Heap of Size K |

### Most Important Interview Takeaway

**Kth Largest → Min Heap of Size K**

**Kth Smallest → Max Heap of Size K**

**Remember:**

- Min Heap keeps the K largest elements.
- Max Heap keeps the K smallest elements.
- `peek()` gives the answer after processing all elements.

---

**LeetCode:** 215  
**Problem:** Kth Largest Element in an Array  
**Pattern:** Top K Elements  
**Data Structure:** Priority Queue / Heap  
**Optimal Approach:** Min Heap of Size K  
**Time:** O(N log K)  
**Space:** O(K)