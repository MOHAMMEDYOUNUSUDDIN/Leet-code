class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> list = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < firstList.length && j < secondList.length) {

            int[] first = firstList[i];
            int[] second = secondList[j];

            // No overlap
            if (first[1] < second[0]) {
                i++;
                continue;
            }

            // No overlap
            if (second[1] < first[0]) {
                j++;
                continue;
            }

            // Overlap
            int add1 = Math.max(first[0], second[0]);
            int add2 = Math.min(first[1], second[1]);

            list.add(new int[]{add1, add2});

            // Move the interval which ends first
            if (first[1] <= second[1]) {
                i++;
            } else {
                j++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}