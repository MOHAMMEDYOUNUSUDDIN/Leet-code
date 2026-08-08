class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left =0;
        int maxlength =0;

        boolean[] sum = new boolean[128];

        for(int right=0;right<s.length();right++){
            while(sum[s.charAt(right)]){
                sum[s.charAt(left)]=false;
                left++;
            }
            sum[s.charAt(right)]=true;

            maxlength = Math.max(maxlength,right-left+1);
        }
     return maxlength;

    }
}