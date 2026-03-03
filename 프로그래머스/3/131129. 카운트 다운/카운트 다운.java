import java.util.*;

class Solution {
    public int[] solution(int target) {
        // dp[i][0]: i점을 만드는 최소 다트 수
        // dp[i][1]: 그때의 최대 싱글/불 횟수
        int[][] dp = new int[target + 1][2];
        
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= 20; j++) {
                update(dp, i, j, 1);
            }
            update(dp, i, 50, 1);

            for (int j = 1; j <= 20; j++) {
                update(dp, i, j * 2, 0);
                update(dp, i, j * 3, 0);
            }
        }

        return dp[target];
    }

    private void update(int[][] dp, int total, int score, int isSingleOrBull) {
        if (total >= score) {
            int prev = total - score;
            int currentCount = dp[prev][0] + 1;
            int currentSB = dp[prev][1] + isSingleOrBull;
            
            if (currentCount < dp[total][0]) {
                dp[total][0] = currentCount;
                dp[total][1] = currentSB;
            } else if (currentCount == dp[total][0]) {
                if (currentSB > dp[total][1]) {
                    dp[total][1] = currentSB;
                }
            }
        }
    }
}