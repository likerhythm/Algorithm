import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V; // 정점의 개수
    static int E; // 간선의 개수
    static List<Edge>[] edges;
    static boolean[] visited;

    static class Edge {
        int to, w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V = input[0];
        E = input[1];

        edges = new List[V+1];
        for (int i=0; i<V+1; i++) {
            edges[i] = new ArrayList<>();
        }

        visited = new boolean[V + 1];

        for (int i=0; i<E; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int node1 = input[0];
            int node2 = input[1];
            int w = input[2];

            edges[node1].add(new Edge(node2, w));
            edges[node2].add(new Edge(node1, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w - o2.w;
            }
        });

        pq.addAll(edges[1]);
        visited[1] = true;
        int totalW = 0;
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            int to = poll.to;
            int w = poll.w;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            totalW += w;
            for (Edge edge : edges[to]) {
                if (visited[edge.to]) {
                    continue;
                }
                pq.add(edge);
            }
        }

        System.out.println(totalW);
    }
}
