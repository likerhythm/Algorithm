import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final int MAX = 987654321;

    static List<Integer> steps = new ArrayList<>();
    static int[][][] dp; // dp[i][j][k]: k번째 버튼을 누를 때 왼발이 i번, 오른발이 j번에 위치할 때의 최소의 힘

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        steps.add(0);
        for (int i : input) {
            if (i == 0) break;
            steps.add(i);
        }

        dp = new int[5][5][steps.size()];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }

        dp[0][0][0] = 0;
        for (int k = 1; k < steps.size(); k++) {
            int next = steps.get(k);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[i][next][k] = Math.min(dp[i][next][k], dp[i][j][k - 1] + calcCost(j, next));
                    dp[next][j][k] = Math.min(dp[next][j][k], dp[i][j][k - 1] + calcCost(i, next));
                }
            }
        }

        int last = steps.get(steps.size() - 1);
        int answer = MAX;
        for (int i = 0; i < 5; i++) {
            answer = Math.min(answer, dp[last][i][steps.size() - 1]);
            answer = Math.min(answer, dp[i][last][steps.size() - 1]);
        }

        System.out.println(answer);
    }

    private static int calcCost(int pre, int next) {
        if (pre == next) return 1;
        if (pre == 0) return 2;
        if (Math.abs(pre - next) == 2) return 4;
        return 3;
    }
}
