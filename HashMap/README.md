HashMap — Pattern Recognition & Complete Guide (Java)

1. What Is a HashMap?

A "HashMap" stores data as key-value pairs.

Think of it like a phone contacts app:

Key| Value
Rahul| 9876543210
Priya| 9876543211
Aman| 9876543212

When you search for Rahul, you can retrieve his number quickly without checking every contact one by one.

In Java, we use "HashMap<K, V>".

import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>();

map.put("Rahul", 90);
map.put("Priya", 85);

System.out.println(map.get("Rahul")); // 90
System.out.println(map.containsKey("Aman")); // false

Important methods

Method| Purpose
"put(key, value)"| Insert or update a value
"get(key)"| Retrieve the value
"getOrDefault(key, defaultValue)"| Retrieve a value or a default
"containsKey(key)"| Check whether a key exists
"remove(key)"| Remove a key-value pair
"size()"| Number of key-value pairs
"keySet()"| Get all keys

Most basic HashMap operations take O(1) average time.

---

2. The Most Important Question: How Do I Know HashMap Will Work?

Before writing code, ask yourself these questions.

1. Am I repeatedly searching for an element?
2. Do I need to count the frequency of elements?
3. Do I need to remember previously seen elements?
4. Do I need to find a complement, such as "target - current"?
5. Do I need to map one value to another?
6. Do I need to group elements that share the same property?

If the answer to any of these is yes, consider a HashMap or HashSet.

The main idea

HashMap helps when remembering information allows you to avoid repeated searching.

Example:

Given "nums = [2, 7, 11, 15]", find two numbers whose sum equals "9".

The brute-force approach checks every pair. Instead, while visiting each number, remember previous numbers and check whether its required complement exists.

For "2", the complement is "9 - 2 = 7". It is not stored yet, so remember "2".

For "7", the complement is "9 - 7 = 2". We already stored "2", so we found the answer.

That is the core idea behind many HashMap problems.

---

3. Pattern 1: Frequency Counting

When should you recognize this pattern?

Look for phrases such as:

- Count how many times each element appears.
- Find the first unique character.
- Check whether two strings are anagrams.
- Find the most frequent element.
- Determine whether every element occurs equally often.

Example: Count each character

Input: "banana"

Expected frequencies:

Character| Frequency
b| 1
a| 3
n| 2

Java code

import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        String s = "banana";

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        System.out.println(map);
    }
}

How to think

For every character:

1. Check its existing count.
2. If it is absent, use "0".
3. Add "1".
4. Store the updated count.

The key is the character, and the value is its frequency.

Dry run

Character| Previous count| New count| Map after processing
b| 0| 1| "{b=1}"
a| 0| 1| "{b=1, a=1}"
n| 0| 1| "{b=1, a=1, n=1}"
a| 1| 2| "{b=1, a=2, n=1}"
n| 1| 2| "{b=1, a=2, n=2}"
a| 2| 3| "{b=1, a=3, n=2}"

Complexity

- Time: O(n)
- Space: O(k), where "k" is the number of distinct characters.

Related problems

- "387. First Unique Character in a String" (https://leetcode.com/problems/first-unique-character-in-a-string/)
- "242. Valid Anagram" (https://leetcode.com/problems/valid-anagram/)
- "169. Majority Element" (https://leetcode.com/problems/majority-element/)
- "1189. Maximum Number of Balloons" (https://leetcode.com/problems/maximum-number-of-balloons/)

---

4. Pattern 2: HashMap for Fast Lookup — Two Sum

How to recognize it

The problem asks you to find:

- Two elements that satisfy a target.
- A pair with a specific sum.
- A complement or matching value.
- A previously seen element that satisfies a condition.

Example

nums = [2, 7, 11, 15]
target = 9
Output = [0, 1]

How to think

Suppose the current number is "x".

The number needed to reach the target is:

"complement = target - x"

Instead of searching the entire array for the complement, check whether it already exists in the HashMap.

Store each previously seen number as the key and its index as the value.

Java code

import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[] {};
    }
}

Dry run

Input: "nums = [2, 7, 11, 15]", "target = 9"

Index| Current number| Complement| Map before lookup| Action
0| 2| 7| "{}"| 7 absent; store "2 → 0"
1| 7| 2| "{2=0}"| 2 exists; return "[0, 1]"

Why check before inserting?

For "nums = [3, 3]" and "target = 6", the first "3" is stored. The second "3" finds the first one.

Checking before inserting ensures that we use two different indices.

Complexity

- Time: O(n) average
- Space: O(n)

Problem: "1. Two Sum" (https://leetcode.com/problems/two-sum/)

---

5. Pattern 3: Detect Duplicates

How to recognize it

The question asks whether:

- Any element appears more than once.
- An element has appeared before.
- Two values are equal.
- A duplicate exists within a certain condition.

If you only need to know whether a value exists, a "HashSet" is often enough. If you also need its index or frequency, use a "HashMap".

Java code

import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }
}

How to think

A set remembers which elements have appeared. If the current element is already in the set, a duplicate exists.

Complexity

- Time: O(n) average
- Space: O(n)

Problems:

- "217. Contains Duplicate" (https://leetcode.com/problems/contains-duplicate/)
- "219. Contains Duplicate II" (https://leetcode.com/problems/contains-duplicate-ii/)

---

6. Pattern 4: HashMap for String Mapping

How to recognize it

Look for questions about:

- Whether two strings follow the same pattern.
- Whether characters map consistently.
- Isomorphic strings.
- Repeated character relationships.

Example: Isomorphic Strings

s = "egg"
t = "add"
Output = true

The mapping is:

- "e → a"
- "g → d"

Each character in one string must consistently map to the same character in the other string. Two different characters must not map to the same character when a one-to-one mapping is required.

Java code

import java.util.HashMap;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> mapST = new HashMap<>();
        HashMap<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if (mapST.containsKey(a) && mapST.get(a) != b) {
                return false;
            }

            if (mapTS.containsKey(b) && mapTS.get(b) != a) {
                return false;
            }

            mapST.put(a, b);
            mapTS.put(b, a);
        }

        return true;
    }
}

