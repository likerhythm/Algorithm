class Solution {
    
    static final int SPACE = 0;
    static final int RED_START = 1;
    static final int BLUE_START = 2;
    static final int RED_GOAL = 3;
    static final int BLUE_GOAL = 4;
    static final int WALL = 5;
    
    static int N, M;
    static boolean[][] redVisited;
    static boolean[][] blueVisited;
    static int answer = 987654321;
    static int[][] maze;
    
    public int solution(int[][] maz) {
        maze = maz;
        N = maze.length;
        M = maze[0].length;
        
        redVisited = new boolean[N][M];
        blueVisited = new boolean[N][M];
        
        int redStartN = 0, redStartM = 0;
        int blueStartN = 0, blueStartM = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (maze[n][m] == RED_START) {
                    redStartN = n;
                    redStartM = m;
                } else if (maze[n][m] == BLUE_START) {
                    blueStartN = n;
                    blueStartM = m;
                }
            }
        }
        
        redVisited[redStartN][redStartM] = true;
        blueVisited[blueStartN][blueStartM] = true;
        move(new int[] {redStartN, redStartM}, new int[] {blueStartN, blueStartM}, 0);
        
        if (answer == 987654321) answer = 0;
        return answer;
    }
    
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    
    static int move(int[] redPos, int[] bluePos, int count) {
        int rn = redPos[0];
        int rm = redPos[1];
        int bn = bluePos[0];
        int bm = bluePos[1];
        
        if (maze[rn][rm] == RED_GOAL && maze[bn][bm] == BLUE_GOAL) { // 두 수레가 모두 도착한 경우
            return count;
        }
        
        for (int i = 0; i < 4; i++) {
            int nrn, nrm;
            if (maze[rn][rm] == RED_GOAL) { // 빨간 수레가 도착한 경우
                nrn = rn;
                nrm = rm;
            } else {
                nrn = rn + dns[i];
                nrm = rm + dms[i];
                if (!inRange(nrn, nrm)) continue; // 격자를 벗어나는 경우
                if (maze[nrn][nrm] == WALL) continue; // 벽인 경우
                if (redVisited[nrn][nrm]) continue; // 이미 방문한 경우
            }
            redVisited[nrn][nrm] = true;
            for (int j = 0; j < 4; j++) {
                int nbn, nbm;
                if (maze[bn][bm] == BLUE_GOAL) { // 파란 수레가 도착한 경우
                    nbn = bn;
                    nbm = bm;
                } else {
                    nbn = bn + dns[j];
                    nbm = bm + dms[j];
                    if (!inRange(nbn, nbm)) continue; // 격자를 벗어나는 경우
                    if (maze[nbn][nbm] == WALL) continue; // 벽인 경우
                    if (blueVisited[nbn][nbm]) continue; // 이미 방문한 경우
                    if (nbn == rn && nbm == rm && nrn == bn && nrm == bm) continue; // 두 수레가 자리를 바꾸는 경우
                }
                if (nbn == nrn && nbm == nrm) continue; // 두 수레가 같은 칸에 위치한 경우
                blueVisited[nbn][nbm] = true;
                answer = Math.min(answer, move(new int[] {nrn, nrm}, new int[] {nbn, nbm}, count + 1));
                blueVisited[nbn][nbm] = false;
            }
            redVisited[nrn][nrm] = false;
        }
        
        return answer;
    }
    
    static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}