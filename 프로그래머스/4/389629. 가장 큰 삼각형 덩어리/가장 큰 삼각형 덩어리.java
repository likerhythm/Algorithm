import java.util.*;

class Solution {
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    static int N, M;
    static int[][] grids;
    static boolean[][][] visited;
    
    // 슬라이딩 윈도우 성능 최적화를 위한 전역 배열
    static int[][] lastSeenId;
    static int[][] lastSeenIdx;
    static int pathIdCounter = 0;

    public int solution(int[][] grid) {
        grids = grid;
        N = grid.length;
        M = grid[0].length;
        
        visited = new boolean[N][M][4];
        lastSeenId = new int[N][M];
        lastSeenIdx = new int[N][M];
        
        int answer = 0;
        
        // 격자 밖에서 들어오는 열린 경로 먼저 모두 탐색
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (!visited[n][m][dir] && isBoundary(n, m, dir)) {
                        answer = Math.max(answer, trace(n, m, dir));
                    }
                }
            }
        }
        
        // 내부에서 닫힌 사이클 탐색
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (!visited[n][m][dir]) {
                        answer = Math.max(answer, trace(n, m, dir));
                    }
                }
            }
        }
        
        return answer;
    }

    static int trace(int n, int m, int dir) {
        List<int[]> path = new ArrayList<>();
        
        // 경로 추적 및 리스트에 좌표 저장
        while (inRange(n, m) && !visited[n][m][dir]) {
            visited[n][m][dir] = true;
            path.add(new int[]{n, m});
            
            int[] next = getNext(grids[n][m], dir);
            n += next[0];
            m += next[1];
            dir = next[2];
        }
        
        // 탐색이 끝난 후 여전히 격자 내부라면, 사이클이 발생하여 원점으로 돌아온 것임
        // 윈도우가 경계를 넘어갈 수 있도록 리스트 복제 (마지막 중복점 제외)
        if (inRange(n, m)) {
            int size = path.size();
            for (int i = 0; i < size - 1; i++) {
                path.add(path.get(i));
            }
        }
        
        // 투 포인터(슬라이딩 윈도우)를 활용해 중복 없는 최대 길이 도출
        pathIdCounter++;
        int maxLen = 0;
        int start = 0;
        
        for (int i = 0; i < path.size(); i++) {
            int r = path.get(i)[0];
            int c = path.get(i)[1];
            
            // 현재 칸이 윈도우 내에 이미 존재한다면, 시작점을 중복 발생 다음 칸으로 이동
            if (lastSeenId[r][c] == pathIdCounter && lastSeenIdx[r][c] >= start) {
                start = lastSeenIdx[r][c] + 1;
            }
            
            lastSeenId[r][c] = pathIdCounter;
            lastSeenIdx[r][c] = i;
            
            maxLen = Math.max(maxLen, i - start + 1);
        }
        
        return maxLen;
    }

    static boolean isBoundary(int n, int m, int dir) {
        if (dir == 0 && n == 0) return true;
        if (dir == 1 && m == M - 1) return true;
        if (dir == 2 && n == N - 1) return true;
        if (dir == 3 && m == 0) return true;
        return false;
    }

    static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    static public int[] getNext(int value, int inDirect) {
        int nextDir = 0;
        if (value == -1) {
            if (inDirect == 0) nextDir = 1;
            else if (inDirect == 1) nextDir = 0;
            else if (inDirect == 2) nextDir = 3;
            else nextDir = 2;
        } else if (value == 1) {
            if (inDirect == 0) nextDir = 3;
            else if (inDirect == 1) nextDir = 2;
            else if (inDirect == 2) nextDir = 1;
            else nextDir = 0;
        }
        
        int nextInDir = (nextDir + 2) % 4;
        return new int[] {dns[nextDir], dms[nextDir], nextInDir};
    }
}