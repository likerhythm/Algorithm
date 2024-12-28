import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int K;
    static int[] Ws;
    static int[] Vs;
    static int[][] dp;

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = bf.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);

        Ws = new int[N + 1];
        Vs = new int[N + 1];

        for (int i=1; i<N+1; i++) {
            split = bf.readLine().split(" ");
            Ws[i] = Integer.parseInt(split[0]);
            Vs[i] = Integer.parseInt(split[1]);
        }

        dp = new int[N + 1][K + 1];
        for (int i=0; i<K+1; i++) {
            dp[0][i] = 0;
        }

        for (int i=0; i<N+1; i++) {
            dp[i][0] = 0;
        }

        for (int k=1; k<K+1; k++) {
            for (int n=1; n<N+1; n++) {
                int w = Ws[n];
                int v = Vs[n];

                if (w > k) {
                    dp[n][k] = dp[n - 1][k];
                } else {
                    dp[n][k] = Math.max(dp[n - 1][k - w] + v, dp[n - 1][k]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
