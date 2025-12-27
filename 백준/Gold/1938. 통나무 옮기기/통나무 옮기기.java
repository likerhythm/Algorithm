import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    static int N;
    static int[][] arr;
    static Move[] moves = new Move[5];
    static boolean[][][] visited; // visited[i][j][k]: i, j 좌표에 통나무의 중심이 위치하면서 (k = 0 -> 수평인 경우)(k = 1 -> 수직인 경우)
    static Info startInfo;
    static Info targetInfo;

    static class Info {
        int x, y, count;
        boolean isVertical;

        Info (int x, int y, boolean isVertical) {
            this.x = x;
            this.y = y;
            this.isVertical = isVertical;
        }

        Info (int x, int y, boolean isVertical, int count) {
            this.x = x;
            this.y = y;
            this.isVertical = isVertical;
            this.count = count;
        }

        @Override
        public String toString() {
            return "x = " + this.x + " y = " + this.y + " count = " + this.count + " isVertical = " + this.isVertical;
        }
    }

    static class Move {
        Predicate<Info> predicate;
        Function<Info, Info> function;

        Move (Predicate<Info> predicate, Function<Info, Info> function) {
            this.predicate = predicate;
            this.function = function;
        }

        boolean isValid(Info info) {
            return predicate.test(info);
        }

        Info run(Info info) {
            return function.apply(info);
        }
    }

    public static void main(String[] args) throws IOException {
        getInput();
        setMoves();
        visited = new boolean[N][N][2];
        Queue<Info> q = new LinkedList<>();
        q.add(startInfo);
        visited[startInfo.x][startInfo.y][isVerticalAsInt(startInfo)] = true;
        while (!q.isEmpty()) {
            Info now = q.poll();
//            System.out.println(now);
            if (now.x == targetInfo.x && now.y == targetInfo.y && now.isVertical == targetInfo.isVertical) {
                System.out.println(now.count);
                return;
            }
            for (Move move : moves) {
                if (!move.isValid(now)) continue;
                Info next = move.run(now);
                if (visited[next.x][next.y][isVerticalAsInt(next)]) continue;
                visited[next.x][next.y][isVerticalAsInt(next)] = true;
                q.add(next);
            }
        }
        System.out.println(0);
    }

    private static int isVerticalAsInt(Info next) {
        return next.isVertical ? 1 : 0;
    }

    private static void setMoves() {
        moves[0] = new Move( // 위로 이동
                info -> {
                    int x = info.x, y = info.y;
                    boolean isVertical = info.isVertical;
                    if (isVertical) {
                        if (!inRange(x - 2, y)) return false;
                        return arr[x - 2][y] != 1;
                    } else {
                        if (!inRange(x - 1, y))return false;
                        return arr[x - 1][y - 1] != 1 && arr[x - 1][y] != 1 && arr[x - 1][y + 1] != 1;
                    }
                },
                info -> {
                   return new Info(info.x - 1, info.y, info.isVertical, info.count + 1);
                });
        moves[1] = new Move( // 오른쪽으로 이동
                info -> {
                    int x = info.x, y = info.y;
                    boolean isVertical = info.isVertical;
                    if (isVertical) {
                        if (!inRange(x, y + 1)) return false;
                        return arr[x - 1][y + 1] != 1 && arr[x][y + 1] != 1 && arr[x + 1][y + 1] != 1;
                    } else {
                        if (!inRange(x, y + 2))return false;
                        return arr[x][y + 2] != 1;
                    }
                },
                info -> {
                    return new Info(info.x, info.y + 1, info.isVertical, info.count + 1);
                });
        moves[2] = new Move( // 아래쪽으로 이동
                info -> {
                    int x = info.x, y = info.y;
                    boolean isVertical = info.isVertical;
                    if (isVertical) {
                        if (!inRange(x + 2, y)) return false;
                        return arr[x + 2][y] != 1;
                    } else {
                        if (!inRange(x + 1, y))return false;
                        return arr[x + 1][y - 1] != 1 && arr[x + 1][y] != 1 && arr[x + 1][y + 1] != 1;
                    }
                },
                info -> {
                    return new Info(info.x + 1, info.y, info.isVertical, info.count + 1);
                });
        moves[3] = new Move( // 왼쪽으로 이동
                info -> {
                    int x = info.x, y = info.y;
                    boolean isVertical = info.isVertical;
                    if (isVertical) {
                        if (!inRange(x, y - 1)) return false;
                        return arr[x - 1][y - 1] != 1 && arr[x][y - 1] != 1 && arr[x + 1][y - 1] != 1;
                    } else {
                        if (!inRange(x, y - 2))return false;
                        return arr[x][y - 2] != 1;
                    }
                },
                info -> {
                    return new Info(info.x, info.y - 1, info.isVertical, info.count + 1);
                });
        moves[4] = new Move( // 90도 회전
                info -> {
                    int x = info.x, y = info.y;
                    boolean isVertical = info.isVertical;
                    if (isVertical) {
                        if (!inRange(x, y + 1)) return false;
                        if (!inRange(x, y - 1)) return false;
                        return arr[x - 1][y - 1] != 1 && arr[x][y - 1] != 1 && arr[x + 1][y - 1] != 1
                                && arr[x - 1][y + 1] != 1 && arr[x][y + 1] != 1 && arr[x + 1][y + 1] != 1;
                    } else {
                        if (!inRange(x - 1, y))return false;
                        if (!inRange(x + 1, y))return false;
                        return arr[x - 1][y - 1] != 1 && arr[x - 1][y] != 1 && arr[x - 1][y + 1] != 1
                                && arr[x + 1][y - 1] != 1 && arr[x + 1][y] != 1 && arr[x + 1][y + 1] != 1;
                    }
                },
                info -> {
                    return new Info(info.x, info.y, !info.isVertical, info.count + 1);
                });
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        int startX = 0, startY = 0, targetX = 0, targetY = 0;
        boolean isVerticalB = false, isVerticalE = false;
        int startR = 0, targetR = 0;
        int countB = 0, countE = 0;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                if (input[j].equals("B")) {
                    countB++;
                    if (countB == 1) {
                        startR = i;
                    }
                    arr[i][j] = 0;
                    if (countB == 2) {
                        startX = i;
                        startY = j;
                        if (startR != i) isVerticalB = true;
                    }
                } else if (input[j].equals("E")) {
                    countE++;
                    if (countE == 1) {
                        targetR = i;
                    }
                    arr[i][j] = 0;
                    if (countE == 2) {
                        targetX = i;
                        targetY = j;
                        if (targetR != i) isVerticalE = true;
                    }
                } else {
                    arr[i][j] = Integer.parseInt(input[j]);
                }
            }
        }
        startInfo = new Info(startX, startY, isVerticalB);
        targetInfo = new Info(targetX, targetY, isVerticalE);
    }
}
