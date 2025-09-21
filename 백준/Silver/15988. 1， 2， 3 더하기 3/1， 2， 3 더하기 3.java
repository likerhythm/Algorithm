import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int max = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] dp = new int[max + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= max; i++) {
            for (int j = 1; j <=3; j++) {
                dp[i] += dp[i - j] % 1_000_000_009;
                dp[i] %= 1_000_000_009;
            }
        }

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
