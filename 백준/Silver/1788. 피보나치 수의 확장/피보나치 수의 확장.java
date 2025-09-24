import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] positive;
    static int[] negative;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());
        N = Math.abs(target);
        if (target > 0) {
            positive = new int[N + 1];
            positive[0] = 0;
            positive[1] = 1;
            for (int i = 2; i <= N; i++) {
                positive[i] = (positive[i - 1] % 1_000_000_000 + positive[i - 2] % 1_000_000_000) % 1_000_000_000;
            }
            System.out.println(1);
            System.out.println(Math.abs(positive[N]));
        } else if (target < 0) {
            negative = new int[N + 1];
            negative[0] = 0;
            negative[1] = 1;
            for (int i = 2; i <= N; i++) {
                negative[i] = (negative[i - 2] % 1_000_000_000 - negative[i - 1] % 1_000_000_000) % 1_000_000_000;
            }
            if (negative[N] > 0) {
                System.out.println(1);
            } else if (negative[N] < 0) {
                System.out.println(-1);
            } else {
                System.out.println(0);
            }
            System.out.println(Math.abs(negative[N]));
        } else {
            System.out.println(0);
            System.out.println(0);
        }
    }
}
