import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] arr;
    static long[][][] dp; // dp[i][j][k]: (i, j) 좌표에 파이프의 한쪽 끝이 k 방향으로 위치하도록 이동시키는 경우의 수
    // k는 0(가로), 1(대각선), 2(세로)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new long[N][N][3];
        for (int i = 1; i < N; i++) { // 첫 번째 행에 파이프가 가로로 위치하는 경우는 첫 번째 칸을 제외한 모든 칸에서 한 가지 뿐
            if (arr[0][i] == 1) break; // 벽이 있는 경우 중단
            dp[0][i][0] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                setDp0(i, j);
                setDp1(i, j);
                setDp2(i, j);
            }
        }

//        printDp(0);
//        printDp(1);
//        printDp(2);
        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    }

    private static void printDp(int dir) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dp[i][j][dir] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void setDp2(int i, int j) {
        if (i < 1) return; // 파이프를 세로로 놓을 수 없는 경우
        if (arr[i][j] == 1) return;

        dp[i][j][2] += dp[i - 1][j][2];
        dp[i][j][2] += dp[i - 1][j][1];
    }

    private static void setDp1(int i, int j) {
        if (i < 1 || j < 1) return; // 파이프를 대각선으로 놓을 수 없는 경우
        if (arr[i][j] == 1 || arr[i][j - 1] == 1 || arr[i - 1][j] == 1) return; // 벽이 있는 경우

        dp[i][j][1] += dp[i - 1][j - 1][0];
        dp[i][j][1] += dp[i - 1][j - 1][1];
        dp[i][j][1] += dp[i - 1][j - 1][2];
    }

    private static void setDp0(int i, int j) {
        if (j < 1) return; // 파이프를 가로로 놓을 수 없는 경우
        if (arr[i][j] == 1) return; // 벽이 있는 경우

        dp[i][j][0] += dp[i][j - 1][0];
        dp[i][j][0] += dp[i][j - 1][1];
    }
}
