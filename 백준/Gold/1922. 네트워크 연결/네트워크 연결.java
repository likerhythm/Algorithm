import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int N; // 컴퓨터의 개수
    static int M; // 연결할 수 있는 선의 개수
    static boolean[] visited;
    static List<Edge>[] graph;

    static class Edge implements Comparable<Edge> {
        int dest;
        int w;

        Edge(int dest, int w) {
            this.dest = dest;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i=1; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) { // 간선 입력
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = input[0];
            int to = input[1];
            int w = input[2];

            graph[from].add(new Edge(to, w));
            graph[to].add(new Edge(from, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : graph[1]) {
            pq.add(e);
        }
        visited[1] = true;

        int totalW = 0;
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            if (visited[poll.dest]) {
                continue;
            }

            for (Edge e : graph[poll.dest]) {
                if (visited[e.dest]) {
                    continue;
                }
                pq.add(e);
            }

            visited[poll.dest] = true;
            totalW += poll.w;
        }

        System.out.println(totalW);
    }
}
