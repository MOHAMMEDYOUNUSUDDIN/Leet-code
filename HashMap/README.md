HashMap in Java — Complete Notes for DSA

1. What Is a HashMap?

A "HashMap" is a data structure in Java that stores data in key-value pairs.

Each key is unique, but multiple keys can have the same value.

Real-world example: A student database stores a roll number as the key and the student's name as the value.

Key (Roll No.)| Value (Name)
101| Rahul
102| Priya
103| Aman

Instead of searching every record, we can look up a student using their key.

2. Why Do We Use HashMap?

- Fast searching and retrieval.
- Counting the frequency of elements.
- Detecting duplicates.
- Finding pairs that satisfy a condition.
- Storing relationships between elements.
- Grouping similar elements.
- Remembering previously processed information.

3. How Does HashMap Work Internally?

HashMap uses a process called hashing.

1. You provide a key.
2. Java calls the key's "hashCode()" method.
3. The hash is used to determine a bucket location.
4. Java stores or retrieves the key-value entry from that bucket.
5. If multiple keys map to the same bucket, Java handles the collision using equality checks and internal bucket structures.

Important terms

- Hashing: Converting a key into a hash value.
- Bucket: A location in the internal table.
- Collision: Different keys map to the same bucket.
- "hashCode()": Produces a hash value for an object.
- "equals()": Checks whether two keys are equal.

A collision does not mean two keys are equal.

4. How to Create a HashMap

import java.util.HashMap;

HashMap<Integer, String> map = new HashMap<>();

map.put(101, "Rahul");
map.put(102, "Priya");
map.put(103, "Aman");

System.out.println(map);

Syntax:

HashMap<KeyType, ValueType> map = new HashMap<>();

Examples:

HashMap<Integer, Integer> map1 = new HashMap<>();
HashMap<Character, Integer> map2 = new HashMap<>();
HashMap<String, Integer> map3 = new HashMap<>();
HashMap<String, String> map4 = new HashMap<>();

5. Important HashMap Methods

Method| Purpose| Example
"put(key, value)"| Insert or update| "map.put(1, 10)"
"get(key)"| Retrieve a value| "map.get(1)"
"getOrDefault(key, default)"| Get value or fallback| "map.getOrDefault(2, 0)"
"containsKey(key)"| Check whether key exists| "map.containsKey(1)"
"containsValue(value)"| Check whether value exists| "map.containsValue(10)"
"remove(key)"| Delete an entry| "map.remove(1)"
"size()"| Number of entries| "map.size()"
"isEmpty()"| Check whether empty| "map.isEmpty()"
"keySet()"| Get all keys| "map.keySet()"
"values()"| Get all values| "map.values()"
"entrySet()"| Get key-value entries| "map.entrySet()"
"clear()"| Remove all entries| "map.clear()"
"putIfAbsent(key, value)"| Insert only if key is absent| "map.putIfAbsent(1, 10)"

Important behavior

map.put(1, 10);
map.put(1, 20);

System.out.println(map.get(1)); // 20

A key cannot have multiple separate entries. Inserting the same key updates its value.

6. How to Traverse a HashMap

Method 1: Using "keySet()"

for (Integer key : map.keySet()) {
    System.out.println(key + " -> " + map.get(key));
}

Method 2: Using "entrySet()" — recommended for key-value traversal

for (var entry : map.entrySet()) {
    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}

Method 3: Using "forEach()"

map.forEach((key, value) ->
    System.out.println(key + " -> " + value)
);

Remember: "HashMap" does not guarantee iteration order.

7. Frequency Counting Pattern

Frequency counting is one of the most important HashMap applications in DSA.

Example

Input:

nums = [1, 2, 2, 3, 1, 2]

Output frequencies:

1 -> 2
2 -> 3
3 -> 1

Java code

import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1, 2};

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println(map);
    }
}

How "getOrDefault()" works

map.getOrDefault(num, 0)

- If the key exists, return its current value.
- If the key does not exist, return "0".
- Add "1" and store the updated count.

Dry run

Number| Previous frequency| New frequency
1| 0| 1
2| 0| 1
2| 1| 2
3| 0| 1
1| 1| 2
2| 2| 3

8. HashMap vs HashSet

