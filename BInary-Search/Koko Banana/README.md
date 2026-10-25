# Koko Eating Bananas – Binary Search

## Step 1: Question Explanation

### Problem Statement

You are given an integer array `a`, where `a[i]` represents the number of bananas in the `i-th` pile.

You are also given an integer `guard`, representing the total number of hours available to eat all the bananas.

Koko can choose an integer eating speed `speed`, which represents the number of bananas she eats per hour.

**Rules:**

1. Koko can eat from only one pile per hour.
2. If a pile contains fewer bananas than her eating speed, she finishes that pile within one hour.
3. Koko cannot eat more than one pile in the same hour.
4. Find the minimum eating speed required to finish all the bananas within `guard` hours.

### Example

**Input:**

```text
a = [3, 6, 7, 11]
guard = 8
```

**Output:**

```text
4
```

**Explanation:**

If Koko eats at a speed of `4` bananas per hour:

* Pile 1: 3 bananas → 1 hour
* Pile 2: 6 bananas → 2 hours
* Pile 3: 7 bananas → 2 hours
* Pile 4: 11 bananas → 3 hours

Total hours = 1 + 2 + 2 + 3 = 8 hours.

Therefore, the minimum eating speed is `4`.

---

## Step 2: Pattern Recognition

### Pattern: Binary Search on Answer

This problem uses **Binary Search on Answer** because we need to find the minimum valid eating speed.

We are not searching for an element in an array. Instead, we are searching for the smallest speed that satisfies a condition.

### How to Recognize This Pattern

Look for these characteristics:

1. The problem asks for the minimum possible speed.
2. We can define a range of possible answers.
3. We can check whether a particular speed is valid.
4. If a speed is too slow, we need to increase it.
5. If a speed is valid, we try a smaller speed.

### Search Space

```text
low = 1
high = maximum pile size
```

The minimum possible speed is `1`.

The maximum necessary speed is the size of the largest pile because eating faster than that cannot reduce the time needed to finish a pile below one hour.

### Why Binary Search?

Instead of checking every speed from `1` to `max(a)`, binary search eliminates half of the search space in every iteration.

This makes the solution more efficient.

---

## Step 3: Concept and Core Logic — Monotonic Function

### What Is a Monotonic Function?

A function is monotonic when its output consistently moves in one direction as its input increases or decreases.

In this problem:

* If eating speed increases, the total hours required never increase.
* If eating speed decreases, the total hours required never decrease.

Therefore, the relationship between eating speed and required hours is monotonic.

### Understanding the Monotonic Property

Suppose:

```text
a = [3, 6, 7, 11]
guard = 8
```

| Eating Speed | Hours Required | Valid? |
| -----------: | -------------: | ------ |
|            1 |             27 | No     |
|            2 |             15 | No     |
|            3 |             10 | No     |
|            4 |              8 | Yes    |
|            5 |              6 | Yes    |
|            6 |              6 | Yes    |
|            7 |              5 | Yes    |
|            8 |              5 | Yes    |
|            9 |              5 | Yes    |
|           10 |              5 | Yes    |
|           11 |              4 | Yes    |

Notice the pattern:

```text
Speed:  1  2  3  4  5  6  7  8  9  10  11

Valid:  N  N  N  Y  Y  Y  Y  Y  Y   Y   Y
```

Here:

* `N` means the speed is invalid.
* `Y` means the speed is valid.

All invalid speeds appear before the valid speeds.

Our goal is to find the **first valid speed**.

### Binary Search Logic

We initialize:

```java
int low = 1;
int high = max(a);
int res = -1;
```

Calculate the middle speed:

```java
int mid = low + (high - low) / 2;
```

Calculate the hours required:

```java
long hours = getTime(a, n, mid);
```

There are two possible cases.

**Case 1: Hours required > guard**

The speed is too slow.

We need to increase the speed.

```java
low = mid + 1;
```

**Case 2: Hours required <= guard**

The speed is valid.

Store it as a possible answer and search for a smaller valid speed.

```java
res = mid;
high = mid - 1;
```

