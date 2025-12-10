import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
        int[] weights = new int[N];
        int[] values = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            int w = weights[i];
            int v = values[i];
            for (int k = K; k >= w; k--) { // 무게 w를 추가해서 무게 k를 만들 때
                dp[k] = Math.max(dp[k], dp[k - w] + v);
            }
        }

        System.out.println(dp[K]);
    }
}
