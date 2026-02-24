import java.util.*;

class Solution {
    
    static int[][] dp; // dp[time][temp] = time 시간에 temp 온도를 만들 수 있는 최소 전력
    static int maxTime;
    static int INF = 987654321;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        maxTime = onboard.length;
        dp = new int[maxTime][52]; // -10도 ~ 40도
        
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }
        
        dp[0][temperature + 10] = 0;
        for (int time = 0; time < maxTime - 1; time++) {
            for (int temp = -10; temp <= 40; temp++) {
                int tempIdx = temp + 10;
                if (dp[time][tempIdx] == INF) continue;
                
                // 에어컨을 켜서 온도를 1도 올리는 경우
                if (temp + 1 <= 40) 
                    dp[time + 1][tempIdx + 1] = Math.min(dp[time + 1][tempIdx + 1], dp[time][tempIdx] + a);
                // 에어컨을 켜서 온도를 1도 낮추는 경우
                if (temp - 1 >= -10) 
                    dp[time + 1][tempIdx - 1] = Math.min(dp[time + 1][tempIdx - 1], dp[time][tempIdx] + a);
                // 에어컨을 켜서 현재 온도를 유지하는 경우
                dp[time + 1][tempIdx] = Math.min(dp[time + 1][tempIdx], dp[time][tempIdx] + b);
                //에어컨을 끄는 경우
                int nextTemp = temp;
                if (temp < temperature) nextTemp++;
                else if (temp > temperature) nextTemp--;
                int nextTempIdx = nextTemp + 10;
                dp[time + 1][nextTempIdx] = Math.min(dp[time + 1][nextTempIdx], dp[time][tempIdx]);
            }
            
            if (onboard[time + 1] == 1) {
                for (int temp = -10; temp < t1; temp++) dp[time + 1][temp + 10] = INF;
                for (int temp = t2 + 1; temp <= 40; temp++) dp[time + 1][temp + 10] = INF;
            }
        }
        
        int answer = INF;
        for (int temp = 0; temp <= 50; temp++) {
            answer = Math.min(answer, dp[maxTime - 1][temp]);
        }
        
        return answer;
    }
}