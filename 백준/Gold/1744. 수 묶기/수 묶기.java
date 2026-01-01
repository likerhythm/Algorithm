import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> negativeAndZero = new PriorityQueue<>();
        PriorityQueue<Integer> positive = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) negativeAndZero.add(num);
            else positive.add(num);
        }

        int answer = 0;
        while (!negativeAndZero.isEmpty()) {
            int a = negativeAndZero.poll();
            if (negativeAndZero.isEmpty()) {
                answer += a;
                continue;
            }
            int b = negativeAndZero.poll();
            answer += a * b;
        }

        while (!positive.isEmpty()) {
            int a = positive.poll();
            if (positive.isEmpty()) {
                answer += a;
                continue;
            }
            int b = positive.poll();
            if (a == 1 || b == 1) answer += a + b;
            else answer += a * b;
        }

        System.out.println(answer);
    }
}
