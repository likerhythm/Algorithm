import java.util.*;

class Solution {
    
    int[] childCount;
    List<Integer>[] graph;
    
    public int solution(int n, int[][] wires) {
        childCount = new int[n + 1];
        graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 1번 노드를 루트로
        setCount(0, 1);
        System.out.println(Arrays.toString(childCount));
        
        int answer = 101;
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            int child = 0;
            if (hasChild(a, b)) child = a;
            else child = b;
            answer = Math.min(answer, Math.abs((childCount[1] - childCount[child]) - childCount[child]));
        }
        
        return answer;
    }
    
    boolean hasChild(int n1, int n2) {
        for (int n : graph[n1]) {
            if (n == n2) return true;
        }
        return false;
    }
    
    int setCount(int p, int n) {
        int c = 1;
        for (int next : graph[n]) {
            if (p == next) continue;
            c += setCount(n, next);
        }
        
        graph[n].remove(Integer.valueOf(p));
        
        return childCount[n] = c;
    }
}