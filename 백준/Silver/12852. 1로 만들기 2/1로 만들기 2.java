import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;

        for (int i = N; i >= 2; i--) {
            if (dp[i][0] == Integer.MAX_VALUE) continue;
            if (i % 2 == 0) {
                if (dp[i / 2][0] > dp[i][0] + 1) {
                    dp[i / 2][0] = dp[i][0] + 1;
                    dp[i / 2][1] = i;
                }
            }
            if (i % 3 == 0) {
                if (dp[i / 3][0] > dp[i][0] + 1) {
                    dp[i / 3][0] = dp[i][0] + 1;
                    dp[i / 3][1] = i;
                }
            }
            if (dp[i - 1][0] > dp[i][0] + 1) {
                dp[i - 1][0] = dp[i][0] + 1;
                dp[i - 1][1] = i;
            }
        }

        System.out.println(dp[1][0]);
        int num = 1;
        List<Integer> answer = new ArrayList<>();
        while (true) {
            answer.add(num);
            num = dp[num][1];
            if (num == Integer.MAX_VALUE) break;
        }

        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }
}
