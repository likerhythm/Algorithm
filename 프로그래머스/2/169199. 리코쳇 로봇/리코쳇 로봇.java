import java.util.*;

class Solution {
    
    int N, M;
    char[][] board;
    boolean[][] visited;
    int[] startPos;
    int[] endPos;
    
    public int solution(String[] b) {
        N = b.length;
        M = b[0].length();
        board = new char[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            board[i] = b[i].toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    startPos = new int[] {i, j};
                } else if (board[i][j] == 'G') {
                    endPos = new int[] {i, j};
                }
            }
        }
        
        int answer = bfs();
        return answer;
    }
    
    int[] dns = {-1, 0, 1, 0};
    int[] dms = {0, 1, 0, -1};
    
    int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {startPos[0], startPos[1], 0});
        visited[startPos[0]][startPos[1]] = true;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int n = now[0];
            int m = now[1];
            int count = now[2];
            // System.out.println("[" + n + ", " + m + "] " + count);
            if (board[n][m] == 'G') return count;
            
            int moveCnt = 0;
            for (int i = 0; i < 4; i++) {
                int[] nextPos = searchNextPos(n, m, i);
                int nn = nextPos[0];
                int nm = nextPos[1];
                if (visited[nn][nm]) continue;
                q.add(new int[] {nn, nm, count + 1});
                visited[nn][nm] = true;
            }
        }
        
        return -1;
    }
    
    int[] searchNextPos(int n, int m, int dir) {
        int nn = n;
        int nm = m;
        while (inRange(nn + dns[dir], nm + dms[dir]) && board[nn + dns[dir]][nm + dms[dir]] != 'D') {
            nn += dns[dir];
            nm += dms[dir];
        }
        
        return new int[] {nn, nm};
    }
    
    boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}