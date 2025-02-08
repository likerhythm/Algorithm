import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N, L, R;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        L = Integer.parseInt(split[1]);
        R = Integer.parseInt(split[2]);

        map = new int[N][N];

        for (int i=0; i<N; i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = 0;
        while (true) {
            int flag = 0;

            int[][] newMap = new int[N][N];
            visited = new boolean[N][N];

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (!visited[i][j]) {
                        int countries = bfs(i, j, newMap);
//                        System.out.println("countries: " + countries);
                        if (countries == 1) {
                            flag += 1;
                        }
                    }
                }
            }
            if (flag == N * N) {
                break;
            }

            map = newMap;

//            printMap(map);

            answer++;
        }

        System.out.println(answer);
    }

    private static void printMap(int[][] map) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int bfs(int i, int j, int[][] newMap) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> countries = new ArrayList<>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;

        int[] dis = {-1, 0, 1, 0};
        int[] djs = {0, 1, 0, -1};

        int population = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            i = poll[0];
            j = poll[1];
            population += map[i][j];
            countries.add(new int[] {i, j});
            for (int k=0; k<4; k++) {
                int ni = i + dis[k];
                int nj = j + djs[k];
                if (!inRange(ni, nj)) {
                    continue;
                }
                if (visited[ni][nj]) {
                    continue;
                }
                int diff = Math.abs(map[i][j] - map[ni][nj]);
                if (L <= diff && diff <= R) {
                    queue.offer(new int[] {ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }

        int avg = population / countries.size();

        for (int[] country : countries) {
            i = country[0];
            j = country[1];
            newMap[i][j] = avg;
        }

        return countries.size();
    }

    private static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}
