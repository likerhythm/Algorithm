import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N; // 벌집의 가로 세로 크기
    static int M; // 진행 날짜 수
    static int[] line; // 테두리 성장치를 저장할 배열 (크기: 2*N - 1)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        line = new int[2 * N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int zeroCnt = Integer.parseInt(st.nextToken());
            int oneCnt = Integer.parseInt(st.nextToken());
            int twoCnt = Integer.parseInt(st.nextToken());

            int oneStartIdx = zeroCnt;

            int twoStartIdx = zeroCnt + oneCnt;

            if (oneStartIdx < 2 * N - 1) {
                line[oneStartIdx]++;
            }
            if (twoStartIdx < 2 * N - 1) {
                line[twoStartIdx]++;
            }
        }

        int currentGrowth = 0;
        for (int i = 0; i < 2 * N - 1; i++) {
            currentGrowth += line[i];
            line[i] = currentGrowth;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(line[(N - 1) - i] + 1).append(" ");
            for (int j = 1; j < N; j++) {
                sb.append(line[(N - 1) + j] + 1).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}