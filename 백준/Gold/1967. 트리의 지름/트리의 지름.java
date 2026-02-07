import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N; // 노드의 수
    static Node[] nodes;
    static int[] distances;

    static class Node {
        Map<Integer, Integer> nexts = new HashMap<>();

        void addEdge(int c, int w) {
            nexts.put(c, w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[p].addEdge(c, w);
            nodes[c].addEdge(p, w);
        }

        int n = findFarNodeFrom(1);
        int result = findFarNodeFrom(n);
        System.out.println(distances[result]);
    }

    private static int findFarNodeFrom(int start) {
        distances = new int[N + 1];
        Arrays.fill(distances, -1);

        int maxLength = 0;
        int maxNode = start;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        distances[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Map.Entry<Integer, Integer> entry : nodes[now].nexts.entrySet()) {
                int next = entry.getKey();
                if (distances[next] != -1) continue; // 이미 방문한 경우

                int weight = entry.getValue();
                int nextDist = distances[now] + weight;

                distances[next] = nextDist;
                if (maxLength < nextDist) {
                    maxLength = nextDist;
                    maxNode = next;
                }
                q.add(next);
            }
        }

        return maxNode;
    }
}
