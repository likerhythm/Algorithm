import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, K;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = split[0];
            K = split[1];

            nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(nums);

            int answer = 0;
            int minDiff = Integer.MAX_VALUE;
            for (int i = 0; i < N - 1; i++) {
                int base = nums[i];
                int left = i + 1, right = N - 1;

                while (left <= right) { // 이분탐색: base와의 합이 K에 가장 가까운 수 찾기
                    int mid = (left + right) / 2;
                    int sum = nums[mid] + base;

                    if (sum < K) {
                        left = mid + 1;
                        if (minDiff > K - sum) {
                            minDiff = K - sum;
                            answer = 1;
                        } else if (minDiff == K - sum) {
                            answer++;
                        }
                    } else {
                        right = mid - 1;
                        if (minDiff > sum - K) {
                            minDiff = sum - K;
                            answer = 1;
                        } else if (minDiff == sum - K) {
                            answer++;
                        }
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
