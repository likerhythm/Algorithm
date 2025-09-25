import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();;

        int[][] dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
            dp[i][1] = -1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] <= arr[j]) continue;
                if (dp[j][0] + 1 > dp[i][0]) {
                    dp[i][0] = dp[j][0] + 1;
                    dp[i][1] = j;
                }
            }
        }

        int maxIdx = 0;
        for (int i = 1; i < N; i++) {
            if (dp[maxIdx][0] < dp[i][0]) {
                maxIdx = i;
            }
        }

        System.out.println(dp[maxIdx][0]);
        int idx = maxIdx;
        List<Integer> answer = new ArrayList<>();
        while (idx != -1) {
            answer.add(arr[idx]);
            idx = dp[idx][1];
        }

        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }
}
