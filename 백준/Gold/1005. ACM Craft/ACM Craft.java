import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N, K;
    static int[] cost;
    static int destination;
    static List<Integer>[] graph;
    static int[] dp; // dp[i] : i번 건물을 짓는 데 걸린 시간
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            T--;
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = split[0];
            K = split[1];

            cost = new int[N + 1];
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < N; i++) {
                cost[i + 1] = split[i];
            }

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            boolean[] isStart = new boolean[N + 1];
            Arrays.fill(isStart, true);
            indegree = new int[N + 1];
            Arrays.fill(indegree, 0);
            isStart[0] = false;

            for (int i = 0; i < K; i++) {
                split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int s = split[0];
                int e = split[1];
                graph[s].add(e);
                isStart[e] = false;
                indegree[e]++;
            }

            destination = Integer.parseInt(br.readLine());

            dp = new int[N + 1];
            Arrays.fill(dp, -1);
            Queue<Integer> q = new LinkedList<>();
            for (int num = 1; num <= N; num++) {
                if (isStart[num]) {
                    dp[num] = cost[num];
                    q.add(num);
                }
            }

            while (!q.isEmpty()) {
                int poll = q.poll();
                for (int next : graph[poll]) {
                    indegree[next]--;
                    dp[next] = Math.max(dp[next], dp[poll] + cost[next]);
                    if (indegree[next] == 0) {
                        q.add(next);
                    }
                }
            }

//            System.out.println(Arrays.toString(dp));
            System.out.println(dp[destination]);
        }
    }
}
