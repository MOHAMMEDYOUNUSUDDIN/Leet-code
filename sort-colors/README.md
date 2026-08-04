# LeetCode 75 - Sort Colors (Dutch National Flag Algorithm)

## Problem
You are given an array containing only **0s, 1s, and 2s**.

- `0` → Red
- `1` → White
- `2` → Blue

Sort the array **in-place** without using any extra array.

### Example
```
Input:  [2, 0, 2, 1, 1, 0]
Output: [0, 0, 1, 1, 2, 2]
```

---

# Approach (Three Pointers)

We use **3 pointers**:

- **i** → Position where the next `0` should be placed.
- **j** → Current element we are checking.
- **k** → Position where the next `2` should be placed.

Initially,

```
i = 0
j = 0
k = n - 1
```

---

# Rules

### Case 1: nums[j] == 0

Swap `nums[i]` and `nums[j]`.

Then,

- i++
- j++

Because the `0` is now in its correct position.

---

### Case 2: nums[j] == 1

Nothing to do.

Just move to the next element.

```
j++
```

Because `1` belongs in the middle.

---

### Case 3: nums[j] == 2

Swap `nums[j]` and `nums[k]`.

Then,

```
k--
```

Do **NOT** increase `j`.

Why?

Because the element that came from the end has not been checked yet.

---

# Dry Run

Input

```
[2,0,2,1,1,0]
```

Initial

```
i = 0
j = 0
k = 5
```

### Step 1

```
2 0 2 1 1 0
^

nums[j] = 2
```

Swap with `k`

```
0 0 2 1 1 2

k--
```

Now

```
i=0
j=0
k=4
```

---

### Step 2

```
0 0 2 1 1 2
^

nums[j]=0
```

Swap with `i`

(No change)

```
0 0 2 1 1 2
```

Move

```
i=1
j=1
```

---

### Step 3

```
0 0 2 1 1 2
  ^

nums[j]=0
```

Swap with `i`

(No change)

Move

```
i=2
j=2
```

---

### Step 4

```
0 0 2 1 1 2
    ^

nums[j]=2
```

Swap with `k`

```
0 0 1 1 2 2

k--
```

Now

```
i=2
j=2
k=3
```

---

### Step 5

```
0 0 1 1 2 2
    ^

nums[j]=1
```

Just move

```
j=3
```

---

### Step 6

```
0 0 1 1 2 2
      ^

nums[j]=1
```

Move

```
j=4
```

Now

```
j > k
```

Stop.

Final Answer

```
0 0 1 1 2 2
```

---

# Why don't we increment `j` after swapping with `k`?

Suppose

```
2 1 0
^
j
```

Swap with `k`

```
0 1 2
^
j
```

The new element (`0`) came from the end.

We have **not checked it yet**, so `j` should stay at the same position.

---

# Time Complexity

```
O(n)
```

Each element is visited at most once.

---

# Space Complexity

```
O(1)
```

No extra space is used.

---

# Key Idea

- Keep all **0s** on the left.
- Keep all **2s** on the right.
- **1s** automatically remain in the middle.

This is called the **Dutch National Flag Algorithm**, and it is the optimal solution for this problem.