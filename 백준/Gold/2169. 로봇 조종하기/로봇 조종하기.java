import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][][] dp;
    static int MIN = Integer.MIN_VALUE;
    static boolean[][][] isDone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[N][M][3];
        isDone = new boolean[N][M][3];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(Math.max(setDp(0, 0, 1), setDp(0, 0, 2)));
//        printArr(dp);
    }

    private static void printArr(int[][][] dp) {
        for (int[][] d : dp) {
            for (int[] p : d)
                System.out.print(Arrays.toString(p));
            System.out.println();
        }
        System.out.println();
    }

    static int[] dns = {0, 1, 0};
    static int[] dms = {-1, 0, 1};

    // dir: 현재 위치(n, m)에서 이동할 방향.
    private static int setDp(int n, int m, int dir) {
        if (n == N - 1 && m == M - 1) return arr[n][m];
        if (isDone[n][m][dir]) return dp[n][m][dir];

        int nn = n + dns[dir];
        int nm = m + dms[dir];
        if (!inRange(nn, nm)) {
            isDone[n][m][dir] = true;
            return dp[n][m][dir] = MIN;
        }

        dp[n][m][dir] = 0;
        if (dir == 0 || dir == 2){
            int sameWay = setDp(nn, nm, dir);
            int goDown = setDp(nn, nm, 1);
            if (sameWay == MIN && goDown == MIN) dp[n][m][dir] = MIN;
            else dp[n][m][dir] = Math.max(sameWay, goDown) + arr[n][m];
        }
        else {
            dp[n][m][dir] = Math.max(setDp(nn, nm, 0), Math.max(setDp(nn, nm, 1), setDp(nn, nm, 2))) + arr[n][m];
        }

        isDone[n][m][dir] = true;
        return dp[n][m][dir];
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
