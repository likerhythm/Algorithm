import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static int[] master;
    static List<Integer>[] graph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        master = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new ArrayList[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int num = 1; num < N; num++) {
            int masterIdx = master[num];
            graph[masterIdx].add(num);
        }

        System.out.println(dfs(0));
    }

    private static int dfs(int now) {
        if (graph[now].isEmpty()) {

            return dp[now] = 0;
        }

        List<Integer> times = new ArrayList<>();
        for (int next : graph[now]) {
            times.add(dfs(next));
        }
        Collections.sort(times);
        Collections.reverse(times);

        for (int waitTime = 1; waitTime <= times.size(); waitTime++) {
            dp[now] = Math.max(times.get(waitTime - 1) + waitTime, dp[now]);
        }

        return dp[now];
    }
}
