class Solution {
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int totalsum =0 ;
        
        for(int i : nums){
            totalsum += i;
        }
         
        for(int i=0;i<nums.length;i++){
            int rightSum = totalsum-leftSum-nums[i];

            if(leftSum==rightSum){
                return i;
            }

            leftSum +=nums[i];
        }

        return -1;

    }
}