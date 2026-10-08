# 🔥 LeetCode 496 — Next Greater Element I

## 📝 Question

You are given two arrays:

```text
nums1
nums2
```

`nums1` is a subset of `nums2`.

For every element in `nums1`, find the **next greater element** of that element in `nums2`.

The **next greater element** means:

> The first element to the right that is greater than the current element.

If no greater element exists, return:

```text
-1
```

### Example

```text
Input:
nums1 = [4,1,2]
nums2 = [1,3,4,2]

Output:
[-1,3,-1]
```

Explanation:

```text
4 → no greater element → -1
1 → 3 is the first greater element → 3
2 → no greater element → -1
```

---

# 🧠 Understand the Problem

Take:

```text
nums2 = [1,3,4,2]
```

For every element:

```text
1 → 3
3 → 4
4 → -1
2 → -1
```

So we can create a mapping:

```text
1 → 3
3 → 4
4 → -1
2 → -1
```

Then for every element in `nums1`, simply look up its answer.

This is why we use:

```text
HashMap
```

---

# 🔍 Pattern Identification

This is a classic:

```text
MONOTONIC STACK
```

problem.

### How to recognize this pattern?

Whenever the question says:

* Next greater element
* Next smaller element
* Previous greater element
* Previous smaller element
* First greater element on the right
* First smaller element on the right

Think:

```text
STACK
```

More specifically:

```text
MONOTONIC STACK
```

---

# 🤔 Why Use Stack?

Suppose:

```text
nums2 = [1,3,4,2]
```

We are looking for the **next greater element**.

When we see:

```text
1
```

we don't know its answer yet.

So we keep it inside the stack.

When we later see:

```text
3
```

we realize:

```text
3 > 1
```

Therefore:

```text
1 → 3
```

So we remove `1` from the stack and store its answer.

---

# 💡 Main Idea

Think:

> "I will keep elements whose next greater element I haven't found yet."

When a new number comes:

```text
current number > stack top
```

Then the current number is the next greater element of the stack top.

So:

```text
while stack.top < current
        ↓
   stack.pop()
        ↓
   map.put(top, current)
```

This is the most important logic in the problem.

---

# 🧠 Hint to Write the Code Yourself

### Step 1

We need an answer for every element in `nums2`.

Use:

```java
HashMap<Integer, Integer> map
```

It will store:

```text
element → next greater element
```

---

### Step 2

Use a Stack.

```java
Stack<Integer> stack = new Stack<>();
```

The stack stores elements whose next greater element hasn't been found yet.

---

### Step 3

Traverse `nums2`.

```java
for(int i = 0; i < nums2.length; i++)
```

---

### Step 4

Before pushing the current element, check:

```text
Is current > stack top?
```

If yes, current is the next greater element.

```java
while(!stack.isEmpty() && stack.peek() < nums2[i])
```

---

### Step 5

Remove the smaller element:

```java
int value = stack.pop();
```

And store:

```java
map.put(value, nums2[i]);
```

---

### Step 6

After processing everything, some elements will still be inside the stack.

Those elements don't have a greater element.

So:

```text
remaining elements → -1
```

---

### Step 7

Finally, traverse `nums1` and get the answers from the HashMap.

---

# 💻 Code

```java
class Solution { 
    public int[] nextGreaterElement(int[] nums1, int[] nums2) { 
         
        int[] res = new int[nums1.length]; 
 
        HashMap<Integer,Integer> map = new HashMap<>(); 
 
        Stack<Integer> stack = new Stack<>(); 
 
        for(int i = 0; i < nums2.length; i++){ 
            
            while(!stack.isEmpty() && stack.peek() < nums2[i]){ 
                map.put(stack.pop(), nums2[i]); 
            } 
            
            stack.push(nums2[i]); 
        } 
        
        for(int i : stack){ 
            map.put(i, -1); 
        } 
        
        for(int i = 0; i < nums1.length; i++){ 
            res[i] = map.get(nums1[i]); 
        } 
        
        return res; 
    } 
}
```

---

# 🔎 Code Explanation

## 1. Result Array

```java
int[] res = new int[nums1.length];
```

Stores the final answers.

For:

```text
nums1 = [4,1,2]
```

we need:

```text
res = [-1,3,-1]
```

---

## 2. HashMap

```java
HashMap<Integer,Integer> map = new HashMap<>();
```

Stores:

```text
number → next greater number
```

Example:

```text
1 → 3
3 → 4
4 → -1
2 → -1
```

---

## 3. Stack

```java
Stack<Integer> stack = new Stack<>();
```

Stores elements waiting to find their next greater element.

---

## 4. Traverse `nums2`

```java
for(int i = 0; i < nums2.length; i++)
```

We process:

```text
1 → 3 → 4 → 2
```

---

## 5. Find Greater Element

```java
while(!stack.isEmpty() && stack.peek() < nums2[i])
```

This means:

> While the current number is greater than the number waiting at the top of the stack.

Example:

```text
Stack top = 1
Current = 3

3 > 1
```

Therefore:

```text
1 → 3
```

---

## 6. Store Answer

```java
map.put(stack.pop(), nums2[i]);
```

First:

```java
stack.pop()
```

removes the element.

Then:

```java
map.put()
```

stores its next greater element.

---

## 7. Push Current Element

```java
stack.push(nums2[i]);
```

