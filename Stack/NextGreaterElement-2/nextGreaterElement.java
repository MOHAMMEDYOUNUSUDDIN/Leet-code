class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] res=new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n=nums.length;
         
         for(int i=2*n-1;i>=0;i--){
            int cur=nums[i%n];
            while(!stack.isEmpty() && stack.peek()<=cur){
                stack.pop();
            }
           if (i < n) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(cur);
         }
         
         return res;

    }
}