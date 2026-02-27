import java.util.*;

class Solution {
    
    static int N, M;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        Result result = backtracking(board, aloc[0], aloc[1], bloc[0], bloc[1]);
        
        return result.step;
    }
    
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    
    static class Result {
        boolean win;
        int step;
        
        Result(boolean win, int step) {
            this.win = win;
            this.step = step;
        }
    }
    
    static Result backtracking(int[][] board, int n1, int m1, int n2, int m2) {
        if (board[n1][m1] == 0) return new Result(false, 0); // 다른 플레이어에 의해 밟고 있던 발판이 없어진 경우
        
        boolean canWin = false;
        int minWinCount = 987654321;
        int maxLoseCount = 0;
        int moveCount = 0;
        
        for (int i = 0; i < 4; i++) {
            int nn = n1 + dns[i];
            int nm = m1 + dms[i];
            
            if (!inRange(nn, nm)) continue;
            if (board[nn][nm] == 0) continue;
            
            board[n1][m1] = 0;
            Result nextTurn = backtracking(board, n2, m2, nn, nm);
            board[n1][m1] = 1;
            moveCount++;
            
            if (!nextTurn.win) { // 다음 턴에 상대가 지는 경우. 즉 내가 이기는 경우
                canWin = true;
                minWinCount = Math.min(minWinCount, nextTurn.step); // 이기는 경우 중 최소 이동 횟수
            } else { // 다음 턴에 상대가 이기는 경우. 즉 내가 지는 경우
                maxLoseCount = Math.max(maxLoseCount, nextTurn.step); // 지는 경우 중 최대 이동 횟수
            }
        }
        
        if (moveCount == 0) {
            return new Result(false, 0);
        }
        
        if (canWin) {
            return new Result(true, minWinCount + 1);
        } else {
            return new Result(false, maxLoseCount + 1);
        }
    }
    
    static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}