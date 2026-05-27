import java.util.*;

class Solution {
    
    int[][] pick = {
        {1, 2, 3, 4, 5},
        {2, 1, 2, 3, 2, 4, 2, 5},
        {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };
    
    public int[] solution(int[] answers) {
        int[] count = new int[3];
        int N = answers.length;
        int maxScore = 0;
        
        for (int i = 0; i < N; i++) {
            int a = answers[i];
            for (int j = 0; j < 3; j++) {
                int p = pick[j][i % pick[j].length];
                if (a == p) {
                    count[j]++;
                    maxScore = Math.max(maxScore, count[j]);
                }
            }
        }
        
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (count[i] == maxScore) {
                l.add(i + 1);
            }
        }
        int[] result = l.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }
}