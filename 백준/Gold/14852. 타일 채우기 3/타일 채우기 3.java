import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int mod = 1_000_000_007;
        long[] dp = new long[1_000_001];
        long[] subsum = new long[1_000_001];
        dp[1] = 2;
        dp[2] = 7;
        subsum[1] = 2;
        subsum[2] = 9;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] * 2) % mod + (dp[i - 2] * 3) % mod;
            dp[i] += (subsum[i - 3] * 2) % mod;
            dp[i] += 2;
            dp[i] = dp[i] % mod;

            subsum[i] = (subsum[i - 1] + dp[i]) % mod;
        }

        System.out.println(dp[N]);
    }
}
