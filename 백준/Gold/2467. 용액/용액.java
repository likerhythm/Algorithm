import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = N - 1;

        int minSum = Integer.MAX_VALUE;
        int answerL = left;
        int answerR = right;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (minSum > Math.abs(sum)) {
                minSum = Math.abs(sum);
                answerL = left;
                answerR = right;
            }

            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                break;
            }
        }

        System.out.println(arr[answerL] + " " + arr[answerR]);
    }
}
