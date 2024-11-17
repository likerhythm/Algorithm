import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static int N;
    static int M;
    static int K;
    static int[][] graph;
    static int[][] visited;
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder answer = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new int[N][M];
            visited = new int[N][M];

            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());

                graph[n][m] = 1;
            }

            int count = 0;
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (graph[i][j] == 1 && visited[i][j] == 0) {
                        bfs(i, j);
                        count += 1;
                    }
                }
            }
            answer.append(count).append("\n");
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void bfs(int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {n, m});

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            n = data[0];
            m = data[1];
            for (int i=0; i<4; i++) {
                int dn = dns[i];
                int dm = dms[i];
                int nn = n + dn;
                int nm = m + dm;

                if (inRange(nn, nm) && visited[nn][nm] == 0 && graph[nn][nm] == 1) {
                    queue.offer(new int[] {nn, nm});
                    visited[nn][nm] = 1;
                }
            }
        }
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
