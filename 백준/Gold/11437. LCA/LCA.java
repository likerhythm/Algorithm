import java.util.*;
import java.io.*;

// 트리의 최대 높이 = 16 (2^16 > 50,000)
public class Main {

    static int N;
    static Map<Integer, List<Integer>> graph; // 그래프
    static Map<Integer, List<int[]>> tree; // level, 등록된 정점의 부모 정점 번호
    static int[] level; // 각 정점들의 level 정보
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        tree = new HashMap<>();
        level = new int[N + 1];

        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i=0; i<N-1; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        setTree(1);
        // printTree();

        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            System.out.println(findSameParent(a, b));
        }
    }

    static int findSameParent(int a, int b) {
        int aLevel = level[a];
        int bLevel = level[b];
        // a와 b의 레벨 같게 만들기
        while (aLevel > bLevel) {
            a = findParent(a, aLevel);
            // a = tree.get(aLevel)[a];
            aLevel--;
        }
        while (aLevel < bLevel) {
            b = findParent(b, bLevel);
            // b = tree.get(bLevel)[b];
            bLevel--;
        }

        // a와 b가 같아질 때까지 상위 레벨로 같이 이동
        while (a != b) {
            a = findParent(a, aLevel);
            b = findParent(b, bLevel);
            aLevel--;
            bLevel--;
        }

        return a;
    }

    static int findParent(int node, int level) {
        for (int[] n : tree.get(level)) {
            if (n[0] == node) {
                return n[1];
            }
        }
        return -1;
    }

    static void printTree() {
        for (int i=1; i<=tree.size(); i++) {
            System.out.println(tree.get(i).toString());
        }
    }

    static void setTree(int start) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new int[] {start, 1, start}); // 정점, 레벨, 부모
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowNode = now[0];
            int nowLevel = now[1];
            int parent = now[2];
            tree.computeIfAbsent(nowLevel, key -> new ArrayList<>()).add(new int[] {nowNode, parent});
            // tree.get(nowLevel)[nowNode] = parent;
            level[nowNode] = nowLevel;
            
            for (int next : graph.get(nowNode)) {
                if (next == parent) {
                    continue;
                }
                q.add(new int[] {next, nowLevel + 1, nowNode}); // 정점, 레벨, 부모
                visited[next] = true;
            }
        }
    }
}