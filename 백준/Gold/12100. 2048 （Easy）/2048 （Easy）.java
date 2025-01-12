import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static final int MAX_MOVE_CNT = 5;
    static int maxScore = 0;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        int[][] board = new int[N][N];

        for (int i=0; i<N; i++) {
            String[] split = bf.readLine().split(" ");
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        if (N == 1) {
            System.out.println(board[0][0]);
        } else {
            move(board, 0);
            System.out.println(maxScore);
        }
    }

    private static void printBoard(int[][] board) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static void move(int[][] board, int moveCount) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] > maxScore) {
                    maxScore = board[i][j];
                }
            }
        }
        if (moveCount >= MAX_MOVE_CNT) {
            return;
        }
        moveUp(board, moveCount);
        moveRight(board, moveCount);
        moveLeft(board, moveCount);
        moveDown(board, moveCount);
    }

    private static void moveUp(int[][] board, int moveCount) {
        int[][] tempBoard = new int[N][N];
        boolean[][] isApplied = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                isApplied[i][j] = false;
            }
        }

        for (int i=1; i<N; i++) { // 합쳐질 수 있는 것들 먼저 합쳐서 tempBoard에 등록
            for (int j=0; j<N; j++) {
                for (int k=i-1; k>=0; k--) {
                    if (board[k][j] > 0 && board[k][j] != board[i][j]) {
                        break;
                    }
                    if (board[i][j] > 0 && board[k][j] == board[i][j] && !isApplied[k][j]) {
                        tempBoard[k][j] = board[k][j] * 2;
                        isApplied[i][j] = true;
                        isApplied[k][j] = true;
                    }
                }
            }
        }

        for (int i=0; i<N; i++) { // 합쳐지지 않은 숫자들 tempBoard에 등록
            for (int j=0; j<N; j++) {
                if (!isApplied[i][j] && board[i][j] > 0) {
                    tempBoard[i][j] = board[i][j];
                }
            }
        }

        for (int j=0; j<N; j++) { // tempBoard의 각 숫자들 위로 옮기기
            for (int i=1; i<N; i++) {
                if (tempBoard[i][j] > 0) {
                    int k = i - 1;
                    while (k >= 0 && tempBoard[k][j] == 0) {
                        k--;
                    }
                    k++;
                    tempBoard[k][j] = tempBoard[i][j];
                    if (k < i) {
                        tempBoard[i][j] = 0;
                    }
                }
            }
        }
//        printBoard(tempBoard);
        move(tempBoard, moveCount + 1);
    }

    private static void moveRight(int[][] board, int moveCount) {
        int[][] tempBoard = new int[N][N];
        boolean[][] isApplied = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                isApplied[i][j] = false;
            }
        }

        for (int j=N-2; j>=0; j--) { // 합쳐질 수 있는 것들 먼저 합쳐서 tempBoard에 등록
            for (int i=0; i<N; i++) {
                for (int k=j+1; k<N; k++) {
                    if (board[i][k] > 0 && board[i][k] != board[i][j]) {
                        break;
                    }
                    if (board[i][j] > 0 && board[i][k] == board[i][j] && !isApplied[i][k]) {
                        tempBoard[i][k] = board[i][k] * 2;
                        isApplied[i][j] = true;
                        isApplied[i][k] = true;
                    }
                }
            }
        }

        for (int i=0; i<N; i++) { // 합쳐지지 않은 숫자들 tempBoard에 등록
            for (int j=0; j<N; j++) {
                if (!isApplied[i][j] && board[i][j] > 0) {
                    tempBoard[i][j] = board[i][j];
                }
            }
        }

        for (int i=0; i<N; i++) { // tempBoard의 각 숫자들 오른쪽로 옮기기
            for (int j=N-2; j>=0; j--) {
                if (tempBoard[i][j] > 0) {
                    int k = j + 1;
                    while (k < N && tempBoard[i][k] == 0) {
                        k++;
                    }
                    k--;
                    tempBoard[i][k] = tempBoard[i][j];
                    if (k > j) {
                        tempBoard[i][j] = 0;
                    }
                }
            }
        }
        move(tempBoard, moveCount + 1);
    }

    private static void moveLeft(int[][] board, int moveCount) {
        int[][] tempBoard = new int[N][N];
        boolean[][] isApplied = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                isApplied[i][j] = false;
            }
        }

        for (int j=1; j<N; j++) { // 합쳐질 수 있는 것들 먼저 합쳐서 tempBoard에 등록
            for (int i=0; i<N; i++) {
                for (int k=j-1; k>=0; k--) {
                    if (board[i][k] > 0 && board[i][k] != board[i][j]) {
                        break;
                    }
                    if (board[i][j] > 0 && board[i][k] == board[i][j] && !isApplied[i][k]) {
                        tempBoard[i][k] = board[i][k] * 2;
                        isApplied[i][j] = true;
                        isApplied[i][k] = true;
                    }
                }
            }
        }

        for (int i=0; i<N; i++) { // 합쳐지지 않은 숫자들 tempBoard에 등록
            for (int j=0; j<N; j++) {
                if (!isApplied[i][j] && board[i][j] > 0) {
                    tempBoard[i][j] = board[i][j];
                }
            }
        }

        for (int i=0; i<N; i++) { // tempBoard의 각 숫자들 오른쪽로 옮기기
            for (int j=1; j<N; j++) {
                if (tempBoard[i][j] > 0) {
                    int k = j - 1;
                    while (k >= 0 && tempBoard[i][k] == 0) {
                        k--;
                    }
                    k++;
                    tempBoard[i][k] = tempBoard[i][j];
                    if (k < j) {
                        tempBoard[i][j] = 0;
                    }
                }
            }
        }
        move(tempBoard, moveCount + 1);
    }

    private static void moveDown(int[][] board, int moveCount) {
        int[][] tempBoard = new int[N][N];
        boolean[][] isApplied = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                isApplied[i][j] = false;
            }
        }

        for (int i=N-2; i>=0; i--) { // 합쳐질 수 있는 것들 먼저 합쳐서 tempBoard에 등록
            for (int j=0; j<N; j++) {
                for (int k=i+1; k<N; k++) {
                    if (board[k][j] > 0 && board[k][j] != board[i][j]) {
                        break;
                    }
                    if (board[i][j] > 0 && board[k][j] == board[i][j] && !isApplied[k][j]) {
                        tempBoard[k][j] = board[k][j] * 2;
                        isApplied[i][j] = true;
                        isApplied[k][j] = true;
                    }
                }
            }
        }

        for (int i=0; i<N; i++) { // 합쳐지지 않은 숫자들 tempBoard에 등록
            for (int j=0; j<N; j++) {
                if (!isApplied[i][j] && board[i][j] > 0) {
                    tempBoard[i][j] = board[i][j];
                }
            }
        }

        for (int j=0; j<N; j++) { // tempBoard의 각 숫자들 아래로 옮기기
            for (int i=N-2; i>=0; i--) {
                if (tempBoard[i][j] > 0) {
                    int k = i + 1;
                    while (k < N && tempBoard[k][j] == 0) {
                        k++;
                    }
                    k--;
                    tempBoard[k][j] = tempBoard[i][j];
                    if (k > i) {
                        tempBoard[i][j] = 0;
                    }
                }
            }
        }
        move(tempBoard, moveCount + 1);
    }
}
