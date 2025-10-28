import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] arr;
    static boolean[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        dp = new boolean[1 << N][N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                arr[i][j] = line.charAt(j - 1) - '0';
            }
        }

        int BIT = 1 << 0; 
        int maxPeople = getDP(1, 1, BIT, 0);
        System.out.println(maxPeople);
    }

    // idx: 현재 아티스트, people: 지금까지 소유한 사람 수
    // bit: 현재까지 그림을 소유한 사람 집합, num: 이전 거래 가격
    static int getDP(int idx, int people, int bit, int num) {
        int maxPeople = people;

        for (int i = 1; i <= N; i++) {
            // 이미 소유했거나 거래 가격이 이전보다 낮으면 패스
            if ((bit & (1 << (i - 1))) != 0 || arr[idx][i] < num) continue;

            int nextBit = bit | (1 << (i - 1));
            // 현재 상태를 이미 겪은 경우 패스
            if (dp[nextBit][idx][i]) continue;
            
            dp[nextBit][idx][i] = true;

            // 재귀 호출 후 최대 사람 수 갱신
            maxPeople = Math.max(maxPeople, getDP(i, people + 1, nextBit, arr[idx][i]));
        }

        return maxPeople;
    }
}
