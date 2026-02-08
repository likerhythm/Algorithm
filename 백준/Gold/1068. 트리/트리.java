import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int root;
    static Node[] nodes;
    static int[] parents;

    static class Node {
        List<Integer> childs = new ArrayList<>();
        int leafCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            int p = Integer.parseInt(st.nextToken());
            parents[idx] = p;
            if (p == -1) {
                root = idx;
                continue;
            }
            nodes[p].childs.add(idx);
        }

        for (int idx = 0; idx < N; idx++) {
            Node node = nodes[idx];
            if (node.childs.isEmpty()) node.leafCount = 1;
        }

        setLeaf(root);

        int target = Integer.parseInt(br.readLine());
        int p = parents[target];
        if (p != -1 && nodes[p].childs.size() == 1) {
            System.out.println(nodes[root].leafCount - nodes[target].leafCount + 1);
        } else {
            System.out.println(nodes[root].leafCount - nodes[target].leafCount);
        }

    }

    private static int setLeaf(int idx) {
        Node node = nodes[idx];
        if (node.childs.isEmpty()) return 1;
        if (node.leafCount > 0) return node.leafCount;

        for (int child : node.childs) {
            node.leafCount += setLeaf(child);
        }

        return node.leafCount;
    }
}
