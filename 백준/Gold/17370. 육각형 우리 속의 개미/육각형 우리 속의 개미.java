import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int answer;
    static int[] dns = {-1, -1, 1, 1, 1, -1}; // 위, 오른쪽 위, 오른쪽 아래, 아래, 왼쪽 아래, 왼쪽 위
    static int[] dms = {0, 1, 1, 0, -1, -1};
    static boolean[][] visited = new boolean[1000][1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited[500][500] = true;
        backtracking(499, 500, 0, 0);
        System.out.println(answer);
    }

    private static void backtracking(int n, int m, int cnt, int preDir) {
//        System.out.println("[" + n + ", " + m + "] 방문");
        if (visited[n][m]) {
            if (cnt == N) {
//                System.out.println("==========성공");
                answer++;
            }
            return;
        }

        if (cnt == N) {
//            System.out.println("==========실패");
            return;
        }

        visited[n][m] = true;
        for (int nowDir = 0; nowDir < 6; nowDir++) {
            if (preDir % 2 == nowDir % 2) continue;
            if ((preDir + 3) % 6 == nowDir) continue;
            int nn = n + dns[nowDir];
            int nm = m + dms[nowDir];
            backtracking(nn, nm, cnt + 1, nowDir);
        }

        visited[n][m] = false;
    }
}
