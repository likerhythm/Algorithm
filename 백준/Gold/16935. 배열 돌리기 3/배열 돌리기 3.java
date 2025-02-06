import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, M, R;
    static int[][] board;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        R = Integer.parseInt(split[2]);

        board = new int[N][M];
        for (int i=0; i<N; i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        String[] commands = br.readLine().split(" ");

        for (int i=0; i<R; i++) {
            if (commands[i].equals("1")) upToDown(); // 상하반전
            if (commands[i].equals("2")) leftToRight(); // 좌우반전
            if (commands[i].equals("3")) right90(); // 오른쪽 90도
            if (commands[i].equals("4")) left90(); // 왼쪽 90도
            if (commands[i].equals("5")) clockwise(); // 각 부분을 시계방향 회전
            if (commands[i].equals("6")) counterClockWise(); // 각 부분을 반시계방향 회전
        }

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void upToDown() {
        N = board.length;
        M = board[0].length;
        int[][] temp = new int[N][M];

        for (int i=0; i<N/2; i++) {
            for (int j=0; j<M; j++) {
                temp[N - i - 1][j] = board[i][j];
                temp[i][j] = board[N - i - 1][j];
            }
        }

        board = temp;
    }

    private static void leftToRight() {
        N = board.length;
        M = board[0].length;
        int[][] temp = new int[N][M];

        for (int j=0; j<M/2; j++) {
            for (int i=0; i<N; i++) {
                temp[i][M - j - 1] = board[i][j];
                temp[i][j] = board[i][M - j - 1];
            }
        }

        board = temp;
    }

    private static void right90() {
        N = board.length;
        M = board[0].length;
        int[][] temp = new int[M][N];

        for (int j=0; j<M; j++) {
            for (int i=0; i<N; i++) {
                temp[j][N - i - 1] = board[i][j];
            }
        }

        board = temp;
    }

    private static void left90() {
        N = board.length;
        M = board[0].length;
        int[][] temp = new int[M][N];

        for (int j=0; j<M; j++) {
            for (int i=0; i<N; i++) {
                temp[M - j - 1][i] = board[i][j];
            }
        }

        board = temp;
    }

    private static void clockwise() {
        N = board.length;
        M = board[0].length;
        int[][] temp = new int[N][M];

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                // 1 -> 2
                if (i < N / 2 && j < M / 2) {
                    temp[i][j + M / 2] = board[i][j];
                }
                // 2 -> 3
                if (i < N / 2 && M / 2 <= j) {
                    temp[i + N / 2][j] = board[i][j];
                }
                // 3 -> 4
                if (N / 2 <= i && M / 2 <= j) {
                    temp[i][j - M / 2] = board[i][j];
                }
                // 4 -> 1
                if (N / 2 <= i && j < M / 2) {
                    temp[i - N / 2][j] = board[i][j];
                }
            }
        }

        board = temp;
    }

    private static void counterClockWise() {
        N = board.length;
        M = board[0].length;
        int[][] temp = new int[N][M];

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                // 1 -> 4
                if (i < N / 2 && j < M / 2) {
                    temp[i + N / 2][j] = board[i][j];
                }
                // 4 -> 3
                if (N / 2 <= i && j < M / 2) {
                    temp[i][j + M / 2] = board[i][j];
                }
                // 3 -> 2
                if (N / 2 <= i && M / 2 <= j) {
                    temp[i - N / 2][j] = board[i][j];
                }
                // 2 -> 1
                if (i < N / 2 && M / 2 <= j) {
                    temp[i][j - M / 2] = board[i][j];
                }
            }
        }

        board = temp;
    }
}