HashMap| HashSet
Stores key-value pairs| Stores unique elements
Use when information must be associated with a key| Use when only membership matters
Example: number → frequency| Example: numbers already seen
"map.put(key, value)"| "set.add(value)"

Example: To count duplicate occurrences, a HashMap is useful. To check whether any duplicate exists, a HashSet may be enough.

9. When Should You Use HashMap in DSA?

Recognize these common problem patterns:

Problem clue| Possible pattern
Count occurrences| Frequency map
Find first unique character| Frequency map + second traversal
Find two numbers with a target sum| Complement lookup
Detect repeated elements| HashSet or HashMap
Check whether two strings are anagrams| Character frequencies
Map one character to another| Key-value mapping
Group anagrams| Signature → list
Count subarrays with a target sum| Prefix Sum + HashMap
Find the top K frequent elements| Frequency map + heap or bucket sort
Remember previously seen values| Lookup map or set

The key question is:

Can I avoid repeated searching by remembering information I've already processed?

If yes, consider a HashMap or HashSet.

10. HashMap and the Two Sum Pattern

Problem: Find two indices whose values add up to a target.

Example:

nums = [2, 7, 11, 15]
target = 9
Output = [0, 1]

For each number, calculate:

complement = target - currentNumber

Check whether the complement is already stored.

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

Here, the key is the number and the value is its index.

11. Time and Space Complexity

Operation| Average time complexity
"put()"| O(1)
"get()"| O(1)
"containsKey()"| O(1)
"remove()"| O(1)
"size()"| O(1)
Traverse all entries| O(n)

These are average-case expectations. Performance can differ with collisions and other implementation details.

Space complexity

If the map stores "n" distinct entries, its space usage is generally O(n).

12. Important Properties and Limitations

- Keys are unique.
- Values may be duplicated.
- One key maps to at most one value at a time.
- "HashMap" permits one "null" key and multiple "null" values.
- It is not synchronized for concurrent modification by multiple threads.
- It does not preserve insertion order.
- Mutable objects used as keys can cause lookup problems if changes affect their "hashCode()" or equality.
- Equal keys must have equal hash codes.

13. HashMap vs Other Map Implementations

Feature| HashMap| LinkedHashMap| TreeMap
Ordering| No guaranteed order| Maintains insertion order by default| Sorted by key
Typical lookup| O(1) average| O(1) average| O(log n)
Main use| Fast general lookup| Predictable insertion order| Sorted keys

14. Common Mistakes

1. Using "map.get(key)" without handling a missing key.
2. Confusing keys with values.
3. Assuming HashMap keeps insertion order.
4. Using a HashMap when a HashSet would be simpler.
5. Forgetting to update a frequency after processing an element.
6. Assuming different keys cannot have the same hash code.
7. Forgetting that inserting an existing key replaces its previous value.
8. Choosing the wrong value type: index, count, character, or list.

15. Practice Problems

Beginner

1. "1. Two Sum" (https://leetcode.com/problems/two-sum/)
2. "217. Contains Duplicate" (https://leetcode.com/problems/contains-duplicate/)
3. "242. Valid Anagram" (https://leetcode.com/problems/valid-anagram/)
4. "383. Ransom Note" (https://leetcode.com/problems/ransom-note/)
5. "387. First Unique Character in a String" (https://leetcode.com/problems/first-unique-character-in-a-string/)

Intermediate

6. "49. Group Anagrams" (https://leetcode.com/problems/group-anagrams/)
7. "560. Subarray Sum Equals K" (https://leetcode.com/problems/subarray-sum-equals-k/)
8. "347. Top K Frequent Elements" (https://leetcode.com/problems/top-k-frequent-elements/)
9. "128. Longest Consecutive Sequence" (https://leetcode.com/problems/longest-consecutive-sequence/)
10. "205. Isomorphic Strings" (https://leetcode.com/problems/isomorphic-strings/)

16. Final Revision

Before solving a HashMap problem, ask:

1. What should the key represent?
2. What should the value represent?
3. Do I need a count, index, mapping, or list?
4. Can I use previously stored information to avoid repeated searching?
5. Can I solve the problem in one pass?
6. What are the time and space complexities?
7. Have I tested duplicates, missing keys, and empty inputs?

Final mental model: HashMap helps you remember information so that you can retrieve it quickly when you need it later.
