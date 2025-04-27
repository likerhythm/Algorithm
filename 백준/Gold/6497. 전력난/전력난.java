import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = Integer.MAX_VALUE;

    static int N; // 집의 수
    static int M; // 길의 수
    static boolean[] visited;
    static List<Edge>[] edges;

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            visited = new boolean[N];
            edges = new ArrayList[M];
            for (int i=0; i<N; i++) {
                edges[i] = new ArrayList<>();
            }
    
            int totalCost = 0;
            for (int m=0; m<M; m++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges[x].add(new Edge(y, z));
                edges[y].add(new Edge(x, z));
    
                totalCost += z;
            }
    
            int cost = prim();
            System.out.println(totalCost - cost);
        }
    }

    static int prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : edges[0]) { // 0번 건물부터 시작
            pq.add(e);
        }
        visited[0] = true;

        int cost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.to]) { // 이미 방문한 경우 넘김
                continue;
            }
            
            cost += edge.cost;
            visited[edge.to] = true;
            
            for (Edge e : edges[edge.to]) {
                if (visited[e.to]) {
                    continue;
                }
                
                pq.add(e);
            }
        }

        return cost;
    }
}