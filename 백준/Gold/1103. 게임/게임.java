import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visited; // 현재 DFS 경로에 포함된 칸 추적
    static final int INF = 987654321;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean infinite = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        board = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            Arrays.fill(dp[i], -1);
        }

        int answer = dfs(0, 0);
        System.out.println(infinite ? -1 : answer);
    }

    static int dfs(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M || board[r][c] == 'H') {
            return 0;
        }

        if (visited[r][c]) { // 현재 경로에서 다시 방문 → 사이클
            infinite = true;
            return 0;
        }

        if (dp[r][c] != -1) { // 이미 계산된 경우
            return dp[r][c];
        }

        visited[r][c] = true;
        int move = board[r][c] - '0';
        int max = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i] * move;
            int nc = c + dc[i] * move;
            max = Math.max(max, dfs(nr, nc) + 1);
            if (infinite) break; // 사이클 발견 시 더 이상 진행할 필요 없음
        }

        visited[r][c] = false; // 경로에서 제거
        return dp[r][c] = max;
    }
}
