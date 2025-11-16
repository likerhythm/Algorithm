import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Matrix[] matrices;
    static long[][] dp;

    static class Matrix {
        long a, b;

        public Matrix(long a, long b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrices = new Matrix[N];
        dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrices[i] = new Matrix(a, b);
        }

        long answer = topDown(0, N - 1);
        System.out.println(answer);
    }

    private static long topDown(int start, int end) {
        if (dp[start][end] >= 0) {
            return dp[start][end];
        }

        if (start == end) {
            return dp[start][end] = 0;
        }

        long min = Long.MAX_VALUE;
        for (int mid = start; mid < end; mid++) {
            long tmp = topDown(start, mid) + topDown(mid + 1, end);
            tmp = tmp + matrices[start].a * matrices[mid].b * matrices[end].b;
            min = Math.min(min, tmp);
        }

        return dp[start][end] = min;
    }
}
