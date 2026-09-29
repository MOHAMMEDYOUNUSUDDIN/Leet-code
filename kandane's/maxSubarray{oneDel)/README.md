MAXIMUM SUBARRAY SUM WITH ONE DELETION
======================================

CODE:
-----

class Solution {

    public int maximumSum(int[] arr) {

        // Base case: if the array has only one element, we cannot delete it
        // because the problem requires a non-empty subarray.
        int noDel = arr[0];

        int oneDel = Integer.MIN_VALUE;

        int res = arr[0];

        // Start the loop from index 1
        for (int i = 1; i < arr.length; i++) {

            int prevNoDel = noDel;

            // Option 1: Start a new subarray at arr[i],
            // or extend the previous no-deletion subarray
            noDel = Math.max(arr[i], noDel + arr[i]);

            // Option 2: Delete arr[i] (keep prevNoDel),
            // or extend a previous subarray that already had a deletion
            //
            // If oneDel is Integer.MIN_VALUE, don't add arr[i]
            // to avoid integer overflow.
            int extendOneDel =
                    (oneDel == Integer.MIN_VALUE)
                    ? Integer.MIN_VALUE
                    : oneDel + arr[i];

            oneDel = Math.max(prevNoDel, extendOneDel);

            // Track the global maximum sum found so far
            res = Math.max(res, Math.max(noDel, oneDel));
        }

        return res;
    }
}


============================================================
WHAT DO THE VARIABLES MEAN?
============================================================

1. noDel
-----------

Maximum subarray sum ending at the current index
WITHOUT deleting any element.


2. oneDel
-----------

Maximum subarray sum ending at the current index
WITH EXACTLY ONE element deleted.


3. prevNoDel
------------

Stores the OLD value of noDel before noDel is updated.

We need this because:

prevNoDel

means:

"Delete the CURRENT element."


4. extendOneDel
----------------

Stores:

oneDel + arr[i]

only when oneDel is not Integer.MIN_VALUE.

This prevents integer overflow.


5. res
-------

Stores the maximum answer found so far.


============================================================
EXAMPLE
============================================================

Input:

[1, -2, 0, 3]


We want the maximum subarray sum
after deleting AT MOST one element.


Possible answer:

[1, -2, 0, 3]
    X
    |
  delete -2


Remaining:

[1, 0, 3]


Sum:

1 + 0 + 3 = 4


Therefore:

Answer = 4


============================================================
INITIALIZATION
============================================================

Array:

arr = [1, -2, 0, 3]


Before the loop:

noDel = arr[0]
      = 1


oneDel = Integer.MIN_VALUE
       = -∞


res = arr[0]
    = 1


Initial State:

noDel  = 1
oneDel = -∞
res    = 1


IMPORTANT:

The first element arr[0] is already processed.

Therefore:

for (int i = 1; i < arr.length; i++)

We start from index 1.


============================================================
LOOP 1
============================================================

i = 1

arr[1] = -2


------------------------------------------------------------
STEP 1: prevNoDel
------------------------------------------------------------

prevNoDel = noDel

          = 1


So:

prevNoDel = 1


------------------------------------------------------------
STEP 2: noDel
------------------------------------------------------------

noDel = Math.max(arr[i], noDel + arr[i])


      = max(-2, 1 + (-2))


      = max(-2, -1)


      = -1


Therefore:

noDel = -1


------------------------------------------------------------
STEP 3: extendOneDel
------------------------------------------------------------

Current:

oneDel = Integer.MIN_VALUE


Therefore:

extendOneDel = Integer.MIN_VALUE


Because:

oneDel == Integer.MIN_VALUE


So we DON'T calculate:

Integer.MIN_VALUE + (-2)


This prevents overflow.


------------------------------------------------------------
STEP 4: oneDel
------------------------------------------------------------

oneDel = Math.max(prevNoDel, extendOneDel)


       = max(1, -∞)


       = 1


Therefore:

oneDel = 1


Meaning:

[1, -2]
    X


Delete -2:

[1]


Sum:

