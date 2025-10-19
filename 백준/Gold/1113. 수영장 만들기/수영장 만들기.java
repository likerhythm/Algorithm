import java.io.*;
import java.util.*;

public class Main {

    static int N, M; // 50 이하 자연수
    static int[][] map;
    static int answer;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int h = 1; h <= 9; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (map[n][m] != h) continue;
                    if (visited[n][m]) continue;
                    answer += bfs(h, n, m);
                }
            }
        }

        System.out.println(answer);
    }

    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    private static int bfs(int h, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m});
        visited[n][m] = true;
        List<int[]> pool = new ArrayList<>(); // 물을 채울 공간을 저장
        pool.add(new int[] {n, m});
        boolean flood = false;

        int minHeight = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            if (n == 0 || n == N - 1 || m == 0 || m == M - 1) {
                flood = true;
            }

            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];

                if (!inRange(nn, nm)) continue;
                if (map[nn][nm] < h) flood = true;
                if (map[nn][nm] > h) {
                    minHeight = Math.min(minHeight, map[nn][nm]);
                    continue;
                }
                if (visited[nn][nm]) continue;

                q.add(new int[] {nn, nm});
                visited[nn][nm] = true;
                pool.add(new int[] {nn, nm});
            }
        }

        if (!flood) {
            for (int[] a : pool) {
                map[a[0]][a[1]] = minHeight;
                visited[a[0]][a[1]] = false;
            }

            return (minHeight - h) * pool.size();
        }

        return 0;
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
