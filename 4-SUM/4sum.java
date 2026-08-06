import java.util.HashMap;

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store all possible sums of A and B
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        // Find complements from C and D
        for (int c : C) {
            for (int d : D) {
                int target = -(c + d);
                count += map.getOrDefault(target, 0);
            }
        }

        return count;
    }
}