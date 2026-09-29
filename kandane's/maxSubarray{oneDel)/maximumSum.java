class Solution {
    public int maximumSum(int[] arr) {
        // Base case: if the array has only one element, we cannot delete it 
        // because the problem requires a non-empty subarray.
        int noDel = arr[0];
        int oneDel = Integer.MIN_VALUE;
        int res = arr[0];
        
        // Start the loop from index 1
        for (int i = 1; i < arr.length; i++) {
            int prevNoDel = noDel;

            // Option 1: Start a new subarray at arr[i], or extend the previous no-deletion subarray
            noDel = Math.max(arr[i], noDel + arr[i]);

            // Option 2: Delete arr[i] (keep prevNoDel), or extend a previous subarray that already had a deletion
            // Note: If oneDel is Integer.MIN_VALUE, adding arr[i] will cause an underflow. 
            // We use a ternary check to safely handle the addition.
            int extendOneDel = (oneDel == Integer.MIN_VALUE) ? Integer.MIN_VALUE : oneDel + arr[i];
            oneDel = Math.max(prevNoDel, extendOneDel);

            // Track the global maximum sum found so far
            res = Math.max(res, Math.max(noDel, oneDel));
        }
        
        return res;
    }
}
