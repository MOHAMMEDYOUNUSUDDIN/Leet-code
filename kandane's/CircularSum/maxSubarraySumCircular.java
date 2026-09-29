class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0];
        int currentMax = 0;
        int minSum = nums[0];
        int currentMin = 0;
        int total =0;
        for(int num : nums){
            total+=num;
            currentMax=Math.max(currentMax+num,num);
            maxSum = Math.max(maxSum,currentMax);
            currentMin=Math.min(currentMin+num,num);
            minSum = Math.min(minSum,currentMin);
            

        }
        if(maxSum<0){
            return maxSum;
        }
        int circular = total - minSum;
        return Math.max(maxSum,circular);
    }
}