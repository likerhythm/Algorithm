import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 곱셈
public class Main {

    static long A, B, C;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // A를 B번 곱한 수를 C로 나눈 나머지
    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");

        A = Long.parseLong(split[0]);
        B = Long.parseLong(split[1]);
        C = Long.parseLong(split[2]);

        run();
    }

    private static void run() {
        long answer = multiply(B);
        System.out.println(answer);
    }

    private static long multiply(long cnt) {
        if (cnt == 1) {
            return A % C;
        }

        long res = multiply(cnt / 2);

        if (cnt % 2 == 1) {
            return (res * res % C) * A % C;
        }
        return (res * res) % C;
    }
}