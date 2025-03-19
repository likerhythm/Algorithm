import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dp1; // dp[i][j] : i행 j열에 1번 방향으로 도달했을 때 최소 연료
    static int[][] dp2; // dp[i][j] : i행 j열에 2번 방향으로 도달했을 때 최소 연료
    static int[][] dp3; // dp[i][j] : i행 j열에 3번 방향으로 도달했을 때 최소 연료

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp1 = new int[N][M];
        dp2 = new int[N][M];
        dp3 = new int[N][M];
        for (int i=0; i<N; i++) {
            if (i == 0) {
                dp1[0] = map[0].clone();
                dp2[0] = map[0].clone();
                dp3[0] = map[0].clone();
                continue;
            }
            Arrays.fill(dp1[i], 987654321);
            Arrays.fill(dp2[i], 987654321);
            Arrays.fill(dp3[i], 987654321);
        }

        dp1[1][0] = map[1][0] + map[0][1];
        dp2[1][0] = map[1][0] + map[0][0];
        for (int i=1; i<M-1; i++) {
            dp1[1][i] = map[1][i] + map[0][i + 1];
            dp2[1][i] = map[1][i] + map[0][i];
            dp3[1][i] = map[1][i] + map[0][i - 1];
        }
        dp2[1][M - 1] = map[1][M - 1] + map[0][M - 1];
        dp3[1][M - 1] = map[1][M - 1] + map[0][M - 2];

        // dp 업데이트 : 이전 칸의 최소 값 + 현재 칸의 연료 소모 값
        for (int i=2; i<N; i++) {
            dp1[i][0] = Math.min(dp2[i - 1][1], dp3[i - 1][1]) + map[i][0];
            dp2[i][0] = dp1[i - 1][0] + map[i][0];
            for (int j=1; j<M-1; j++) {
                dp1[i][j] = Math.min(dp2[i - 1][j + 1], dp3[i - 1][j + 1]) + map[i][j];
                dp2[i][j] = Math.min(dp1[i - 1][j], dp3[i - 1][j]) + map[i][j];
                dp3[i][j] = Math.min(dp1[i - 1][j - 1], dp2[i - 1][j - 1]) + map[i][j];
            }
            dp2[i][M - 1] = dp3[i - 1][M - 1] + map[i][M - 1];
            dp3[i][M - 1] = Math.min(dp1[i - 1][M - 2], dp2[i - 1][M - 2]) + map[i][M - 1];
        }

        int answer = Integer.MAX_VALUE;
        for (int i=0; i<M; i++) {
            int tmpAnswer = Math.min(dp1[N - 1][i], Math.min(dp2[N - 1][i], dp3[N - 1][i]));
            answer = Math.min(tmpAnswer, answer);
        }

        System.out.println(answer);
    }
}
