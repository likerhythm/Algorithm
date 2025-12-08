import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, n, m, K;
    static int[][] map;
    static int[] commands;
    static int[] dns = {0, 0, 0, -1, 1};
    static int[] dms = {0, 1, -1, 0, 0};

    static class Dice {

        private int top, bottom, front, back, left, right;
        private Runnable[] moveMethods = {
                () -> {},
                this::moveEast,
                this::moveWest,
                this::moveNorth,
                this::moveSouth
        };

        public void move(int command) {
            moveMethods[command].run();
        }

        public void moveNorth() {
            int tmp = top;
            top = front;
            front = bottom;
            bottom = back;
            back = tmp;
        }

        public void moveEast() {
            int tmp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = tmp;
        }

        public void moveSouth() {
            int tmp = top;
            top = back;
            back = bottom;
            bottom = front;
            front = tmp;
        }

        public void moveWest() {
            int tmp = top;
            top = right;
            right = bottom;
            bottom = left;
            left = tmp;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int num) {
            this.bottom = num;
        }

        public int getTop() {
            return this.top;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        commands = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Dice dice = new Dice();
        for (int command : commands) {
            int nn = n + dns[command];
            int nm = m + dms[command];
            if (!inRange(nn, nm)) continue;
            dice.move(command);
            if (map[nn][nm] == 0) {
                map[nn][nm] = dice.getBottom();
            } else {
                dice.setBottom(map[nn][nm]);
                map[nn][nm] = 0;
            }
            n = nn;
            m = nm;
            System.out.println(dice.getTop());
        }
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
