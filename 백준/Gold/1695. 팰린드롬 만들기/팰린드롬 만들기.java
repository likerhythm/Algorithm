import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(setDp(0, N - 1));
    }

    private static int setDp(int left, int right) {
        if (left >= right) return 0;

        if (dp[left][right] != -1) return dp[left][right];

        if (arr[left] == arr[right]) dp[left][right] = setDp(left + 1, right - 1);
        else dp[left][right] = Math.min(
                setDp(left, right - 1), // [left, right] 구간의 왼쪽 끝에 arr[right] 삽입
                setDp(left + 1, right))  // [left, right] 구간의 오른쪽 끝에 arr[left] 삽입
                + 1;

        return dp[left][right];
    }
}
