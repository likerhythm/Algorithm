import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[1_000_001];
        int mod = 1_000_000_000;

        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            dp[i] = ((i - 1) * dp[i - 1] + (i - 1) * dp[i - 2]) % mod; // 새로운 사람과 주고받지 않는 경우 + 새로운 사람과 주고 받는 경우
        }

        System.out.println(dp[N] % mod);
    }
}
