import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N; // 참가자 수
    static int[] score;
    static int[][] R;
    static boolean[] kill;
    static int mafia;
    static int answer;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = new int[N][N];
        kill = new boolean[N];
        for (int i = 0; i < N; i++) {
            R[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        mafia = Integer.parseInt(br.readLine());

        backtracking(0);

        System.out.println(answer);
	}

    private static void backtracking(int nightCnt) {
        if (!isNight()) { // 낮인 경우
            int vote = 0;
            int maxScore = 0;
            for (int i = 0; i < N; i++) {
                if (kill[i]) continue;
                if (maxScore < score[i]) {
                    maxScore = score[i];
                    vote = i;
                }
            }

            if (vote == mafia) {
                answer = Math.max(answer, nightCnt);
                return;
            }

            kill[vote] = true;
            backtracking(nightCnt);
            kill[vote] = false;
        } else { // 밤인 경우
            for (int target = 0; target < N; target++) {
                if (target == mafia) continue;
                if (kill[target]) continue;

                kill[target] = true;
                for (int i = 0; i < N; i++) {
                    score[i] += R[target][i];
                }

                backtracking(nightCnt + 1);

                kill[target] = false;
                for (int i = 0; i < N; i++) {
                    score[i] -= R[target][i];
                }
            }
        }
    }

    private static boolean isNight() {
        int cnt = 0;
        for (boolean k : kill) {
            if (!k) cnt++;
        }
        return cnt % 2 == 0;
    }
}
