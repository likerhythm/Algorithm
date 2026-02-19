import java.util.*;
import java.io.*;

// 홀짝 트리가 되려면
// => 루트 노드를 제외한 모든 노드들이 번호가 홀일 경우 짝수개의 간선, 번호가 짝일 경우 홀수개의 간선을 가지고 있어야 하고 루트 노드는 홀일 경우 홀, 짝일 경우 짝수개의 간선을 가지고 있어야 한다.
// 역홀짝 트리가 되려면
// => 루트 노드를 제외한 모든 노드들이 번호가 홀일 경우 홀수개의 간선, 번호가 짝일 경우 짝수개의 간선을 가지고 있어야 하고 루트 노드는 홀일 경우 짝, 짝일 경우 홀수개의 간선을 가지고 있어야 한다.

// 위 두 경우에 해당하지 않으면 홀짝 트리와 역홀짝 트리에 모두 포함되지 않는다.

class Solution {
    
    static final int MAX_NODE = 1_000_001;
    static Node[] nodes;
    
    static class Node {
        int num;
        boolean visited;
        List<Integer> edges;
        
        public Node(int num) {
            this.num = num;
            edges = new ArrayList<>();
        }
        
        public void addEdge(int n) {
            this.edges.add(n);
        }
        
        public boolean isEdgeCntModAndNumModEquals() {
            return num % 2 == edges.size() % 2;
        }
        
        public boolean allVisited(boolean[] visited) {
            for (int n : edges) {
                if (!visited[n]) return false;
            }
            return true;
        }
    }
    
    public int[] solution(int[] inputNodes, int[][] inputEdges) {
        nodes = new Node[MAX_NODE];
        for (int n : inputNodes) {
            nodes[n] = new Node(n);
        }
        
        for (int[] edge : inputEdges) {
            int a = edge[0];
            int b = edge[1];
            nodes[a].addEdge(b);
            nodes[b].addEdge(a);
        }
        
        // 홀짝트리 찾기
        int count1 = 0;
        boolean[] visited = new boolean[MAX_NODE];
        for (int n : inputNodes) {
            if (visited[n]) continue;
            int cnt = search(n, true, visited); // 노드의 번호와 간선의 개수가 홀,홀 또는 짝,짝인 노드의 개수
            if (cnt == 1) {
                count1++;
            }
        }
        
        // 역홀짝트리 찾기
        int count2 = 0;
        visited = new boolean[MAX_NODE];
        for (int n : inputNodes) {
            if (visited[n]) continue;
            int cnt = search(n, false, visited); // 노드의 번호와 간선의 개수가 홀,짝 또는 짝,홀인 노드의 개수
            if (cnt == 1) {
                count2++;
            }
        }
        
        return new int[] {count1, count2};
    }
    
    // flag: 노드의 번호와 간선의 개수가 같은 걸 찾는다면 true, 아니라면 false
    static int search(int now, boolean flag, boolean[] visited) {
        visited[now] = true;
        if (nodes[now].allVisited(visited)) {
            return (flag == nodes[now].isEdgeCntModAndNumModEquals()) ? 1 : 0;
        }
        
        int count = (flag == nodes[now].isEdgeCntModAndNumModEquals()) ? 1 : 0;
        for (int next : nodes[now].edges) {
            if (visited[next]) continue;
            count += search(next, flag, visited);
        }
        
        return count;
    }
}