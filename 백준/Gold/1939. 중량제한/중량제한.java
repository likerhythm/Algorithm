import java.util.*;
import java.io.*;

public class Main {

    static int N; // 2<=섬의 개수<=10,000
    static int M; // 1<=다리의 개수<=100,000
    static List<Bridge>[] bridges;
    static Map<Integer, Integer>[] newBridges;
    static int start, end;
    static int answer;

    static class Bridge {
        int end;
        int capacity;

        Bridge(int end, int capacity) {
            this.end = end;
            this.capacity = capacity;
        }

        @Override
        public boolean equals(Object o) {
            return this.end == ((Bridge)o).end;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(end);
        }

        @Override
        public String toString() {
            return "end = " + end + " capacity = " + capacity;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

//        bridges = new ArrayList[N + 1];
        newBridges = new HashMap[N + 1];
        for (int i=1; i<N+1; i++) {
//            bridges[i] = new ArrayList<>();
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

//            Bridge b1 = new Bridge(e, c);
//            Bridge b2 = new Bridge(s, c);
//            if (bridges[s].contains(b1)) {
//                int idx = bridges[s].indexOf(b1);
//                Bridge old = bridges[s].get(idx);
//                if (old.capacity < b1.capacity) {
//                    bridges[s].remove(b1);
//                    bridges[s].add(b1);
//                }
//            } else {
//                bridges[s].add(b1);
//            }
//
//            if (bridges[e].contains(b2)) {
//                int idx = bridges[e].indexOf(b2);
//                Bridge old = bridges[e].get(idx);
//                if (old.capacity < b2.capacity) {
//                    bridges[e].remove(b2);
//                    bridges[e].add(b2);
//                }
//            } else {
//                bridges[e].add(b2);
//            }
        }

        split = br.readLine().split(" ");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);

        // printBridges();

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
//            boolean canGo = bfs(mid);

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

    static boolean bfs(int limit) {
        boolean[] visited = new boolean[M + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int poll = q.poll();
            if (poll == end) {
                return true;
            }

            for (Bridge bridge : bridges[poll]) {
                int next = bridge.end;
                int capacity = bridge.capacity;

                if (visited[next]) { // 이미 방문한 경우
                    continue;
                }

                if (capacity < limit) {
                    continue;
                }

                q.add(next);
                visited[next] = true;
            }
        }

        return false;
    }

    static void printBridges() {
        for (int i=1;i<=N; i++) {
            List<Bridge> list = bridges[i];
            for (Bridge b : list) {
                System.out.print(b.toString());
            }
            System.out.println();
        }
        System.out.println();
    }
}
