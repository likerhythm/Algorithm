import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final char JONGSOO = 'I';
    static final char CRAZY = 'R';
    static final char BLANK = '.';

    static int R, C;
    static char[][] board;
    static int[][] count;
    static int[] command;
    static int[] drs = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dcs = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static List<Pos> crazy = new ArrayList<>();
    static Pos jongsoo = new Pos();

    static class Pos {
        int r, c;
        boolean isAlive = true;

        Pos() {}
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        count = new int[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (board[i][j] == JONGSOO) {
                    jongsoo.r = i;
                    jongsoo.c = j;
                } else if (board[i][j] == CRAZY) {
                    crazy.add(new Pos(i, j));
                }
            }
        }
        command = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        for (int cmd = 0; cmd < command.length; cmd++) {
            int nr = jongsoo.r + drs[command[cmd]];
            int nc = jongsoo.c + dcs[command[cmd]];

            for (Pos craz : crazy) {
                if (!craz.isAlive) continue;
                if (craz.r == nr && craz.c == nc) {
                    System.out.println("kraj " + (cmd + 1));
                    return;
                }
            }

            jongsoo.r = nr;
            jongsoo.c = nc;

            for (Pos craz : crazy) {
                if (!craz.isAlive)
                    continue;

                int r = craz.r;
                int c = craz.c;
                int targetR = jongsoo.r;
                int targetC = jongsoo.c;
                int dist = Math.abs(r - targetR) + Math.abs(c - targetC);
                for (int dir = 1; dir <= 9; dir++) {
                    if (dir == 5)
                        continue;
                    int tmpR = r + drs[dir];
                    int tmpC = c + dcs[dir];
                    if (!inRange(tmpR, tmpC)) continue;
                    int tmpDist = Math.abs(tmpR - targetR) + Math.abs(tmpC - targetC);
                    if (tmpDist < dist) {
                        dist = tmpDist;
                        craz.r = tmpR;
                        craz.c = tmpC;
                    }
                }
                count[craz.r][craz.c] += 1;
                if (craz.r == jongsoo.r && craz.c == jongsoo.c) {
                    System.out.println("kraj " + (cmd + 1));
                    return;
                }
            }

            for (Pos craz : crazy) {
                if (count[craz.r][craz.c] > 1) {
                    craz.isAlive = false;
                }
            }

//            setBoard();
//            printBoard();
//            System.out.println();

            for (int i = 0; i < R; i++) {
                Arrays.fill(count[i], 0);
            }
        }

        setBoard();
        printBoard();
    }

    private static void printBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static void setBoard() {
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(board[i], '.');
        }
        board[jongsoo.r][jongsoo.c] = 'I';
        for (Pos p : crazy) {
            if (!p.isAlive) continue;
            board[p.r][p.c] = 'R';
        }
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
