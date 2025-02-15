import java.util.*;

class Solution {
    
    public int solution(int[][] board, int[] moves) {
        
        int answer = 0;
        
        List<Stack<Integer>> stacks = new ArrayList<>();
        Stack<Integer> basket = new Stack<>();
        
        for (int i=0; i<board[0].length; i++) {
            stacks.add(new Stack<>());
        }
        
        for (int i=0; i<board[0].length; i++) {
            for (int j=board.length-1; j>=0; j--) {
                if (board[j][i] > 0)
                stacks.get(i).push(board[j][i]);
            }
        }
        
        for (int move : moves) {
            move -= 1;
            if (stacks.get(move).isEmpty()) {
                continue;
            }
            int pop = stacks.get(move).pop();
            
            if (basket.isEmpty()) {
                basket.push(pop);
                continue;
            }
            
            if (basket.peek() == pop) {
                basket.pop();
                answer += 2;
            } else {
                basket.push(pop);
            }
        }
        return answer;
    }
}