class Solution {
    public int[] dailyTemperatures(int[] t) {
        int[] res = new int[t.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < t.length; i++) {

            while (!stack.empty() && t[stack.peek()] < t[i]) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }

            stack.push(i);
        }

        return res;
    }
}