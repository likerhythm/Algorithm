import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] dp;
    static int[] leftCards;
    static int[] rightCards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        leftCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rightCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[N][N];

        System.out.println(setDp(0, 0));
    }

    private static int setDp(int left, int right) {
        if (left == N || right == N) return 0;

        if (dp[left][right] > 0) return dp[left][right];

        dp[left][right] = Math.max(dp[left][right], setDp(left + 1, right));
        dp[left][right] = Math.max(dp[left][right], setDp(left + 1, right + 1));
        if (leftCards[left] > rightCards[right]) dp[left][right] = Math.max(dp[left][right], setDp(left, right + 1) + rightCards[right]);

        return dp[left][right];
    }
}
