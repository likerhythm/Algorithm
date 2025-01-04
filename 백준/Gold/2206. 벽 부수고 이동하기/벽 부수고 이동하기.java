import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int M;
    static int[][] board;
    static int[][][] visited; // 벽을 부수지 않고 도달한 경우 최소 거리, 벽을 부수고 도달한 경우 최소 거리
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int answer = Integer.MAX_VALUE - 1;

    public static void main(String[] args) throws IOException {
        String[] split = bf.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        board = new int[N][M];
        visited = new int[N][M][2];

        for (int i=0; i<N; i++) {
            split = bf.readLine().split("");
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(split[j]);
                visited[i][j][0] = Integer.MAX_VALUE - 1;
                visited[i][j][1] = Integer.MAX_VALUE - 1;
            }
        }

        visited[0][0][0] = 1; // 벽을 부수지 않고 도달한 시작 지점까지의 거리
        bfs(0, 0);

        if (visited[N - 1][M - 1][0] == Integer.MAX_VALUE - 1 && visited[N - 1][M - 1][1] == Integer.MAX_VALUE - 1) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(visited[N - 1][M - 1][0], visited[N - 1][M - 1][1]));
        }
    }

    private static void bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];

            for (int i=0; i<4; i++) {
                int nxtN = n + dns[i];
                int nxtM = m + dms[i];

                if (inRange(nxtN, nxtM)) {
                    if (board[nxtN][nxtM] == 1) { // 다음이 벽인 경우
                        if (visited[nxtN][nxtM][1] > visited[n][m][0] + 1) {
                            visited[nxtN][nxtM][1] = visited[n][m][0] + 1;
                            q.add(new int[] {nxtN, nxtM});
                        }
                    } else { // 다음이 벽이 아닌 경우
                        if (visited[nxtN][nxtM][0] > visited[n][m][0] + 1 || visited[nxtN][nxtM][1] > visited[n][m][1] + 1) {
                            if (visited[nxtN][nxtM][0] > visited[n][m][0] + 1) {
                                visited[nxtN][nxtM][0] = visited[n][m][0] + 1;
                            }
                            if (visited[nxtN][nxtM][1] > visited[n][m][1] + 1) {
                                visited[nxtN][nxtM][1] = visited[n][m][1] + 1;
                            }
                            q.add(new int[] {nxtN, nxtM});
                        }
                    }
                }
            }
        }
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
