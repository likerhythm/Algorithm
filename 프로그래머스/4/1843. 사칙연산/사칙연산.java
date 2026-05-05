import java.util.*;
import java.io.*;

class Solution {
    
    final int MIN = -987654321;
    
    int N;
    int[] numbers;
    int[][] maxDp;
    int[][] minDp;
    char[] operators;
    boolean[][] visited;
    
    public int solution(String arr[]) {
        N = arr.length / 2 + 1;
        numbers = new int[N];
        maxDp = new int[N][N];
        minDp = new int[N][N];
        operators = new char[N - 1];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(arr[i * 2]);
        }
        
        for (int i = 0; i < N - 1; i++) {
            operators[i] = arr[i * 2 + 1].charAt(0);
        }
        
        return getMax(0, N - 1);
    }
    
    int getMax(int start, int end) {
        setDp(start, end);
        return maxDp[start][end];
    }
    
    int getMin(int start, int end) {
        setDp(start, end);
        return minDp[start][end];
    }
    
    void setDp(int start, int end) {
        if (visited[start][end]) return;
        visited[start][end] = true;
        
        if (start == end) {
            maxDp[start][end] = numbers[start];
            minDp[start][end] = numbers[start];
            return;
        }
        
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int mid = start; mid < end; mid++) {
            int leftMax = getMax(start, mid);
            int leftMin = getMin(start, mid);
            int rightMax = getMax(mid + 1, end);
            int rightMin = getMin(mid + 1, end);
            
            char op = operators[mid];
            if (op == '-') {
                maxVal = Math.max(maxVal, leftMax - rightMin);
                minVal = Math.min(minVal, leftMin - rightMax);
            } else if (op == '+') {
                maxVal = Math.max(maxVal, leftMax + rightMax);
                minVal = Math.min(minVal, leftMin + rightMin);
            }
        }
            
        maxDp[start][end] = maxVal;
        minDp[start][end] = minVal;
    }
}