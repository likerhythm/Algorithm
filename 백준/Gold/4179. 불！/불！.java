import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static Queue<Node> q1 = new LinkedList<>();
    static Queue<Node> q2 = new LinkedList<>();
    static boolean[][] visited1;
    static boolean[][] visited2;
    static int[] drs = {-1, 0, 1, 0};
    static int[] dcs = {0, 1, 0, -1};

    static class Node {
        int[] pos;
        int time;

        Node(int[] pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited1 = new boolean[R][C];
        visited2 = new boolean[R][C];


        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    q1.add(new Node(new int[] {i, j}, 0));
                    visited1[i][j] = true;
                }
                else if (map[i][j] == 'F') {
                    q2.add(new Node(new int[] {i, j}, 0));
                    visited2[i][j] = true;
                }
            }
        }

        int answer = -1;
        int worldTime = 0;
        outer : while (!q2.isEmpty() || !q1.isEmpty()) {
            while (!q2.isEmpty()) {
                if (q2.peek().time != worldTime) break;
                Node now = q2.poll();
                int r = now.pos[0];
                int c = now.pos[1];
                int time = now.time;
                for (int i = 0; i < 4; i++) {
                    int nr = r + drs[i];
                    int nc = c + dcs[i];
                    if (!inRange(nr, nc)) continue;
                    if (map[nr][nc] == '#') continue;
                    if (visited2[nr][nc]) continue;

                    visited2[nr][nc] = true;
                    q2.add(new Node(new int[] {nr, nc}, time + 1));
                }
            }

            while (!q1.isEmpty()) {
                if (q1.peek().time != worldTime) break;
                Node now = q1.poll();
                int r = now.pos[0];
                int c = now.pos[1];
                int time = now.time;
                if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                    answer = time + 1;
                    break outer;
                }
                for (int i = 0; i < 4; i++) {
                    int nr = r + drs[i];
                    int nc = c + dcs[i];
                    if (!inRange(nr, nc)) continue;
                    if (map[nr][nc] == '#') continue;
                    if (visited1[nr][nc]) continue;
                    if (visited2[nr][nc]) continue;

                    visited1[nr][nc] = true;
                    q1.add(new Node(new int[] {nr, nc}, time + 1));
                }
            }
            worldTime += 1;
        }

        if (answer == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
