import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] arr;
    static boolean isValid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9][9];
        for (int i = 0; i < 9; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        sudoku(0, 0);
        for (int n = 0; n < 9; n++) {
            for (int m = 0; m < 9; m++) {
                System.out.print(arr[n][m]);
            }
            System.out.println();
        }
    }

    private static void sudoku(int n, int m) {
        if (n == 9) {
            isValid = true;
            return;
        }

        int nn, nm;
        if (m == 8) {
            nn = n + 1;
            nm = 0;
        } else {
            nn = n;
            nm = m + 1;
        }

        if (arr[n][m] > 0) {
            sudoku(nn, nm);
            return;
        }

        for (int num = 1; num <= 9; num++) {
            if (!checkRow(n, num) || !checkCol(m, num) || !checkBox(n, m, num)) continue;
            arr[n][m] = num;
            sudoku(nn, nm);
            if (isValid) break;
            arr[n][m] = 0;
        }
    }

    private static boolean checkBox(int n, int m, int num) {
        int startN = n - (n % 3);
        int startM = m - (m % 3);
        for (n = startN; n < startN + 3; n++) {
            for (m = startM; m < startM + 3; m++) {
                if (arr[n][m] == num) return false;
            }
        }
        return true;
    }

    private static boolean checkCol(int m, int num) {
        for (int n = 0; n < 9; n++) {
            if (arr[n][m] == num) return false;
        }
        return true;
    }

    private static boolean checkRow(int n, int num) {
        for (int m = 0; m < 9; m++) {
            if (arr[n][m] == num) return false;
        }
        return true;
    }
}
