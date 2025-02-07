import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();;
        }

        int cnt = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == '1' && !visited[i][j]) {
                    int houseCount = bfs(i, j);
                    pq.offer(houseCount);
                    cnt += 1;
                }
            }
        }

        System.out.println(cnt);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    private static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});
        visited[i][j] = true;

        int[] dis = {-1, 0, 1, 0};
        int[] djs = {0, 1, 0, -1};

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            cnt++;
            i = poll[0];
            j = poll[1];
            for (int k=0; k<4; k++) {
                int ni = i + dis[k];
                int nj = j + djs[k];
                if (!inRange(ni, nj)) {
                    continue;
                }
                if (!visited[ni][nj] && map[ni][nj] == '1') {
                    q.offer(new int[] {ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }

        return cnt;
    }

    private static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}
