import java.util.*;

class Solution {
    
    static int arrowCnt;
    static int[] answer;
    static int[] lionShoot;
    static int[] apichShoot;
    static int maxDiff;
    
    static public int[] solution(int n, int[] info) {
        lionShoot = new int[11];
        Arrays.fill(lionShoot, 0);
        apichShoot = info;
        arrowCnt = n;
        
        f(0, 10);
        
        if (maxDiff == 0) {
            return new int[] {-1};
        }
        return answer;
    }
    
    static private void f(int cnt, int scoreNum) {
        if (cnt == arrowCnt) {
            int diff = calcDiff();
            if (maxDiff < diff) {
                maxDiff = diff;
                answer = lionShoot.clone();
                System.out.println(Arrays.toString(answer));
            }
            return;
        }
        
        for (int i=scoreNum; i>=0; i--) {
            lionShoot[i]++;
            f(cnt + 1, i);
            lionShoot[i]--;
        }
    }
    
    static private int calcDiff() {
        int diff = 0;
        for (int i=0; i<11; i++) {
            if (apichShoot[i] == 0 && lionShoot[i] == 0) {
                continue;
            }
            if (apichShoot[i] >= lionShoot[i]) {
                diff -= (10 - i);
            } else {
                diff += (10 - i);
            }
        }
        if (diff <= 0) {
            return 0;
        }
        return diff;
    }
}