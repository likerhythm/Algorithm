import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static int V; // 정점의 개수
    static int E; // 간선의 개수
    static Edge[] edges;
    static int[] parent;

    static class Edge {
        int node1, node2, w;

        Edge(int node1, int node2, int w) {
            this.node1 = node1;
            this.node2 = node2;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V = input[0];
        E = input[1];

        edges = new Edge[E];
        parent = new int[V + 1];
        for (int i=1; i<=V; i++) {
            parent[i] = i;
        }

        for (int i=0; i<E; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int node1 = input[0];
            int node2 = input[1];
            int w = input[2];

            edges[i] = new Edge(node1, node2, w);
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e.w)); // 가중치의 오름차순으로 정렬

        int totalW = 0;
        int cnt = 0;
        for (int i=0; i<E; i++) {
            int node1 = edges[i].node1;
            int node2 = edges[i].node2;
            int w = edges[i].w;

            if (find(node1) == find(node2)) {
                continue;
            }

            union(node1, node2);
            totalW += w;
            cnt++;
            if (cnt == V - 1) {
                break;
            }
        }

        System.out.println(totalW);
    }

    private static void union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);

        parent[p1] = Math.min(p1, p2);
        parent[p2] = Math.min(p1, p2);
    }

    private static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }


}
