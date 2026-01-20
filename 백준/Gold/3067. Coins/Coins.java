import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] coins;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            M = Integer.parseInt(br.readLine());

            dp = new long[M + 1];

            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                for (int j = coins[i]; j <= M; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }

            System.out.println(dp[M]);
        }
    }
}
