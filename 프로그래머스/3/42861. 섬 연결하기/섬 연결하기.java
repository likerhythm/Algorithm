import java.util.*;

class Solution {
    
    class Edge implements Comparable<Edge>{
        int from, to;
        int cost;
        
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    
    boolean[] visited;
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    List<Edge>[] graph;
    
    public int solution(int n, int[][] costs) {
        visited = new boolean[n];
        graph= new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] c : costs) {
            int from = c[0];
            int to = c[1];
            int cost = c[2];
            Edge e1 = new Edge(from, to, cost);
            graph[from].add(e1);
            Edge e2 = new Edge(to, from, cost);
            graph[to].add(e2);
        }
        
        for (Edge edge : graph[0]) {
            pq.add(edge);
        }
        visited[0] = true;
        
        int totalCost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            
            if (visited[to]) continue;
            visited[to] = true;
            totalCost += cost;
            
            for (Edge next : graph[to]) {
                if (visited[next.to]) continue;
                pq.add(next);
            }
        }
        
        return totalCost;
    }
}