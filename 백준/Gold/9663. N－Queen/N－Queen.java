import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] board;
    static int answer = 0;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        board = new int[N][N];

        int[] queenRow = new int[N];
        for (int i=0; i<N; i++) {
            queenRow[i] = -1;
        }

        f(0, queenRow);
        System.out.println(answer);
    }

    private static void f(int line, int[] queenRow) {
        if (line == N) {
            answer++;
            return;
        }

        for (int i=0; i<N; i++) {
            if (hasRow(queenRow, i)) {
                continue;
            }
            queenRow[line] = i;
            if (!check(queenRow, line + 1)) { // 대각선에 존재하는 경우
                queenRow[line] = -1;
                continue;
            }
            f(line + 1, queenRow);
            queenRow[line] = -1;
        }
    }

    private static boolean hasRow(int[] row, int i) {
        for (int r : row) {
            if (r == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean check(int[] queenRow, int size) {
        for (int i=0; i<size; i++) {
            for (int ni=i+1; ni<size; ni++) {
                int j = queenRow[i];
                int nj = queenRow[ni];
                if (Math.abs(i - ni) == Math.abs(j - nj)) { // 대각선에 존재하는 경우
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkNQueen(int i, int j) {
        int[] dis = {-1, 1, 1, -1};
        int[] djs = {1, 1, -1, -1};

        for (int dist = 1; dist <= N; dist++) {
            for (int k=0; k<4; k++) {
                int ni = i + dis[k] * dist;
                int nj = j + djs[k] * dist;

                if (inRange(ni, nj)) {
                    if (board[ni][nj] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}
