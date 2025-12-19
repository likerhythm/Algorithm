import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static char[][] arr;
    static int[][][] dp;
    static String target;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        target = br.readLine();

        dp = new int[N][M][target.length()];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == target.charAt(0)) {
                    answer += setDp(i, j, 0);
                }
            }
        }

        System.out.println(answer);
    }

    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    private static int setDp(int n, int m, int nowIdx) {
        if (arr[n][m] != target.charAt(nowIdx)) { // 문자열 자체가 일치하지 않는 경우
            return dp[n][m][nowIdx] = 0;
        }

        if (nowIdx == target.length() - 1) {
            return dp[n][m][nowIdx] = 1;
        }

        if (dp[n][m][nowIdx] >= 0) {
            return dp[n][m][nowIdx];
        }

        dp[n][m][nowIdx] = 0;
        for (int i = 0; i < 4; i++) {
            for (int k = 1; k <= K; k++) {
                int nn = n + dns[i] * k;
                int nm = m + dms[i] * k;
                if (!inRange(nn, nm)) continue;
                dp[n][m][nowIdx] += setDp(nn, nm, nowIdx + 1);
            }
        }

        return dp[n][m][nowIdx];
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
