import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 스도쿠
public class Main {

    static boolean found = false;
    static int[][] board;
    static List<Unknown> unknowns = new ArrayList<>();
    static class Unknown {
        int x, y;

        Unknown (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];

        for (int i=0; i<9; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<9; j++) {
                if (arr[j] == 0) {
                    unknowns.add(new Unknown(i, j));
                }
            }
            board[i] = arr;
        }

        int unknownIdx = 0;
        play(unknownIdx);
    }

    private static void play(int unknownIdx) {
        if (found) {
            return;
        }
        Unknown unknown = unknowns.get(unknownIdx);
        int x = unknown.x;
        int y = unknown.y;
        for (int i=1; i<=9; i++) {
            if (!canWriteNumber(unknown, i)) { // 숫자 i를 못 적는 경우
                continue;
            }
            board[x][y] = i;
            if (unknownIdx == unknowns.size() - 1) {
                printBoard();
                found = true;
                return;
            }
            play(unknownIdx + 1);
            board[x][y] = 0;
        }
    }

    private static void printBoard() {
        for (int[] row : board) {
            for (int r : row) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }

    private static boolean canWriteNumber(Unknown unknown, int number) {
        return canRow(unknown, number) && canColumn(unknown, number) && canSquare(unknown, number);
    }

    private static boolean canRow(Unknown unknown, int number) {
        int x = unknown.x;

        for (int i=0; i<9; i++) {
            if (board[x][i] == number) {
                return false;
            }
        }

        return true;
    }

    private static boolean canColumn(Unknown unknown, int number) {
        int y = unknown.y;

        for (int i=0; i<9; i++) {
            if (board[i][y] == number) {
                return false;
            }
        }

        return true;
    }

    private static boolean canSquare(Unknown unknown, int number) {
        int x = unknown.x;
        int y = unknown.y;
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[startX + i][startY + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }
}
