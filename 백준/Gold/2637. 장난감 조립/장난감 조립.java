import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] dp;
    static int[][] graph; // graph[a][b]: a 부품을 만드는 데 필요한 b 부품의 개수
    static int[] indegree;
    static boolean[] isDone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N + 1][N + 1];
        graph = new int[N + 1][N + 1];
        isDone = new boolean[N + 1];
        indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            graph[X][Y] = K;
            indegree[X]++;
        }

        for (int i = 1; i < N; i++) {
            if (indegree[i] > 0) continue;
            dp[i][i] = 1;
        }

        int[] result = setDp(N);
        for (int i = 1; i < N; i++) {
            if (indegree[i] > 0) continue;
            System.out.println(i + " " + result[i]);
        }

//        printArr(dp);
    }

    private static void printArr(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    // now 부품에 필요한 next 부품의 개수 count
    private static int[] setDp(int now) {
        if (isDone[now]) return dp[now];

        for (int next = 1; next < N; next++) {
            if (graph[now][next] == 0) continue;
            int[] result = setDp(next);
            for (int i = 1; i < N; i++) {
                if (indegree[i] > 0) continue;
                dp[now][i] += result[i] * graph[now][next];
            }
        }

        isDone[now] = true;
        return dp[now];
    }
}
