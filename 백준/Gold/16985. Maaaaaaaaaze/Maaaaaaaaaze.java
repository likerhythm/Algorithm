import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static Floor[] floors;
    static int[][][] maze;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        floors = new Floor[5];
        maze = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            int[][] floor = new int[5][5];
            for (int j = 0; j < 5; j++) {
                floor[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            floors[i] = new Floor(floor);
        }

        int[] tmpMaze = new int[5];
        Arrays.fill(tmpMaze, -1);
        setFloor(tmpMaze, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    static class Floor {
        int[][] floor;

        Floor(int[][] floor) {
            this.floor = floor;
        }

        int[][] rotate(int rate) { // 시계 방향 0, 90, 180, 270도 회전
            int[][] tmp = floor;
            int[][] rotatedFloor = new int[5][5];
            deepCopyArray(floor, rotatedFloor);

            for (int i = 0; i < rate / 90; i++) {
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        rotatedFloor[col][4 - row] = tmp[row][col];
                    }
                }
                deepCopyArray(rotatedFloor, tmp);
            }
            return rotatedFloor;
        }

        private void deepCopyArray(int[][] origin, int[][] other) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    other[i][j] = origin[i][j];
                }
            }
        }
    }

    private static void setFloor(int[] tmpMaze, int idx) {
        if (idx == 5) {
            setRotateAndRun(tmpMaze, 0);
            return;
        }

        for (int f = 0; f < 5; f++) {
            boolean visited = false;
            for (int m = 0; m < idx; m++) {
                if (tmpMaze[m] == f) {
                    visited = true;
                    break;
                }
            }

            if (visited) continue;
            tmpMaze[idx] = f;
            setFloor(tmpMaze, idx + 1);
            if (answer == -1) return;
            tmpMaze[idx] = -1;
        }
    }

    private static void setRotateAndRun(int[] tmpMaze, int idx) {
        if (idx == 5) {
            run(maze);
            return;
        }

        for (int rate = 0; rate <= 270; rate += 90) {
            int[][] tmp = floors[tmpMaze[idx]].rotate(rate);
            maze[idx] = tmp;
            setRotateAndRun(tmpMaze, idx + 1);
            if (answer == -1) return;
        }
    }

    static class Bfs {
        int x, y, z, count;

        Bfs(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }

    static int[] dxs = {-1, 0, 1, 0, 0, 0};
    static int[] dys = {0, 1, 0, -1, 0, 0};
    static int[] dzs = {0, 0, 0, 0, -1, 1};

    // (0, 0, 0)이 시작점, (4, 4, 4)가 도착점
    private static void run(int[][][] maze) {
        if (maze[0][0][0] == 0) return;
        if (maze[4][4][4] == 0) return;

        Queue<Bfs> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        visited[0][0][0] = true;
        q.add(new Bfs(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Bfs poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int z = poll.z;
            int count = poll.count;

            if (x == 4 && y == 4 && z == 4) {
                answer = Math.min(answer, count);
                return;
            }
            for (int i = 0; i < 6; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                int nz = z + dzs[i];
                if (!inRange(nx, ny, nz)) continue;
                if (visited[nx][ny][nz]) continue;
                if (maze[nx][ny][nz] == 0) continue;
                visited[nx][ny][nz] = true;
                q.add(new Bfs(nx, ny, nz, count + 1));
            }
        }
    }

    private static boolean inRange(int x, int y, int z) {
        return inMaze(x) && inMaze(y) && inMaze(z);
    }

    private static boolean inMaze(int v) {
        return 0 <= v && v < 5;
    }
}
