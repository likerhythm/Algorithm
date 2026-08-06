class Solution {

    int N, M;

    public void gameOfLife(int[][] board) {
        N = board.length;
        M = board[0].length;
        int[][] result = new int[N][M];
        int[] dns = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dms = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    int nn = n + dns[i];
                    int nm = m + dms[i];
                    if (!inRange(nn, nm)) continue;
                    if (board[nn][nm] == 1) count++;
                }
                if (board[n][m] == 0) {
                    if (count == 3) {
                        result[n][m] = 1;
                    }
                } else if (board[n][m] == 1) {
                    if (count == 2 || count == 3) {
                        result[n][m] = 1;
                    }
                }
            }
        }
        for (int n = 0; n < N; n++) {
            System.arraycopy(result[n], 0, board[n], 0, M);
        }
    }

    boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}