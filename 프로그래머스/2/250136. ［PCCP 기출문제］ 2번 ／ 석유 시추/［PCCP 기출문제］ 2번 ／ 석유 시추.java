import java.util.*;

class Solution {
    
    int N, M;
    int[][] arr;
    int[][] visited;
    int[] sum;
    
    static class Result {
        Set<Integer> cols;
        int count;
        
        Result(Set<Integer> cols, int count) {
            this.cols = cols;
            this.count = count;
        }
    }
    
    public int solution(int[][] land) {
        arr = land;
        N = land.length;
        M = land[0].length;
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }
        sum = new int[M];
        
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (arr[n][m] == 0) continue;
                if (visited[n][m] != -1) continue;
                Result result = bfs(n, m);
                for (int col : result.cols) {
                    sum[col] += result.count;
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < M; i++) {
            answer = Math.max(answer, sum[i]);
        }
        
        return answer;
    }
    
    int[] dns = {-1, 0, 1, 0};
    int[] dms = {0, 1, 0, -1};
    
    Result bfs(int n, int m) {
        Set<Integer> cols = new HashSet<>();
        int count = 1;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m});
        visited[n][m] = 1;
        cols.add(m);
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            
            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                
                if (!inRange(nn, nm)) continue;
                if (visited[nn][nm] != -1) continue;
                if (arr[nn][nm] == 0) continue;
                
                q.add(new int[] {nn, nm});
                visited[nn][nm] = 1;
                cols.add(nm);
                count++;
            }
        }
        
        return new Result(cols, count);
    }
    
    boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}