When the loop finishes, `res` contains the minimum valid eating speed.

### Calculating Hours Using Ceiling Division

For each pile, the required hours are:

```text
Hours = ceil(pile size / speed)
```

For example:

```text
Pile size = 7
Speed = 3

Hours = ceil(7 / 3) = 3
```

In Java, integer division removes the decimal part. Therefore, we add one when a remainder exists.

```java
h = h + a[i] / speed;

if (a[i] % speed != 0) {
    h++;
}
```

This is equivalent to:

```text
ceil(a[i] / speed)
```

We use `long` for the total hours to avoid potential integer overflow.

---

## Step 4: Code and Code Explanation

### Java Solution

```java
class Solution {

    public long getTime(int[] a, int n, int speed) {
        long h = 0;

        for (int i = 0; i < a.length; i++) {
            h = h + a[i] / speed;

            if (a[i] % speed != 0) {
                h++;
            }
        }

        return h;
    }

    public int minEatingSpeed(int[] a, int guard) {
        int n = a.length;
        int res = -1;
        int low = 1;
        int high = 0;

        for (int i = 0; i < n; i++) {
            high = Math.max(high, a[i]);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            long hours = getTime(a, n, mid);

            if (hours > guard) {
                low = mid + 1;
            } else {
                res = mid;
                high = mid - 1;
            }
        }

        return res;
    }
}
```

### Code Explanation

#### 1. `getTime()` Method

```java
public long getTime(int[] a, int n, int speed)
```

This method calculates the total hours required to eat all the bananas at a given speed.

```java
long h = 0;
```

Initializes the total hours to zero.

```java
h = h + a[i] / speed;
```

Adds the number of complete hours required for the current pile.

```java
if (a[i] % speed != 0) {
    h++;
}
```

Adds one extra hour when the pile cannot be finished in the complete hours calculated by integer division.

```java
return h;
```

Returns the total hours required.

#### 2. Initialize Binary Search

```java
int low = 1;
int high = 0;
int res = -1;
```

* `low`: Minimum possible eating speed.
* `high`: Maximum possible eating speed, calculated from the array.
* `res`: Stores the minimum valid speed found so far.

#### 3. Find the Maximum Pile

```java
for (int i = 0; i < n; i++) {
    high = Math.max(high, a[i]);
}
```

Finds the largest pile in the array.

This becomes the upper boundary of the search space.

#### 4. Calculate the Middle Speed

```java
int mid = low + (high - low) / 2;
```

Calculates the middle value of the current search range.

This formula avoids potential overflow associated with `(low + high) / 2`.

#### 5. Calculate Required Hours

```java
long hours = getTime(a, n, mid);
```

Calculates how many hours are required if Koko eats at speed `mid`.

#### 6. If the Speed Is Too Slow

```java
if (hours > guard) {
    low = mid + 1;
}
```

If the required hours exceed the available hours, the current speed is insufficient.

Therefore, we search for a higher speed.

#### 7. If the Speed Is Valid

```java
else {
    res = mid;
    high = mid - 1;
}
```

If the required hours are within the limit, the speed is valid.

We save it in `res` and search the left half for a smaller valid speed.

#### 8. Return the Answer

```java
return res;
```

Returns the minimum valid eating speed.

---

## Step 5: Dry Run and Tabulation

### Input

```text
a = [3, 6, 7, 11]
guard = 8
```

First, calculate the search boundaries:

```text
low = 1
high = 11
res = -1
```

### Binary Search Dry Run

| Iteration | Low | High | Mid | Hours Required | Condition | Action       | Result |
| --------: | --: | ---: | --: | -------------: | --------- | ------------ | -----: |
|         1 |   1 |   11 |   6 |              6 | 6 <= 8    | Search left  |      6 |
|         2 |   1 |    5 |   3 |             10 | 10 > 8    | Search right |      6 |
|         3 |   4 |    5 |   4 |              8 | 8 <= 8    | Search left  |      4 |

After the third iteration:

```text
low = 4
high = 3
```

Since `low > high`, the loop terminates.

