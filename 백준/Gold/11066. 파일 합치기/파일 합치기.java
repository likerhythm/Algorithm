import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr, sum;
    static int[][] dp; // dp[i][j] = i번 파일부터 j번 파일까지 합치는 데 필요한 최소 비용
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sum = new int[N];
            sum[0] = arr[0];
            for (int i = 1; i < N; i++) {
                sum[i] = sum[i - 1] + arr[i];
            }
            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], -1);
            }

            answer = setDp(0, N - 1);
            System.out.println(answer);
        }
    }

    private static int setDp(int start, int end) {
        if (dp[start][end] >= 0) {
            return dp[start][end];
        }

        if (start == end) {
            return dp[start][end] = 0;
        }

        int subsum = sum[end] - (start > 0 ? sum[start - 1] : 0);
        int min = Integer.MAX_VALUE;
        for (int mid = start; mid < end; mid++) {
            int tmp = setDp(start, mid) + setDp(mid + 1, end) + subsum;
            min = Math.min(min, tmp);
        }

        return dp[start][end] = min;
    }
}
