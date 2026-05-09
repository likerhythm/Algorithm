import java.util.*;

class Solution {
    
    int N;
    int[][] computers;
    int answer;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        this.N = n;
        this.computers = computers;
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            bfs(i);
            answer++;
        }
        
        return answer;
    }
    
    void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next = 0; next < N; next++) {
                if (next == now) continue; // 자기 자신인 경우
                if (visited[next]) continue; // 이미 방문한 경우
                if (computers[now][next] == 0) continue; // 연결되어 있지 않은 경우
                q.add(next);
                visited[next] = true;
            }
        }
    }
}