The final answer is:

```text
res = 4
```

### Detailed Calculation of Hours

#### Iteration 1: Speed = 6

```text
Pile 3  -> ceil(3 / 6)  = 1 hour
Pile 6  -> ceil(6 / 6)  = 1 hour
Pile 7  -> ceil(7 / 6)  = 2 hours
Pile 11 -> ceil(11 / 6) = 2 hours

Total = 1 + 1 + 2 + 2 = 6 hours
```

Since `6 <= 8`, speed `6` is valid.

Store `6` as the answer and search for a smaller speed.

```text
res = 6
high = 5
```

#### Iteration 2: Speed = 3

```text
Pile 3  -> ceil(3 / 3)  = 1 hour
Pile 6  -> ceil(6 / 3)  = 2 hours
Pile 7  -> ceil(7 / 3)  = 3 hours
Pile 11 -> ceil(11 / 3) = 4 hours

Total = 1 + 2 + 3 + 4 = 10 hours
```

Since `10 > 8`, speed `3` is too slow.

Increase the lower boundary.

```text
low = 4
```

#### Iteration 3: Speed = 4

```text
Pile 3  -> ceil(3 / 4)  = 1 hour
Pile 6  -> ceil(6 / 4)  = 2 hours
Pile 7  -> ceil(7 / 4)  = 2 hours
Pile 11 -> ceil(11 / 4) = 3 hours

Total = 1 + 2 + 2 + 3 = 8 hours
```

Since `8 <= 8`, speed `4` is valid.

Store `4` as the answer and search for a smaller speed.

```text
res = 4
high = 3
```

The loop terminates because `low > high`.

### Final Output

```text
4
```

### Why Is 4 the Minimum Speed?

Speed `3` requires `10` hours, which exceeds the available `8` hours.

Speed `4` requires exactly `8` hours.

Therefore, `4` is the minimum valid eating speed.

---

## Step 6: Interview Point of View

### 1. Which algorithm is used?

Binary Search on Answer.

### 2. Why do we use binary search?

Because the answer lies in a searchable range, and the validity of a speed follows a monotonic pattern.

### 3. What is the search space?

```text
1 to maximum pile size
```

### 4. Why is the maximum pile size the upper boundary?

Eating at the maximum pile size allows Koko to finish every non-empty pile in at most one hour. A higher speed cannot improve this further.

### 5. What is the monotonic property?

As the eating speed increases, the total hours required never increase.

Once a speed becomes valid, every higher speed is also valid.

### 6. Why do we use `long` for hours?

To safely store the total hours without risking integer overflow for large inputs.

### 7. Why do we check the remainder?

Integer division removes the fractional part.

For example:

```text
7 / 3 = 2
```

But eating seven bananas at a speed of three requires three hours.

Therefore, if the remainder is not zero, we add one extra hour.

### 8. Why do we use `res = mid`?

Because `mid` is a valid answer, but a smaller valid speed might exist.

We store the current answer and continue searching the left half.

### 9. Why not return immediately when `hours <= guard`?

Because the problem asks for the minimum valid speed, not just any valid speed.

We must continue searching for a smaller valid answer.

### 10. What is the time complexity?

Let:

* `n` = number of piles.
* `M` = maximum pile size.

Finding the maximum pile takes `O(n)` time.

Binary search performs `O(log M)` iterations, and each iteration calls `getTime()`, which takes `O(n)` time.

Therefore, the overall time complexity is:

```text
O(n log M)
```

### 11. What is the space complexity?

The algorithm uses only a constant number of extra variables and does not create additional data structures.

Therefore, the auxiliary space complexity is:

```text
O(1)
```

### 12. What is the main interview takeaway?

**Recognize the Binary Search on Answer pattern whenever a problem asks for a minimum or maximum value and you can efficiently check whether a candidate answer is valid.**

In this problem:

* Search space: Eating speed.
* Validation function: `getTime()`.
* Invalid condition: `hours > guard`.
* Valid condition: `hours <= guard`.
* Goal: Find the first valid speed.
