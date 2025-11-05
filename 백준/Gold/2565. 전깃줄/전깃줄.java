import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Node> arr;

    static class Node implements Comparable<Node> {
        int idx, num;

        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.idx, o.idx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            arr.add(new Node(idx, num));
        }

        Collections.sort(arr);
        int[] dp = new int[N]; // dp[i] = i번째 수를 마지막으로 하는 LIS의 길이
        Arrays.fill(dp, 1);

        int max = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(i).num <= arr.get(j).num) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            max = Math.max(dp[i], max);
        }

        System.out.println(N - max);
    }
}
