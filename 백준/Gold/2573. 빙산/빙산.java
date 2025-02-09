import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N, M;
    static int[][] ocean;
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static List<Ice> ices = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class Ice {
        int n, m;

        Ice(int n, int m) {
            this.n = n;
            this.m = m;
        }

        @Override
        public String toString() {
            return n + " " + m;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        ocean = new int[N][M];

        for (int i=0; i<N ;i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                int v = Integer.parseInt(split[j]);
                if (v > 0) {
                    ices.add(new Ice(i, j));
                    ocean[i][j] = v;
                }
            }
        }

        int year = 0;
        while (!isSeparated()) {
            int[][] tempOcean = new int[N][M];
            List<Ice> melt = new ArrayList<>();
            for (Ice ice : ices) { // 각 얼음들의 다음 크기 구하기
                int n = ice.n;
                int m = ice.m;
                int v = ocean[n][m];
                for (int i=0; i<4; i++) {
                    int nn = n + dns[i];
                    int nm = m + dms[i];
                    if (ocean[nn][nm] > 0) { // 빙산인 경우
                        continue;
                    }
                    v--;
                    if (v == 0) {
                        melt.add(ice);
                        break;
                    }
                }
                tempOcean[n][m] = v;
            }
            ocean = tempOcean;
            ices.removeAll(melt);
            year++;
        }

        if (ices.size() == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(year);
    }

    private static boolean isSeparated() {
        if (ices.size() == 0) {
            return true;
        }

        int cnt = 0;
        boolean[][] visited = new boolean[N][M];
        for (Ice ice : ices) {
            if (!visited[ice.n][ice.m]) {
                cnt++;
                if (cnt > 1) {
                    return true;
                }
                bfs(ice.n, ice.m, visited);
            }
        }
        return false;
    }

    private static void bfs(int n, int m, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m});
        visited[n][m] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i=0; i<4; i++) {
                int nn = poll[0] + dns[i];
                int nm = poll[1] + dms[i];
                if (ocean[nn][nm] == 0) {
                    continue;
                }
                if (visited[nn][nm]) {
                    continue;
                }
                q.add(new int[] {nn, nm});
                visited[nn][nm] = true;
            }
        }
    }
}
