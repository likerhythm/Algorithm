import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * tsp 알고리즘의 dp 테이블이 가지는 값의 의미
 * => “현재 i번 도시에 있고, 방문 상태가 j일 때, 앞으로 남은 모든 노드를 방문하고 출발지로 돌아가는 경로 중 총 비용이 최소인 경우의 비용”
 */
public class Main {

    static int N;
    static int[][] arr;
    static boolean[][][] dp; // dp[status][seller][buyer]: 지금까지 그림을 소유했던 아티스트 목록이 status(bit masking)일 때 seller가 buyer에게 그림을 파는 상황을 겪은 유무

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new boolean[1 << N][N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int status = 1; // 그림을 1번 아티스트만 소유했던 상태. 즉 초기 상태
        System.out.println(sell(1, status, 0));
    }

    /**
     *
     * @param seller - 현재 그림을 파는 아티스트 번호
     * @param status - 이때까지 그림을 소유했던 아티스트 목록(bit masking)
     * @param nowCost - 그림의 직전 거래 가격
     * @return
     */
    private static int sell(int seller, int status, int nowCost) {
        int count = Integer.bitCount(status);
        for (int buyer = 1; buyer <= N; buyer++) {
            if ((status & (1 << (buyer - 1))) != 0) continue; // 이미 buyer 아티스트에게 팔았던 status인 경우
            if (arr[seller - 1][buyer - 1] < nowCost) continue; // 현재 buyer 아티스트가 그림을 살 수 없는 경우

            int nextStatus = status | (1 << (buyer - 1));
            if (dp[nextStatus][seller][buyer]) continue; // 현재 상태를 이미 겪은 경우

            dp[nextStatus][seller][buyer] = true;
            count = Math.max(count, sell(buyer, nextStatus, arr[seller - 1][buyer - 1]));
        }
        return count;
    }
}
