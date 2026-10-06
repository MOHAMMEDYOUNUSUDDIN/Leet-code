class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();

        boolean insert = false;

        for (int i = 0; i < intervals.length; i++) {

            // Current interval comes before new interval
            if (intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
            }

            // Current interval comes after new interval
            else if (intervals[i][0] > newInterval[1]) {

                if (!insert) {
                    res.add(newInterval);
                    insert = true;
                }

                res.add(intervals[i]);
            }

            // Overlapping intervals
            else {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }

        // If new interval was never inserted
        if (!insert) {
            res.add(newInterval);
        }

        return res.toArray(new int[res.size()][]);
    }
}