Input:
s = "ADOBECODEBANC"
t = "ABC"

t = ABC

Initial:
freq[A] = 1
freq[B] = 1
freq[C] = 1

left = 0
right = 0
required = 3
minLen = ∞
start = 0


STEP 1:
right = 0
r = A

A is required
required = 2

Window = "A"


STEP 2:
right = 1
r = D

D is not required
required = 2

Window = "AD"


STEP 3:
right = 2
r = O

O is not required
required = 2

Window = "ADO"


STEP 4:
right = 3
r = B

B is required
required = 1

Window = "ADOB"


STEP 5:
right = 4
r = E

E is not required
required = 1

Window = "ADOBE"


STEP 6:
right = 5
r = C

C is required
required = 0

Window = "ADOBEC"

All required characters found:
A, B, C

Window length = 6

minLen = 6
start = 0


Now shrink from left:

left = 0
l = A

Remove A

A is required, so:
required = 1

left = 1

Window = "DOBEC"


Continue expanding right...


right reaches index 9:
r = B

Window contains:
A, B, C

required = 0

Window = "DOBECODEB"

Shrink from left.


Remove D
required stays 0

Remove O
required stays 0

Remove B
Now B becomes missing

required = 1


Continue expanding...


right = 10
r = A

required = 0

Window becomes valid again.


Continue shrinking...


Eventually:

Window = "EBANC"

length = 5

minLen = 5
start = 8


Remove E

Window = "BANC"

length = 4

minLen = 4
start = 9


Now removing B would make the window invalid.

So:
required = 1


End of loop.


Final:
start = 9
minLen = 4

s.substring(9, 9 + 4)

s.substring(9, 13)

Answer = "BANC"


OUTPUT:
BANC