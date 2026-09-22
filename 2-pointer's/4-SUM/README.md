# 454. 4Sum II

## Approach: HashMap + Pair Sums

### Intuition

The brute-force approach checks every possible combination of four numbers.

```text
A[i] + B[j] + C[k] + D[l] == 0
```

This requires **4 nested loops**, giving a time complexity of **O(n⁴)**, which is too slow.

Instead, we can divide the problem into two parts:

* First half: `A + B`
* Second half: `C + D`

We store all possible sums of `A` and `B` in a `HashMap`.

Then, for every sum from `C` and `D`, we look for its opposite value (complement) in the map.

If:

```text
(A + B) + (C + D) = 0
```

then

```text
A + B = -(C + D)
```

So, instead of checking every quadruplet, we only need to find whether the complement exists in the map.

---

## Algorithm

### Step 1: Store all sums of A and B

Create a `HashMap<Integer, Integer>`.

* Key = Sum (`a + b`)
* Value = Number of times that sum occurs

Example:

```text
A = [1, 2]
B = [-2, -1]

Possible sums:

1 + (-2) = -1
1 + (-1) = 0
2 + (-2) = 0
2 + (-1) = 1

HashMap:

-1 → 1
 0 → 2
 1 → 1
```

Notice that sum `0` appears **2 times**, so we store its frequency.

---

### Step 2: Find complements using C and D

Now generate every sum from `C` and `D`.

For each sum:

```text
sum = c + d
target = -(sum)
```

If `target` exists in the map, add its frequency to the answer.

---

## Dry Run

### Input

```text
A = [1, 2]
B = [-2, -1]
C = [-1, 2]
D = [0, 2]
```

### Step 1: Build HashMap

```text
1 + (-2) = -1
1 + (-1) = 0
2 + (-2) = 0
2 + (-1) = 1
```

HashMap

```text
-1 → 1
 0 → 2
 1 → 1
```

---

### Step 2: Process C and D

#### Pair 1

```text
c = -1
d = 0

sum = -1
target = 1
```

Map contains:

```text
1 → 1
```

Answer:

```text
count = 1
```

---

#### Pair 2

```text
c = -1
d = 2

sum = 1
target = -1
```

Map contains:

```text
-1 → 1
```

Answer:

```text
count = 2
```

---

#### Pair 3

```text
c = 2
d = 0

sum = 2
target = -2
```

Not found.

```text
count = 2
```

---

#### Pair 4

```text
c = 2
d = 2

sum = 4
target = -4
```

Not found.

Final answer:

```text
2
```

---

## Why HashMap?

The HashMap stores:

```text
Sum → Frequency
```

Example:

```text
0 → 5
```

means there are **5 different pairs** whose sum is `0`.

Whenever we need `0`, we simply add `5` to the answer instead of checking every pair again.

This makes the solution much faster.

---

## Time Complexity

### Building HashMap

Two nested loops:

```text
O(n²)
```

### Searching Complements

Again two nested loops:

```text
O(n²)
```

### Total Time Complexity

```text
O(n²)
```

---

## Space Complexity

The HashMap stores all possible sums of `A` and `B`.

```text
O(n²)
```

---

# Key Idea to Remember

Instead of checking all four arrays together:

```text
A + B + C + D = 0
```

Split the problem into two halves:

```text
(A + B) + (C + D) = 0
```

Store all sums of the first half in a `HashMap`, then search for the required complement from the second half.

This reduces the time complexity from:

```text
O(n⁴)
```

to

```text
O(n²)
```

which is the optimal solution for this problem.
