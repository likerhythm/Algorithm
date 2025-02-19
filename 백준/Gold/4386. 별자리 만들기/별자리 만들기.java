import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int N;
    static Star[] stars;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Star {
        double x, y;

        Star (double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int to;
        double w;

        Edge (int to, double w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        stars = new Star[N];
        visited = new boolean[N];

        for (int i=0; i<N; i++) {
            double[] input = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            stars[i] = new Star(input[0], input[1]);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i=1; i<N; i++) {
            double w = calcCost(0, i);
            pq.add(new Edge(i, w));
        }
        visited[0] = true;

        double cost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.to]) {
                continue;
            }
            cost += edge.w;
            visited[edge.to] = true;

            for (int i=0; i<N; i++) {
                if (i == edge.to) { // 자기 자신과 연결된 간선은 제외
                    continue;
                }
                if (visited[i]) {
                    continue;
                }
                double w = calcCost(edge.to, i);
                pq.add(new Edge(i, w));
            }
        }

        System.out.println(cost);
    }

    private static double calcCost(int a, int b) {
        return Math.sqrt(Math.pow(stars[a].x - stars[b].x, 2) + Math.pow(stars[a].y - stars[b].y, 2));
    }
}
