import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[N + 1];

            dp[0] = 1;
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < N; j++) {
                    if (j + i > N) continue;
                    dp[j + i] += dp[j];
                }
            }
            System.out.println(dp[N]);
        }
    }
}
