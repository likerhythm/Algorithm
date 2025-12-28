import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N; // 정점의 개수
    static Node[] nodes;
    static int a;
    static int maxDist;
    static boolean[] visited;

    static class Node {
        Map<Integer, Integer> child = new HashMap<>();

        void addChild(int idx, int dist) {
            child.put(idx, dist);
        }

        boolean hasChild() {
            return !child.isEmpty();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        nodes = new Node[N + 1];
        for (int i = 1; i < N + 1; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            Node node = nodes[idx];
            while (st.hasMoreTokens()) {
                int child = Integer.parseInt(st.nextToken());
                if (child == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                node.addChild(child, dist);
            }
        }

        dfs(1, 0);
        Arrays.fill(visited, false);
        maxDist = 0;
        dfs(a, 0);

        System.out.println(maxDist);
    }

    private static void dfs(int now, int dist) {
        if (allVisited(now)) {
            if (maxDist < dist) {
                maxDist = dist;
                a = now;
            }
            return;
        }

        visited[now] = true;
        for (Map.Entry<Integer, Integer> entry : nodes[now].child.entrySet()) {
            int idx = entry.getKey();
            int nextDist = entry.getValue();
            if (visited[idx]) continue;
            dfs(idx, dist + nextDist);
        }
    }

    private static boolean allVisited(int now) {
        for (Map.Entry<Integer, Integer> entry : nodes[now].child.entrySet()) {
            int idx = entry.getKey();
            if (!visited[idx]) return false;
        }
        return true;
    }
}
