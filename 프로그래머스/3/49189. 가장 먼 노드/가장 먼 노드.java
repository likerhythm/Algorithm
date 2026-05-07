import java.util.*;
import java.io.*;

class Solution {
    
    int N;
    List<Integer>[] graph;
    
    public int solution(int n, int[][] edge) {
        N = n;
        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        // System.out.println(graph[1]);
        
        int[] distances = bfs(1);
        System.out.println(Arrays.toString(distances));
        int answer = 0;
        int maxDist = 0;
        for (int dist : distances) {
            if (maxDist < dist) {
                maxDist = dist;
                answer = 1;
            } else if (maxDist == dist) {
                answer++;
            }
        }
        
        return answer;
        // return 0;
    }
    
    int[] bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        
        q.add(new int[] {start, 0});
        visited[start] = true;
        
        int[] distances = new int[N + 1];
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int now = poll[0];
            int dist = poll[1];
            distances[now] = dist;
            
            for (int next : graph[now]) {
                if (visited[next]) continue;
                q.add(new int[] {next, dist + 1});
                visited[next] = true;
            }
        }
        
        return distances;
    }
}