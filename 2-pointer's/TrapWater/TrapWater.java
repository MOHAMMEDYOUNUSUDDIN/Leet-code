class Solution {
    public int trap(int[] height) {
        int left =0 ;
        int rig = height.length-1;
        int leftmax = 0;
        int rigmax =0;
        int water =0;
        while(left<rig){
            leftmax = Math.max(leftmax,height[left]);
            rigmax = Math.max(rigmax,height[rig]);
            if(leftmax<rigmax){
                water+=leftmax-height[left];
                left++;
            }
            else{
                water+=rigmax-height[rig];
                rig--;
            }

        }
        return water;
    }
}