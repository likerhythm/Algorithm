import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp; // dp[i][j]: i번째 원소까지 고려하고 구간 j개를 선택했을 때 최대 합
    static boolean[][] visited;
    static int[] arr;
    static int MIN = -987654321;
    static int[] subsum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        subsum = new int[N + 1];
        for (int[] d : dp) {
            Arrays.fill(d, MIN);
        }
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            subsum[i] = subsum[i - 1] + arr[i - 1];
        }

        setDp(N, M);
//        printDp();
        System.out.println(dp[N][M]);
    }

    private static void printDp() {
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        System.out.println();
    }

    private static int setDp(int end, int count) {
        if (count == 0) return 0;
        if (end < 0) return MIN;

        if (visited[end][count]) return dp[end][count];
        visited[end][count] = true;
        dp[end][count] = setDp(end - 1, count);
        for (int i = end; i > 0; i--) {
            dp[end][count] = Math.max(dp[end][count], setDp(i - 2, count - 1) + subsum[end] - subsum[i - 1]);
        }

        return dp[end][count];
    }
}
