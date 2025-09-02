import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    static int N, K, R;
    static Node[][] map;
    static boolean[][] cowExist;
    static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

    static class Node {
        int x, y;
        boolean[] canGo = {true, true, true, true}; // 위, 오, 아, 왼

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setCanNotGo(int d) {
            canGo[d] = false;
        }

        public boolean canGo(int d) {
            return canGo[d];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        K = split[1];
        R = split[2];

        map = new Node[N][N];
        cowExist = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Node(i, j);
            }
        }
        for (int i = 0; i < R; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = split[0] - 1, y1 = split[1] - 1, x2 = split[2] - 1, y2 = split[3] - 1;
            if (x1 < x2) {
                map[x1][y1].setCanNotGo(DOWN);
                map[x2][y2].setCanNotGo(UP);
            } else if (x1 > x2) {
                map[x1][y1].setCanNotGo(UP);
                map[x2][y2].setCanNotGo(DOWN);
            } else if (y1 < y2) {
                map[x1][y1].setCanNotGo(RIGHT);
                map[x2][y2].setCanNotGo(LEFT);
            } else if (y1 > y2) {
                map[x1][y1].setCanNotGo(LEFT);
                map[x2][y2].setCanNotGo(RIGHT);
            }
        }

        for (int i = 0; i < K; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = split[0] - 1, y = split[1] - 1;
            cowExist[x][y] = true;
        }

        List<Integer> cowCnt = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    int cnt = bfs(i, j, visited);
                    cowCnt.add(idx++, cnt);
                }
            }
        }

        if (cowCnt.size() == 1) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        for (int i = 0; i < cowCnt.size(); i++) {
            for (int j = i + 1; j < cowCnt.size(); j++) {
                if (i == j) {
                    continue;
                }
                answer += cowCnt.get(i) * cowCnt.get(j);
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[] {x, y});

        int cowCnt = 0;
        int[] dxs = {-1, 0, 1, 0};
        int[] dys = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            x = poll[0];
            y = poll[1];
            if (cowExist[x][y]) {
                cowCnt++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];

                if (!inRange(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (nx > x && !map[x][y].canGo(DOWN)) continue;
                else if (nx < x && !map[x][y].canGo(UP)) continue;
                else if (ny > y && !map[x][y].canGo(RIGHT)) continue;
                else if (ny < y && !map[x][y].canGo(LEFT)) continue;

                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }

        return cowCnt;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
