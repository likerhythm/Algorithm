import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static List<int[]> places = new ArrayList<>();
    static int[][] selected;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        selected = new int[M][2];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (split[j] == 2) places.add(new int[] {i, j});
                map[i][j] = split[j];
            }
        }

        backtracking(0, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void backtracking(int cnt, int start) {
        if (cnt == M) {
            // 바이러스 퍼뜨리기
            virus();
            return;
        }

        for (int i = start; i < places.size(); i++) {
            selected[cnt][0] = places.get(i)[0];
            selected[cnt][1] = places.get(i)[1];
            backtracking(cnt + 1, i + 1);
        }
    }

    static class Node {
        int x, y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    private static void virus() {
        Queue<Node> q = new LinkedList<>();
        int[][] timeMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(timeMap[i], -1);
        }
        for (int[] s : selected) {
            int x = s[0];
            int y = s[1];
            timeMap[x][y] = 0;
            q.add(new Node(x, y, 0));
        }

        int[] dxs = {-1, 0, 1, 0};
        int[] dys = {0, 1, 0, -1};
        int maxTime = 0;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int time = poll.time;
            maxTime = Math.max(maxTime, time);

            for (int i = 0; i < 4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                if (!inRange(nx, ny)) continue; // 범위를 벗어나는 경우
                if (map[nx][ny] == 1) continue; // 벽인 경우
                if (timeMap[nx][ny] > -1) continue; // 이미 바이러스가 있는 경우

                q.add(new Node(nx, ny, time + 1));
                timeMap[nx][ny] = time + 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1 && timeMap[i][j] == -1) return; // 바이러스가 다 퍼지지 못한 경우
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(timeMap[i]));
//        }

        answer = Math.min(answer, maxTime);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
