import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] costs;
    static int[][] dp;
    static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (input[j] == 0) {
                    input[j] = -1;
                }
            }
            costs[i] = input;
        }

        System.out.println(solve(0, 1));
    }

    private static int solve(int now, int info) {
        if (info == (1 << N) - 1) {
            return costs[now][0] == -1 ? MAX : costs[now][0];
        }

        if (dp[now][info] > 0) return dp[now][info];

        int result = MAX;
        for (int next = 0; next < N; next++) {
            if ((info & (1 << next)) != 0) continue; // 이미 방문한 경우
            if (costs[now][next] == -1) continue; // 길이 없는 경우
            result = Math.min(result, solve(next, info | (1 << next)) + costs[now][next]);
        }

        return dp[now][info] = result;
    }
}
