import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int m;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent;
    static int answer = 0;
    static boolean isCycle = false;

    public static void main(String[] args) throws IOException {
        String input = bf.readLine();
        String[] split = input.split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }

        for (int i=1; i<m+1; i++) {
            input = bf.readLine();
            split = input.split(" ");
            int node1 = Integer.parseInt(split[0]);
            int node2 = Integer.parseInt(split[1]);
            if (isCycle) {
                continue;
            }
            isCycle = union(node1, node2);
            if (isCycle) {
                answer = i;
            }
        }

        System.out.println(answer);
    }

    private static boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) {
            parent[p2] = p1;
        }
        else if (p1 > p2) {
            parent[p1] = p2;
        }

        return p1 == p2;
    }

    private static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
}
