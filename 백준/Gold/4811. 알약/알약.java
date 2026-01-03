import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static long[][] dp = new long[31][31]; // dp[i][j]: 병에 온전한 알약이 i개, 반쪽짜리 알약이 j개 남았을 때 문자열 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            int N = Integer.parseInt(line);
            if (N == 0) break;
            System.out.println(dfs(N, 0));
        }
    }

    private static long dfs(int whole, int half) {
        if (whole == 0) return 1;

        if (dp[whole][half] > 0) return dp[whole][half];

        long count = 0;

        count += dfs(whole - 1, half + 1);

        if (half > 0) {
            count += dfs(whole, half - 1);
        }

        return dp[whole][half] = count;
    }
}