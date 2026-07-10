import java.util.*;

class Solution {
    
    class Edge implements Comparable<Edge> {
        String from, to;
        boolean visited;
        
        Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }
        
        @Override
        public int compareTo(Edge other) {
            return this.to.compareTo(other.to);
        }
        
        @Override
        public String toString() {
            return "(" + from + ", " + to + ")";
        }
    }
    
    Map<String, List<Edge>> edges;
    int edgeCnt;
    
    public String[] solution(String[][] tickets) {
        this.edgeCnt = tickets.length;
        this.edges = new HashMap<>();
        
        for (int i = 0; i < edgeCnt; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            Edge e = new Edge(from, to);
            
            edges.computeIfAbsent(from, k -> new ArrayList<>()).add(e);
        }
        
        for (List<Edge> l : edges.values()) {
            Collections.sort(l);
        }
        
        // for (Map.Entry e : edges.entrySet()) {
        //     System.out.println(e.getKey() + ": " + e.getValue().toString());
        // }
        
        String[] route = new String[edgeCnt + 1];
        route[0] = "ICN";
        dfs("ICN", route, 1);
        
        return route;
    }
    
    boolean done = false;
    
    void dfs(String now, String[] route, int idx) {
        if (idx == edgeCnt + 1) {
            done = true;
            return;
        }
        
        if (edges.get(now) == null) return;
        for (Edge e : edges.get(now)) {
            if (e.visited) continue;
            
            route[idx] = e.to;
            e.visited = true;
            dfs(e.to, route, idx + 1);
            
            if (done) return;
            e.visited = false;
        }
    }
}