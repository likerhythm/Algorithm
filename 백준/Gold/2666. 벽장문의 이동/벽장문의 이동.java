import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][][] dp;
    static int max = Integer.MAX_VALUE;
    static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1][20];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                Arrays.fill(dp[i][j], max);
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int count = Integer.parseInt(br.readLine());
        order = new int[count];
        for (int i = 0; i < count; i++) {
            order[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(setDp(a, b, 0));
    }

    private static int setDp(int a, int b, int idx) {
        if (idx == order.length) return 0;

        int length1 = Math.abs(order[idx] - a);
        int length2 = Math.abs(order[idx] - b);

        return Math.min(length1 + setDp(order[idx], b, idx + 1),
                length2 + setDp(order[idx], a, idx + 1));
    }
}
