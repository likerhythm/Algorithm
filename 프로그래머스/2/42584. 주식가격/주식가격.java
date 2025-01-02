import java.util.*;

class Solution {
    
    static class Price {
        int price;
        int idx;
        int pushTime;
        
        public Price (int price, int idx, int pushTime) {
            this.price = price;
            this.idx = idx;
            this.pushTime = pushTime;
        }
        
        public boolean isBiggerThan(int price) {
            return this.price > price;
        }
        
        public int getIdx() {
            return idx;
        }
        
        public int calcTime(int time) {
            return time - pushTime;
        }
    }
        
        
    public int[] solution(int[] prices) {
        Stack<Price> stack = new Stack<>();
        int[] answer = new int[prices.length];
        
        int time = 0;
        for (int i=0; i<prices.length; i++) {
            int price = prices[i];
            
            if (stack.isEmpty()) {
                stack.push(new Price(price, i, time));
            } else {
                Price top = stack.peek();
                while (top.isBiggerThan(price)) {
                    Price pop = stack.pop();
                    int idx = pop.getIdx();
                    answer[idx] = pop.calcTime(time);
                    if (stack.isEmpty()) {
                        break;
                    }
                    top = stack.peek();
                }
                stack.push(new Price(price, i, time));
            }
            
            time++;
        }
        
        for (int i=0; i<prices.length; i++) {
            if (answer[i] == 0) {
                answer[i] = prices.length - i - 1;
            }
        }
        return answer;
    }
}