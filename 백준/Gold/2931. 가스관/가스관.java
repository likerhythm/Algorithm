import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int R, C;
    static char[][] map;
    enum Direction { UP, RIGHT, DOWN, LEFT };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        R = split[0];
        C = split[1];
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int nowR = 0, nowC = 0;
        int preR = 0, preC = 0;
        outer : for (int r = 0; r < R; r++) { // 시작 위치 설정
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'M') {
                    nowR = r;
                    nowC = c;
                    break outer;
                }
            }
        }

        int[] drs = {-1, 0, 1, 0};
        int[] dcs = {0, 1, 0, -1};

        Direction nowD = Direction.UP; // 시작 방향 설정
        for (int i = 0; i < 4; i++) {
            int nr = nowR + drs[i];
            int nc = nowC + dcs[i];
            if (!inRange(nr, nc)) continue;
            if (map[nr][nc] != '.' && map[nr][nc] != 'Z') {
                nowD = Direction.values()[i];
                break;
            }
        }

        while (true) {
            boolean moved = false;

            for (int i = 0; i < 4; i++) {
                if (nowD == Direction.UP || nowD == Direction.DOWN) {
                    if (i == 1 || i == 3) continue;
                    if (nowD == Direction.UP && i == 2) continue;
                    if (nowD == Direction.DOWN && i == 0) continue;
                }
                if (nowD == Direction.LEFT || nowD == Direction.RIGHT) {
                    if (i == 0 || i == 2) continue;
                    if (nowD == Direction.LEFT && i == 1) continue;
                    if (nowD == Direction.RIGHT && i == 3) continue;
                }
                int nr = nowR + drs[i];
                int nc = nowC + dcs[i];
                if (!inRange(nr, nc)) continue;
                if (nr == preR && nc == preC) continue;

                char next = map[nr][nc];
                if (next == '+'  // 방향이 바뀌지 않는 경우
                    || (next == '|' && (nowD == Direction.UP || nowD == Direction.DOWN))
                    || (next == '-' && (nowD == Direction.LEFT || nowD == Direction.RIGHT))) {
                    preR = nowR; preC = nowC;
                    nowR = nr; nowC = nc;
                    moved = true;
                    break;
                } else if (next == '1' && (nowD == Direction.UP || nowD == Direction.LEFT)) {
                    preR = nowR; preC = nowC;
                    nowR = nr; nowC = nc;
                    moved = true;
                    nowD = nowD == Direction.UP ? Direction.RIGHT : Direction.DOWN;
                } else if (next == '2' && (nowD == Direction.DOWN || nowD == Direction.LEFT)) {
                    preR = nowR; preC = nowC;
                    nowR = nr; nowC = nc;
                    moved = true;
                    nowD = nowD == Direction.DOWN ? Direction.RIGHT : Direction.UP;
                } else if (next == '3' && (nowD == Direction.DOWN || nowD == Direction.RIGHT)) {
                    preR = nowR; preC = nowC;
                    nowR = nr; nowC = nc;
                    moved = true;
                    nowD = nowD == Direction.DOWN ? Direction.LEFT : Direction.UP;
                } else if (next == '4' && (nowD == Direction.UP || nowD == Direction.RIGHT)) {
                    preR = nowR; preC = nowC;
                    nowR = nr; nowC = nc;
                    moved = true;
                    nowD = nowD == Direction.UP ? Direction.LEFT : Direction.DOWN;
                }

                if (moved) break;
            }

            if (!moved) break;
        }

        int emptyR = nowR + drs[nowD.ordinal()];
        int emptyC = nowC + dcs[nowD.ordinal()];

        List<Direction> directionAnswers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int nr = emptyR + drs[i];
            int nc = emptyC + dcs[i];
            if (!inRange(nr, nc)) continue;
            if (nowD == Direction.UP && i == 2
                || nowD == Direction.RIGHT && i == 3
                || nowD == Direction.DOWN && i == 0
                || nowD == Direction.LEFT && i == 1)
                continue;

            Direction nextD = Direction.values()[i]; // i 방향으로 이동한다고 가정
            char next = map[nr][nc];
            if (nextD == Direction.UP && (next == '|' || next == '+' || next == '1' || next == '4')) {
                directionAnswers.add(Direction.UP);
            }
            if (nextD == Direction.RIGHT && (next == '-' || next == '+' || next == '3' || next == '4')) {
                directionAnswers.add(Direction.RIGHT);
            }
            if (nextD == Direction.DOWN && (next == '|' || next == '+' || next == '2' || next == '3')) {
                directionAnswers.add(Direction.DOWN);
            }
            if (nextD == Direction.LEFT && (next == '-' || next == '+' || next == '1' || next == '2')) {
                directionAnswers.add(Direction.LEFT);
            }
        }

        char directionAnswer = '?';
        if (directionAnswers.size() > 1) {
            directionAnswer = '+';
        } else {
            Direction nextD = directionAnswers.get(0);
            if (nextD == nowD && (nowD == Direction.DOWN || nowD == Direction.UP)) directionAnswer = '|';
            if (nextD == nowD && (nowD == Direction.LEFT || nowD == Direction.RIGHT)) directionAnswer = '-';
            if ((nowD == Direction.UP && nextD == Direction.RIGHT) || (nowD == Direction.LEFT && nextD == Direction.DOWN)) directionAnswer = '1';
            if ((nowD == Direction.DOWN && nextD == Direction.RIGHT) || (nowD == Direction.LEFT && nextD == Direction.UP)) directionAnswer = '2';
            if ((nowD == Direction.DOWN && nextD == Direction.LEFT) || (nowD == Direction.RIGHT && nextD == Direction.UP)) directionAnswer = '3';
            if ((nowD == Direction.UP && nextD == Direction.LEFT) || (nowD == Direction.RIGHT && nextD == Direction.DOWN)) directionAnswer = '4';
        }

        System.out.println((emptyR + 1) + " " + (emptyC + 1) + " " + directionAnswer);
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
