import java.util.*;
import java.io.*;

class Solution {
    
    static int N;
    static int[][] queries;
    static int[] matches;
    static int answer;
    
    public int solution(int n, int[][] q, int[] ans) {
        queries = q;
        matches = ans;
        N = n;

        List<Integer> code = new ArrayList<>();
        
        combination(1, code);
        return answer;
    }
    
    static void combination(int start, List<Integer> code) {
        if (code.size() == 5) {
            for (int i = 0; i < queries.length; i++) {
                int[] query = queries[i];
                int match = matches[i];
                int count = 0;
                for (int num : query) {
                    if (code.contains(num)) count++;
                }
                if (count != match) {
                    return;
                }
            }
            answer++;
        }
        
        for (int i = start; i < N + 1; i++) {
            code.add(i);
            combination(i + 1, code);
            code.remove(code.size() - 1);
        }
    }
}