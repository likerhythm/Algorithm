import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int T; // 지폐 금액
    static int K; // 동전 가짓수
    static Coin[] coins;

    static class Coin {
        int value;
        int cnt;

        public Coin(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "[" + value + ", " + cnt + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        coins = new Coin[K + 1];
        for (int i = 1; i <= K ; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            coins[i] = new Coin(split[0], split[1]);
        }

        int[][] dp = new int[T + 1][K + 1];

        for (int coinIdx = 1; coinIdx <= K; coinIdx++) {
            Coin coin = coins[coinIdx];
            dp[0][coinIdx - 1] = 1;
            for (int cnt = 1; cnt <= coin.cnt; cnt++) {
                int sum = coin.value * cnt;
                for (int total = sum; total <= T; total++) {
                    dp[total][coinIdx] += dp[total - sum][coinIdx - 1];
                }
            }

            for (int t = 1; t <= T; t++) {
                dp[t][coinIdx] += dp[t][coinIdx - 1];
            }
        }

        System.out.println(dp[T][K]);
    }
}
