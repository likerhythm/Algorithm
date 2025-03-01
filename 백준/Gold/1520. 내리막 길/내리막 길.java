import java.util.*;
import java.io.*;

// 시간초과 문제 해결 : https://www.acmicpc.net/board/view/127838
public class Main {

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        for (int[] v : visited) {
            Arrays.fill(v, -1);
        }

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int answer = dfs(0, 0);
        System.out.println(answer);
    }

    private static int dfs(int n, int m) {
        if (n == N - 1 && m == M - 1) {
            return 1;
        }

        if (visited[n][m] > -1) { // 한 번이라도 방문한 경우
            return visited[n][m];
        }

        visited[n][m] = 0; // 한 번도 방문하지 않은 칸이므로 0(도착지까지의 경로의 수)으로 초기화
        for (int i=0; i<4; i++) {
            int nn = n + dns[i];
            int nm = m + dms[i];

            if (!inRange(nn, nm)) { // 범위를 벗어난 경우
                continue;
            }

            if (map[nn][nm] >= map[n][m]) { // 다음 숫자가 더 크거나 같은 경우
                continue;
            }

            visited[n][m] += dfs(nn, nm);
        }

        return visited[n][m];
    }

    private static boolean inRange(int n, int m) {
        return 0<=n && n<N && 0<=m && m<M;
    }
}