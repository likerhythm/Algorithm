import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int F; // 친구 관계의 수 <= 100,000
    static Map<String, Integer> map;
    static int[] parent;
    static int[] childCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while (testcase-- > 0) {
            map = new HashMap<>();
            parent = new int[200_000];
            childCount = new int[200_000];
            F = Integer.parseInt(br.readLine());
            int idx = 0;
            for (int f = 0; f < F; f++) {
                String[] split = br.readLine().split(" ");
                String name1 = split[0];
                String name2 = split[1];
                if (!map.containsKey(name1)) {
                    parent[idx] = idx;
                    childCount[idx] = 1;
                    map.put(name1, idx++);
                }
                if (!map.containsKey(name2)) {
                    parent[idx] = idx;
                    childCount[idx] = 1;
                    map.put(name2, idx++);
                }

                union(map.get(name1), map.get(name2));
                System.out.println(childCount[find(map.get(name1))]);
            }
        }
    }

    private static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) {
            parent[p2] = p1;
            childCount[p1] += childCount[p2];
            childCount[p2] = 0;
        } else if (p1 > p2) {
            parent[p1] = p2;
            childCount[p2] += childCount[p1];
            childCount[p1] = 0;
        }
    }

    private static int find(int n) {
        if (parent[n] == n) {
            return n;
        }

        return parent[n] = find(parent[n]);
    }

}
