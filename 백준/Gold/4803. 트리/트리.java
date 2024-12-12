import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static int n;
    static int m;
    static int[][] edges;
    static int[] parent;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int testCase = 0;
        while (true) {
            testCase += 1;

            String s = bf.readLine();
            String[] split = s.split(" ");
            if (split[0].equals("0") && split[1].equals("0")) {
                break;
            }

            n = Integer.parseInt(split[0]);
            m = Integer.parseInt(split[1]);

            setEdges();
            int treeCount = countTrees();
            if (treeCount == 0) {
                System.out.println("Case " + testCase + ": No trees.");
            }
            if (treeCount == 1) {
                System.out.println("Case " + testCase + ": There is one tree.");
            }
            if (treeCount > 1) {
                System.out.println("Case " + testCase + ": A forest of " + treeCount + " trees.");
            }
        }
    }

    private static int countTrees() {
        parent = new int[n + 1];
        for (int i=0; i<n+1; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            union(node1, node2);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i=1; i<n+1; i++) {
            if (find(i) != 0) {
                set.add(find(i));
            }
        }
        return set.size();
    }

    private static void union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);

        if (p1 == p2) {
            if (node1 < node2)      parent[p1] = 0;
            else if (node1 > node2) parent[p2] = 0;
        } else if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }

    private static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    private static void setEdges() throws IOException {
        edges = new int[m][2];

        for (int i=0; i<m; i++) {
            String[] edgeInfo = bf.readLine().split(" ");
            int node1 = Integer.parseInt(edgeInfo[0]);
            int node2 = Integer.parseInt(edgeInfo[1]);
            edges[i][0] = node1;
            edges[i][1] = node2;
        }
    }
}
