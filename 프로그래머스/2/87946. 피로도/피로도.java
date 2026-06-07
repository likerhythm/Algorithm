class Solution {
    
    int N;
    int answer;
    boolean[] visited;
    int[][] dungeons;
    int stemina;
    
    public int solution(int k, int[][] d) {
        stemina = k;
        N = d.length;
        dungeons = d;
        visited = new boolean[N];
        
        backtracking();
        
        return answer;
    }
    
    void backtracking() {
        int countv = countVisited();
        if (countv == N) {
            answer = N;
            return;
        }
        
        boolean canNext = false;
        for (int next = 0; next < N; next++) {
            if (visited[next]) continue;
            if (dungeons[next][0] > stemina) continue;
            
            visited[next] = true;
            stemina -= dungeons[next][1];
            
            backtracking();
            
            visited[next] = false;
            stemina += dungeons[next][1];
            canNext = true;
        }
        
        if (!canNext) {
            answer = Math.max(answer, countv);
        }
    }
    
    int countVisited() {
        int count = 0;
        for (boolean v : visited) {
            if (v) count++;
        }
        return count;
    }
}