import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static boolean[] isPrime;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= N; i++) {
            if (!isPrime[i]) continue;
            primes.add(i);
            for (int j = 2; i * j <= N; j++) {
                isPrime[i * j] = false;
            }
        }

        if (N == 1) {
            System.out.println(0);
            return;
        } else if (N == 2) {
            System.out.println(1);
            return;
        }

        int answer = 0;
        int left = 0;
        int right = 1;
        int tmpSum = primes.get(left) + primes.get(right);
        while (left < right) {
            if (tmpSum > N) {
                tmpSum -= primes.get(left++);
            }
            else if (tmpSum < N) {
                tmpSum += primes.get(++right);
            }
            else {
                tmpSum -= primes.get(left++);
                tmpSum += primes.get(++right);
                answer += 1;
            }
        }

        if (primes.contains(N)) answer++;
        System.out.println(answer);
    }
}
