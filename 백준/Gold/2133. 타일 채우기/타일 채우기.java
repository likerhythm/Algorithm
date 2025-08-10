import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        dp = new int[N + 1];
        dp[2] = 3;
        for (int i = 4; i <= N; i += 2) {
            dp[i] = 2;
        }

        for (int i = 4; i <= N; i += 2) {
            for (int j = 2; j < i; j += 2) {
                int diff = i - j;
                if (diff == 2) {
                    dp[i] += dp[j] * 3;
                    continue;
                }

                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
