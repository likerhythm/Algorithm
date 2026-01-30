import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, W;
    static int[] distances;
    static int MAX = 987654321;
    static List<Edge> edges;

    static class Edge {
        int s, e, t;

        Edge(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            distances = new int[N + 1];
            Arrays.fill(distances, MAX);
            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -t));
            }

            if (bellmanFord()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean bellmanFord() {
        Arrays.fill(distances, MAX);

        for (int i = 0; i < N; i++) {
            for (Edge edge : edges) {
                if (distances[edge.e] > distances[edge.s] + edge.t) {
                    distances[edge.e] = distances[edge.s] + edge.t;
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
