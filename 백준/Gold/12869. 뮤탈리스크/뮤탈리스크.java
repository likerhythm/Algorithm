import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][][] dp; // dp[i][j][k]: 각 scv의 체력이 i, j, k인 경우 이전까지 공격한 최소 횟수
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 현재 scv의 체력
        int[] scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[61][61][61];

        setDp(scv, 0);
        System.out.println(answer);
    }

    private static void setDp(int[] scv, int attackCnt) {
        int hp1 = scv[0];
        int hp2 = scv[1];
        int hp3 = scv[2];

        if (dp[hp1][hp2][hp3] > 0 && dp[hp1][hp2][hp3] <= attackCnt) return; // 현재 상태를 이미 방문한 경우

        dp[hp1][hp2][hp3] = attackCnt;

        if (hp1 == 0 && hp2 == 0 && hp3 == 0) { // 모든 scv가 죽은 경우
            answer = Math.min(answer, attackCnt);
            return;
        }

        setDp(new int[] {Math.max(hp1 - 9, 0), Math.max(hp2 - 3, 0), Math.max(hp3 - 1, 0)}, attackCnt + 1);
        setDp(new int[] {Math.max(hp1 - 9, 0), Math.max(hp2 - 1, 0), Math.max(hp3 - 3, 0)}, attackCnt + 1);
        setDp(new int[] {Math.max(hp1 - 1, 0), Math.max(hp2 - 3, 0), Math.max(hp3 - 9, 0)}, attackCnt + 1);
        setDp(new int[] {Math.max(hp1 - 3, 0), Math.max(hp2 - 9, 0), Math.max(hp3 - 1, 0)}, attackCnt + 1);
        setDp(new int[] {Math.max(hp1 - 3, 0), Math.max(hp2 - 1, 0), Math.max(hp3 - 9, 0)}, attackCnt + 1);
        setDp(new int[] {Math.max(hp1 - 1, 0), Math.max(hp2 - 9, 0), Math.max(hp3 - 3, 0)}, attackCnt + 1);
    }
}
