import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        int answer = 0;
        int tmp = 1;
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            if (c == '(') {
                stack.push(c);
                tmp *= 2;
            } else if (c == '[') {
                stack.push(c);
                tmp *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }

                if (input[i - 1] == '(') {
                    answer += tmp;
                }
                stack.pop();
                tmp /= 2;
            } else if (c == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }

                if (input[i - 1] == '[') {
                    answer += tmp;
                }

                stack.pop();
                tmp /= 3;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
            return;
        }
        System.out.println(answer);
    }
}
