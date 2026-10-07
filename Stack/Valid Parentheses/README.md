# 🔗 LeetCode 20 — Valid Parentheses

## 📝 Question

Given a string `s` containing only the characters:

```text
'(', ')', '{', '}', '[', ']'
```

Determine if the input string is valid.

A string is valid when:

1. Every opening bracket has a corresponding closing bracket.
2. Brackets are closed in the correct order.
3. Every closing bracket matches the most recently opened bracket.

### Examples

```text
Input:  "()"
Output: true

Input:  "()[]{}"
Output: true

Input:  "(]"
Output: false

Input:  "([{}])"
Output: true

Input:  "([)]"
Output: false
```

---

# 💡 Explanation

The main problem is checking whether brackets are properly opened and closed.

For example:

```text
([{}])
```

We need to match:

```text
( → )
[ → ]
{ → }
```

The important thing is:

> The bracket opened **last** must be closed **first**.

This is exactly the **LIFO** behavior of a Stack.

### LIFO

**Last In → First Out**

Real-world example:

Imagine plates stacked on top of each other.

```text
    🍽️  ← Last plate placed
    🍽️
    🍽️
```

You remove the top plate first.

A Stack works the same way.

---

# 🧠 Why Use Stack?

Consider:

```text
([{}])
```

When we see opening brackets:

```text
(
([
([{
```

The latest opening bracket is:

```text
{
```

So the next closing bracket must be:

```text
}
```

After removing `{`:

```text
([
```

Next expected:

```text
]
```

Therefore, Stack is perfect for this problem.

### Stack Operations

| Operation   | Meaning                      |
| ----------- | ---------------------------- |
| `push()`    | Add element                  |
| `pop()`     | Remove top element           |
| `peek()`    | See top element              |
| `isEmpty()` | Check whether stack is empty |

---

# 🔍 Key Pattern to Identify

Whenever you see:

* Matching brackets
* Nested brackets
* Opening and closing symbols
* Last opened → first closed
* Undo operations
* Nested structures

Think:

```text
STACK
```

### Important Pattern

```text
OPENING → PUSH

CLOSING → POP + CHECK
```

This pattern is extremely important in DSA.

---

# 💡 Hint

Don't immediately think about all possible combinations.

Instead ask:

> "When I encounter a closing bracket, which opening bracket should it match?"

Answer:

> The most recently opened bracket.

So:

```text
Opening bracket → push into stack

Closing bracket → compare with stack top
```

If they don't match:

```text
return false
```

At the end:

```text
stack empty → true
stack not empty → false
```

---

# ✍️ How to Write the Code Yourself

### Step 1 — Create a Stack

We need to store opening brackets.

```java
Stack<Character> stack = new Stack<>();
```

---

### Step 2 — Traverse the string

Check every character.

```java
for (char ch : s.toCharArray()) {
    
}
```

---

### Step 3 — If it is an opening bracket

Push it.

```java
if (ch == '(' || ch == '{' || ch == '[') {
    stack.push(ch);
}
```

---

### Step 4 — If it is a closing bracket

First check:

> Is the stack empty?

If yes, there is nothing to match.

```java
if (stack.isEmpty()) {
    return false;
}
```

---

### Step 5 — Get the latest opening bracket

```java
char top = stack.pop();
```

Now compare:

```text
current closing bracket
        ↓
      top
```

---

### Step 6 — Check mismatch

```java
if ((ch == ')' && top != '(') ||
    (ch == '}' && top != '{') ||
    (ch == ']' && top != '[')) {
    return false;
}
```

---

### Step 7 — Final Check

After processing the entire string:

```java
return stack.isEmpty();
```

Why?

Because if something is still inside the stack, an opening bracket was never closed.

---

# 💻 Code

```java
public class Solution { 
    public boolean isValid(String s) { 
        Stack<Character> stack = new Stack<>(); 

        for (char ch : s.toCharArray()) { 
            
            if (ch == '(' || ch == '{' || ch == '[') { 
                stack.push(ch); 
            }  
            else { 
                
                if (stack.isEmpty()) { 
                    return false; 
                }  

                char top = stack.pop(); 

                if ((ch == ')' && top != '(') ||  
                    (ch == '}' && top != '{') ||  
                    (ch == ']' && top != '[')) { 
                    return false; 
                } 
            } 
        } 

        return stack.isEmpty(); 
    } 
}
```

---

