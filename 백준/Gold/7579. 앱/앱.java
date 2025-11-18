import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 현재 실행 중인 앱의 수
    static int M; // 비워야 하는 메모리 크기
    static int[] memories; // 각 앱이 차지하는 메모리 크기
    static int[] costs; // 각 앱을 비활성화 하는 데 드는 비용
    static int[][] dp; // dp[i][j] = i번째 앱까지 고려했을 때 cost를 j 이하로 지불하면서 비울 수 있는 최대 메모리 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memories = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int totalCost = 0;
        for (int cost : costs) {
            totalCost += cost;
        }

        dp = new int[totalCost + 1][N + 1];
        for (int app = 1; app <= N; app++) {
            int appCost = costs[app - 1];
            int appMemory = memories[app - 1];
            for (int cost = 0; cost <= totalCost; cost++) {
                if (appCost > cost) {
                    dp[cost][app] = dp[cost][app - 1];
                    continue;
                }
                dp[cost][app] = Math.max(dp[cost][app - 1], dp[cost - appCost][app - 1] + appMemory);
            }
        }

        int answer = 0;
        outer : for (int cost = 0; cost <= totalCost; cost++) {
            for (int app = 1; app <= N; app++) {
                if (dp[cost][app] >= M) {
                    answer = cost;
                    break outer;
                }
            }
        }

        System.out.println(answer);
    }
}
