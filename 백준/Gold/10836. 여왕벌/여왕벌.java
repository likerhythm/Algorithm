import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 벌집의 가로 세로 크기
    static int M; // 진행 날짜 수
    static int[] initIncrements;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initIncrements = new int[2 * N - 1];
        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int idx = 0;
            for (int j = 0; j < 3; j++) {
                while (input[j] > 0) {
                    initIncrements[idx++] += j;
                    input[j] -= 1;
                }
            }
        }

        int[][] realIncrements = new int[N][N];
        for (int i = N - 1; i >= 0; i--) {
            realIncrements[i][0] = initIncrements[N - 1 - i];
        }
        for (int i = 1; i < N; i++) {
            realIncrements[0][i] = initIncrements[N + i - 1];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                realIncrements[i][j] = realIncrements[0][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(1 + realIncrements[i][j] + " ");
            }
            System.out.println();
        }
    }
}
