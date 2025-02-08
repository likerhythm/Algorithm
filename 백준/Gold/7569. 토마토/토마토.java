import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int H, N, M;
    static int[][][] map;
    static int[][][] days;
    static int maxDay = 0;
    static List<int[]> greenTomatoes = new LinkedList<>();
    static List<int[]> redTomatoes = new LinkedList<>();
    static int[] dhs = {-1, 0, 1, 0, 0, 0};
    static int[] dns = {0, 0, 0, 0, 1, -1};
    static int[] dms = {0, 1, 0, -1, 0, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        M = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        H = Integer.parseInt(split[2]);

        map = new int[H][N][M];

        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                split = br.readLine().split(" ");
                for (int k=0; k<M; k++) {
                    int tomato = Integer.parseInt(split[k]);
                    if (tomato == 0) {
                        greenTomatoes.add(new int[] {i, j, k});
                    } else if (tomato == 1) {
                        redTomatoes.add(new int[] {i, j, k});
                    }
                    map[i][j][k] = tomato;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for (int[] t : redTomatoes) {
            q.add(new int[] {t[0], t[1], t[2], 0});
        }

        while (!q.isEmpty()) {
            int[] tomato = q.poll();
            int day = tomato[3];
            maxDay = Math.max(maxDay, day);
            for (int i=0; i<6; i++) {
                int nh = tomato[0] + dhs[i];
                int nn = tomato[1] + dns[i];
                int nm = tomato[2] + dms[i];
                if (!inRnage(nh, nn, nm)) { // 범위를 벗어나는 경우
                    continue;
                }
                if (map[nh][nn][nm] == 1 || map[nh][nn][nm] == -1) { // 이미 익은 토마토이거나 빈 공간인 경우
                    continue;
                }
                map[nh][nn][nm] = 1;
                q.add(new int[] {nh, nn, nm, day + 1});
            }
        }

        for (int[] gt: greenTomatoes) {
            int h = gt[0];
            int n = gt[1];
            int m = gt[2];
            if (map[h][n][m] == 0) { // 익지 않은 토마토가 하나라도 남은 경우
                System.out.println(-1);
                return;
            }
        }

        System.out.println(maxDay);
    }

    private static boolean inRnage(int h, int n, int m) {
        return 0 <= h && h < H && 0 <= n && n < N && 0 <= m && m < M;
    }
}
