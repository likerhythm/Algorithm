import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[] isResort;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isResort = new boolean[N + 1];
        Arrays.fill(isResort, true);
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                isResort[Integer.parseInt(st.nextToken())] = false;
            }
        }
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        System.out.println(setDp(1, 0));
    }

    private static int setDp(int day, int coupon) {
        if (day > N) return 0;

        if (dp[day][coupon] != Integer.MAX_VALUE) return dp[day][coupon];

        if (!isResort[day]) {
            return dp[day][coupon] = setDp(day + 1, coupon);
        }

        dp[day][coupon] = Math.min(dp[day][coupon], setDp(day + 1, coupon) + 10000);
        dp[day][coupon] = Math.min(dp[day][coupon], setDp(day + 3, coupon + 1) + 25000);
        dp[day][coupon] = Math.min(dp[day][coupon], setDp(day + 5, coupon + 2) + 37000);
        if (coupon >= 3) {
            dp[day][coupon] = Math.min(dp[day][coupon], setDp(day + 1, coupon - 3));
        }
        return dp[day][coupon];
    }
}
