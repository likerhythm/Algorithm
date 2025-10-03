import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] maxScore = new int[N];

            int max = 0;
            for (int i = N - 1; i >= 0; i--) {
                max = Math.max(max, arr[i]);
                maxScore[i] = max;
            }

            long answer = 0;
            for (int i = 0; i < N; i++) {
                answer += maxScore[i] - arr[i];
            }

            System.out.println(answer);
        }
    }
}
