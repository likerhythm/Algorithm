import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 벌집의 가로 세로 크기
    static int M; // 진행 날짜 수
    static int[][] initIncrements; // 날짜별 초기 애벌레들의 증가량
    static int[][] arr; // 벌집
    static int[][] increments;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initIncrements = new int[M][2 * N - 1];

        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int idx = 0;
            for (int j = 0; j < 3; j++) {
                while (input[j] > 0) {
                    initIncrements[i][idx++] = j;
                    input[j] -= 1;
                }
            }
        }

        arr = new int[N][N];
        increments = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(arr[i], 1);
        }

        for (int day = 1; day <= M; day++) {
            dayInit(day);
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    int nowIncrement = Math.max(increments[i][j - 1], Math.max(increments[i - 1][j - 1], increments[i - 1][j]));
                    arr[i][j] += nowIncrement;
                    increments[i][j] = nowIncrement;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void dayInit(int day) {
        int idx = 0;
        for (int i = N - 1; i >= 0; i--) {
            arr[i][0] += initIncrements[day - 1][idx];
            increments[i][0] = initIncrements[day - 1][idx++];
        }
        for (int i = 1; i < N; i++) {
            arr[0][i] += initIncrements[day - 1][idx];
            increments[0][i] = initIncrements[day - 1][idx++];
        }
    }
}
