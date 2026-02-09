import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Node[] nodes;
    static int[] colors;

    static class Node {
        List<Integer> nextNodes = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            nodes = new Node[V + 1];
            colors = new int[V + 1];
            Arrays.fill(colors, -1);
            for (int i = 1; i <= V; i++) {
                nodes[i] = new Node();
            }
            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                nodes[a].nextNodes.add(b);
                nodes[b].nextNodes.add(a);
            }

            boolean isBinary = true;
            for (int i = 1; i <= V; i++) {
                if (colors[i] != -1) continue;
                isBinary = bfs(i);
                if (!isBinary) break;
            }

            if (isBinary) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        colors[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            int color = colors[now];
            for (int next : nodes[now].nextNodes) {
                if (colors[next] == color) return false;
                if (colors[next] != -1) continue;
                colors[next] = 1 - color;
                q.add(next);
            }
        }

        return true;
    }
}
