import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 연구소
public class Main {

    static int N, M; // 세로, 가로
    static int[][] map;
    static int answer = 0;
    static List<int[]> viruses = new ArrayList<>();
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        map = new int[N][];

        for (int i=0; i<N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<M; j++) {
                if (input[j] == 2) {
                    viruses.add(new int[] {i, j});
                }
            }
            map[i] = input;
        }

        int[] walls = {-1, -1, -1};
        setWalls(walls, 0, 0);
        System.out.println(answer);
    }

    private static void setWalls(int[] walls, int cnt, int start) {
        if (cnt == 3) {
            int safeCnt = bfs(walls);
            answer = Math.max(answer, safeCnt);
            return;
        }

        for (int i=start; i<N*M; i++) {
            int n = i / M;
            int m = i % M;
            if (map[n][m] == 1 || map[n][m] == 2) {
                continue;
            }
            walls[cnt] = i;
            setWalls(walls, cnt + 1, i + 1);
        }
    }

    private static int bfs(int[] walls) {
        int[][] tempMap = new int[N][M];
        for (int i=0; i<N; i++) {
            tempMap[i] = map[i].clone();
        }

        for (int w : walls) { // 벽 세우기
            tempMap[w / M][w % M] = 1;
        }

        boolean[][] visited = new boolean[N][M];

        Queue<int[]> q = new LinkedList<>(viruses);
        for (int[] idx : viruses) {
            visited[idx[0]][idx[1]] = true;
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int n = poll[0];
            int m = poll[1];
            tempMap[n][m] = 2;

            for (int i=0; i<4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) {
                    continue;
                }

                if (visited[nn][nm]) {
                    continue;
                }

                if (tempMap[nn][nm] == 0) {
                    q.add(new int[] {nn, nm});
                    visited[nn][nm] = true;
                }
            }
        }

//        System.out.println("tempMap");
//        printMap(tempMap);
//        System.out.println("map");
//        printMap(map);

        int cnt = 0;
        for (int[] m : tempMap) {
            for (int i : m) {
                if (i == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void printMap(int[][] map) {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    private static boolean inRange(int n, int m) {
        return 0<=n && n<N && 0<=m && m<M;
    }
}
