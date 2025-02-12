import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// dp문제로 판단
// dp[i]가 가진 정보 : i킬로그램을 가져가기 위한 최소의 봉지 수
// dp 초기값 : dp[3] = 1, dp[5] = 1
// dp 점화식 : dp[i] = min(dp[i-3], dp[i-5]) + 1
// 점화식 조건 : dp[i-3], dp[i-5]의 값이 둘 중 하나는 존재해야 한다
public class Main {

    static int N;
    static int[] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, 987654321);

        if (N == 3) {
            System.out.println(1);
            return;
        }
        if (N == 4) {
            System.out.println(-1);
            return;
        }
        dp[3] = dp[5] = 1;

        for (int i=6; i<=N; i++) {
            if (dp[i - 3] == 987654321 && dp[i - 5] == 987654321) {
                continue;
            }
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }

        if (dp[N] == 987654321) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N]);
        }
    }
}
