import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] numbers;
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N < 3) {
            System.out.println(0);
            return;
        }
        numbers = new int[N];

        String[] split = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(numbers);

        int answer = 0;
        for (int i=0; i<N; i++) {
            if (isGood(i)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean isGood(int idx) {
        int number = numbers[idx];
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            if (left == idx) {
                left++;
                continue;
            }
            if (right == idx) {
                right--;
                continue;
            }
            int sum = numbers[left] + numbers[right];
            if (sum == number) {
                return true;
            }

            if (sum > number) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
}
