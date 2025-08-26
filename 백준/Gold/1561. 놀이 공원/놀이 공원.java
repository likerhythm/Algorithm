import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N; // 아이들 수
    static int M; // 놀이기구 수
    static long[] times; // 놀이기구 운행 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];

        times = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long left = 0;
        long right = 30L * N;

        while (left < right) {
            long totalTime = (left + right) / 2;

            long volume = calcVolume(totalTime);

            if (volume < N) {
                left = totalTime + 1;
            } else {
                right = totalTime;
            }
        }

        long totalTime = left;
        long child = calcVolume(totalTime - 1);

        for (int i = 0; i < M; i++) {
            if (totalTime % times[i] == 0) {
                child++;
            }

            if (child == N) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static long calcVolume(long totalTime) {
        long result = M;

        for (long time : times) {
            result += totalTime / time;
        }

        return result;
    }
}
