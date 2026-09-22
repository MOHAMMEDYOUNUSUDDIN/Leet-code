class Solution {
    public void sortColors(int[] nums) {
        int i = 0;                  // Points to the next position for 0
        int j = 0;                  // Current element
        int k = nums.length - 1;    // Points to the next position for 2

        while (j <= k) {
            if (nums[j] == 1) {
                j++;
            } 
            else if (nums[j] == 2) {
                swap(nums, j, k);
                k--;
            } 
            else { // nums[j] == 0
                swap(nums, i, j);
                i++;
                j++;
            }
        }
    }

    // Helper method to swap two elements
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}