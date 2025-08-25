import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        // int maxDiff = Arrays.stream(diffs).mapToInt(d -> d).max();
        
        int maxDiff = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int diff : diffs) {
            maxDiff = Math.max(maxDiff, diff);
            minDiff = Math.min(minDiff, diff);
        }
        
        int left = minDiff;
        int right = maxDiff;
        
        int answer = 1;
        
        while (left < right) {
            int mid = (left + right) / 2;
            long totalTime = calcTotalTime(diffs, times, mid);
            
            if (totalTime <= limit) { // 제한시간 내에 해결이 가능한 경우 숙련도(mid)를 더 낮춰서 진행
                answer = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    long calcTotalTime(int[] diffs, int[] times, int level) {
        long result = 0;
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int curTime = times[i];
            int prevTime = i > 0 ? times[i - 1] : 0;
            
            if (diff <= level) {
                result += curTime;
                continue;
            }
            
            result += (curTime + prevTime) * (diff - level);
            result += curTime;
        }
        
        return result;
    }
}