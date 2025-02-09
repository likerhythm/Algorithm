import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int minTime = Integer.MAX_VALUE;
    static int[][] laboratory;
    static Virus[] onViruses;
    static List<Virus> viruses = new ArrayList<>();
    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Virus {
        int n, m;
        Virus(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        laboratory = new int[N][N];
        onViruses = new Virus[M];

        for (int i=0; i<N; i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                int v = Integer.parseInt(split[j]);
                if (v == 2) {
                    viruses.add(new Virus(i, j));
                }
                laboratory[i][j] = v;
            }
        }

        f(0, 0);
        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(minTime);
    }

    private static void f(int pre, int cnt) {
        if (cnt == M) {
            int time = spread();
            if (time == -1) {
                return;
            }
            minTime = Math.min(time, minTime);
            return;
        }
        for (int i=pre; i<viruses.size(); i++) {
            onViruses[cnt] = viruses.get(i);
            f(i + 1, cnt + 1);
        }
    }

    private static int spread() {
        int[][] visited = new int[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        Queue<int[]> q = new LinkedList<>();
        for (Virus v : onViruses) {
            q.add(new int[] {v.n, v.m});
            visited[v.n][v.m] = 0;
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int v = visited[x][y];
            for (int i=0; i<4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                if (!inRange(nx, ny)) {
                    continue;
                }
                if (laboratory[nx][ny] == 1) {
                    continue;
                }
                int nv = visited[nx][ny];
                if (nv > v + 1) {
                    visited[nx][ny] = v + 1;
                    q.add(new int[] {nx, ny});
                }
            }
        }

        int time = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (laboratory[i][j] == 1) {
                    continue;
                }
                if (laboratory[i][j] == 2) {
                    continue;
                }
                if (visited[i][j] == Integer.MAX_VALUE) {
                    return -1;
                }
                time = Math.max(time, visited[i][j]);
            }
        }

        return time;
    }

    private static boolean inRange(int x, int y) {
        return 0 <=x && x < N && 0 <= y && y < N;
    }
}
