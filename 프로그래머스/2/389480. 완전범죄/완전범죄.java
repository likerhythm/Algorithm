import java.util.*;
import java.io.*;

class Solution {

    static final int INF = 987654321;
    
    static Cost[] costs;
    static int[][] dp;
    static int N; // 물건의 개수
    
    static class Cost {
        int a, b;
        
        public Cost(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        @Override
        public String toString() {
            return "[" + a + ", " + b + "]";
        }
    }
    
    // B도둑이 훔친 물건에서 A도둑의 흔적은 최대로, B도둑의 흔적은 최소로 되도록 선택
    public int solution(int[][] info, int n, int m) {
        N = info.length;
        costs = new Cost[N];
        for (int i = 0; i < N; i++) {
            costs[i] = new Cost(info[i][0], info[i][1]);
        }
        
        dp = new int[N + 1][m];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            int a = costs[i].a;
            int b = costs[i].b;
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == INF) continue;
                
                if (dp[i][j] + a < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + a);
                }
                if (j + b < m) {
                    dp[i + 1][j + b] = Math.min(dp[i + 1][j + b], dp[i][j]);
                }
            }
        }
        
        int answer = INF;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, dp[N][j]);
        }

        return (answer == INF) ? -1 : answer;
    }
}