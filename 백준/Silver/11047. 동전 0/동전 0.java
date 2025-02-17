import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        int[] coins = new int[N];
        for (int i=0; i<N; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        int answer = 0;
        for (int i=N-1; i>=0; i--) {
            if (coins[i] <= K) {
                answer += K / coins[i];
                K -= coins[i] * (K / coins[i]);
            }
            if (K == 0) {
                break;
            }
        }

        System.out.println(answer);
    }
}
