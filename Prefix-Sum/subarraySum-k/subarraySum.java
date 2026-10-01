class Solution {
    public int subarraySum(int[] nums, int k) {

        Map<Integer,Integer> prefixSum =new HashMap<>();
        int sum=0;
        int count=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum==k){
                count++;
            }

            int diff = sum-k;
            if(prefixSum.containsKey(diff)){
                count+=prefixSum.get(diff);
            }
            prefixSum.put(sum,prefixSum.getOrDefault(sum,0)+1);

        }
        return count;
    }
}