import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int id;
        int x, y;

        public Pos(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double dist;

        public Edge(int u, int v, double dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    static int N, M;
    static Pos[] poses;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        poses = new Pos[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            poses[i] = new Pos(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (i == j) continue;
                double dist = Math.sqrt(Math.pow(poses[i].x - poses[j].x, 2) + Math.pow(poses[i].y - poses[j].y, 2));
                edges.add(new Edge(i, j, dist));
            }
        }

        Collections.sort(edges);

        double result = 0;
        for (Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                result += edge.dist;
            }
        }

        System.out.println(String.format("%.2f", result));
    }

    private static boolean union(int u, int v) {
        int p1 = find(u);
        int p2 = find(v);

        if (p1 != p2) {
            int newP = Math.min(p1, p2);
            parents[p1] = newP;
            parents[p2] = newP;
            return true;
        }
        return false;
    }

    private static int find(int node) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }
}