1


------------------------------------------------------------
STEP 5: res
------------------------------------------------------------

res = Math.max(res, Math.max(noDel, oneDel))


    = max(1, max(-1, 1))


    = 1


State after Loop 1:

noDel  = -1
oneDel = 1
res    = 1


============================================================
LOOP 2
============================================================

i = 2

arr[2] = 0


------------------------------------------------------------
STEP 1: prevNoDel
------------------------------------------------------------

prevNoDel = noDel

          = -1


------------------------------------------------------------
STEP 2: noDel
------------------------------------------------------------

noDel = Math.max(arr[i], noDel + arr[i])


      = max(0, -1 + 0)


      = max(0, -1)


      = 0


Therefore:

noDel = 0


------------------------------------------------------------
STEP 3: extendOneDel
------------------------------------------------------------

oneDel = 1


Since:

oneDel != Integer.MIN_VALUE


We calculate:

extendOneDel = oneDel + arr[i]


             = 1 + 0


             = 1


Therefore:

extendOneDel = 1


------------------------------------------------------------
STEP 4: oneDel
------------------------------------------------------------

oneDel = Math.max(prevNoDel, extendOneDel)


       = max(-1, 1)


       = 1


Therefore:

oneDel = 1


Meaning:

We already deleted -2:

[1, -2, 0]
    X


Remaining:

[1, 0]


Sum:

1 + 0 = 1


------------------------------------------------------------
STEP 5: res
------------------------------------------------------------

res = Math.max(res, Math.max(noDel, oneDel))


    = max(1, max(0, 1))


    = 1


State after Loop 2:

noDel  = 0
oneDel = 1
res    = 1


============================================================
LOOP 3
============================================================

i = 3

arr[3] = 3


------------------------------------------------------------
STEP 1: prevNoDel
------------------------------------------------------------

prevNoDel = noDel

          = 0


------------------------------------------------------------
STEP 2: noDel
------------------------------------------------------------

noDel = Math.max(arr[i], noDel + arr[i])


      = max(3, 0 + 3)


      = max(3, 3)


      = 3


Therefore:

noDel = 3


------------------------------------------------------------
STEP 3: extendOneDel
------------------------------------------------------------

oneDel = 1


Since:

oneDel != Integer.MIN_VALUE


We calculate:

extendOneDel = oneDel + arr[i]


             = 1 + 3


             = 4


Therefore:

extendOneDel = 4


------------------------------------------------------------
STEP 4: oneDel
------------------------------------------------------------

oneDel = Math.max(prevNoDel, extendOneDel)


       = max(0, 4)


       = 4


Therefore:

oneDel = 4


Meaning:

We already deleted -2:

[1, -2, 0, 3]
    X


Remaining:

[1, 0, 3]


Sum:

1 + 0 + 3 = 4


------------------------------------------------------------
STEP 5: res
------------------------------------------------------------

res = Math.max(res, Math.max(noDel, oneDel))


    = max(1, max(3, 4))


    = 4


Final State:

noDel  = 3
oneDel = 4
res    = 4


============================================================
FINAL ANSWER
============================================================

return res;


return 4


Therefore:

Maximum Sum = 4


============================================================
VISUALIZATION
============================================================

Array:

        1       -2       0       3
        |        |       |       |
        ↓        ↓       ↓       ↓


noDel:

        1       -1       0       3


oneDel:

       -∞        1       1       4


res:

        1        1       1       4


Final:

        1       -2       0       3
                 X
                 |
              DELETE
                 |
                 ↓

        1        0       3


        1 + 0 + 3

             ↓

             4


============================================================
TWO TRACKS
============================================================

TRACK 1: noDel
---------------

Meaning:

NO element has been deleted.


        1
        ↓
       -1
        ↓
        0
        ↓
        3


Final:

noDel = 3


------------------------------------------------------------

TRACK 2: oneDel
---------------

Meaning:

ONE element has been deleted.


       -∞
        ↓
        1
        ↓
        1
        ↓
        4


Final:

oneDel = 4


