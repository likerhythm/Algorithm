import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] pantsCnt = new int[n + 1];
        Arrays.fill(pantsCnt, 1);
        for (int r : reserve) {
            pantsCnt[r] += 1;
        }
        for (int l : lost) {
            pantsCnt[l] -= 1;
        }
        
        for (int i = 1; i <= n; i++) {
            if (pantsCnt[i] == 1) continue;
            if (pantsCnt[i] == 2) {
                if (i - 1 > 0 && pantsCnt[i - 1] == 0) {
                    pantsCnt[i - 1] = 1;
                    pantsCnt[i] -= 1;
                } else if (i + 1 <= n && pantsCnt[i + 1] == 0) {
                    pantsCnt[i + 1] = 1;
                    pantsCnt[i] -= 1;
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int p = pantsCnt[i];
            if (p >= 1) answer++;
        }
        return answer;
    }
}