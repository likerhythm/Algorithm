import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] dp = new int[41][2]; // dp[i][0] : fibonacci(i)를 호출할 때 0이 호출된 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        setDp();

        for (int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }

    private static void setDp() {
        dp[0][0] = 1; // 초기값
        dp[1][1] = 1; // 초기값

        for (int i=2; i<41; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }
    }
}
