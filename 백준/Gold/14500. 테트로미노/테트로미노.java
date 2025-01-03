import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int M;
    static int[][] paper;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    static class Tetromino {

        int nRange;
        int mRange;
        int[] dns;
        int[] dms;

        public Tetromino (int nRange, int mRange, int[] dns, int[] dms) {
            this.nRange = nRange;
            this.mRange = mRange;
            this.dns = dns;
            this.dms = dms;
        }

        public boolean inRange(int n, int m) {
            return N - n >= nRange && M - m >= mRange;
        }

        public int calcScore(int n, int m) {
            int score = 0;
            for (int i=0; i<4; i++) {
                int dn = dns[i];
                int dm = dms[i];

                score += paper[n + dn][m + dm];
            }
            return score;
        }
    }

    public static void main(String[] args) throws IOException {
        String input = bf.readLine();
        String[] split = input.split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        paper = new int[N][M];

        for (int i=0; i<N; i++) {
            split = bf.readLine().split(" ");
            for (int j=0; j<M; j++) {
                paper[i][j] = Integer.parseInt(split[j]);
            }
        }

        Tetromino[] tetrominos = new Tetromino[5];
        tetrominos[0] = new Tetromino(1, 4, new int[] {0, 0, 0, 0}, new int[] {0, 1, 2, 3});
        tetrominos[1] = new Tetromino(2, 2, new int[] {0, 0, 1, 1}, new int[] {0, 1, 0, 1});
        tetrominos[2] = new Tetromino(3, 2, new int[] {0, 1, 2, 2}, new int[] {0, 0, 0, 1});
        tetrominos[3] = new Tetromino(3, 2, new int[] {0, 1, 1, 2}, new int[] {0, 0, 1, 1});
        tetrominos[4] = new Tetromino(2, 3, new int[] {0, 0, 0, 1}, new int[] {0, 1, 2, 1});

        int maxScore = 0;
        for (int reverse=0; reverse<2; reverse++) {
            for (int rotate=0; rotate<4; rotate++) {
                for (int n=0; n<N; n++) {
                    for (int m=0; m<M; m++) {
                        for (int idx=0; idx<5; idx++) {
                            Tetromino tetromino = tetrominos[idx];
                            if (tetromino.inRange(n, m)) {
                                maxScore = Math.max(maxScore, tetromino.calcScore(n, m));
                            }
                        }
                    }
                }
                rotatePaperClock();
            }
            reversePaper();
        }

        System.out.println(maxScore);
    }

    private static void reversePaper() {
        int[][] tempPaper = new int[N][M];

        for (int n=0; n<N; n++) {
            for (int m=0; m<M; m++) {
                tempPaper[n][M - m - 1] = paper[n][m];
            }
        }

        paper = tempPaper;
    }

    private static void rotatePaperClock() {
         int[][] tempPaper = new int[M][N];

         for (int n=0; n<N; n++) {
             for (int m=0; m<M; m++) {
                 tempPaper[m][N - n - 1] = paper[n][m];
             }
         }

         paper = tempPaper;
         int tempM = M;
         M = N;
         N = tempM;
    }
}
