import java.util.*;

class Solution {
    
    int N, M;
    int[][] dp;
    boolean[][] canGo;
    
    public int solution(int m, int n, int[][] puddles) {
        N = n;
        M = m;
        
        dp = new int[N + 1][M + 1];
        canGo = new boolean[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(canGo[i], true);
        }
        
        for (int[] p : puddles) {
            int y = p[0];
            int x = p[1];
            canGo[x][y] = false;
        }
        
        return setDp(1, 1);
    }
    
    int setDp(int n, int m) {
        if (!inRange(n, m)) return 0; // 범위를 벗어나는 경우
        if (!canGo[n][m]) return 0; // 물에 잠긴 지역인 경우
        if (dp[n][m] != -1) return dp[n][m]; // 이미 계산한 경우
        if (n == N && m == M) return dp[n][m] = 1;
        
        dp[n][m] = 0;
        return dp[n][m] = (setDp(n + 1, m) + setDp(n, m + 1)) % 1_000_000_007;
    }
    
    boolean inRange(int n, int m) {
        return 1 <= n && n <= N && 1 <= m && m <= M;
    }
}