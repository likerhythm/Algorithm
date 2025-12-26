import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][] isOn;
    static boolean[][] visited;
    static ArrayList<Integer>[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        isOn = new boolean[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[x][y].add(a * N + b);
        }

        isOn[0][0] = true;
        visited[0][0] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int answer = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            int x = now / N;
            int y = now % N;
            for (int next : graph[x][y]) {
                int a = next / N;
                int b = next % N;
                if (!isOn[a][b]) {
                    isOn[a][b] = true;
                    answer += 1;
                    if (isMovable(a, b)) {
                        q.add(a * N + b);
                        visited[a][b] = true;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                if (!inRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (!isOn[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(nx * N + ny);
            }
        }

        System.out.println(answer);
    }

    private static boolean isMovable(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dxs[i];
            int ny = y + dys[i];
            if (!inRange(nx, ny)) continue;
            if (visited[nx][ny]) return true;
        }
        return false;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};
}
