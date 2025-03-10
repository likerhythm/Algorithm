import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// 탑
public class Main {

    static int[] answer;
    static Top[] tops;

    static class Top {
        int idx, height;

        Top(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tops = new Top[N];
        int[] intH = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i=0; i<N; i++) {
            tops[i] = new Top(i, intH[i]);
        }
        answer = new int[N];

        Stack<Top> stack = new Stack<>();

        for (int i=tops.length-1; i>=0; i--) {
            if (stack.isEmpty()) {
                stack.push(tops[i]);
                continue;
            }
            while (!stack.isEmpty() && stack.peek().height <= tops[i].height) {
                Top pop = stack.pop();
                answer[pop.idx] = tops[i].idx + 1;
            }
            stack.push(tops[i]);
        }

        while (!stack.isEmpty()) {
            Top pop = stack.pop();
            answer[pop.idx] = 0;
        }

        for (int i=0; i<N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
