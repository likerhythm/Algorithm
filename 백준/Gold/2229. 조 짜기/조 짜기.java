import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int minVal = scores[i];
            int maxVal = scores[i];

            for (int j = i; j > 0; j--) {
                minVal = Math.min(minVal, scores[j]);
                maxVal = Math.max(maxVal, scores[j]);

                dp[i] = Math.max(dp[i], dp[j - 1] + (maxVal - minVal));
            }
        }

        System.out.println(dp[N]);
    }
}
