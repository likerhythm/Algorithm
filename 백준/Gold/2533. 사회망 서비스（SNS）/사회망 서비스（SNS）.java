import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;
    // dp[i][0]: i번째 노드가 얼리 어댑터가 아닐 때 하위 노드들 중 얼리 어댑터 수의 합
    // dp[i][1]: i번째 노드가 얼리 어댑터일 때 하위 노드들 중 얼리 어댑터 수의 합 + 1(자기자신)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        setDp(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    // parent가 얼리 어댑터가 아니라면 자식은 모두 얼리 어댑터여야 한다.
    // parent가 얼리 어댑터라면 자식은 얼리 어댑터일 수도 있고 아닐 수도 있다.
    private static void setDp(int parent) {
        dp[parent][0] = 0;
        dp[parent][1] = 1;

        visited[parent] = true;
        for (int ch : graph[parent]) {
            if (visited[ch]) continue;
            setDp(ch);
            dp[parent][0] += dp[ch][1];
            dp[parent][1] += Math.min(dp[ch][0], dp[ch][1]);
        }
    }
}
