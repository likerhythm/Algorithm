import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] dp; // dp[i] : 카드 i개를 얻기 위해 지불해야 하는 최대 금액
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        String[] split = br.readLine().split(" ");

        for (int i=1; i<N+1; i++) {
            dp[i] = Integer.parseInt(split[i - 1]);
        }
        for (int i=2; i<N+1; i++) {
            for (int j=1; j<=i/2; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
            }
        }

        System.out.println(dp[N]);
    }
}