# 🔎 Code Explanation

### Create Stack

```java
Stack<Character> stack = new Stack<>();
```

Stores opening brackets.

---

### Loop through characters

```java
for (char ch : s.toCharArray())
```

Processes every character one by one.

---

### Opening bracket

```java
stack.push(ch);
```

Store it because we need to match it later.

---

### Closing bracket

```java
char top = stack.pop();
```

Take the most recently opened bracket.

---

### Compare

```java
(ch == ')' && top != '(')
```

Means:

```text
If current bracket is )
then top must be (
```

Similarly:

```text
} → {
] → [
```

---

### Final condition

```java
return stack.isEmpty();
```

If the stack is empty:

```text
Everything matched → true
```

Otherwise:

```text
Some opening bracket is still unmatched → false
```

---

# 🧪 Dry Run

## Example 1

```text
s = "({[]})"
```

### Step 1

Character:

```text
(
```

Opening → push.

```text
Stack: (
```

### Step 2

```text
{
```

Push.

```text
Stack: ({ 
```

### Step 3

```text
[
```

Push.

```text
Stack: ({[
```

### Step 4

```text
]
```

Pop:

```text
[
```

Matches.

```text
Stack: ({
```

### Step 5

```text
}
```

Pop:

```text
{
```

Matches.

```text
Stack: (
```

### Step 6

```text
)
```

Pop:

```text
(
```

Matches.

```text
Stack: empty
```

Finally:

```java
stack.isEmpty()
```

returns:

```text
true
```

### Output

```text
true
```

---

# ❌ Dry Run — Invalid Case

```text
s = "([)]"
```

### Step 1

```text
(
```

Push.

```text
Stack: (
```

### Step 2

```text
[
```

Push.

```text
Stack: ([
```

### Step 3

```text
)
```

Pop:

```text
[
```

But:

```text
) expects (
```

We got:

```text
[
```

Mismatch!

Therefore:

```java
return false;
```

### Output

```text
false
```

---

# ⚡ Another Important Case

```text
s = "]"
```

There is no opening bracket.

Stack:

```text
empty
```

We encounter:

```text
]
```

Since:

```java
stack.isEmpty()
```

is true:

```java
return false;
```

---

# ⏱️ Complexity

Let `n` be the length of the string.

### Time Complexity

```text
O(n)
```

We visit every character once.

### Space Complexity

```text
O(n)
```

In the worst case, all characters can be opening brackets.

Example:

```text
(((((((
```

The stack stores all of them.

---

# 🎯 Interview Point of View

### Interviewer may ask:

**Why did you use Stack?**

Answer:

> Because brackets follow a Last-In-First-Out pattern. The most recently opened bracket must be closed first, which matches the behavior of a stack.

---

### Why can't we simply count brackets?

Because counting doesn't check the order.

For example:

```text
([)]
```

The number of opening and closing brackets is correct.

But the order is wrong.

Stack detects this.

---

### What if the stack is empty when a closing bracket appears?

Return:

```text
false
```

Because there is no opening bracket to match it.

---

### Why check `stack.isEmpty()` at the end?

Because there could be unmatched opening brackets.

Example:

```text
(((
```

No mismatch occurs while processing, but the brackets were never closed.

Therefore:

```java
return stack.isEmpty();
```

returns `false`.

---

# 🧠 Pattern Summary

Remember this simple template:

```text
Opening bracket
      ↓
    PUSH

Closing bracket
      ↓
Is stack empty?
      ↓
     No
      ↓
    POP
      ↓
   Compare
      ↓
Mismatch → false
```

At the end:

```text
Stack empty → true
Stack not empty → false
```

---

# 🔑 What You Should Learn From This Problem

This problem teaches the important **Stack pattern**:

```text
LIFO
```

You should recognize Stack when you see:

* Parentheses matching
* Nested expressions
* Undo/Redo
* Browser history
* Function call stack
* Expression evaluation
* Next Greater Element
* Monotonic Stack problems
* Removing adjacent elements
* Backtracking-like nested structures

---

# 🏆 Interview Takeaway

The most important sentence to remember:

> **"When the problem requires matching the most recently opened or inserted element, think Stack."**

For Valid Parentheses:

```text
Opening → PUSH
Closing → POP + MATCH
End → STACK MUST BE EMPTY
```

This is one of the most important beginner Stack problems and is a good problem to understand before moving to more advanced Stack patterns.
