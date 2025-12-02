import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N; // 전체 객차 수
    static int M; // 기관차가 끌 수 있는 최대 객차 수
    static int[] arr;
    static int[][] dp; // dp[i][j]: i개의 기관차를 사용했을 때 j번째 객차까지 고려한 경우 운송 가능한 최대 손님 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());

        dp = new int[4][N + 1];
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= N; j++) {
                if (j < M) continue;
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + (sum[j] - sum[j - M]));
            }
        }

        System.out.println(dp[3][N]);
    }
}