Why use two maps?

One map checks "s → t"; the other checks "t → s".

Without checking both directions, two different characters might incorrectly map to the same character.

Complexity

- Time: O(n)
- Space: O(k), where "k" is the number of distinct characters.

Problem: "205. Isomorphic Strings" (https://leetcode.com/problems/isomorphic-strings/)

---

7. Pattern 5: Grouping Elements Using a HashMap

How to recognize it

The problem asks you to group:

- Anagrams together.
- Values with the same property.
- Elements that share a common key.
- Words that can be represented by the same signature.

Example: Group Anagrams

Input = ["eat", "tea", "tan", "ate", "nat", "bat"]

The words "eat", "tea", and "ate" contain the same characters, so they belong to the same group.

How to think

1. Convert each word into a common signature.
2. Use that signature as the HashMap key.
3. Store all words with that key in the same list.
4. Return the grouped lists.

One possible signature is the sorted version of each word.

For example:

eat → aet
tea → aet
ate → aet

Java code

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>())
               .add(word);
        }

        return new ArrayList<>(map.values());
    }
}

Complexity

If there are "n" words and each word has at most "k" characters:

- Time: O(n × k log k)
- Space: O(n × k), including stored words.

Problem: "49. Group Anagrams" (https://leetcode.com/problems/group-anagrams/)

---

8. Pattern 6: Prefix Sum + HashMap

This is an important combination. A HashMap can remember previous prefix sums so that we do not need to check every possible subarray.

How to recognize it

Look for:

- Count the number of subarrays whose sum equals "k".
- Find a subarray with a target sum.
- Count ranges satisfying a sum condition.
- Array values may be negative, so ordinary sliding-window sum logic is not reliable.

Example: Subarray Sum Equals K

nums = [1, 1, 1]
k = 2
Output = 2

The two valid subarrays are:

[1, 1] at indices 0–1
[1, 1] at indices 1–2

The key formula

Let "prefixSum" be the sum from the beginning up to the current index.

If an earlier prefix sum was:

"previousPrefix = prefixSum - k"

then the elements between that earlier position and the current position sum to "k".

Therefore, remember prefix sums and how often each has appeared.

Java code

import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            int needed = prefixSum - k;
            count += map.getOrDefault(needed, 0);

            map.put(
                prefixSum,
                map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }
}

Dry run

Input: "nums = [1, 1, 1]", "k = 2"

Initially, "map = {0=1}" and "count = 0".

Number| Prefix sum| Needed ("prefixSum - k")| Previous occurrences| Count| Map update
1| 1| -1| 0| 0| Store "1 → 1"
1| 2| 0| 1| 1| Store "2 → 1"
1| 3| 1| 1| 2| Store "3 → 1"

Answer: "2"

Why initialize "map.put(0, 1)"?

It handles subarrays that begin at index "0". If the current prefix sum itself equals "k", then "prefixSum - k = 0", and the initial entry counts that subarray.

Complexity

- Time: O(n) average
- Space: O(n)

