import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, K;
    static int[][] distances;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        K = split[1];
        distances = new int[N][N];

        for (int i = 0; i < N; i++) {
            distances[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                if (start == mid) {
                    continue;
                }
                for (int end = 0; end < N; end++) {
                    if (end == mid || end == start) {
                        continue;
                    }
                    distances[start][end] = Math.min(distances[start][end], distances[start][mid] + distances[mid][end]);
                }
            }
        }

        boolean[] visited = new boolean[N];
        visited[K] = true;
        tracking(K, visited, 1, 0);

        System.out.println(answer);
    }

    private static void tracking(int from, boolean[] visited, int cnt, int cost) {
        if (cnt == N) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int to = 0; to < N; to++) {
            if (visited[to]) continue;

            visited[to] = true;
            tracking(to, visited, cnt + 1, cost + distances[from][to]);
            visited[to] = false;
        }
    }
}
