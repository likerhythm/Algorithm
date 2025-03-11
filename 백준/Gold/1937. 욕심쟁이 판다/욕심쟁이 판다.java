import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                answer = Math.max(updateDp(i, j), answer);
            }
        }

        System.out.println(answer);
    }

    private static int updateDp(int x, int y) {
        if (dp[x][y] > 0) {
            return dp[x][y];
        }
        
        dp[x][y] = 1;
        for (int i=0; i<4; i++) {
            int nx = x + dxs[i];
            int ny = y + dys[i];
            if (!inRange(nx, ny)) {
                continue;
            }

            if (map[nx][ny] <= map[x][y]) {
                continue;
            }

            dp[x][y] = Math.max(updateDp(nx, ny) + 1, dp[x][y]);
        }

        return dp[x][y];
    }

    private static boolean inRange(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}
