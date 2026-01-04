import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dp;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }

        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        for (int i = N; i > 0; i--) {
            if (dp[i] == -1) setDp(i);
        }
        for (int i = 1; i < N + 1; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    private static int setDp(int n) {
        if (graph[n].isEmpty()) {
            return dp[n] = 1;
        }

        if (dp[n] != -1) return dp[n];

        int max = 0;
        for (int t : graph[n]) {
            max = Math.max(max, setDp(t) + 1);
        }

        return dp[n] = max;
    }
}
