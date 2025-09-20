import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static Node[] nodes;
    static int[] dp;

    static class Node {
        int time;
        int pay;

        public Node(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[i] = new Node(split[0], split[1]);
        }

        int max = 0;
        for (int day = 0; day < N; day++) {
            int time = nodes[day].time;
            int pay = nodes[day].pay;
            int targetDay = day + time;
            if (max < dp[day]) max = dp[day];
            if (targetDay > N) continue;

            dp[targetDay] = Math.max(dp[targetDay], max + pay);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
