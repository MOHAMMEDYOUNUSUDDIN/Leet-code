class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res=new ArrayList<>();

        int start=intervals[0][0];
        int end =intervals[0][1];

        for(int i=1;i<intervals.length;i++){
            int nextStart=intervals[i][0];
            int nextEnd=intervals[i][1];
            if(end>=nextStart){
                end=Math.max(end,nextEnd);
            }
            else{
                res.add(new int[]{start,end});
                start=nextStart;
                end=nextEnd;
            }
        }
        res.add(new int[]{start,end});
        return res.toArray(new int[res.size()][]);
    }
}