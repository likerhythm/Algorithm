import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M;
    static char[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'W') continue;
                answer = Math.max(answer, bfs(i, j));
            }
        }

        System.out.println(answer);
    }

    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    private static int bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m, 0});
        boolean[][] visited = new boolean[N][M];
        visited[n][m] = true;

        int result = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            int dist = poll[2];
            result = Math.max(result, dist);

            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) continue;
                if (visited[nn][nm]) continue;
                if (map[nn][nm] == 'W') continue;
                q.add(new int[] {nn, nm, dist + 1});
                visited[nn][nm] = true;
            }
        }

        return result;
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
