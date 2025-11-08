import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int m = 0; m < M; m++) {
            dp[0][m] = arr[0][m];
        }
        for (int n = 0; n < N; n++) {
            dp[n][0] = arr[n][0];
        }

        for (int n = 1; n < N; n++) {
            for (int m = 1; m < M; m++) {
                if (arr[n][m] == 0) continue;
                if (dp[n - 1][m - 1] == 0) {
                    dp[n][m] = 1;
                    continue;
                }
                if(dp[n - 1][m] == 0) {
                    dp[n][m] = 1;
                    continue;
                }
                if (dp[n][m - 1] == 0) {
                    dp[n][m] = 1;
                    continue;
                }
                dp[n][m] = Math.min(dp[n][m - 1], Math.min(dp[n - 1][m], dp[n - 1][m - 1])) + 1;
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer * answer);
    }
}
