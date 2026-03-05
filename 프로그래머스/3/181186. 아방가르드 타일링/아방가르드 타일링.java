
// n == 1: 1
// n == 2: 3
// n == 3: 10

class Solution {
    
    static long[] dp;
    static long[] sum;
    static long MOD = 1_000_000_007;
    
    public long solution(int n) {
        dp = new long[n + 1];
        sum = new long[n + 1];

        dp[0] = 1;
        sum[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = (dp[i - 1] * 1) % MOD;
            if (i >= 2) dp[i] = (dp[i] + dp[i - 2] * 2) % MOD;
            if (i >= 3) dp[i] = (dp[i] + dp[i - 3] * 5) % MOD;

            if (i >= 4) dp[i] = (dp[i] + sum[i - 4] * 2) % MOD;
            if (i >= 5) dp[i] = (dp[i] + sum[i - 5] * 2) % MOD;
            if (i >= 6) dp[i] = (dp[i] + sum[i - 6] * 4) % MOD;

            sum[i] = dp[i];
            if (i >= 3) sum[i] = (sum[i] + sum[i - 3]) % MOD;
        }
        
        return dp[n] % MOD;
    }
}