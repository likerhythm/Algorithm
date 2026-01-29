import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] indegree;
    static boolean[][] graph; // graph[i][j]: 가수 i 다음에 j가 와야 함

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N + 1];
        graph = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (!graph[from][to]) {
                    indegree[to]++;
                }
                graph[from][to] = true;
                from = to;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append("\n");
            for (int next = 1; next <= N; next++) {
                if (!graph[now][next]) continue;
                if (--indegree[next] == 0) q.add(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (indegree[i] > 0) {
                sb = new StringBuilder("0");
            }
        }

        System.out.println(sb);
    }
}
