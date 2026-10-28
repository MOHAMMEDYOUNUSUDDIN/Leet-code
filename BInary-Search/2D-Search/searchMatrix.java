class Solution {
    public boolean searchMatrix(int[][] a, int target) {
      int n=a.length;
      int m=a[0].length;
      int low=0;
      int high=n*m-1;
      while(low<=high){
        int mid = low + (high - low) / 2;
        int col=mid%m;
        int rol=mid/m;
        if(a[rol][col]==target){
            return true;
        }
        else if(a[rol][col]<target){
            low=mid+1;
        }
        else{
            high=mid-1;
        }
      }

       return false;
    }
}