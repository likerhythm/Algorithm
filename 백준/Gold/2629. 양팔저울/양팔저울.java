import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] weights;
    static boolean[] dp; // dp[i] = true: i무게를 만들 수 있음, dp[i] = false: i무게를 만들 수 없음
    static int[] balls;
    static int range;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int weightCnt = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int ballCnt = Integer.parseInt(br.readLine());
        balls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int totalWeight = 0;
        for (int w: weights) {
            totalWeight += w;
        }

        range = 2 * totalWeight + 1;
        dp = new boolean[range];
        int baseWeight = totalWeight;
        for (int w : weights) {
            boolean[] tmp = Arrays.copyOf(dp, dp.length);
            for (int target = 0; target < range; target++) {
                if (!dp[target]) continue;
                if (inRange(target + w)) {
                    tmp[target + w] = true;
                }
                if (inRange(target - w)) {
                    tmp[target - w] = true;
                }
            }
            tmp[baseWeight + w] = true;
            tmp[baseWeight - w] = true;
            dp = Arrays.copyOf(tmp, tmp.length);
        }

        StringBuilder sb = new StringBuilder();
        for (int ball : balls) {
            if (!inRange(baseWeight + ball)) {
                sb.append("N ");
                continue;
            }
            if (dp[baseWeight + ball]) {
                sb.append("Y");
            } else {
                sb.append("N");
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }

    private static void printdp(int totalWeight) {
        for (int i = 0; i <= totalWeight; i++) {
            System.out.print(dp[i] + ", ");
        }
        System.out.println();
        for (int i = totalWeight + 1; i < 2 * totalWeight + 1; i++) {
            System.out.print(dp[i] + ", ");
        }
        System.out.println();
    }

    private static boolean inRange(int num) {
        return 0 <= num && num < range;
    }
}
