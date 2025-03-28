import java.util.*;
import java.io.*;

public class Main {

    static int V, E; // 정점, 간선의 개수
    static int start;
    static List<int[]>[] graph;
    static int[] distances;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        start = Integer.parseInt(br.readLine());
        distances = new int[V + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        graph = new ArrayList[V + 1];
        for (int i=1; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<E; i++) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            graph[s].add(new int[] {e, w});
        }

        dijkstra();

        for (int i=1; i<=V; i++) {
            int dist = distances[i];
            if (dist == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distances[i]);
            }
        }
    }

    static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int nowNode = poll[0];
            int nowDist = poll[1];

            if (distances[nowNode] < nowDist) {
                continue;
            }

            for (int[] g : graph[nowNode]) {
                int nextNode = g[0];
                int nextDist = g[1];
                if (nowDist + nextDist < distances[nextNode]) {
                    distances[nextNode] = nowDist + nextDist;
                    pq.add(new int[] {nextNode, nowDist + nextDist});
                }
            }
        }
    }
}