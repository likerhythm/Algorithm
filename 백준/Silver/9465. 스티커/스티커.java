import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t=0; t<tc; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] scores = new int[2][N];
            for (int i=0; i<2; i++) {
                scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            if (N == 1) {
                System.out.println(Math.max(scores[0][N - 1], scores[1][N - 1]));
                continue;
            }
            int[][] dp = new int[2][N];
            dp[0][0] = scores[0][0];
            dp[1][0] = scores[1][0];
            dp[0][1] = scores[1][0] + scores[0][1];
            dp[1][1] = scores[0][0] + scores[1][1];

            for (int i=2; i<N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], Math.max(dp[0][i - 2], dp[1][i - 2])) + scores[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], Math.max(dp[0][i - 2], dp[1][i - 2])) + scores[1][i];
            }

            System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
        }
    }
}
