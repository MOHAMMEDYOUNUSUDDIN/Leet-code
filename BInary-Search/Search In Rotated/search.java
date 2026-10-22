
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int guess = low + (high - low) / 2;

            if (nums[guess] == target) {
                return guess;
            }

            // Left half is sorted
            if (nums[low] <= nums[guess]) {
                if (nums[low] <= target && target < nums[guess]) {
                    high = guess - 1;
                } else {
                    low = guess + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[guess] < target && target <= nums[high]) {
                    low = guess + 1;
                } else {
                    high = guess - 1;
                }
            }
        }

        return -1;
    }
}