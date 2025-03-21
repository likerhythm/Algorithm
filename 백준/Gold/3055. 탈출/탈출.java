import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] drs = {-1, 0, 1, 0};
    static int[] dcs = {0, 1, 0, -1};
    static List<int[]> waters = new ArrayList<>();
    static int waterR = -1, waterC = -1, animalR, animalC;
    static boolean[][] visited;

    static class Visit {
        int r, c, time;
        char type;

        Visit(int r, int c, int time, char type) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);

        map = new char[R][C];
        for (int i=0; i<R; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j=0; j<C; j++) {
                if (charArray[j] == '*') {
                    waters.add(new int[] {i, j});
                } else if (charArray[j] == 'S') {
                    animalR = i;
                    animalC = j;
                }
            }
            map[i] = charArray;
        }

        int answer = bfs();
        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
            return;
        }
        System.out.println("KAKTUS");
    }

    private static int bfs() {
        Queue<Visit> queue = new LinkedList<>();

        queue.add(new Visit(animalR, animalC, 0, 'a'));
        if (!waters.isEmpty()) {
            for (int[] w : waters) {
                queue.add(new Visit(w[0], w[1], 0, 'w'));
            }
        }

        visited = new boolean[R][C]; // 고슴도치의 visited 배열
        visited[animalR][animalC] = true;

        while (!queue.isEmpty()) {
//            printMap();
//            printVisited();
            Visit poll = queue.poll();
            int r = poll.r;
            int c = poll.c;
            char type = poll.type;

            if (type == 'a' && map[r][c] == 'D') { // 고슴도치가 비버 굴에 도착한 경우
                return poll.time;
            }

            for (int i=0; i<4; i++) {
                int nr = r + drs[i];
                int nc = c + dcs[i];

                if (!inRange(nr, nc)) { // 범위를 벗어나는 경우
                    continue;
                }
                if (map[nr][nc] == 'X') { // 돌인 경우
                    continue;
                }
                if (type == 'a') { // 고슴도치의 이동인 경우
                    if (visited[nr][nc]) {
//                        System.out.println(nr + ", " + nc + ": 이미 방문함");
                        continue;
                    }
                    if (map[nr][nc] == '.' && nearWater(nr, nc)) {
//                        System.out.println(nr + ", " + nc + ": 근처에 물 있음");
                        continue;
                    }
                    if (map[nr][nc] == '*') {
//                        System.out.println(nr + ", " + nc + ": 물임");
                        continue;
                    }
                    visited[nr][nc] = true;
                } else if (type == 'w') { // 물의 이동인 경우
                    if (map[nr][nc] == '*') {
                        continue;
                    }
                    if (map[nr][nc] == 'D') {
                        continue;
                    }
                    map[nr][nc] = '*';
                }

                queue.add(new Visit(nr, nc, poll.time + 1, type));
            }
        }

        return Integer.MAX_VALUE;
    }

    private static void printVisited() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printMap() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean nearWater(int r, int c) {
        for (int i=0; i<4; i++) {
            int nr = r + drs[i];
            int nc = c + dcs[i];
            if (!inRange(nr, nc)) {
                continue;
            }
            if (map[nr][nc] == '*') {
                return true;
            }
        }
        return false;
    }

    private static boolean inRange(int r, int c) {
        return 0<=r && r<R && 0<=c && c<C;
    }
}
