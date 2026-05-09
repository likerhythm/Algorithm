import java.util.*;

class Solution {
    
    int N;
    String begin, target;
    String[] words;
    int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.N = words.length;
        this.words = words;
        
        boolean[] visited = new boolean[N];
        List<String> footprint = new ArrayList<>();
        footprint.add(begin);
        dfs(begin, visited, 0, footprint);
        
        if (answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    
    void dfs(String pre, boolean[] visited, int count, List<String> footprint) {
        if (allVisited(visited)) { // 모두 방문한 경우
            return;
        }
        
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            String word = words[i];
            if (countDiff(pre, word) == 1) {
                if (word.equals(target)) {
                    answer = Math.min(answer, count + 1);
                    return;
                }
            
                visited[i] = true;
                footprint.add(word);
                dfs(word, visited, count + 1, footprint);
                footprint.remove(footprint.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    int countDiff(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) count++;
        }
        return count;
    }
    
    boolean allVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}