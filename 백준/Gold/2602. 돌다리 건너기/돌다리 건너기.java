import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        String[] strings = {br.readLine(), br.readLine()};

        int answer = 0;
        for (int start = 0; start <= 1; start++) {
            int[][] dp = new int[target.length() + 1][strings[start].length() + 1];
            Arrays.fill(dp[0], 1);
            for (int i = 1; i <= target.length(); i++) {
                String s;
                if (start == 0 && i % 2 == 1) {
                    s = strings[0];
                } else if (start == 0 && i % 2 == 0) {
                    s = strings[1];
                }
                else if (start == 1 && i % 2 == 1) {
                    s = strings[1];
                } else {
                    s = strings[0];
                }
                for (int j = 1; j <= strings[start].length(); j++) {
                    if (target.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
            answer += dp[target.length()][strings[start].length()];
//            for (int[] d : dp) {
//                System.out.println(Arrays.toString(d));
//            }
//            System.out.println();
        }
        System.out.println(answer);
    }
}
