import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static long[][] dp = new long[31][31]; // dp[i][j]: 병에 온전한 알약이 i개, 반쪽짜리 알약이 j개 남았을 때 문자열 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while ((line = br.readLine()) != null) {
            int N = Integer.parseInt(line);
            if (N == 0) break;
            System.out.println(dfs(N, 0));
        }
    }

    private static long dfs(int open, int close) {
        if (open == 0) return 1;

        if (dp[open][close] > 0) return dp[open][close];

        long count = 0;
        count += dfs(open - 1, close + 1);
        if (close > 0) count += dfs(open, close - 1);

        return dp[open][close] = count;
    }
}