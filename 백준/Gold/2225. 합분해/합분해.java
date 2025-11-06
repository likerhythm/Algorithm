import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K; // >= 1
    static long[][] dp; // dp[n][k] = n을 숫자 k개 써서 만들 수 있는 경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][K + 1];
        Arrays.fill(dp[0], 1); // 0을 만들 수 있는 경우의 수는 모두 한 개

        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                for (int m = 0; m <= n; m++) {
                    dp[n][k] += dp[m][k - 1];
                    dp[n][k] %= 1_000_000_000;
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
