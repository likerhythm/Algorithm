import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int N, M; // <= 25
    static int T, D; // T: 이동할 수 있는 최대 높이차, D: 호텔 출발 후 돌아오는 데까지 걸려야 하는 최대 시간
    static int[][] height;
    static int[][] costFromHotel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        T = Integer.parseInt(split[2]);
        D = Integer.parseInt(split[3]);

        height = new int[N][M];
        for (int i = 0; i < N; i++) {
            split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                char c = split[j].charAt(0);
                if ('A' <= c && c <= 'Z') {
                    height[i][j] = c - 'A';
                } else {
                    height[i][j] = c - 'a' + 26;
                }
            }
        }

        costFromHotel = new int[N][M];
        dijkstra(0, 0, costFromHotel);

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (costFromHotel[i][j] == Integer.MAX_VALUE) continue;
                if (height[i][j] < maxHeight) continue;
                if (costFromHotel[i][j] > D) continue;

                int[][] costFromHere = new int[N][M];
                dijkstra(i, j, costFromHere);

                if (costFromHere[0][0] + costFromHotel[i][j] <= D) {
                    maxHeight = height[i][j];
                }
            }
        }

        System.out.println(maxHeight);
    }

    static class Node implements Comparable<Node> {

        int cost;
        int n, m;

        public Node(int cost, int n, int m) {
            this.cost = cost;
            this.n = n;
            this.m = m;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) return -1;
            else if(this.cost > o.cost) return 1;
            return 0;
        }
    }

    static int[] dns = {-1, 0 ,1, 0};
    static int[] dms = {0, 1, 0, -1};

    private static void dijkstra(int n, int m, int[][] times) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(times[i], Integer.MAX_VALUE);
        }

        times[n][m] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, n, m));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            n = poll.n;
            m = poll.m;
            int cost = poll.cost;
            int nowHeight = height[n][m];

            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];

                if (!inRange(nn, nm)) continue;
                int nextHeight = height[nn][nm];
                if (Math.abs(nowHeight - nextHeight) > T) continue;

                int nextCost;
                if (nowHeight >= nextHeight) nextCost = 1;
                else nextCost = (int) Math.pow(nextHeight - nowHeight, 2);

                int newCost = cost + nextCost;
                if (times[nn][nm] <= newCost) continue;
                pq.add(new Node(newCost, nn, nm));
                times[nn][nm] = newCost;
            }
        }
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
