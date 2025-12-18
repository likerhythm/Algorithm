import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] arr;
    static int[][] dp;
    static final int CAN_OUT = 1;
    static final int CANNOT_OUT = 0;
    static final int NOT_VISITED = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], NOT_VISITED);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] == NOT_VISITED) {
                    setDp(i, j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] == CAN_OUT) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static int setDp(int n, int m) {
        if (dp[n][m] != NOT_VISITED) {
            return dp[n][m];
        }

        dp[n][m] = CANNOT_OUT;
        int[] next = getNext(n, m);
        int nn = next[0];
        int nm = next[1];

        if (!inRange(nn, nm)) { // 미로를 벗어나는 경우
            return dp[n][m] = CAN_OUT;
        }

        return dp[n][m] = setDp(nn, nm);
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    private static int[] getNext(int n, int m) {
        if (arr[n][m] == 'U') {
            return new int[] {n - 1, m};
        }
        if (arr[n][m] == 'R') {
            return new int[] {n, m + 1};
        }
        if (arr[n][m] == 'D') {
            return new int[] {n + 1, m};
        }
        if (arr[n][m] == 'L') {
            return new int[] {n, m - 1};
        }
        return new int[] {};
    }
}
