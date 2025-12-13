import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] arr;
    static int answer;
    static int r, c; // 토네이도의 현재 위치
    static int[] sr = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    static int[] sc = {0, -1, 0, 1, -2, -1, 0, 1, 0};
    static int[] percent = {2, 10, 7, 1, 5, 10, 7, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        run();
        System.out.println(answer);
    }

    private static void run() {
        r = N / 2;
        c = N / 2;

        for (int move = 1; move <= N; move++) {
            for (int i = 0; i < 2; i++) {
                boolean canMove = moveTornado(move);
                rotate(); // 모래밭을 시계방향으로 90도 회전
                if (!canMove) break;
            }
        }
    }

    private static void rotate() { // 회전 마치고 r, c 바꾸기
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[j][N - i - 1] = arr[i][j];
            }
        }
        arr = tmp;
        int tmpr = r;
        r = c;
        c = N - tmpr - 1;
    }

    private static boolean moveTornado(int move) {
        for (int i = 0; i < move; i++) {
            int nr = r;
            int nc = c - 1;
            if (!inRange(nr, nc)) return false;
            spread(nr, nc);
            c = nc;
        }
        return true;
    }

    private static void spread(int r, int c) {
        int sand = arr[r][c];
        for (int i = 0; i < 9; i++) {
            int spr = r + sr[i];
            int spc = c + sc[i];
            int per = percent[i];
            int value = sand * per / 100;
            if (!inRange(spr, spc)) {
                answer += value;
            } else {
                arr[spr][spc] += value;
            }
            arr[r][c] -= value;
        }

        if (!inRange(r, c - 1)) {
            answer += arr[r][c];
        } else {
            arr[r][c - 1] += arr[r][c];
        }
        arr[r][c] = 0;
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static void print(int[][] arr) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
