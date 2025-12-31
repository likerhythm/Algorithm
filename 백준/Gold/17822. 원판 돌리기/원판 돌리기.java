import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T;
    static Boards boards;
    static Command[] commands;

    static class Boards {
        Board[] instances;

        Boards(Board[] instances) {
            this.instances = instances;
        }

        public void rotateOnly(int idx, int dir, int cnt) {
            instances[idx].rotate(dir, cnt);
        }

        public void processAfterRotation() {
            boolean hasRemoved = removeAdjacent();

            if (!hasRemoved) {
                updateByAverage();
            }
        }

        private boolean removeAdjacent() {
            boolean isRemoved = false;
            boolean[][] visited = new boolean[N + 1][M];

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (instances[i].numbers[j] == 0) continue;
                    if (visited[i][j]) continue;

                    if (bfs(i, j, visited)) {
                        isRemoved = true;
                    }
                }
            }
            return isRemoved;
        }

        private boolean bfs(int r, int c, boolean[][] visited) {
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{r, c});
            visited[r][c] = true;

            List<int[]> targetList = new ArrayList<>();
            targetList.add(new int[]{r, c});

            int targetNum = instances[r].numbers[c];

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int currR = curr[0];
                int currC = curr[1];

                for (int i = 0; i < 4; i++) {
                    int nextR = currR + dx[i];
                    int nextC = currC + dy[i];

                    if (nextR < 1 || nextR > N) continue;

                    if (nextC == -1) nextC = M - 1;
                    else if (nextC == M) nextC = 0;

                    if (!visited[nextR][nextC] && instances[nextR].numbers[nextC] == targetNum) {
                        visited[nextR][nextC] = true;
                        q.add(new int[]{nextR, nextC});
                        targetList.add(new int[]{nextR, nextC});
                    }
                }
            }

            if (targetList.size() > 1) {
                for (int[] pos : targetList) {
                    instances[pos[0]].numbers[pos[1]] = 0;
                }
                return true;
            }

            return false;
        }

        private void updateByAverage() {
            double sum = 0;
            int count = 0;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (instances[i].numbers[j] != 0) {
                        sum += instances[i].numbers[j];
                        count++;
                    }
                }
            }

            if (count == 0) return;

            double avg = sum / count;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (instances[i].numbers[j] != 0) {
                        if (instances[i].numbers[j] > avg) {
                            instances[i].numbers[j]--;
                        } else if (instances[i].numbers[j] < avg) {
                            instances[i].numbers[j]++;
                        }
                    }
                }
            }
        }

        public int sum() {
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    sum += instances[i].numbers[j];
                }
            }
            return sum;
        }
    }

    // Board 클래스는 기존 로직이 맞으므로 그대로 사용
    static class Board {
        int[] numbers;
        Board(int[] numbers) { this.numbers = numbers; }
        public void rotate(int dir, int cnt) {
            int len = numbers.length;
            cnt %= len;
            if (dir == 1) cnt = len - cnt;
            int[] tmp = new int[len];
            for (int i = 0; i < len; i++) tmp[(i + cnt) % len] = numbers[i];
            numbers = tmp;
        }
    }

    static class Command {
        int x, d, k;
        Command(int x, int d, int k) { this.x = x; this.d = d; this.k = k; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        Board[] boardInstance = new Board[N + 1];
        for (int i = 1; i <= N; i++) {
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boardInstance[i] = new Board(numbers);
        }
        boards = new Boards(boardInstance);

        commands = new Command[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            commands[i] = new Command(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (Command cmd : commands) {
            for (int i = 1; i <= N; i++) {
                if (i % cmd.x == 0) {
                    boards.rotateOnly(i, cmd.d, cmd.k);
                }
            }

            boards.processAfterRotation();
        }

        System.out.println(boards.sum());
    }
}