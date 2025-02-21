import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N; // 정점의 수
    static int M; // 길의 개수
    static Edge[] edges;
    static List<Edge> mst = new ArrayList<>();
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int node1, node2, cost;

        Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = input[0];
        M = input[1];
        edges = new Edge[M];
        parent = new int[N + 1];
        for (int i=1; i<N+1; i++) {
            parent[i] = i;
        }

        for (int i=0; i<M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges[i] = new Edge(input[0], input[1], input[2]);
        }

        Arrays.sort(edges);

        int totalCost = 0;
        for (Edge e : edges) {
            int node1 = e.node1;
            int node2 = e.node2;
            int cost = e.cost;

            if (find(node1) == find(node2)) {
                continue;
            }

            union(node1, node2);
            totalCost += cost;
            mst.add(e);
        }

        Collections.sort(mst);

        Edge remove = mst.remove(mst.size() - 1);
        totalCost -= remove.cost;

        System.out.println(totalCost);
    }

    private static void union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);

        int newParent = Math.min(p1, p2);
        parent[p1] = newParent;
        parent[p2] = newParent;
    }

    private static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }
}
