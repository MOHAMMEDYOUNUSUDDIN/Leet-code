# LeetCode 71 — Simplify Path

**Problem Link:** https://leetcode.com/problems/simplify-path/description/

**Pattern:** Stack  
**Difficulty:** Medium  
**Topics:** String, Stack

---

## 1. Problem Explanation

We are given an absolute Unix-style file path. We need to simplify it and return its canonical path.

### Rules

1. A single slash `/` separates directories.
2. Multiple consecutive slashes `//` should be treated as a single slash.
3. A single dot `.` means the current directory, so we ignore it.
4. Two dots `..` mean the parent directory, so we remove the last directory.
5. Any other string represents a valid directory name.
6. The final path must start with `/`, must not end with `/` unless it is the root directory, and must not contain consecutive slashes.

### Example 1

**Input:**
```text
path = "/home/"
```

**Output:**
```text
"/home"
```

**Explanation:**

The trailing slash is unnecessary, so we remove it.

### Example 2

**Input:**
```text
path = "/home//foo/"
```

**Output:**
```text
"/home/foo"
```

**Explanation:**

Multiple consecutive slashes are treated as a single slash.

### Example 3

**Input:**
```text
path = "/home/user/Documents/../Pictures"
```

**Output:**
```text
"/home/user/Pictures"
```

**Explanation:**

The `..` means we must move to the parent directory. Therefore, `Documents` is removed.

### Example 4

**Input:**
```text
path = "/../"
```

**Output:**
```text
"/"
```

**Explanation:**

We cannot move above the root directory. Therefore, the result remains `/`.

### Example 5

**Input:**
```text
path = "/.../a/../b/c/../d/./"
```

**Output:**
```text
"/.../b/d"
```

**Explanation:**

- `...` is a valid directory name because it is not exactly `.` or `..`.
- `a` is removed because of `..`.
- `c` is removed because of `..`.
- `.` is ignored.
- The remaining directories are `...`, `b`, and `d`.

---

## 2. Why Do We Use a Stack?

The main challenge is handling `..`.

Whenever we encounter `..`, we must remove the most recently added directory.

For example:

```text
/home/user/Documents/..
```

When we encounter `..`, we need to remove `Documents`.

A stack follows the LIFO principle:

**LIFO = Last In, First Out**

The last directory added is the first directory removed.

Consider this example:

```text
Path: /home/user/docs/..
```

Our stack changes as follows:

```text
Add home       -> [home]
Add user       -> [home, user]
Add docs       -> [home, user, docs]
Encounter ..   -> [home, user]
```

The directory `docs` is removed.

This is exactly the behavior we need.

### Stack Operations

| Operation | Meaning |
|---|---|
| `addLast(x)` | Push an element onto the stack |
| `removeLast()` | Remove the most recently added element |
| `isEmpty()` | Check whether the stack is empty |

In Java, we can use:

```java
Deque<String> stack = new ArrayDeque<>();
```

We use `String` because each stack element represents a directory name.

**Important:** We should not store the complete path as one element. We store individual directory names.

---

## 3. How to Think About the Approach

Before writing code, ask yourself three questions.

### Question 1: How can we separate the path into directories?

Consider:

```text
/home/user/Documents/..
```

We can split the path using `/`.

```java
String[] parts = path.split("/");
```

The resulting array is conceptually:

```text
["", "home", "user", "Documents", ".."]
```

The initial empty string appears because the path starts with `/`.

Empty strings caused by repeated or leading slashes can be ignored.

### Question 2: What should we do with each part?

There are three main cases.

**Case 1: Empty string or `.`**

Ignore it.

```java
if (part.equals("") || part.equals(".")) {
    continue;
}
```

Why?

- Empty strings arise from leading or repeated slashes.
- `.` represents the current directory, so it does not change the path.

**Case 2: `..`**

Remove the last directory if the stack is not empty.

```java
if (part.equals("..")) {
    if (!stack.isEmpty()) {
        stack.removeLast();
    }
}
```

Why check `isEmpty()`?

Because we cannot move above the root directory.

For example:

```text
/../
```

The stack is empty when we encounter `..`, so nothing happens.

**Case 3: A normal directory name**

Push it onto the stack.

```java
else {
    stack.addLast(part);
}
```

For example:

```text
home
user
Documents
```

Each directory is added to the stack.

### Question 3: How do we construct the final path?

After processing all the parts, the stack contains the directories that belong in the final path.

For example:

```text
Stack = [home, user, Pictures]
```

The final path should be:

```text
/home/user/Pictures
```

We can construct it using a `StringBuilder`.

