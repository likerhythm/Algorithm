import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 팰린드롬?
public class Main {

    static int N; // 수열의 크기
    static int M; // 질문의 개수
    static int[] numbers; // 수열
    static int[][] questions; // 질문
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        questions = new int[M][2];

        for (int i=0; i<M; i++) {
            String[] split = br.readLine().split(" ");
            questions[i][0] = Integer.parseInt(split[0]);
            questions[i][1] = Integer.parseInt(split[1]);
        }

        dp = new boolean[N][N];
        for (int i=0; i<N; i++) {
            dp[i][i] = true; // 자기 자신을 팰린드롬으로 추가
        }

        for (int start=N-2; start>=0; start--) {
            for (int end=start+1; end<N; end++) {
                if (isPalindrome(start, end)) {
                    dp[start][end] = true;
                }
            }
        }

        for (int[] q : questions) {
            int s = q[0] - 1;
            int e = q[1] - 1;

            if (dp[s][e]) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.close();
    }

    private static boolean isPalindrome(int start, int end) {
        if (numbers[start] != numbers[end]) {
            return false;
        }
        if (end - start == 1) {
            return true;
        }
        return dp[start + 1][end - 1];
    }
}
