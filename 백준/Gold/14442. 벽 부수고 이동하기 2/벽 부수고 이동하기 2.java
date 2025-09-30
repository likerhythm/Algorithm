import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[][][] visited;

    static class Node {
        int n, m;
        int brokeCnt;

        public Node(int n, int m, int brokeCnt) {
            this.n = n;
            this.m = m;
            this.brokeCnt = brokeCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        K = split[2];

        map = new int[N][M];
        visited = new int[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k <= K; k++) {
                    visited[i][j][k] = -1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        int[] dns = {-1, 0, 1, 0};
        int[] dms = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int n = poll.n;
            int m = poll.m;
            int brokeCnt = poll.brokeCnt;
            int moveCnt = visited[n][m][brokeCnt];

            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];

                if (!inRange(nn, nm)) continue;
                if (map[nn][nm] == 0) { // 벽이 아닌 경우
                    if (visited[nn][nm][brokeCnt] == -1) {
                        visited[nn][nm][brokeCnt] = moveCnt + 1;
                        q.add(new Node(nn, nm, brokeCnt));
                    }
                    else if (visited[nn][nm][brokeCnt] > moveCnt + 1) {
                        visited[nn][nm][brokeCnt] = moveCnt + 1;
                        q.add(new Node(nn, nm, brokeCnt));
                    }
                } else if (map[nn][nm] == 1) { // 벽인 경우
                    if (brokeCnt == K) continue; // 더 이상 벽을 부술 수 없으면 넘김
                    if (visited[nn][nm][brokeCnt + 1] == -1) { // 처음 도달한 경우
                        visited[nn][nm][brokeCnt + 1] = moveCnt + 1;
                        q.add(new Node(nn, nm, brokeCnt + 1));
                    }
                    else if (visited[nn][nm][brokeCnt + 1] > moveCnt + 1) {
                            visited[nn][nm][brokeCnt + 1] = moveCnt + 1;
                            q.add(new Node(nn, nm, brokeCnt + 1));
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (visited[N - 1][M - 1][i] == -1) continue;
            answer = Math.min(visited[N - 1][M - 1][i], answer);
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(visited[i][j][1] + " ");
//            }
//            System.out.println();
//        }
        
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
