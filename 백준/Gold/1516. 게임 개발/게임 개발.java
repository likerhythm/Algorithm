import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] cost;
    static int[] dp;
    static Node[] nodes;

    static class Node {
        int cost;
        List<Integer> required;

        Node(int cost, List<Integer> required) {
            this.cost = cost;
            this.required = required;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        boolean[] isLast = new boolean[N + 1];
        Arrays.fill(isLast, true);
        isLast[0] = false;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            List<Integer> required = new ArrayList<>();
            while (true) {
                int req = Integer.parseInt(st.nextToken());
                if (req == -1) break;
                required.add(req);
                isLast[req] = false;
            }
            nodes[i] = new Node(cost, required);
        }

        for (int num = 1; num <= N; num++) {
            if (!isLast[num]) continue;
            setDp(num);
        }

        for (int num = 1; num <= N; num++) {
            System.out.println(dp[num]);
        }
    }

    private static int setDp(int num) {
        List<Integer> required = nodes[num].required;
        int cost = nodes[num].cost;
        if (required.isEmpty()) {
            return dp[num] = cost;
        }
        
        if (dp[num] > -1) {
            return dp[num];
        }

        int maxCost = 0;
        for (int req : required) {
            maxCost = Math.max(setDp(req), maxCost);
        }

        return dp[num] = maxCost + cost;
    }
}
