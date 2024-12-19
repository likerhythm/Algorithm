import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int S;
    static int[] numbers;
    static int answer = 0;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String input = bf.readLine();
        String[] split = input.split(" ");
        N = Integer.parseInt(split[0]);
        S = Integer.parseInt(split[1]);

        numbers = new int[N];
        input = bf.readLine();
        split = input.split(" ");
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }

        f(0, 0);

        if (S == 0) {
            answer--;
        }
        System.out.println(answer);
    }

    private static void f(int index, int sum) {
        if (index == numbers.length) {
            if (sum == S) {
                answer++;
            }
            return;
        }
        int nextIndex = index + 1;
        f(nextIndex, sum + numbers[index]);
        f(nextIndex, sum);
    }
}
