import java.util.*;

class Solution {
    
    static int answer = 987654321;
    static int[] picks;
    static String[] minerals;
    static int pickCnt;
    
    public int solution(int[] p, String[] m) {
        picks = p;
        minerals = m;
        pickCnt = 0;
        for (int v : picks) {
            pickCnt += v;
        }
        
        int[] pickOrder = new int[pickCnt];
        setPicks(0, pickOrder);
        
        return answer;
    }
    
    void setPicks(int cnt, int[] pickOrder) {
        if (cnt == pickCnt) {
            int cost = calcCost(pickOrder);
            // System.out.println(Arrays.toString(pickOrder) + " cost = " + cost);
            answer = Math.min(answer, cost);
            return;
        }
        
        for (int i = 0; i <= 2; i++) {
            if (picks[i] == 0) continue;
            pickOrder[cnt] = i;
            picks[i]--;
            setPicks(cnt + 1, pickOrder);
            picks[i]++;
        }
    }
    
    int calcCost(int[] pickOrder) {
        int pickIdx = 0;
        int count = 0;
        int cost = 0;
        for (String m : minerals) {
            if (pickOrder[pickIdx] == 0) { // 다이아몬드 곡괭이
                cost += 1;
            } else if (pickOrder[pickIdx] == 1) { // 철 곡괭이
                if (m.equals("diamond")) cost += 5;
                else cost += 1;
            } else { // 돌 곡괭이
                if (m.equals("diamond")) cost += 25;
                else if (m.equals("iron")) cost += 5;
                else cost += 1;
            }
            
            count++;
            if (count == 5) {
                pickIdx++;
                count = 0;
            }
            
            if (pickIdx >= pickCnt) break;
        }
        
        return cost;
    }
}