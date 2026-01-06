import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        System.out.println(setDp(N));
    }

    private static long setDp(int n) {
        if (n < 0) {
            return 0;
        }

        if (dp[n] >= 0) {
            return dp[n];
        }

        dp[n] = setDp(n - 1) + 1;

        for (int bef = 3; bef <= n - 1; bef++) {
            dp[n] = Math.max(dp[n], setDp(n - bef) * (bef - 1));
        }

        return dp[n];
    }
}
