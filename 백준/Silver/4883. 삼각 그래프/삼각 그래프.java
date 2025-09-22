import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int t = 1;

        while (N != 0) {
            int[][] arr = new int[N][3];
            int[][] dp = new int[N][3];

            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            dp[0][0] = 0;
            dp[0][1] = arr[0][1];
            dp[0][2] = arr[0][1] + arr[0][2];
            dp[1][0] = arr[0][1] + arr[1][0];
            dp[1][1] = Math.min(Math.min(dp[0][1], dp[1][0]), dp[0][2]) + arr[1][1];
            dp[1][2] = Math.min(Math.min(dp[1][1], dp[0][1]), dp[0][2]) + arr[1][2];

            for (int i = 2; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][0];
                dp[i][1] = Math.min(Math.min(dp[i][0], dp[i - 1][0]), Math.min(dp[i - 1][1], dp[i - 1][2])) + arr[i][1];
                dp[i][2] = Math.min(Math.min(dp[i][1], dp[i - 1][1]), dp[i - 1][2]) + arr[i][2];
            }

            System.out.println(t + ". " + dp[N - 1][1]);

            N = Integer.parseInt(br.readLine());
            t++;
        }
    }
}
