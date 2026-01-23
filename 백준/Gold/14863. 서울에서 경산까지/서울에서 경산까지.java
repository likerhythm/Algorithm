import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1];

        st = new StringTokenizer(br.readLine());
        int walkTime = Integer.parseInt(st.nextToken());
        int walkMoney = Integer.parseInt(st.nextToken());
        int bikeTime = Integer.parseInt(st.nextToken());
        int bikeMoney = Integer.parseInt(st.nextToken());

        if(walkTime <= K) dp[1][walkTime] = walkMoney;
        if(bikeTime <= K) dp[1][bikeTime] = Math.max(dp[1][bikeTime], bikeMoney);

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            walkTime = Integer.parseInt(st.nextToken());
            walkMoney = Integer.parseInt(st.nextToken());
            bikeTime = Integer.parseInt(st.nextToken());
            bikeMoney = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= K; j++) {
                if (dp[i - 1][j] == 0) continue;

                int nextTime = j + walkTime;
                if (nextTime <= K) {
                    dp[i][nextTime] = Math.max(dp[i][nextTime], dp[i - 1][j] + walkMoney);
                }

                nextTime = j + bikeTime;
                if (nextTime <= K) {
                    dp[i][nextTime] = Math.max(dp[i][nextTime], dp[i - 1][j] + bikeMoney);
                }
            }
        }

        int answer = 0;
        for (int j = 0; j <= K; j++) {
            answer = Math.max(answer, dp[N][j]);
        }

        System.out.println(answer);
    }
}