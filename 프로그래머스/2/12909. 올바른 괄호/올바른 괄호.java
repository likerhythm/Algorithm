import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty()) {
                if (c == ')') {
                    return false;
                }
                stack.push(c);
                continue;
            }
            if (stack.peek() == '(' && c == ')') {
                char pop = stack.pop();
                continue;
            }
            stack.push(c);
        }
        
        return stack.isEmpty();
    }
}