import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long[] acid;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        acid = new long[N];

        String[] split = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            acid[i] = Long.parseLong(split[i]);
        }
        Arrays.sort(acid);

        // -99 -2 -1 4 98

        int left = 0, right = acid.length - 1;
        long minSum = Long.MAX_VALUE;
        long[] answer = new long[2];
        while (left < right) {
            long sum = acid[left] + acid[right];
            if (minSum > Math.abs(sum)) {
                minSum = Math.abs(sum);
                answer[0] = acid[left];
                answer[1] = acid[right];
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                break;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
