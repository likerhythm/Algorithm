import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 색종이 수
    static Paper[] papers;
    static int[] dp; // dp[i]: i번째 색종이가 가장 아래에 있을 때 쌓을 수 있는 최대 색종이 개수

    static class Paper {
        int a, b; // 가로, 세로

        Paper(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        papers = new Paper[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 가로가 긴 길이, 세로가 짧은 길이
            papers[i] = new Paper(Math.max(a, b), Math.min(a, b));
        }

        Arrays.sort(papers, (p1, p2) -> {
            if (p1.b == p2.b) {
                return p2.a - p1.a;
            }
            return p2.b - p1.b;
        });

        int answer = 1;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, setDp(i));
        }

        System.out.println(answer);
//        System.out.println(Arrays.toString(dp));
    }

    private static int setDp(int under) {
        if (dp[under] > 0) return dp[under];

        dp[under] = 1;

        int baseA = papers[under].a;
        int baseB = papers[under].b;
        for (int next = under + 1; next < N; next++) {
            int a = papers[next].a;
            int b = papers[next].b;
            if (a > baseA || b > baseB) continue;

            dp[under] = Math.max(dp[under], setDp(next) + 1);
        }

        return dp[under];
    }
}
