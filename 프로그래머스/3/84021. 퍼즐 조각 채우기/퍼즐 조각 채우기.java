import java.util.*;

class Solution {
    
    int N;
    int[] drs = {-1, 0, 1, 0};
    int[] dcs = {0, 1, 0, -1};

    public int solution(int[][] gameBoard, int[][] table) {
        N = gameBoard.length;

        List<int[][]> holes = new ArrayList<>();   // 게임보드의 빈 칸(구멍) 모양들
        List<int[][]> pieces = new ArrayList<>();  // 테이블 위 조각 모양들

        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (gameBoard[i][j] == 0 && !visited[i][j])
                    holes.add(getShape(gameBoard, i, j, visited, 0));

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (table[i][j] == 1 && !visited[i][j])
                    pieces.add(getShape(table, i, j, visited, 1));

        boolean[] used = new boolean[pieces.size()];
        int answer = 0;

        for (int[][] hole : holes) {
            for (int k = 0; k < pieces.size(); k++) {
                if (used[k]) continue;
                if (matches(hole, pieces.get(k))) {
                    used[k] = true;
                    answer += countOnes(hole);
                    break;
                }
            }
        }
        return answer;
    }

    int[][] getShape(int[][] board, int r, int c, boolean[][] visited, int target) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        List<int[]> cells = new ArrayList<>();
        int minr = r, maxr = r, minc = c, maxc = c;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cells.add(cur);
            minr = Math.min(minr, cur[0]);
            maxr = Math.max(maxr, cur[0]);
            minc = Math.min(minc, cur[1]);
            maxc = Math.max(maxc, cur[1]);

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + drs[d], nc = cur[1] + dcs[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc] || board[nr][nc] != target) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        int[][] shape = new int[maxr - minr + 1][maxc - minc + 1];
        for (int[] cell : cells) shape[cell[0] - minr][cell[1] - minc] = 1;
        return shape;
    }

    int[][] rotate(int[][] a) {
        int rows = a.length, cols = a[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][rows - i - 1] = a[i][j];
        return result;
    }

    boolean sameShape(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) return false;
        for (int i = 0; i < a.length; i++)
            if (!Arrays.equals(a[i], b[i])) return false;
        return true;
    }

    boolean matches(int[][] hole, int[][] piece) {
        int[][] rotated = piece;
        for (int k = 0; k < 4; k++) {
            if (sameShape(hole, rotated)) return true;
            rotated = rotate(rotated);
        }
        return false;
    }

    int countOnes(int[][] shape) {
        int cnt = 0;
        for (int[] row : shape) for (int v : row) cnt += v;
        return cnt;
    }
}