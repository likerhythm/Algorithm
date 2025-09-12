import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    static int N;
    static int[] arr;
    static Stack<Integer>[] stacks = new Stack[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < 4; i++) {
            stacks[i] = new Stack<>();
        }

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                stacks[0].push(arr[i]);
                continue;
            }

            for (int j = 0; j < 4; j++) {
                if (stacks[j].isEmpty()) {
                    stacks[j].push(arr[i]);
                    break;
                }

                if (stacks[j].peek() < arr[i]) {
                    stacks[j].push(arr[i]);
                    break;
                }

                if (j == 3) {
                    flag = false;
                }
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
