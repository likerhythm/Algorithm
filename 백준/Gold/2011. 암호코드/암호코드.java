import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String input;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in)).readLine();

        int[][] dp = new int[input.length()][2]; // dp[i][0] : i번째 숫자를 한자릿수로 생각할 경우 해석의 가짓수

        if (input.charAt(0) != '0') {
            dp[0][0] = 1;
        }

        if (input.length() == 1) {
            System.out.println(dp[0][0]);
            return;
        }

        // 두 번째 숫자를 한자릿수로 고려할 수 있는 경우
        if (input.charAt(1) != '0') {
            dp[1][0] = dp[0][0] + dp[0][1];
        }
        // 두번째 숫자를 두자릿수로 고려할 수 없는 경우
        if (!cantTwo(1)) {
            dp[1][1] = 1;
        }

        // 점화식 : dp[i][0] = dp[i - 1][0] + dp[i - 1][1], dp[i][1] = dp[i - 2][0] + dp[i - 2][1]
        for (int i=2; i<input.length(); i++) {
            if (input.charAt(i) == '0') {
                dp[i][0] = 0;
            } else {
                dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000;
            }
            if (cantTwo(i)) { // 두자릿수로 했을 때 30이상인 경우
                dp[i][1] = 0;
            } else {
                dp[i][1] = (dp[i - 2][0] + dp[i - 2][1]) % 1000000;
            }
        }

//        System.out.println(Arrays.deepToString(dp));
        System.out.println((dp[input.length() - 1][0] + dp[input.length() - 1][1]) % 1000000);
    }

    private static boolean cantTwo(int index) {
        return input.charAt(index - 1) == '0'
                || input.charAt(index - 1) >= '3'
                || (input.charAt(index - 1) == '2' && input.charAt(index) >= '7');
    }
}
