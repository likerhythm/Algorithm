import java.util.*;

class Solution {
    
    boolean[][] hasResult; // hasResult[winner][loser] 경기 결과가 존재하면 true
    
    public int solution(int n, int[][] results) {
        hasResult = new boolean[n + 1][n + 1];
        
        for (int[] r : results) {
            int winner = r[0];
            int loser = r[1];
            hasResult[winner][loser] = true;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (hasResult[i][k] && hasResult[k][j]) {
                        hasResult[i][j] = true;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int s = 1; s <= n; s++) {
            int count = 0;
            for (int e = 1; e <= n; e++) {
                if (s == e) continue;
                if (hasResult[s][e] || hasResult[e][s]) count++;
            }
            if (count == n - 1) answer++;
        }
        return answer;
    }
}