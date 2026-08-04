# Reverse Vowels of a String - Two Pointer Approach

## Problem Statement

Given a string `s`, reverse **only the vowels** in the string and return the modified string.

The positions of all **non-vowel characters** should remain unchanged.

### Vowels

```text
a, e, i, o, u
A, E, I, O, U
```

---

## Example 1

### Input

```text
s = "hello"
```

### Output

```text
"holle"
```

### Explanation

Original string:

```text
h e l l o
```

Vowels:

```text
e, o
```

Reverse only the vowels:

```text
h o l l e
```

---

## Example 2

### Input

```text
s = "leetcode"
```

### Output

```text
"leotcede"
```

---

# Intuition

We don't need to reverse the entire string.

We only need to reverse the vowels.

The easiest way is to:

* Start one pointer from the beginning.
* Start another pointer from the end.
* Find the next vowel from both sides.
* Swap them.
* Repeat until both pointers meet.

This is called the **Two Pointer Technique**.

---

# Two Pointer Approach

We use two pointers.

* **left** → Starts from the beginning of the string.
* **right** → Starts from the end of the string.

```text
left ------------------> <------------------ right
```

At every step:

1. Move `left` until it finds a vowel.
2. Move `right` until it finds a vowel.
3. Swap both vowels.
4. Move both pointers.
5. Continue until `left >= right`.

---

# Dry Run

## Input

```text
s = "hello"
```

Convert the string into a character array.

```text
Index : 0 1 2 3 4

Value : h e l l o
        ↑       ↑
      left    right
```

---

## Step 1

Check `left`

```text
h
```

Is `h` a vowel?

```text
No
```

Move `left`.

```text
h e l l o
  ↑     ↑
left  right
```

---

## Step 2

Now

```text
left = e
right = o
```

Both are vowels.

Swap them.

Before

```text
h e l l o
```

After

```text
h o l l e
```

Move both pointers.

```text
h o l l e
    ↑ ↑
 left right
```

---

## Step 3

Now

```text
left = l
right = l
```

Since

```text
left >= right
```

Stop.

Final Answer

```text
holle
```

---

# Another Example

## Input

```text
s = "leetcode"
```

Initial

```text
l e e t c o d e
↑             ↑
L             R
```

### Step 1

Both are vowels.

Swap

```text
l e e t c o d e
```

No visible change because both characters are `e`.

Move both pointers.

---

### Step 2

```text
l e e t c o d e
  ↑       ↑
  L       R
```

`e` and `o` are vowels.

Swap

```text
l o e t c e d e
```

Move both.

---

### Step 3

Pointers meet.

Answer

```text
leotcede
```

---

# Visualization

```text
hello

h e l l o
↑       ↑
L       R

↓

Skip 'h'

h e l l o
  ↑     ↑
  L     R

↓

Found vowels

Swap

h o l l e

↓

Move both pointers

Done
```

---

# Java Solution

```java
class Solution {

    // Function to check whether a character is a vowel
    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' ||
               ch == 'o' || ch == 'u' ||
               ch == 'A' || ch == 'E' || ch == 'I' ||
               ch == 'O' || ch == 'U';
    }

    public String reverseVowels(String s) {

        char[] arr = s.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            if (!isVowel(arr[left])) {
                left++;
            }
            else if (!isVowel(arr[right])) {
                right--;
            }
            else {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        }

        return new String(arr);
    }
}
```

---

# Code Explanation

### Step 1

```java
char[] arr = s.toCharArray();
```

Strings in Java are **immutable**, so we convert the string into a character array to allow swapping.

---

### Step 2

```java
int left = 0;
int right = arr.length - 1;
```

Initialize two pointers.

* `left` starts from the beginning.
* `right` starts from the end.

---

### Step 3

```java
while (left < right)
```

Keep processing until both pointers meet.

---

### Step 4

```java
if (!isVowel(arr[left])) {
    left++;
}
```

If the left character is not a vowel, move `left` forward.

---

### Step 5

```java
else if (!isVowel(arr[right])) {
    right--;
}
```

If the right character is not a vowel, move `right` backward.

---

### Step 6

```java
char temp = arr[left];
arr[left] = arr[right];
arr[right] = temp;
```

Both pointers are on vowels, so swap them.

---

### Step 7

```java
left++;
right--;
```

Move both pointers inward and continue searching.

---

# Why Does This Work?

* Every non-vowel is skipped.
* Only vowels are swapped.
* The order of non-vowel characters never changes.
* Each pointer moves only in one direction.

---

# Time Complexity

```text
O(n)
```

Each pointer visits every character at most once.

---

# Space Complexity

```text
O(n)
```

A character array is created from the input string.

---

# Key Takeaways

* Use **Two Pointers**.
* `left` searches for the next vowel from the left.
* `right` searches for the next vowel from the right.
* Swap only when **both pointers are on vowels**.
* Skip consonants.
* Continue until the pointers meet.

---

# Pattern to Remember

```text
Start

left = 0
right = n - 1

While (left < right)

    Is left a vowel?
        No → left++

    Is right a vowel?
        No → right--

    Otherwise
        Swap(left, right)
        left++
        right--

End
```

This is a classic **Two Pointer** pattern where two pointers move toward each other, skipping unwanted elements and processing only the required ones.
