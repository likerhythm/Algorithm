import java.util.*;

class Solution {
    
    int rockCnt;
    
    public int solution(int distance, int[] rocks, int n) {
        rockCnt = rocks.length;
        Arrays.sort(rocks);
        
        int[] sizes = new int[rockCnt + 1];
        sizes[0] = rocks[0];
        for (int i = 1; i < rockCnt; i++) {
            sizes[i] = rocks[i] - rocks[i - 1];
        }
        sizes[rockCnt] = distance - rocks[rockCnt - 1];
        
        int left = 1, right = distance;
        while (left <= right) {
            int mid = (left + right) / 2;
            int removedRock = count(mid, sizes);
            if (removedRock > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
    
    int count(int mid, int[] sizes) {
        int removed = 0;
        int acc = 0;
        for (int size : sizes) {
            acc += size;
            if (acc < mid) {
                removed++;
            } else {
                acc = 0;
            }
        }
        return removed;
    }
}