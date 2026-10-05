class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int sum = 0;
        int count = 0;

        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int rem = ((sum % k) + k) % k;

            if (prefixSum.containsKey(rem)) {
                count += prefixSum.get(rem);
            }

            prefixSum.put(rem, prefixSum.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}