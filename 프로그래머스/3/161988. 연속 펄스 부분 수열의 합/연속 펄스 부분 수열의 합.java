import java.util.*;

class Solution {
    
    static int N;
    
    public long solution(int[] sequence) {
        N = sequence.length;
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        
        int v = 1;
        for (int i = 0; i < N; i++) {
            arr1[i] = sequence[i] * v;
            arr2[i] = sequence[i] * (-v);
            v *= -1;
        }
        
        long[] dp1 = new long[N];
        long[] dp2 = new long[N];
        
        long max1 = arr1[0];
        dp1[0] = arr1[0];
        for (int i = 1; i < N; i++) {
            dp1[i] = Math.max(arr1[i], dp1[i - 1] + arr1[i]);
            max1 = Math.max(max1, dp1[i]);
        }
        
        long max2 = arr2[0];
        dp2[0] = arr2[0];
        for (int i = 1; i < N; i++) {
            dp2[i] = Math.max(arr2[i], dp2[i - 1] + arr2[i]);
            max2 = Math.max(max2, dp2[i]);
        }
        
        return Math.max(max1, Math.max(max2, 0));
    }
}