import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = arr[i];
        }
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] <= arr[j]) continue;
                dp[i] = Math.max(dp[j] + arr[i], dp[i]);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
