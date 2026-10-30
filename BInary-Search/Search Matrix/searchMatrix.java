class Solution {
    public boolean searchMatrix(int[][] a, int target) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return false;
        }
        
        int n = a.length;
        int m = a[0].length;
        
        // Start from the top-right corner
        int row = 0;
        int col = m - 1;
        
        while (row < n && col >= 0) {
            if (a[row][col] == target) {
                return true; // Found the target
            } else if (a[row][col] > target) {
                col--; // Target is smaller, move left
            } else {
                row++; // Target is larger, move down
            }
        }
        
        return false; // Target not found
    }
}
