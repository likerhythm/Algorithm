import java.util.*;
import java.io.*;

class Solution {
    
    static int N;
    static int[][] times;
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        N = schedules.length;
        times = new int[N][8];
        int answer = 0;
        
        int day = startday;
        int origin = 0;
        do {
            for (int i = 0; i < N; i++) {
                times[i][day] = timelogs[i][origin];
            }
            if (day == 7) day = 1;
            else day++;
            origin++;
        } while (day != startday);
        
        for (int emp = 0; emp < N; emp++) {
            int count = 0;
            for (day = 1; day <= 5; day++) {
                int scheduleTime = schedules[emp];
                int realTime = times[emp][day];
                
                int limitTime = scheduleTime + 10;
                if (limitTime % 100 >= 60) {
                    limitTime += 40;
                }
                
                if (limitTime >= realTime) count++;
            }
            
            if (count == 5) answer++;
        }
        
        return answer;
    }
}