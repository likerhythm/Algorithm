import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, K;
    static int[] dp;
    static int[] coins;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = s[0];
        K = s[1];

        coins = new int[N];
        for (int i=0; i<N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        dp = new int[K + 1];
        Arrays.fill(dp, 987654321);
        for (int i=0; i<N; i++) {
            if (coins[i] > K) {
                continue;
            }
            dp[coins[i]] = 1;
        }

        for (int i=1; i<K+1; i++) {
            for (int coin : coins) { // coins 오름차순 정렬 상태
                if (coin > K) {
                    continue;
                }

                if (dp[i] == 987654321) {
                    continue;
                }

                if (i + coin >= K + 1) {
                    break;
                }

                dp[i + coin] = Math.min(dp[i + coin], dp[i] + 1);
            }
        }

        if (dp[K] == 987654321) {
            System.out.println(-1);
            return;
        }

        System.out.println(dp[K]);
    }
}
