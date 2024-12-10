import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static boolean isInRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static int N;
    static int[][] arr;
    static int[][][] dp;
    static int[] pos = {0, 1};

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    // 행과 열이 1부터 시작
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        dp = new int[N][N][3];

        for (int i = 0; i < N; i++) {
            String input = bf.readLine();
            String[] split = input.split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
                dp[i][j][0] = 0;
                dp[i][j][1] = 0;
                dp[i][j][2] = 0;
            }
        }

        for (int i=1; i<N; i++) {
            if (arr[0][i] == 1) {
                break;
            }
            dp[0][i][0] = 1;
        }
        if (arr[0][2] == 0 && arr[1][1] == 0 && arr[1][2] == 0){
            dp[1][2][2] = 1;
        }

        for (int i=1; i<N; i++) {
            if (arr[i][2] == 1) {
                break;
            }
            if (i > 1 && dp[1][2][2] == 1) {
                dp[i][2][1] = 1;
            }
        }

        goDp(pos[0], pos[1]);

//        printDp();
        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    }

    private static void printDp() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(Arrays.toString(dp[i][j]));
            }
            System.out.println();
        }
    }

    private static void goDp(int x, int y) {
        for (int i=1; i<N; i++) {     // 행
            for (int j=3; j<N; j++) { // 열
                if (arr[i][j] == 0) {
                    int countWentRight = wentRight(i, j); // 가로로 이동한 경우의 수
                    int countWentDown = wentDown(i, j);
                    int countWentDiagonal = wentDiagonal(i, j);
                    dp[i][j][0] = countWentRight;
                    dp[i][j][1] = countWentDown;
                    dp[i][j][2] = countWentDiagonal;
                }
            }
        }
    }

    private static int wentRight(int i, int j) {
        int count = 0;
        if (dp[i][j - 1][0] > 0) {
            count += dp[i][j - 1][0]; // 이전에 가로 상태인 경우의 수
        }
        if (dp[i][j - 1][2] > 0) {
            count += dp[i][j - 1][2]; // 이전에 대각선 상태인 경우의 수
        }

        return count;
    }

    private static int wentDown(int i, int j) {
        int count = 0;
        if (dp[i - 1][j][1] > 0) {
            count += dp[i - 1][j][1]; // 이전에 세로 상태인 경우의 수
        }
        if (dp[i - 1][j][2] > 0) {
            count += dp[i - 1][j][2]; // 이전에 대각선 상태인 경우의 수
        }
        return count;
    }

    private static int wentDiagonal(int i, int j) {
        if (arr[i - 1][j] == 0 && arr[i - 1][j - 1] == 0 && arr[i][j - 1] == 0) {
            int count = 0;
            if (dp[i - 1][j - 1][0] > 0) {
                count += dp[i - 1][j - 1][0]; // 이전에 가로 상태인 경우의 수
            }
            if (dp[i - 1][j - 1][1] > 0) {
                count += dp[i - 1][j - 1][1]; // 이전에 세로 상태인 경우의 수
            }
            if (dp[i - 1][j - 1][2] > 0) {
                count += dp[i - 1][j - 1][2]; // 이전에 대각선 상태인 경우의 수
            }
            return count;
        }
        return 0;
    }
}
