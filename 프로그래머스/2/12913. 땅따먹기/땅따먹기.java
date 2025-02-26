class Solution {
    
    static int[][] dp;
    static int answer;
    
    int solution(int[][] land) {
        
        dp = new int[land.length][4];
        for (int i=0; i<4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int row=1; row<dp.length; row++) {
            for (int i=0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    if (i == j) {
                        continue;
                    }
                    dp[row][i] = Math.max(dp[row][i], dp[row - 1][j] + land[row][i]);
                }
            }    
        }
        
        
        for (int i=0; i<4; i++) {
            answer = Math.max(dp[dp.length - 1][i], answer);
        }
        
        return answer;
    }
}