class Solution {
    
    public int solution(int[] money) {
        int n = money.length;
        return Math.max(solve(money, 0, n - 2),
                        solve(money, 1, n - 1));
    }
    
    private int solve(int[] money, int start, int end) {
        int prev = 0; // 두 칸 전 결과
        int curr = 0; // 현재 집을 털지 않았을 때 최댓값
        for (int i = start; i <= end; i++) {
            int next = Math.max(curr, prev + money[i]);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}


// class Solution {
//     public int solution(int[] money) {
//         int n = money.length;
        
//         return Math.max(solve(money, 0, n - 2),
//                         solve(money, 1, n - 1));
//     }
    
//     private int solve(int[] money, int start, int end) {
//         int len = end - start + 1;
        
//         if (len == 1) return money[start];
        
//         int[] dp = new int[len];
//         dp[0] = money[start];
//         dp[1] = Math.max(money[start], money[start + 1]);
        
//         for (int i = 2; i < len; i++) {
//             dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[start + i]);
//         }
        
//         return dp[len - 1];
//     }
// }