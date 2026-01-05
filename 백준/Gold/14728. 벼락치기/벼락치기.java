import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        int[] scores = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            times[i] = time;
            scores[i] = score;
        }

        int[] dp = new int[T + 1];

        for (int i = 0; i < N; i++) {
            int time = times[i];
            int score = scores[i];
            for (int j = T; j >= time; j--) {
                dp[j] = Math.max(dp[j], score + dp[j - time]);
            }
        }

        System.out.println(dp[T]);
    }
}
