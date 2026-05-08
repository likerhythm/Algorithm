class Solution {
    public long solution(int n, int[] times) {
        int maxTime = 0;
        for (int time : times) {
            maxTime = Math.max(maxTime, time);
        }
        
        long left = 1, right = (long) n * maxTime;
        long mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (count(n, times, mid) < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    long count(int n, int[] times, long mid) {
        long totalUser = 0;
        for (int time : times) {
            totalUser += mid / time;
        }
        return totalUser;
    }
}