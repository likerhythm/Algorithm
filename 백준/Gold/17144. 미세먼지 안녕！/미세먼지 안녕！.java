import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int R;
    static int C;
    static int T;
    static int[][] room;
    static int[][] airCleaner;
    static boolean foundAirCleaner = false;

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = bf.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        T = Integer.parseInt(split[2]);

        room = new int[R][C];
        airCleaner = new int[2][2];

        for (int i=0; i<R; i++) {
            split = bf.readLine().split(" ");
            for (int j=0; j<C; j++) {
                int v = Integer.parseInt(split[j]);
                room[i][j] = v;
                if (v == -1 && !foundAirCleaner) {
                    airCleaner[0][0] = i;
                    airCleaner[0][1] = j;
                    airCleaner[1][0] = i + 1;
                    airCleaner[1][1] = j;
                    foundAirCleaner = true;
                }
            }
        }

        for (int t=0; t<T; t++) {
            // 미세먼지 확산
            spread();

            // 공기청정기 작동
            runAirCleaner();
        }

        int answer = 0;
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (room[r][c] > 0) {
                    answer += room[r][c];
                }
            }
        }
        System.out.println(answer);
    }

    private static void spread() {
        int[] drs = {-1, 0, 1, 0};
        int[] dcs = {0, 1, 0, -1};
        int[][][] tempRoom = new int[R][C][2];

        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (room[r][c] > 0) {
                    int count = 0;
                    for (int k=0; k<4; k++) {
                        int nr = r + drs[k];
                        int nc = c + dcs[k];
                        if (canSpread(nr, nc)) {
                            count++;
                        }
                    }

                    if (count == 0) {
                        continue;
                    }

                    int spread = room[r][c] / 5;
                    tempRoom[r][c][0] = spread; // 퍼트려지는 양
                    tempRoom[r][c][1] = count;  // 퍼트려지는 칸의 개수
                }
            }
        }

        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (tempRoom[r][c][1] > 0) { // 퍼트려지는 칸의 개수가 양수인 경우 퍼트리기
                    int spread = tempRoom[r][c][0];
                    int count = tempRoom[r][c][1];
                    int rest = room[r][c] - spread * count;
                    for (int k=0; k<4; k++) {
                        int nr = r + drs[k];
                        int nc = c + dcs[k];
                        if (canSpread(nr, nc)) {
                            room[nr][nc] += spread;
                        }
                    }
                    room[r][c] = rest;
                }
            }
        }
    }

    private static void runAirCleaner() {
        int[] airCleaner1 = airCleaner[0]; // 반시계
        int r1 = airCleaner1[0];
        int c1 = airCleaner1[1];
        for (int r=r1-1; r>0; r--) {
            room[r][c1] = room[r - 1][c1];
        }
        for (int c=0; c<C-1; c++) {
            room[0][c] = room[0][c + 1];
        }
        for (int r=0; r<r1; r++) {
            room[r][C - 1] = room[r + 1][C - 1];
        }
        for (int c=C-1; c>c1+1; c--) {
            room[r1][c] = room[r1][c - 1];
        }
        room[r1][c1 + 1] = 0;

        int[] airCleaner2 = airCleaner[1]; // 시계
        int r2 = airCleaner2[0];
        int c2 = airCleaner2[1];
        for (int r=r2+1; r<R-1; r++) {
            room[r][c2] = room[r + 1][c2];
        }
        for (int c=0; c<C-1; c++) {
            room[R - 1][c] = room[R - 1][c + 1];
        }
        for (int r=R-1; r>r2; r--) {
            room[r][C - 1] = room[r - 1][C - 1];
        }
        for (int c=C-1; c>c2+1; c--) {
            room[r2][c] = room[r2][c - 1];
        }
        room[r2][c2 + 1] = 0;
    }

    private static boolean canSpread(int r, int c) {
        return inRange(r, c) && room[r][c] >= 0;
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static void printRoom() {
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                System.out.print(room[r][c] + " ");
            }
            System.out.println();
        }
    }
}