Problem: "560. Subarray Sum Equals K" (https://leetcode.com/problems/subarray-sum-equals-k/)

---

9. Pattern 7: Longest Consecutive Sequence

How to recognize it

The problem asks for the longest consecutive sequence, but the input array is unsorted.

Sorting is one solution, but it takes O(n log n). A HashSet lets us check whether neighboring values exist in expected O(1) time.

How to think

For each number "num", start counting only if "num - 1" does not exist. This means "num" is the beginning of a consecutive sequence.

Then check "num + 1", "num + 2", and so on.

Java code

import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int current = num;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}

Example

Input = [100, 4, 200, 1, 3, 2]
Sequence = 1, 2, 3, 4
Output = 4

Complexity

- Time: O(n) average
- Space: O(n)

Problem: "128. Longest Consecutive Sequence" (https://leetcode.com/problems/longest-consecutive-sequence/)

---

10. How to Choose the Correct HashMap Pattern

Problem requirement| Pattern to consider| What the map stores
Count occurrences| Frequency counting| Element → frequency
Find a pair for a target| Complement lookup| Number → index
Detect repeated values| HashSet or HashMap| Seen element or element → count
Check character relationships| Mapping| Character → character
Group similar items| Grouping| Signature → list of items
Count target-sum subarrays| Prefix Sum + HashMap| Prefix sum → frequency
Find a longest consecutive sequence| HashSet| Existing values

HashMap vs HashSet vs Sliding Window

Tool| Best use
HashMap| Store a key and associated information
HashSet| Check whether a value exists
Sliding Window| Maintain a contiguous range while moving pointers
Prefix Sum + HashMap| Count or find subarrays using previous prefix sums

These techniques can work together. For example, Longest Substring Without Repeating Characters uses a sliding window and a set or map to track characters.

---

11. Common Mistakes

1. Forgetting "getOrDefault": A missing key may return "null". For frequency counting, use "map.getOrDefault(key, 0)".
2. Using the wrong value: Store an index when the question asks for indices, a frequency when it asks for counts, or a list when it asks for groups.
3. Inserting too early in Two Sum: Check for the complement before inserting the current element.
4. Using only one map for a one-to-one mapping: For isomorphic strings, check both directions.
5. Forgetting the initial prefix sum: For subarray-sum counting, initialize "map.put(0, 1)".
6. Assuming iteration order: "HashMap" does not guarantee a particular iteration order.
7. Assuming every HashMap problem needs a HashMap: Sometimes a "HashSet", sorting, or a two-pointer solution is simpler.
8. Ignoring negative values: A standard sliding window for sum conditions may fail when negative numbers are allowed; consider Prefix Sum + HashMap.

---

12. Practice Problems

Beginner

1. "1. Two Sum" (https://leetcode.com/problems/two-sum/)
2. "217. Contains Duplicate" (https://leetcode.com/problems/contains-duplicate/)
3. "242. Valid Anagram" (https://leetcode.com/problems/valid-anagram/)
4. "387. First Unique Character in a String" (https://leetcode.com/problems/first-unique-character-in-a-string/)
5. "383. Ransom Note" (https://leetcode.com/problems/ransom-note/)

Intermediate

6. "205. Isomorphic Strings" (https://leetcode.com/problems/isomorphic-strings/)
7. "49. Group Anagrams" (https://leetcode.com/problems/group-anagrams/)
8. "560. Subarray Sum Equals K" (https://leetcode.com/problems/subarray-sum-equals-k/)
9. "347. Top K Frequent Elements" (https://leetcode.com/problems/top-k-frequent-elements/)
10. "128. Longest Consecutive Sequence" (https://leetcode.com/problems/longest-consecutive-sequence/)
11. "202. Happy Number" (https://leetcode.com/problems/happy-number/)
12. "454. 4Sum II" (https://leetcode.com/problems/4sum-ii/)

---

13. Interview Thinking Checklist

Whenever you read a new problem, ask:

1. What information do I need to remember?
2. What should the key represent?
3. What should the value represent?
4. Can remembering previous information eliminate repeated searching?
5. Do I need a frequency, index, mapping, list, or prefix sum?
6. Can I solve it in one pass?
7. What is the time and space complexity?
8. What edge cases should I test, such as duplicates, empty inputs, negative values, and repeated prefix sums?

Final Mental Model

HashMap = Remember information so that future decisions become faster.

- Need counts? Use frequency counting.
- Need a matching number? Look up its complement.
- Need to remember previous values? Use a map or set.
- Need consistent relationships? Use mapping.
- Need to group similar items? Use a shared key.
- Need target-sum subarrays? Combine Prefix Sum with a HashMap.

Do not memorize the code first. Identify the information you need to remember, decide what the key and value should mean, and then build the algorithm around that.
