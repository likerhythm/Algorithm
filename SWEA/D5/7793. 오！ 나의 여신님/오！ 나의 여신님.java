import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int N, M;
    static char[][] map;
    static Queue<int[]> devilQ = new LinkedList<>();
    static Queue<int[]> humanQ = new LinkedList<>();
    static boolean[][] devilVisited;
    static boolean[][] humanVisited;
    static int[] start;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = split[0];
            M = split[1];
            map = new char[N][M];
            humanVisited = new boolean[N][M];
            devilVisited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '*') { // 악마 위치 저장
                        devilQ.add(new int[] {i, j, 0});
                        devilVisited[i][j] = true;
                    } else if (map[i][j] == 'S') { // 수연 위치
                        start = new int[] {i, j};
                        humanQ.add(new int[] {i, j, 0});
                        humanVisited[i][j] = true;
                    }
                }
            }

            boolean found = bfs();
            System.out.println("#" + t + " " + (found ? answer : "GAME OVER"));
            devilQ.clear();
            humanQ.clear();
        }
    }

    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    private static boolean bfs() {
        int nowTime = 0;
        boolean found = false;
        outer : while (!humanQ.isEmpty()) {
//            System.out.println("nowTime = " + nowTime);
            nowTime++;
            while (!devilQ.isEmpty() && devilQ.peek()[2] == nowTime - 1) { // 악마 탐색
                int[] poll = devilQ.poll();
                int n = poll[0];
                int m = poll[1];
                int time = poll[2];
                for (int i = 0; i< 4; i++) {
                    int nn = n + dns[i];
                    int nm = m + dms[i];
                    if (!inRange(nn, nm)) continue;
                    if (map[nn][nm] == 'X') continue; // 돌인 경우
                    if (map[nn][nm] == 'D') continue; // 여신인 경우
                    if (devilVisited[nn][nm]) continue;
                    devilVisited[nn][nm] = true;
                    devilQ.add(new int[] {nn, nm, time + 1});
                }
            }

            while (!humanQ.isEmpty() && humanQ.peek()[2] == nowTime - 1) { // 수연 탐색
                int[] poll = humanQ.poll();
                int n = poll[0];
                int m = poll[1];
                int time = poll[2];
                if (map[n][m] == 'D') { // 여신을 찾은 경우
                    found = true;
                    break outer;
                }
                for (int i = 0; i< 4; i++) {
                    int nn = n + dns[i];
                    int nm = m + dms[i];
                    if (!inRange(nn, nm)) continue;
                    if (map[nn][nm] == 'X') continue; // 돌인 경우
                    if (devilVisited[nn][nm]) continue; // 악마인 경우
                    if (humanVisited[nn][nm]) continue;
                    humanVisited[nn][nm] = true;
                    humanQ.add(new int[] {nn, nm, time + 1});
                }
            }

//            printVisited(devilVisited);
//            printVisited(humanVisited);
        }

        answer = nowTime - 1;
        return found;
    }

    private static void printVisited(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
        System.out.println();
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
