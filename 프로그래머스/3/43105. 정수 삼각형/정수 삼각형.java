import java.util.*;

class Solution {
    
    int h; // 삼각형의 높이
    int[][] value;
    int[][] dp;
    
    public int solution(int[][] triangle) {
        value = triangle;
        h = triangle.length;
        dp = new int[h][];
        for (int i = 0; i < h; i++) {
            dp[i] = new int[i + 1];
            Arrays.fill(dp[i], -1);
        }
        
        return setDp(0, 0);
    }
    
    int setDp(int x, int y) {
        if (dp[x][y] >= 0) return dp[x][y];
        if (x == h - 1) return value[x][y];
        return dp[x][y] = Math.max(setDp(x + 1, y), setDp(x + 1, y + 1)) + value[x][y];
    }
}