import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N]; // dp[i]: i번째 상자에 담을 수 있는 최대 상자 수
        Arrays.fill(dp, 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] >= arr[i]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
