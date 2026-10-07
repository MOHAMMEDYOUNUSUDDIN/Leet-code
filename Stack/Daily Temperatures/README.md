# LeetCode 739 - Daily Temperatures

## 1. Question

Given an array of daily temperatures, return an array where answer[i] tells us how many days we have to wait after day i to get a warmer temperature.

If there is no future day with a warmer temperature, put 0.

### Example

Input:
temperatures = [73,74,75,71,69,72,76,73]

Output:
[1,1,4,2,1,1,0,0]

### Explanation

- 73 → warmer temperature 74 comes after 1 day → 1
- 74 → warmer temperature 75 comes after 1 day → 1
- 75 → warmer temperature 76 comes after 4 days → 4
- 71 → warmer temperature 72 comes after 2 days → 2
- 69 → warmer temperature 72 comes after 1 day → 1
- 72 → warmer temperature 76 comes after 1 day → 1
- 76 → no warmer temperature → 0
- 73 → no warmer temperature → 0


## 2. Pattern Identification

### Pattern: Monotonic Stack

Whenever the question asks:

- Next greater element
- Next smaller element
- Previous greater/smaller element
- Wait until something greater appears

Think about a **Monotonic Stack**.

Here we need the:

"Next Greater Element"

For every temperature, we need to find the next temperature that is greater than it.

### Important Observation

Instead of checking every future day using a nested loop, we maintain a stack of indices whose warmer day has not been found yet.

The stack stores **indices**, not temperatures.

Why indices?

Because we need to calculate the number of days:

    currentIndex - previousIndex


## 3. Hints to Create the Code

### Hint 1

Create an answer array with the same size as the input.

    int[] res = new int[t.length];

Initially, every value is 0.

### Hint 2

Create a Stack<Integer>.

Store the INDEX of temperatures in the stack.

    Stack<Integer> stack = new Stack<>();

### Hint 3

Loop through the temperature array.

    for(int i = 0; i < t.length; i++)

### Hint 4

Check the temperature at the top of the stack with today's temperature.

If:

    t[stack.peek()] < t[i]

then today's temperature is warmer.

### Hint 5

When a warmer temperature is found:

1. Remove the old index from the stack.
2. Calculate the number of days.
3. Store it in the result array.

    int pop = stack.pop();
    res[pop] = i - pop;

### Hint 6

After processing warmer temperatures, push today's index.

    stack.push(i);

### Main Logic

    while(!stack.empty() && t[stack.peek()] < t[i]) {
        int pop = stack.pop();
        res[pop] = i - pop;
    }

    stack.push(i);


## 4. Java Code

