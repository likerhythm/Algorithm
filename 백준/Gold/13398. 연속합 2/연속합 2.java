import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr;
    static int[] dp1, dp2;
    // dp1[i]: 0번째 수부터 i번째 수까지의 최대합을 구한 dp
    // dp2[i]: N - 1번째 수부터 i번째 수까지의 최대합을 구한 dp
    // 실제 최대합은 dp1[i] + dp2[i + 2]를 순환하며 구한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp1 = new int[N];
        dp2 = new int[N];

        dp1[0] = arr[0];
        int answer = dp1[0];
        for (int i = 1; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            answer = Math.max(answer, dp1[i]);
        }
        dp2[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp2[i] = Math.max(dp2[i + 1] + arr[i], arr[i]);
        }
        for (int i = 1; i < N - 1; i++) { // i번째 수를 제거했을 때 최대합 구하기
            answer = Math.max(answer, dp1[i - 1] + dp2[i + 1]);
        }

        System.out.println(answer);
    }
}
