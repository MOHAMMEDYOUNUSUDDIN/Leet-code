class Solution {

       public long getTime(int[] a, int n, int speed){
        long h = 0; 
        for(int i=0; i<a.length; i++){
            h = h + a[i]/speed;
            if(a[i]%speed != 0){
                h++;
            }
        }
        return h;
    }

    public int minEatingSpeed(int[] a, int guard) {
        int n =a.length;
        int res=-1;
        int low=1;
        int high=0;
        for(int i=0;i<n;i++){
            high=Math.max(high,a[i]);
        }
        while(low<=high){
           int mid = low + (high - low) / 2; 
            long hours=getTime(a,n,mid);
            if(hours>guard){
                low=mid+1;
            }
            else{
                res=mid;
                high=mid-1;
            }
        }
        return res;
    }
}