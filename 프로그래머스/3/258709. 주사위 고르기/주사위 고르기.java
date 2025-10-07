import java.util.*;

class Solution {
    
    static int[][] dices;
    static boolean[] visited;
    static int maxWinCnt;
    static int[] answer;
    static int tmpIdx;
    
    public int[] solution(int[][] dice) {
        
        dices = dice;
        visited = new boolean[dices.length];
        
        answer = new int[dices.length / 2];
        backtracking(0, 0);
        
        return answer;
    }
    
    public void backtracking(int cnt, int start) {
        if (cnt == dices.length / 2) {
            // 확률 계산하기
            int winCnt = calcWinRate();
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                int idx = 0;
                for (int i = 0; i < dices.length; i++) {
                    if (visited[i]) {
                        answer[idx++] = i + 1;
                    }
                }
            }

            return;
        }
        
        for (int i = start; i < dices.length; i++) {
            visited[i] = true;
            backtracking(cnt + 1, i + 1);
            visited[i] = false;
        }
    }
    
    public int calcWinRate() {
        int maxIdx = (int) Math.pow(6, dices.length / 2);
        int[] aCases = new int[maxIdx];
        int[] bCases = new int[maxIdx];

        tmpIdx = 0;
        backtracking2(true, aCases, 0, 0);
        tmpIdx = 0;
        backtracking2(false, bCases, 0, 0);

        Arrays.sort(aCases);
        Arrays.sort(bCases);

        int win = 0;
        int bIdx = 0;

        for (int aSum : aCases) {
            while (bIdx < bCases.length && bCases[bIdx] < aSum) {
                bIdx++;
            }
            win += bIdx;
        }

        return win;
    }

    
    public void backtracking2(boolean flag, int[] cases, int diceIdx, int sum) {
        if (diceIdx == dices.length) {
            cases[tmpIdx++] = sum;
            return;
        }
        
        if (visited[diceIdx] != flag) {
            backtracking2(flag, cases, diceIdx + 1, sum);
        } else {
            for (int i = 0; i < 6; i++) {
                int num = dices[diceIdx][i];
                backtracking2(flag, cases, diceIdx + 1, sum + num);
            }
        }
    }
}