```java
import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] t) {

        int[] res = new int[t.length];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < t.length; i++) {

            while(!stack.empty() && t[stack.peek()] < t[i]) {

                Integer pop = stack.pop();

                res[pop] = i - pop;
            }

            stack.push(i);
        }

        return res;
    }
}

5. Code Explanation
1. Create result array
int[] res = new int[t.length];

This stores the number of days we need to wait.

Initially:

[0,0,0,0,0,0,0,0]
2. Create stack
Stack<Integer> stack = new Stack<>();

The stack stores indices of temperatures that are waiting for a warmer day.

Example:

stack = [2,3,4]

means indices 2, 3 and 4 are still waiting for a warmer temperature.

3. Loop through temperatures
for(int i = 0; i < t.length; i++)

We process each day one by one.

4. Find a warmer temperature
while(!stack.empty() && t[stack.peek()] < t[i])

If today's temperature is greater than the temperature at the top index of the stack, today's temperature is the answer for that previous day.

5. Remove the waiting index
Integer pop = stack.pop();

We found a warmer day for this index, so remove it from the stack.

6. Calculate waiting days
res[pop] = i - pop;

Example:

Previous day index = 2
Current day index = 6

6 - 2 = 4

So:

res[2] = 4
7. Push current index
stack.push(i);

Today's temperature may need a warmer day in the future, so we store its index.

6. Dry Run
Input
[73,74,75,71,69,72,76,73]
Index
0   1   2   3   4   5   6   7
73  74  75  71  69  72  76  73


| i | t[i] | Stack Before | Condition | Operation         | res               | Stack After |
| - | ---: | ------------ | --------- | ----------------- | ----------------- | ----------- |
| 0 |   73 | []           | Empty     | push(0)           | [0,0,0,0,0,0,0,0] | [0]         |
| 1 |   74 | [0]          | 73 < 74 ✓ | pop 0, res[0]=1-0 | [1,0,0,0,0,0,0,0] | [1]         |
| 2 |   75 | [1]          | 74 < 75 ✓ | pop 1, res[1]=2-1 | [1,1,0,0,0,0,0,0] | [2]         |
| 3 |   71 | [2]          | 75 < 71 ✗ | push(3)           | [1,1,0,0,0,0,0,0] | [2,3]       |
| 4 |   69 | [2,3]        | 71 < 69 ✗ | push(4)           | [1,1,0,0,0,0,0,0] | [2,3,4]     |
| 5 |   72 | [2,3,4]      | 69 < 72 ✓ | pop 4, res[4]=1   | [1,1,0,0,1,0,0,0] | [2,3]       |
| 5 |   72 | [2,3]        | 71 < 72 ✓ | pop 3, res[3]=2   | [1,1,0,2,1,0,0,0] | [2]         |
| 5 |   72 | [2]          | 75 < 72 ✗ | push(5)           | [1,1,0,2,1,0,0,0] | [2,5]       |
| 6 |   76 | [2,5]        | 72 < 76 ✓ | pop 5, res[5]=1   | [1,1,0,2,1,1,0,0] | [2]         |
| 6 |   76 | [2]          | 75 < 76 ✓ | pop 2, res[2]=4   | [1,1,4,2,1,1,0,0] | []          |
| 6 |   76 | []           | Empty     | push(6)           | [1,1,4,2,1,1,0,0] | [6]         |
| 7 |   73 | [6]          | 76 < 73 ✗ | push(7)           | [1,1,4,2,1,1,0,0] | [6,7]       |



Final Answer
[1,1,4,2,1,1,0,0]
7. Important Example

Consider:

Index:        2       6
Temperature: 75      76

At index 2:

75

The next warmer temperature is:

76 at index 6

Therefore:

6 - 2 = 4

So:

res[2] = 4
8. Why Do We Store Indices?

We could store temperatures, but we need to know the distance between days.

For example:

index 2 → 75
index 6 → 76

We need:

6 - 2 = 4

Therefore, storing indices is necessary.

9. Why Does the Stack Work?

The stack contains temperatures that are still waiting for a warmer temperature.

Example:

75
71
69

When 72 arrives:

69 < 72 → answer found
71 < 72 → answer found
75 > 72 → still waiting

So the stack becomes:

[75]

This avoids repeatedly scanning the array.

10. Time and Space Complexity
Time Complexity
O(n)

Although there is a while loop inside the for loop, every index is:

pushed once
popped at most once

Therefore total operations are O(n).

Space Complexity
O(n)

Because the stack can contain up to n indices.

11. Interview POV
Q1. Which pattern is used?

Monotonic Stack / Next Greater Element pattern.

Q2. What does the stack store?

It stores the indices of temperatures that are still waiting for a warmer day.

Q3. Why indices instead of temperatures?

Because we need to calculate the distance between days.

currentIndex - previousIndex
Q4. Why do we use while instead of if?

Because one temperature can be warmer than multiple previous temperatures.

Example:

[75,71,69,72]

When 72 arrives, it is warmer than both:

69
71

So we need to pop multiple elements.

Q5. What happens to temperatures that never get a warmer day?

Their result remains 0.

Example:

76

There is no future temperature greater than 76, so:

res = 0
Q6. What is the time complexity?

O(n), because every index is pushed and popped at most once.

Q7. What is the space complexity?

O(n), because of the stack.

Q8. What is the core logic?
while (!stack.empty() && t[stack.peek()] < t[i]) {
    int pop = stack.pop();
    res[pop] = i - pop;
}

stack.push(i);

In simple words:

"If today's temperature is warmer than the temperature
 waiting on top of the stack, today's day is its answer."