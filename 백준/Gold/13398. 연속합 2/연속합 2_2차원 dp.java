import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr;
    static int[][] dp; // dp[i][j] -> if j == 0: i번째 수까지 더할 때 수를 제거한 적이 없는 경우 최대합
    // if j == 1: i번째 수까지 더할 때 수를 제거한 적이 있는 경우 최대합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[N][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0;
        int answer = dp[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(arr[i], dp[i - 1][0] + arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(answer);
    }
}
