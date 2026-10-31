📚 Stack — Pattern Recognition & Complete Guid

🧠 What Is a Stack?

A Stack is a linear data structure that follows:

LIFO = Last In, First Out

The last element inserted is the first element removed.

Real-World Example

Think of a stack of plates.

       ┌───────┐
       │ Plate 3│ ← TOP
       ├───────┤
       │ Plate 2│
       ├───────┤
       │ Plate 1│
       └───────┘

If you want to remove a plate, you remove Plate 3 first.

Similarly, a Stack provides:

- "push()" → Insert an element
- "pop()" → Remove the top element
- "peek()" → Read the top element
- "isEmpty()" → Check whether the stack is empty

---

🎯 How to Identify Stack Questions?

Do not look only for the word "Stack" in the question.

Instead, ask yourself:

1. Do I need to remember previous elements?
2. Does the most recent unresolved element need to be processed first?
3. Do I need to match opening and closing brackets?
4. Do I need to remove adjacent elements repeatedly?
5. Do I need the next or previous greater/smaller element?
6. Do I need to evaluate an expression in a particular order?
7. Can I solve the problem by maintaining elements until a condition is satisfied?

If the answer is yes, Stack may be useful.

Common Signals

PARENTHESES / BRACKETS
UNDO / REVERSE ORDER
NEXT GREATER / NEXT SMALLER
PREVIOUS GREATER / PREVIOUS SMALLER
REMOVE ADJACENT DUPLICATES
EXPRESSION EVALUATION
NESTED STRUCTURES
HISTOGRAM / BOUNDARIES

These are clues, not guarantees. Always verify that the stack maintains the information needed by the problem.

---

🔥 The Five Main Stack Patterns

                 STACK
                   |
       +-----------+-----------+
       |           |           |
       v           v           v
   Matching    Monotonic    Expression
   Brackets     Stack       Evaluation
       |           |           |
       v           v           v
  Valid         Next/       Operators
 Parentheses   Previous     and operands
               Greater
               
       +-----------------------+
       |                       |
       v                       v
  Simulation              Design
  and Removal          Min Stack etc.

---

1️⃣ Basic Stack Operations

In Java, use "ArrayDeque" as a stack for most everyday problems.

import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) {

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.peek()); // 30

        System.out.println(stack.pop());  // 30
        System.out.println(stack.pop());  // 20

        System.out.println(stack.isEmpty()); // false
    }
}

Complexity

Operation| Time
Push| O(1) amortized
Pop| O(1)
Peek| O(1)
Is Empty| O(1)

The stack stores elements in Last In, First Out order.

---

2️⃣ Valid Parentheses

LeetCode 20 — Valid Parentheses

How to Identify?

The question asks:

- Are the brackets balanced?
- Is every opening bracket closed correctly?
- Are brackets properly nested?
- Does each closing bracket match the latest unmatched opening bracket?

Think:

STACK + MATCHING BRACKETS

Core Idea

When you see an opening bracket, push it.

When you see a closing bracket, check whether it matches the top of the stack.

Example:

Input: "{[()]}"
Output: true

The most recently opened bracket must close first.

Java Code

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {

                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (ch == ')' && top != '(') return false;
                if (ch == '}' && top != '{') return false;
                if (ch == ']' && top != '[') return false;
            }
        }

        return stack.isEmpty();
    }
}

Dry Run

Input: "{[]}"

Character| Action| Stack
"{"| Push| "{"
"["| Push| "{ ["
"]"| Match and pop| "{"
"}"| Match and pop| Empty

The stack is empty at the end, so the string is valid.

Complexity

- Time: O(n)
- Space: O(n)

---

3️⃣ Monotonic Stack

This is one of the most important advanced Stack patterns.

A Monotonic Stack maintains elements in a particular order.

Two main forms:

INCREASING STACK
DECREASING STACK

The order refers to the values from the bottom to the top of the stack.

How to Identify?

Look for:

- Next Greater Element
- Next Smaller Element
- Previous Greater Element
- Previous Smaller Element
- Daily Temperatures
- Stock Span
- Largest Rectangle in Histogram
- Sum of Subarray Minimums

These questions often require finding the nearest element that satisfies a comparison.

Core Idea

Maintain unresolved elements in the stack.

When the current element resolves an earlier element, pop the earlier element and process it.

Previous unresolved elements
             |
             v
         [ 2, 4 ]
              TOP

Current element = 5

5 is greater than 4
        |
        v
Pop 4

5 is greater than 2
        |
        v
Pop 2

The stack allows each element to be pushed and popped at most once in many monotonic-stack algorithms.

---

4️⃣ Next Greater Element

LeetCode 496 — Next Greater Element I

How to Identify?

The question asks:

«For each element, find the first greater element to its right.»

Think:

NEXT GREATER
      |
      v
MONOTONIC STACK

Example

nums = [2, 1, 3]

Answers:

2 → 3
1 → 3
3 → -1

The answer is:

[3, 3, -1]

Core Idea

