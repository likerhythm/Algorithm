import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, S, M;
    static int[] V;
    static boolean[][] dp; // dp[i][0] = i번째 곡에서 볼륨을 내렸을 때 최대 볼륨, dp[i][1] = i번째 곡에서 볼륨을 올렸을 때 최대 볼륨

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        S = split[1];
        M = split[2];

        V = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (!dp[i - 1][j]) continue;

                int up = j + V[i - 1];
                int down = j - V[i - 1];
                if (up <= M) dp[i][up] = true;
                if (down >= 0) dp[i][down] = true;
            }
        }

        int answer = -1;
        for (int i = 0; i <= M; i++) {
            if (dp[N][i]) answer = Math.max(answer, i);
        }

        System.out.println(answer);
    }
}
