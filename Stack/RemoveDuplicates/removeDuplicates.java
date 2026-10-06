class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder res =new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
                continue;
            }
            if(s.charAt(i) == stack.peek()){
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }
          return res.reverse().toString();
    }
}