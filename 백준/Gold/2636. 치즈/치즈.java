import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int time = 0;
        int cheeseCount = Integer.MAX_VALUE;
        while (true) {
            int cheese = bfs(0, 0);
//            printMap();
            if (cheese == 0) break;
            cheeseCount = Math.min(cheeseCount, cheese);
            time++;
        }

        System.out.println(time);
        System.out.println(cheeseCount);
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    private static int bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[] {n, m});
        visited[n][m] = true;
        int[][] tmpMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }

        int cheeseCount = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) continue;
                if (visited[nn][nm]) continue;
                if (map[nn][nm] == 1) { // 치즈인 경우
                    cheeseCount++;
                    tmpMap[nn][nm] = 0;
                    visited[nn][nm] = true;
                    continue;
                }
                visited[nn][nm] = true;
                q.add(new int[] {nn, nm});
            }
        }

        map = tmpMap;
        return cheeseCount;
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
