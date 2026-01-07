import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] graph;
    static int count;
    static boolean[] visited;
    static boolean[] finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            count = 0;
            graph = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N + 1];
            finished = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                if (!finished[i]) {
                    findCycle(i);
                }
            }

            System.out.println(N - count);
        }
    }

    private static void findCycle(int node) {
        visited[node] = true;
        int next = graph[node];

        if (visited[next]) {
            if (!finished[next]) {
                count++;
                for (int n = next; n != node; n = graph[n]) {
                    count++;
                }
            }
        } else {
            findCycle(next);
        }

        finished[node] = true;
    }
}
