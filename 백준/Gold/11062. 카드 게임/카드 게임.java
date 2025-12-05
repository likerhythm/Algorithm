import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int T, N;
    static int[] cards;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T > 0) {
            T--;
            N = Integer.parseInt(br.readLine());
            cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp = new int[N + 1][N + 1];

            pick(0, N - 1, true);
            System.out.println(dp[0][N - 1]);
        }
    }

    private static void printDp() {
        System.out.println();
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
    }

    /**
     *
     * @param left: 지금 선택할 수 있는 카드 중 왼쪽 카드의 인덱스
     * @param right: 지금 선택할 수 있는 카드 중 오른쪽 카드의 인덱스
     * @return
     */
    private static int pick(int left, int right, boolean isGeunwoo) {
        if (left > right) {
            return 0;
        }

        if (dp[left][right] > 0) {
            return dp[left][right];
        }

        if (isGeunwoo) { // 근우의 턴: 근우가 얻을 수 있는 최대의 점수 저장
            return dp[left][right] = Math.max(pick(left + 1, right, false) + cards[left], pick(left, right - 1, false) + cards[right]);
        } else { // 명우의 턴: 근우가 얻을 수 있는 최소의 점수 저장
            return dp[left][right] = Math.min(pick(left + 1, right, true), pick(left, right - 1, true));
        }
    }
}
