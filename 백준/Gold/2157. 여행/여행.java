import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K; // N: 도시의 개수, M: 여행할 최대 도시 개수, K: 항공로의 개수
    // a, b 사이에 여러 항공로 존재 가능
    static int[][] graph;
    static int[][] dp; // dp[i][j]: i번째 도시를 j번째로 방문했을 때 그 당시의 최대 점수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        dp = new int[N + 1][M + 1];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.max(graph[a][b], c);
        }

        System.out.println(setDp(1, 1));
    }

    private static int setDp(int now, int count) {
        if (count > M) return -1;
        if (now == N) return 0;

        if (dp[now][count] != 0) return dp[now][count];

        boolean flag = false;
        int max = 0;
        for (int next = now; next <= N; next++) {
            if (graph[now][next] == 0) continue;
            int v = setDp(next, count + 1);
            if (v != -1) {
                flag = true;
                max = Math.max(max, v + graph[now][next]);
            }
        }

        if (flag) return dp[now][count] = max;
        else return dp[now][count] = -1;
    }
}
