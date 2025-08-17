import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    static int[][] dp;
    static String s1, s2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine();
        s2 = br.readLine();

        dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int i = s1.length();
        int j = s2.length();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                stack.push(s1.charAt(i - 1));
                i--;
                j--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(dp[s1.length()][s2.length()]);
        System.out.println(sb);

//        printDp();
    }

    private static boolean inRange(int i, int j) {
        return 0 < i && i <= s1.length() && 0 < j && j <= s2.length();
    }

    private static void printDp() {
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
