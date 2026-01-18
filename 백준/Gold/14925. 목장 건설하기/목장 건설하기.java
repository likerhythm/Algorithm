import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] == - 1) setDp(i, j);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }

//        printArr(dp);

        System.out.println(answer);
    }

    private static void printArr(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    private static int setDp(int n, int m) {
        if (n >= N || m >= M) return 0;

        if (dp[n][m] > -1) return dp[n][m];
        if (arr[n][m] > 0) return dp[n][m] = 0;

        dp[n][m] = 1;
        int l1 = setDp(n + 1, m);
        int l2 = setDp(n, m + 1);
        int l3 = setDp(n + 1, m + 1);
        int minLength = Math.min(l1, Math.min(l2, l3)) + 1;

        return dp[n][m] = minLength;
    }
}