Traverse the array from right to left.

Maintain a decreasing stack of candidate values.

Remove every value that is less than or equal to the current value because it cannot be the next greater element for the current position or any earlier position that encounters the current value first.

Java Code

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty()
                    && stack.peek() <= nums[i]) {
                stack.pop();
            }

            answer[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i]);
        }

        return answer;
    }
}

This code solves the next-greater-element problem for each position in a single array. LeetCode 496 has an additional relationship between "nums1" and "nums2", so a complete solution to that specific problem also needs a map to connect the answers to "nums1".

Complexity

- Time: O(n)
- Space: O(n)

---

5️⃣ Daily Temperatures

LeetCode 739 — Daily Temperatures

How to Identify?

The question asks:

«For each day, how many days must we wait until a warmer temperature?»

This is equivalent to:

NEXT GREATER ELEMENT TO THE RIGHT

Example

temperatures = [73, 74, 75, 71, 69, 72, 76, 73]

Output:

[1, 1, 4, 2, 1, 1, 0, 0]

For temperature "73" on day "0", the next warmer day is day "1".

Difference:

1 - 0 = 1

Java Code

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty()
                    && temperatures[i] > temperatures[stack.peek()]) {

                int previousIndex = stack.pop();

                answer[previousIndex] = i - previousIndex;
            }

            stack.push(i);
        }

        return answer;
    }
}

Important

Store indices rather than just temperatures because the answer requires a distance between days.

---

6️⃣ Remove Adjacent Duplicates

LeetCode 1047 — Remove All Adjacent Duplicates in String

How to Identify?

Look for:

- Remove adjacent duplicate characters.
- Repeatedly delete equal neighboring elements.
- Continue removing duplicates created by previous removals.

Think:

STACK + ADJACENT REMOVAL

Example

Input: "abbaca"

Process:

a → push        [a]
b → push        [a, b]
b → matches b   [a]
a → matches a   []
c → push        [c]
a → push        [c, a]

Output:

"ca"

Java Code

class Solution {
    public String removeDuplicates(String s) {

        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {

            int size = stack.length();

            if (size > 0 && stack.charAt(size - 1) == ch) {
                stack.deleteCharAt(size - 1);
            } else {
                stack.append(ch);
            }
        }

        return stack.toString();
    }
}

Complexity

- Time: O(n)
- Space: O(n)

---

7️⃣ Evaluate Reverse Polish Notation

LeetCode 150 — Evaluate Reverse Polish Notation

How to Identify?

The question contains:

- Postfix notation
- Operators such as "+", "-", "*", "/"
- Numbers and operators in an expression
- Evaluation without parentheses in conventional infix form

Think:

STACK + OPERAND EVALUATION

Example

["2", "1", "+", "3", "*"]

This represents:

(2 + 1) * 3

Answer:

9

Core Idea

- If the token is a number, push it.
- If the token is an operator, pop two numbers.
- Apply the operator in the correct order.
- Push the result back.

