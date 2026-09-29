# LeetCode 918 - Maximum Sum Circular Subarray

## Problem

Given a circular integer array `nums`, find the maximum possible sum of a non-empty subarray.

A circular array means the last element is connected to the first element.

Example:

nums = [5, -3, 5]

Normal subarray:
[5, -3, 5] = 7

Circular subarray:
[5] + [5] = 10

Answer = 10


## Core Idea

There are 2 possible cases for the maximum subarray:

1. Maximum subarray does NOT wrap around
2. Maximum subarray DOES wrap around


### Case 1: Normal Maximum Subarray

Use normal Kadane's Algorithm.

currentMax = maximum sum ending at current position

maxSum = maximum sum found so far

Formula:

currentMax = max(currentMax + num, num)

maxSum = max(maxSum, currentMax)


### Case 2: Circular Maximum Subarray

For a circular subarray, we can think of it as:

Total Sum - Minimum Subarray Sum

Why?

Suppose:

nums = [5, -3, 5]

Total = 7

Minimum subarray = [-3]

If we remove [-3]:

7 - (-3) = 10

The remaining elements are:

[5] + [5]

So:

circular = total - minSum


To find the minimum subarray, we use a modified Kadane's Algorithm:

currentMin = min(currentMin + num, num)

minSum = min(minSum, currentMin)


## Important Edge Case

If all elements are negative:

nums = [-3, -2, -1]

total = -6
minSum = -6

circular = total - minSum
         = -6 - (-6)
         = 0

But 0 is invalid because the problem requires a NON-EMPTY subarray.

So if:

maxSum < 0

return maxSum.


## Algorithm

1. Initialize maxSum with nums[0]
2. Initialize minSum with nums[0]
3. Initialize currentMax = 0
4. Initialize currentMin = 0
5. Calculate total sum
6. Use Kadane to find maximum subarray sum
7. Use Kadane to find minimum subarray sum
8. If maxSum < 0, return maxSum
9. Calculate circular = total - minSum
10. Return max(maxSum, circular)


## Code

class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int maxSum = nums[0];
        int currentMax = 0;

        int minSum = nums[0];
        int currentMin = 0;

        int total = 0;

        for (int num : nums) {

            total += num;

            // Normal Kadane
            currentMax = Math.max(currentMax + num, num);
            maxSum = Math.max(maxSum, currentMax);

            // Minimum Kadane
            currentMin = Math.min(currentMin + num, num);
            minSum = Math.min(minSum, currentMin);
        }

        // All elements are negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Circular subarray
        int circular = total - minSum;

        return Math.max(maxSum, circular);
    }
}


## Example Dry Run

nums = [5, -3, 5]

Initial:

maxSum = 5
minSum = 5
currentMax = 0
currentMin = 0
total = 0


num = 5

total = 5

currentMax = max(0 + 5, 5)
           = 5

maxSum = max(5, 5)
       = 5

currentMin = min(0 + 5, 5)
           = 5

minSum = min(5, 5)
       = 5


num = -3

total = 2

currentMax = max(5 + (-3), -3)
           = 2

maxSum = max(5, 2)
       = 5

currentMin = min(5 + (-3), -3)
           = -3

minSum = min(5, -3)
       = -3


num = 5

total = 7

currentMax = max(2 + 5, 5)
           = 7

maxSum = max(5, 7)
       = 7

currentMin = min(-3 + 5, 5)
           = 2

minSum = min(-3, 2)
       = -3


Now:

circular = total - minSum
         = 7 - (-3)
         = 10

Answer:

max(7, 10) = 10


## Remember

Normal maximum:

    maxSum

Circular maximum:

    total - minSum

Final answer:

    max(maxSum, total - minSum)

But if all elements are negative:

    return maxSum


## Time Complexity

O(n)

We traverse the array only once.


## Space Complexity

O(1)

We use only a few variables.