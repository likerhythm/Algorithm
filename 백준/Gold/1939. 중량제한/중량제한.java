import java.util.*;
import java.io.*;

public class Main {

    static int N; // 2<=섬의 개수<=10,000
    static int M; // 1<=다리의 개수<=100,000
    static Map<Integer, Integer>[] newBridges;
    static int start, end;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        newBridges = new HashMap[N + 1];
        for (int i=1; i<N+1; i++) {
            newBridges[i] = new HashMap<>();
        }

        for (int i=0; i<M; i++) {
            split = br.readLine().split(" ");
            int s = Integer.parseInt(split[0]);
            int e = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);

            int capacity = newBridges[s].getOrDefault(e, -1);
            if (capacity == -1) {
                newBridges[s].put(e, c);
            } else if (newBridges[s].get(e) < c) {
                newBridges[s].put(e, c);
            }

            capacity = newBridges[e].getOrDefault(s, -1);
            if (capacity == -1) {
                newBridges[e].put(s, c);
            } else if (newBridges[e].get(s) < c) {
                newBridges[e].put(s, c);
            }
        }

        split = br.readLine().split(" ");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);
        binarySearch();

        System.out.println(answer);
    }

    // 최소 중량 = 1, 최대 중량 = 1,000,000,000
    static void binarySearch() {
        int left = 1;
        int right = 1_000_000_000;

        while (left <= right) {
            int mid = (left + right) / 2;
            boolean[] visited = new boolean[N + 1];
            boolean canGo = dfs(mid, start, visited);

            if (canGo) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
    }

    static boolean dfs(int limit, int now, boolean[] visited) {
        if (now == end) {
            return true;
        }

        for (Map.Entry entry : newBridges[now].entrySet()) {
            int next = (int) entry.getKey();
            int capacity = (int) entry.getValue();

            if (visited[next]) {
                continue;
            }

            if (capacity < limit) {
                continue;
            }

            visited[now] = true;
            boolean canGo = dfs(limit, next, visited);
            if (canGo) {
                return true;
            }
        }

        return false;
    }
}
