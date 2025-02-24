import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] costs;
    static int[][] dp; // dp[i][0] : i번째 집을 빨간색으로 칠했을 때 최소 비용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        costs = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        for (int lastC = 0; lastC < 3; lastC++) { // 첫 번째 집 색상
            for (int startC = 0; startC < 3; startC++) { // 마지막 집 색상
                if (lastC == startC) {
                    dp[0][startC] = Integer.MAX_VALUE;
                    continue;
                }
                dp[0][startC] = costs[0][startC];
            }

            for (int i = 1; i < N - 1; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
            }

            for (int i = 0; i < 3; i++) {
                if (lastC == i) {
                    continue;
                }
                answer = Math.min(dp[N - 2][i] + costs[N - 1][lastC], answer);
            }
//            System.out.println(Arrays.deepToString(dp));
        }
        System.out.println(answer);
    }
}