Java Code

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {

            switch (token) {

                case "+":
                case "-":
                case "*":
                case "/":

                    int b = stack.pop();
                    int a = stack.pop();

                    int result = 0;

                    switch (token) {
                        case "+": result = a + b; break;
                        case "-": result = a - b; break;
                        case "*": result = a * b; break;
                        case "/": result = a / b; break;
                    }

                    stack.push(result);
                    break;

                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}

Important

For subtraction and division:

a - b
a / b

The first popped value is "b", and the second popped value is "a".

Reversing them produces an incorrect answer.

Complexity

- Time: O(n)
- Space: O(n)

---

8️⃣ Min Stack

LeetCode 155 — Min Stack

How to Identify?

The question asks for a stack supporting:

- Push
- Pop
- Top
- Retrieve minimum in O(1) time

Think:

STACK + EXTRA MINIMUM INFORMATION

Core Idea

A normal stack gives the top element in O(1), but finding the minimum by scanning every element takes O(n).

Maintain an additional stack of minimum values.

Java Code

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {

    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {

        stack.push(val);

        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {

        int removed = stack.pop();

        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

Complexity

- Push: O(1)
- Pop: O(1)
- Top: O(1)
- Get Minimum: O(1)
- Space: O(n)

---

9️⃣ Largest Rectangle in Histogram

LeetCode 84 — Largest Rectangle in Histogram

How to Identify?

The question asks:

«Find the largest rectangular area that can be formed using consecutive histogram bars.»

Look for:

HISTOGRAM
+
CONSECUTIVE BARS
+
MAXIMUM AREA

Think:

MONOTONIC STACK

Core Idea

For each bar, determine how far it can extend to the left and right before meeting a shorter bar.

If the bar's height is "h", its width is:

width = rightBoundary - leftBoundary - 1

Area:

area = height * width

A monotonic stack helps determine these boundaries efficiently.

This is an advanced pattern. Learn Next Greater Element and Daily Temperatures first.

---

🔟 Trapping Rain Water

LeetCode 42 — Trapping Rain Water

How to Identify?

The question asks:

«Calculate how much rainwater can be trapped between histogram bars.»

A common solution uses two pointers. Another solution uses a monotonic stack.

The stack approach processes bars and calculates trapped water when a taller right boundary appears.

For each popped bar:

boundedHeight =
min(currentHeight, leftBoundaryHeight) - poppedHeight

Then:

water =
boundedHeight * width

Do not assume every water-trapping question requires a stack. Compare the stack, prefix/suffix maximum, and two-pointer approaches.

---

🧠 How to Choose the Correct Stack Pattern

Question clue| Pattern
Balanced parentheses| Matching Stack
Nested brackets| Matching Stack
Next greater element| Monotonic Stack
Next smaller element| Monotonic Stack
Previous greater/smaller| Monotonic Stack
Wait for warmer day| Monotonic Stack
Remove adjacent duplicates| Stack Simulation
Postfix expression| Operand Stack
Retrieve minimum in O(1)| Design + Auxiliary Stack
Largest rectangle in histogram| Monotonic Stack
Trapping rainwater| Stack or Two Pointers
Reverse or undo recent actions| LIFO Stack

---

🔥 Monotonic Stack: Increasing vs Decreasing

The comparison in the "while" loop determines how the stack behaves.

For example:

while (!stack.isEmpty() && stack.peek() <= current) {
    stack.pop();
}

This removes values smaller than or equal to the current value.

For an index-based stack:

while (!stack.isEmpty()
        && nums[stack.peek()] <= nums[i]) {
    stack.pop();
}

The comparison is between the array values at the stored indices.

Important: Whether you use "<=" or "<" depends on whether equal values should remain candidates. Duplicate handling changes from problem to problem.

---

⚠️ Common Mistakes

1. Calling "pop()" on an empty stack.
2. Forgetting to check "isEmpty()" before accessing the top.
3. Reversing operand order for subtraction or division.
4. Storing values when the problem requires indices.
5. Using the wrong comparison in a monotonic stack.
6. Forgetting that a stack may need an additional map or auxiliary stack.
7. Assuming every question involving maximum area requires a stack.
8. Confusing a normal stack with a monotonic stack.

---

🗺️ Pattern Recognition Decision Tree

             READ THE QUESTION
                     |
                     v
         Does order of processing matter?
                     |
                     v
       Do recent unresolved elements matter?
                     |
              +------+------+
              |             |
             YES            NO
              |             |
              v             v
            STACK       Consider other
                           patterns
              |
       +------+-------+-----------+
       |              |           |
       v              v           v
   Brackets       Greater/     Expression
                  Smaller      Evaluation
       |              |           |
       v              v           v
   Normal        Monotonic      Operand
   Stack           Stack         Stack

---

📌 LeetCode Practice List

🟢 Easy

- "20. Valid Parentheses" (https://leetcode.com/problems/valid-parentheses/)
- "1047. Remove All Adjacent Duplicates In String" (https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)
- "496. Next Greater Element I" (https://leetcode.com/problems/next-greater-element-i/)

🟡 Medium

- "155. Min Stack" (https://leetcode.com/problems/min-stack/)
- "150. Evaluate Reverse Polish Notation" (https://leetcode.com/problems/evaluate-reverse-polish-notation/)
- "739. Daily Temperatures" (https://leetcode.com/problems/daily-temperatures/)
- "503. Next Greater Element II" (https://leetcode.com/problems/next-greater-element-ii/)
- "901. Online Stock Span" (https://leetcode.com/problems/online-stock-span/)
- "735. Asteroid Collision" (https://leetcode.com/problems/asteroid-collision/)

🔴 Hard

- "42. Trapping Rain Water" (https://leetcode.com/problems/trapping-rain-water/)
- "84. Largest Rectangle in Histogram" (https://leetcode.com/problems/largest-rectangle-in-histogram/)
- "85. Maximal Rectangle" (https://leetcode.com/problems/maximal-rectangle/)
- "907. Sum of Subarray Minimums" (https://leetcode.com/problems/sum-of-subarray-minimums/)

---

🏆 Final Mental Model

Remember these five patterns:

1. MATCHING
   Opening bracket → push
   Closing bracket → match/pop

2. NEXT/PREVIOUS GREATER OR SMALLER
   Monotonic Stack

3. EXPRESSION EVALUATION
   Push operands, process operators

4. REMOVE ADJACENT ELEMENTS
   Compare with stack top

5. SPECIAL STACK OPERATIONS
   Maintain extra information

🔑 Four Questions to Ask Yourself

Before writing code, ask:

1. What information must I remember? Values, indices, brackets, or operands?
2. When should an element be pushed?
3. What condition makes me pop an element?
4. What answer can I calculate when I pop it?

If you can answer these four questions, you can often derive the Stack algorithm instead of memorizing the solution.

Golden rule: Use a Stack when the most recent unresolved element matters. Use a Monotonic Stack when you need to find the nearest greater/smaller element or process boundaries efficiently.
