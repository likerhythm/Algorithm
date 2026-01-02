import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][][] dp; // dp[i][j][k]: i번째 날에 지각이 j번, 연속된 결석이 k번인 경우의 수
    static int mod = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2][3];
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for (int day = 2; day <= N; day++) {
            dp[day][0][0] = (dp[day - 1][0][0] + dp[day - 1][0][1] + dp[day - 1][0][2]) % mod;
            dp[day][0][1] = dp[day - 1][0][0];
            dp[day][0][2] = dp[day - 1][0][1];

            dp[day][1][0] = (dp[day - 1][0][0] + dp[day - 1][0][1] + dp[day - 1][0][2] + dp[day - 1][1][0] + dp[day - 1][1][1] + dp[day - 1][1][2]) % mod;


            dp[day][1][1] = dp[day - 1][1][0];
            dp[day][1][2] = dp[day - 1][1][1];
        }

        int answer = 0;
        for (int late = 0; late <= 1; late++) {
            for (int abs = 0; abs <= 2; abs++) {
                answer += dp[N][late][abs];
                answer %= mod;
            }
        }

        System.out.println(answer);
    }
}
