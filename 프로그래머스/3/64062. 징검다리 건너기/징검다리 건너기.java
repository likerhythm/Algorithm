import java.util.*;

class Solution {
    
    static int[] arr;
    static int k;
    
    public int solution(int[] stones, int d) {
        k = d;
        arr = stones;
        int left = 1;
        int right = 200_000_000;
        int answer = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canCross(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    // cnt명이 건널 수 있는지 판단
    static boolean canCross(int target) {
        int count = 0;
        for (int num : arr) {
            if (num < target) count++;
            else count = 0;
            
            if (count >= k) return false;
        }
        return true;
    }
}