We haven't found the next greater element for the current number yet.

So keep it in the stack.

---

## 8. Remaining Elements

```java
for(int i : stack){
    map.put(i, -1);
}
```

If an element is still in the stack after processing the entire array:

```text
No greater element exists
```

Therefore:

```text
element → -1
```

---

## 9. Build Final Answer

```java
for(int i = 0; i < nums1.length; i++){
    res[i] = map.get(nums1[i]);
}
```

Simply look up every `nums1` element in the map.

---

# 🧪 Dry Run

### Input

```text
nums1 = [4,1,2]

nums2 = [1,3,4,2]
```

Initially:

```text
Stack = []
Map = {}
```

---

## 📊 Detailed Dry Run

| Step | Current | Stack Before | Action                    | Stack After | Map          |
| ---- | ------: | ------------ | ------------------------- | ----------- | ------------ |
| 1    |       1 | `[]`         | Push 1                    | `[1]`       | `{}`         |
| 2    |       3 | `[1]`        | `3 > 1` → `1 → 3`, push 3 | `[3]`       | `{1=3}`      |
| 3    |       4 | `[3]`        | `4 > 3` → `3 → 4`, push 4 | `[4]`       | `{1=3, 3=4}` |
| 4    |       2 | `[4]`        | `2 < 4` → push 2          | `[4,2]`     | `{1=3, 3=4}` |

After the loop:

```text
Stack = [4,2]
```

These elements have no greater element.

So:

```text
4 → -1
2 → -1
```

Final Map:

```text
{
    1 = 3,
    3 = 4,
    4 = -1,
    2 = -1
}
```

---

# 🔍 Final `nums1` Lookup

```text
nums1 = [4,1,2]
```

| nums1 Element |   Map Lookup | Answer |
| ------------: | -----------: | -----: |
|             4 | `map.get(4)` |     -1 |
|             1 | `map.get(1)` |      3 |
|             2 | `map.get(2)` |     -1 |

Therefore:

```text
Output = [-1,3,-1]
```

---

# 🧠 Visual Dry Run

```text
nums2 = [1, 3, 4, 2]
```

### Read 1

```text
Stack:
[1]
```

No greater element yet.

---

### Read 3

```text
3 > 1

1 → 3
```

```text
Stack:
[3]
```

---

### Read 4

```text
4 > 3

3 → 4
```

```text
Stack:
[4]
```

---

### Read 2

```text
2 < 4
```

So we cannot resolve `4`.

Push `2`.

```text
Stack:
[4,2]
```

End of array.

Both remain:

```text
4 → -1
2 → -1
```

---

# 🚀 Why Is This Better Than Brute Force?

### Brute Force

For every element, search to the right.

This can take:

```text
O(n²)
```

For a large array, this becomes slow.

### Monotonic Stack

Each element is:

```text
Pushed once
Popped once
```

Therefore:

```text
O(n)
```

This is much more efficient.

---

# ⏱️ Complexity

Let:

```text
n = nums2.length
m = nums1.length
```

### Time

Processing `nums2`:

```text
O(n)
```

Processing `nums1`:

```text
O(m)
```

Overall:

```text
O(n + m)
```

### Space

HashMap:

```text
O(n)
```

Stack:

```text
O(n)
```

Result:

```text
O(m)
```

Overall auxiliary/output space:

```text
O(n + m)
```

---

# 🎯 Interview Point of View

### Q1. Why use a Stack?

**Answer:**

> We use a monotonic stack because we need to find the first greater element on the right. Elements remain in the stack until a greater element is encountered.

---

### Q2. Why use a HashMap?

**Answer:**

> After calculating the next greater element for every value in `nums2`, the HashMap lets us retrieve the answer for each `nums1` element in O(1) average time.

---

### Q3. Why use a `while` loop instead of `if`?

This is very important.

Suppose:

```text
nums2 = [1,2,3,4]
```

When `4` arrives:

```text
Stack = [2,3]
```

`4` is greater than **both**.

So we need:

```java
while(...)
```

not:

```java
if(...)
```

The `while` resolves multiple elements.

---

### Q4. Why do remaining elements get `-1`?

Because they reached the end of `nums2` without finding a greater element.

Therefore:

```text
No greater element → -1
```

---

### Q5. What type of Stack is this?

This is a:

```text
MONOTONIC DECREASING STACK
```

The elements in the stack are maintained in decreasing order.

Example:

```text
[4,2]
```

When `3` arrives:

```text
3 > 2
```

So `2` is removed.

Then:

```text
[4]
```

`3` is pushed:

```text
[4,3]
```

The decreasing property is maintained.

---

# 🔑 Pattern to Remember

For **Next Greater Element**:

```text
Current > Stack Top
        ↓
Stack Top found its answer
        ↓
Pop Stack Top
        ↓
Map[Stack Top] = Current
```

Then:

```text
Push Current
```

At the end:

```text
Remaining → -1
```

### Golden Rule 🏆

> **Next Greater → Monotonic Decreasing Stack**

And remember:

```text
NEXT GREATER
      ↓
STACK
      ↓
Current > Top
      ↓
POP
      ↓
MAP
      ↓
PUSH CURRENT
```

This pattern will directly help with problems like **Next Greater Element II, Daily Temperatures, Stock Span, and other monotonic-stack problems.**
