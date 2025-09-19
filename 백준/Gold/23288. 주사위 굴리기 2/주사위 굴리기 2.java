import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M, K;
    static int[][] board;
    static int[] dns = {-1, 0, 1, 0}, dms = {0, 1, 0, -1};

    static class Dice {
        int top = 1, bot = 6, left = 4, right = 3, back = 2, front = 5;
        int nowN, nowM, nowD;

        public Dice() {
            this.nowD = 1; // 초기 방향 설정
        }

        public void goLeft() {
            int tmp = top;
            this.top = this.right;
            this.right = this.bot;
            this.bot = this.left;
            this.left = tmp;

            nowN += dns[3];
            nowM += dms[3];
        }

        public void goRight() {
            int tmp = top;
            this.top = this.left;
            this.left = this.bot;
            this.bot = this.right;
            this.right = tmp;

            nowN += dns[1];
            nowM += dms[1];
        }

        public void goDown() {
            int tmp = top;
            this.top = this.back;
            this.back = this.bot;
            this.bot = this.front;
            this.front = tmp;

            nowN += dns[2];
            nowM += dms[2];
        }

        public void goUp() {
            int tmp = top;
            this.top = this.front;
            this.front = this.bot;
            this.bot = this.back;
            this.back = tmp;

            nowN += dns[0];
            nowM += dms[0];
        }

        public void turnRight() {
            nowD = (nowD + 1) % 4;
        }

        public void turnLeft() {
            nowD -= 1;
            if (nowD < 0) {
                nowD = 3;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        K = split[2];

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Dice dice = new Dice();
        int answer = 0;
        for (int k = 0; k < K; k++) {
            int d = dice.nowD;
            switch (d) {
                case 0: {
                    dice.goUp();
                    break;
                }
                case 1: {
                    dice.goRight();
                    break;
                }
                case 2: {
                    dice.goDown();
                    break;
                }
                case 3: {
                    dice.goLeft();
                    break;
                }
            }

            answer += bfs(dice.nowN, dice.nowM);

            int bot = dice.bot;
            int n = dice.nowN, m = dice.nowM;
            if (bot < board[n][m]) {
                // 이동 방향을 90도 반시계 방향으로 회전
                dice.turnLeft();
            } else if (bot > board[n][m]) {
                // 이동 방향을 90도 시계 방향으로 회전
                dice.turnRight();
            }

            if (!canGo(dice)) {
                dice.nowD = (dice.nowD + 2) % 4; // 반대방향
            }

//            System.out.println("n = " + dice.nowN + ", m = " + dice.nowM);
//            System.out.println("bot = " + dice.bot);
//            System.out.println("num = " + board[dice.nowN][dice.nowM]);
//            System.out.println("dir = " + dice.nowD);
//            System.out.println("---");
        }

        System.out.println(answer);
    }

    private static int bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m});
        boolean[][] visited = new boolean[N][M];
        visited[n][m] = true;

        int num = board[n][m];
        int result = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            result += num;

            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];

                if (!inRange(nn, nm)) {
                    continue;
                }

                if (board[nn][nm] != num) {
                    continue;
                }

                if (visited[nn][nm]) {
                    continue;
                }

                q.add(new int[] {nn, nm});
                visited[nn][nm] = true;
            }
        }

        return result;
    }

    private static boolean canGo(Dice dice) {
        int n = dice.nowN, m = dice.nowM;
        int d = dice.nowD;
        int nn = n + dns[d], nm = m + dms[d];

        return inRange(nn, nm);
    }

    private static boolean inRange(int nn, int nm) {
        return 0 <= nn && nn < N && 0 <= nm && nm < M;
    }
}
