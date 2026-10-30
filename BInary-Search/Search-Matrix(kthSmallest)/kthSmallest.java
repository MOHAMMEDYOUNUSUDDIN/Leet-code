class Solution {
    public int fun(int[][] a,int n,int m,int guess){
        int row=n-1;
        int col=0;
        int count =0;
        while(row>=0 && col<m){
            if(a[row][col]<=guess){
                count+=row+1;
                col++;
            }
            else{
                row--;
            }
        }
        return count;
    }

    public int kthSmallest(int[][] a, int target) {
        int n=a.length;
        int m=a[0].length;
        int low=a[0][0];
        int high=a[n-1][m-1];
        int res=-1;
        while(low<=high){
            int guess=low+(high-low)/2;
            int ans=fun(a,n,m,guess);
            if(ans<target){
                low=guess+1;
            }
            else{
                res=guess;
                high=guess-1;
            }
        }
        return res;
    }
}