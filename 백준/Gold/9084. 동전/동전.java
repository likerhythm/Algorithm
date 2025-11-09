import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(br.readLine());
            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            M = Integer.parseInt(br.readLine());

            dp = new int[M + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int make = 1; make <= M; make++) {
                    if (make - coin < 0) continue;
                    if (dp[make - coin] == 0) continue;
                    dp[make] += dp[make - coin];
                }
            }

//            System.out.println(Arrays.toString(dp));
            System.out.println(dp[M]);
        }
    }
}
