import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_ARCHER = 3;

    static int N, M;
    static int D;
    static int[][] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        boolean[] archers = new boolean[M];

        arrange(0, 0, archers);
        System.out.println(answer);
    }

    private static void arrange(int start, int cnt, boolean[] archers) {
        if (cnt == MAX_ARCHER) {
            answer = Math.max(answer, defense(archers));
            return;
        }

        for (int i = start; i < M; i++) {
            archers[i] = true;
            arrange(i + 1, cnt + 1, archers);
            archers[i] = false;
        }
    }

    private static int defense(boolean[] archers) {
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = arr[i][j];
            }
        }

        int cnt = 0;
        while (!isDone(board)) {
            cnt += attack(board, archers);
//            System.out.println("attack");
//            print(board);
            move(board);
//            System.out.println("move");
//            print(board);
        }
        return cnt;
    }

    private static void print(int[][] board) {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    private static void move(int[][] board) {
        for (int n = N - 1; n >= 1; n--) {
            for (int m = 0; m < M; m++) {
                board[n][m] = board[n - 1][m];
            }
        }
        for (int m = 0; m < M; m++) {
            board[0][m] = 0;
        }
    }

    private static int attack(int[][] board, boolean[] archers) {
        int cnt = 0;
        boolean[][] dead = new boolean[N][M];
        for (int i = 0; i < M; i++) {
            if (!archers[i]) continue;
            int[] target = scan(board, N, i);
            if (target[0] == -1) continue; // 공격할 대상이 범위 내에 없는 경우
            int tn = target[0];
            int tm = target[1];
            dead[tn][tm] = true;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dead[i][j]) {
                    board[i][j] = 0;
                    cnt++;
                }
            }
        }
        
        return cnt;
    }

    private static int[] scan(int[][] board, int n, int m) {
        int[] target = new int[2];
        target[0] = -1;

        int[] dns = {0, -1, 0};
        int[] dms = {-1, 0, 1};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {n, m, 0});

        boolean[][] visited = new boolean[N][M];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            n = poll[0];
            m = poll[1];
            int dist = poll[2];
            if (dist > D) break;
            if (inRange(n, m) && board[n][m] == 1) {
                target[0] = n;
                target[1] = m;
                break;
            }

            for (int i = 0; i < 3; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) continue;
                if (visited[nn][nm]) continue;
                visited[nn][nm] = true;
                queue.add(new int[] {nn, nm, dist + 1});
            }
        }

        return target;
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    private static boolean isDone(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) return false;
            }
        }
        return true;
    }
}
