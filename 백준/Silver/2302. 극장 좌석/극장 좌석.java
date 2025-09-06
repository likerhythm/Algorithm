import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static boolean[] isStatic;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][3];
        isStatic = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            isStatic[Integer.parseInt(br.readLine())] = true;
        }

        if (isStatic[1] || N == 1) {
            dp[1][1] = 1;
        } else {
            dp[1][1] = 1;
            dp[1][2] = 1;
        }

        for (int i = 2; i <= N; i++) {
            if (isStatic[i]) {
                dp[i][0] = 0;
                dp[i][1] = dp[i - 1][0] + dp[i - 1][1];
                dp[i][2] = 0;
                continue;
            }

            dp[i][0] = dp[i - 1][2];
            dp[i][1] = dp[i - 1][0] + dp[i - 1][1];
            if (i == N) {
                dp[i][2] = 0;
                continue;
            }
            dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
        }

        System.out.println(Arrays.stream(dp[N]).sum());
    }
}