============================================================
HOW oneDel BECOMES 4
============================================================

Original:

[1, -2, 0, 3]


Delete:

-2


Therefore:

[1, 0, 3]


Now:

1 + 0 + 3 = 4


So:

oneDel = 4


============================================================
IMPORTANT FORMULAS
============================================================

1. noDel
---------

noDel = Math.max(arr[i], noDel + arr[i])


Two choices:

Choice 1:

Start a NEW subarray:

arr[i]


Choice 2:

CONTINUE previous subarray:

noDel + arr[i]


Therefore:

noDel = max(
    start new,
    continue previous
)


============================================================

2. extendOneDel
---------------

extendOneDel =
    (oneDel == Integer.MIN_VALUE)
    ? Integer.MIN_VALUE
    : oneDel + arr[i]


This means:

If oneDel has not been created yet:

    don't add arr[i]


Otherwise:

    continue the subarray
    after one deletion.


============================================================

3. oneDel
---------

oneDel = Math.max(prevNoDel, extendOneDel)


Two choices:


CHOICE 1:

prevNoDel

Meaning:

DELETE the CURRENT element.


Example:

[1, -2]

Delete -2:

[1]


------------------------------------------------------------


CHOICE 2:

extendOneDel

Meaning:

We ALREADY deleted one element earlier,
so continue with the current element.


Example:

[1, -2, 0, 3]
    X


Continue:

1 + 0 + 3 = 4


============================================================
WHY prevNoDel?
============================================================

We first save:

int prevNoDel = noDel;


Then update:

noDel = Math.max(arr[i], noDel + arr[i]);


The value of noDel has now changed.


But oneDel needs the OLD value of noDel
when we delete the current element.


Therefore:

prevNoDel = OLD noDel


Then:

oneDel = Math.max(prevNoDel, extendOneDel)


============================================================
WHY extendOneDel?
============================================================

Initially:

oneDel = Integer.MIN_VALUE


If we directly write:

oneDel + arr[i]


we could get integer overflow.


For example:

Integer.MIN_VALUE + (-2)


can overflow the int range.


So we use:

int extendOneDel =
    (oneDel == Integer.MIN_VALUE)
    ? Integer.MIN_VALUE
    : oneDel + arr[i];


Meaning:

IF oneDel is still -∞:

    keep it as -∞


ELSE:

    calculate oneDel + arr[i]


============================================================
COMPLETE TABLE
============================================================

Array:

[1, -2, 0, 3]


+-------+------+------------+--------+---------+-----+
|   i   | a[i] | prevNoDel  | noDel  | oneDel  | res |
+-------+------+------------+--------+---------+-----+
| Start |  1   |     -      |   1    |   -∞    |  1  |
|   1   | -2   |     1      |  -1    |    1    |  1  |
|   2   |  0   |    -1      |   0    |    1    |  1  |
|   3   |  3   |     0      |   3    |    4    |  4  |
+-------+------+------------+--------+---------+-----+


FINAL:

noDel  = 3
oneDel = 4
res    = 4


============================================================
EASY WAY TO REMEMBER
============================================================

noDel:

"NO deletion has happened."


oneDel:

"ONE deletion has happened."


At every element:


noDel:

    START NEW
       OR
    CONTINUE


oneDel:

    DELETE CURRENT
       OR
    CONTINUE AFTER
    PREVIOUS DELETION


Finally:

res = maximum(noDel, oneDel)


============================================================
FINAL CONCEPT
============================================================

This is basically:

Kadane's Algorithm
        +
One extra state


Normal Kadane:

noDel


With one deletion:

noDel
  +
oneDel


Normal Kadane asks:

"What is the maximum subarray sum?"


This problem asks:

"What is the maximum subarray sum
after deleting at most one element?"


So we maintain two states:

1. noDel
   →
   No element deleted

2. oneDel
   →
   One element deleted


For:

[1, -2, 0, 3]


Delete:

-2


Therefore:

[1, 0, 3]


Sum:

4


FINAL ANSWER = 4