```java
StringBuilder result = new StringBuilder();

for (String dir : stack) {
    result.append("/").append(dir);
}
```

If the stack is empty, the result must be `/`.

```java
return result.length() == 0 ? "/" : result.toString();
```

---

## 4. Complete Java Solution

```java
import java.util.*;

class Solution {
    public String simplifyPath(String path) {

        Deque<String> stack = new ArrayDeque<>();

        String[] parts = path.split("/");

        for (String part : parts) {

            // Ignore empty strings and current directory
            if (part.equals("") || part.equals(".")) {
                continue;
            }

            // Move to the parent directory
            if (part.equals("..")) {

                if (!stack.isEmpty()) {
                    stack.removeLast();
                }

            } else {

                // Add a valid directory name
                stack.addLast(part);
            }
        }

        // Construct the canonical path
        StringBuilder result = new StringBuilder();

        for (String dir : stack) {
            result.append("/").append(dir);
        }

        // If no directories remain, return root
        return result.length() == 0 ? "/" : result.toString();
    }
}
```

---

## 5. Understanding the Code Step by Step

### Step 1: Create the stack

```java
Deque<String> stack = new ArrayDeque<>();
```

Initially:

```text
Stack = []
```

The stack will store directory names in the order they appear, after accounting for parent-directory operations.

### Step 2: Split the path

```java
String[] parts = path.split("/");
```

For the input:

```text
/home/user/Documents/../Pictures
```

The parts are:

```text
["", "home", "user", "Documents", "..", "Pictures"]
```

We process each part from left to right.

### Step 3: Process each part

```java
for (String part : parts)
```

For every part, we decide whether to ignore it, remove a directory, or add a directory.

### Step 4: Ignore unnecessary parts

```java
if (part.equals("") || part.equals(".")) {
    continue;
}
```

The `continue` statement skips the current iteration and moves to the next part.

### Step 5: Handle the parent directory

```java
if (part.equals("..")) {
    if (!stack.isEmpty()) {
        stack.removeLast();
    }
}
```

If the stack contains directories, remove the last one.

If the stack is empty, do nothing.

### Step 6: Add normal directory names

```java
else {
    stack.addLast(part);
}
```

Every normal directory name is added to the end of the stack.

### Step 7: Build the result

```java
StringBuilder result = new StringBuilder();

for (String dir : stack) {
    result.append("/").append(dir);
}
```

Each directory gets a leading `/`.

This automatically ensures that there are no unnecessary trailing slashes.

### Step 8: Handle the root directory

```java
return result.length() == 0 ? "/" : result.toString();
```

If no directories remain, return `/`.

Otherwise, return the constructed path.

---

## 6. Dry Run Using a Table

Let's dry-run the following input:

```text
path = "/home/user/Documents/../Pictures/./Photos/../../Music/"
```

### Initial State

After splitting the path:

```text
["", "home", "user", "Documents", "..",
 "Pictures", ".", "Photos", "..", "..", "Music"]
```

Initially:

```text
Stack = []
```

### Detailed Dry Run

| Step | Current Part | Condition | Operation | Stack After Operation |
|---:|---|---|---|---|
| 1 | `""` | Empty string | Ignore | `[]` |
| 2 | `"home"` | Normal directory | Push `home` | `[home]` |
| 3 | `"user"` | Normal directory | Push `user` | `[home, user]` |
| 4 | `"Documents"` | Normal directory | Push `Documents` | `[home, user, Documents]` |
| 5 | `".."` | Parent directory | Remove `Documents` | `[home, user]` |
| 6 | `"Pictures"` | Normal directory | Push `Pictures` | `[home, user, Pictures]` |
| 7 | `"."` | Current directory | Ignore | `[home, user, Pictures]` |
| 8 | `"Photos"` | Normal directory | Push `Photos` | `[home, user, Pictures, Photos]` |
| 9 | `".."` | Parent directory | Remove `Photos` | `[home, user, Pictures]` |
| 10 | `".."` | Parent directory | Remove `Pictures` | `[home, user]` |
| 11 | `"Music"` | Normal directory | Push `Music` | `[home, user, Music]` |

### Final Stack

```text
[home, user, Music]
```

### Construct the Result

The code iterates through the stack:

```java
for (String dir : stack) {
    result.append("/").append(dir);
}
```

The construction proceeds as follows:

| Directory | Result |
|---|---|
| `home` | `/home` |
| `user` | `/home/user` |
| `Music` | `/home/user/Music` |

**Final Output:**

```text
"/home/user/Music"
```

---

## 7. Another Dry Run: Moving Above the Root

Consider:

```text
path = "/../../home/../"
```

Initially:

```text
Stack = []
```

