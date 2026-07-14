class Solution {
    
    public int solution(int[] money) {
        if (money.length == 1) return money[0];
        return Math.max(solve(money, 0, money.length - 2), solve(money, 1, money.length - 1));
    }
    
    int solve(int[] money, int start, int end) {
        int len = end - start + 1;
        int[] dp = new int[len];
        
        dp[0] = money[start];
        if (len > 1) dp[1] = Math.max(money[start], money[start + 1]);
        
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[start + i]);
        }
        
        return dp[len - 1];
    }
}
