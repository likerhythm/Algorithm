import java.util.*;

class Solution {
    
    int roads[][] = new int[101][101]; // 0이면 빈 공간, 1이면 길, 2면 사각형 내부
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rec : rectangle) {
            int x1 = rec[0] * 2, y1 = rec[1] * 2; // 좌하단
            int x2 = rec[2] * 2, y2 = rec[3] * 2; // 우상단
            for (int y = y1; y <= y2; y++) {
                if (roads[x1][y] == 0) roads[x1][y] = 1;
                if (roads[x2][y] == 0) roads[x2][y] = 1;
            }
            for (int x = x1; x <= x2; x++) {
                if (roads[x][y1] == 0) roads[x][y1] = 1;
                if (roads[x][y2] == 0) roads[x][y2] = 1;
            }
            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    roads[x][y] = 2;
                }
            }
        }
        
        int answer = 0;
        int[] dxs = {-1, 0, 1, 0};
        int[] dys = {0, 1, 0, -1};
        boolean[][] visited = new boolean[101][101];
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {characterX * 2, characterY * 2, 0});
        visited[characterX * 2][characterY * 2] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int l = now[2];
            if (x == itemX * 2 && y == itemY * 2) {
                answer = l;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                if (!inRange(nx, ny)) continue;
                if (roads[nx][ny] != 1) continue;
                if (visited[nx][ny]) continue;
                q.add(new int[] {nx, ny, l + 1});
                visited[nx][ny] = true;
            }
        }
        
        return answer / 2;
    }
    
    boolean inRange(int x, int y) {
        return 0 <= x && x < 101 && 0 <= y && y < 101;
    }
}