import java.io.*;

public class Main {

    static int N;
    static int[][] board;
    static int tmpCnt = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i=0; i<N; i++) {
            String[] split = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = 0;
        f(0, 0);
        answer += tmpCnt;
        tmpCnt = 0;
        f(1, 0);
        answer += tmpCnt;

        bw.write(answer + "\n");
        bw.flush();
    }

    private static void f(int posNum, int cnt) {
        if (posNum >= N * N) {
            tmpCnt = Math.max(tmpCnt, cnt);
            return;
        }

        int inc = calcInc(posNum);

        int x = posNum / N;
        int y = posNum % N;
        if (board[x][y] == 0) { // 비숍을 놓지 못하는 경우
            f(posNum + inc, cnt);
            return;
        }
        if (isValid(x, y)) { // 비숍을 놓을 수 있는 경우
            board[x][y] = 2;
            f(posNum + inc, cnt + 1);
            board[x][y] = 1;
        }

        f(posNum + inc, cnt); // 비숍을 그냥 놓지 않는 경우
    }

    private static boolean isValid(int x, int y) {
        int[] dxs = {-1, 1, 1, -1};
        int[] dys = {1, 1, -1, -1};

        for (int i=0; i<4; i++) {
            int rate = 1;
            while (true) {
                int nx = x + dxs[i] * rate;
                int ny = y + dys[i] * rate;
                if (!inRange(nx, ny)) {
                    break;
                }
                if (board[nx][ny] == 2) {
                    return false;
                }
                rate++;
            }
        }
        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static int calcInc(int posNum) {
        if (N % 2 > 0)                return 2; // 체스판의 크기가 홀수인 경우
                                                // 체스판의 크기가 짝수인 경우(아래)
        if (posNum % N == N - 1)      return 1; // 마지막 칸인 경우
        else if (posNum % N == N - 2) return 3; // 마지막에서 두 번째 칸인 경우
        else                          return 2; // 그외
    }
}