| Step | Current Part | Operation | Stack |
|---:|---|---|---|
| 1 | `""` | Ignore | `[]` |
| 2 | `".."` | Stack empty, do nothing | `[]` |
| 3 | `".."` | Stack empty, do nothing | `[]` |
| 4 | `"home"` | Push `home` | `[home]` |
| 5 | `".."` | Remove `home` | `[]` |
| 6 | `""` | Ignore | `[]` |

The stack is empty at the end.

Therefore:

```text
Output = "/"
```

This shows why checking whether the stack is empty before removing an element is important.

---

## 8. How to Write This Code Yourself

Remember these five steps.

**Step 1: Create a stack**

```java
Deque<String> stack = new ArrayDeque<>();
```

**Step 2: Split the path**

```java
String[] parts = path.split("/");
```

**Step 3: Ignore empty strings and `.`**

```java
if (part.equals("") || part.equals(".")) {
    continue;
}
```

**Step 4: Handle `..` and normal directories**

```java
if (part.equals("..")) {
    if (!stack.isEmpty()) {
        stack.removeLast();
    }
} else {
    stack.addLast(part);
}
```

**Step 5: Build the final path**

```java
StringBuilder result = new StringBuilder();

for (String dir : stack) {
    result.append("/").append(dir);
}

return result.length() == 0 ? "/" : result.toString();
```

### The Most Important Logic

```java
if (part.equals("") || part.equals(".")) {
    continue;
} else if (part.equals("..")) {
    if (!stack.isEmpty()) {
        stack.removeLast();
    }
} else {
    stack.addLast(part);
}
```

If you understand this logic, you understand the core of the problem.

---

## 9. Time and Space Complexity

Let `n` be the length of the input path.

### Time Complexity: O(n)

- Splitting the path takes O(n) time.
- Processing all path components takes O(n) time in total.
- Constructing the result takes O(n) time.

Therefore, the overall time complexity is **O(n)**.

### Space Complexity: O(n)

- The split operation creates an array of path components.
- The stack can store directory names from the input.
- The result uses space proportional to the output length.

Therefore, the overall auxiliary space complexity is **O(n)**.

---

## 10. Common Mistakes

### Mistake 1: Treating `...` as `..`

Incorrect:

```java
if (part.startsWith(".")) {
    // Ignore
}
```

Correct:

```java
if (part.equals("."))
```

Only a string exactly equal to `.` represents the current directory.

A string such as `...` is a valid directory name.

### Mistake 2: Removing from an empty stack

Incorrect:

```java
if (part.equals("..")) {
    stack.removeLast();
}
```

This can throw an exception if the stack is empty.

Correct:

```java
if (part.equals("..") && !stack.isEmpty()) {
    stack.removeLast();
}
```

### Mistake 3: Adding `.` or `..` as directory names

We must handle these special components before adding normal directory names.

Otherwise, the final path will be incorrect.

### Mistake 4: Forgetting the root directory

If every directory is removed, the answer must be `/`, not an empty string.

### Mistake 5: Returning a path with a trailing slash

For example:

```text
/home/user/
```

The canonical path should be:

```text
/home/user
```

Building the result by adding `/` before each directory avoids this problem.

---

## 11. Pattern Recognition: When Should You Think of a Stack?

Whenever you encounter a problem where the most recently added item may need to be removed, consider using a stack.

Examples:

| Problem | Why a Stack Helps |
|---|---|
| Simplify Path | `..` removes the most recent directory |
| Valid Parentheses | Opening brackets must be matched in reverse order |
| Remove Adjacent Duplicates | The current character may cancel the previous character |
| Next Greater Element | Elements are removed when a greater element resolves them |
| Daily Temperatures | A warmer day resolves earlier unresolved temperatures |
| Largest Rectangle in Histogram | Stack helps identify previous and next smaller elements |
| Remove K Digits | Removing certain preceding digits produces a smaller number |

---

## 12. Final Revision Notes

**Problem:** Simplify a Unix-style absolute path.

**Pattern:** Stack.

**Why stack?** Because `..` requires removing the most recently added directory.

**Data structure:**

```java
Deque<String> stack = new ArrayDeque<>();
```

**Core rules:**

- Empty string → Ignore.
- `.` → Ignore.
- `..` → Remove the last directory if possible.
- Normal directory → Push onto the stack.

**Final result:** Join the remaining directories with `/`, ensuring that an empty stack returns `/`.

**Time Complexity:** O(n).

**Space Complexity:** O(n).

### One-Line Intuition

Process the path from left to right, keep valid directories in a stack, remove the latest directory whenever `..` appears, and construct the canonical path from the remaining directories.
