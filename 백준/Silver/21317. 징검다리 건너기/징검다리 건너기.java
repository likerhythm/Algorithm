import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] jumpEnergy = new int[21][2];
        int veryBigJumpEnergy;
        for (int i=1; i<N; i++) {
            jumpEnergy[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        veryBigJumpEnergy = Integer.parseInt(br.readLine());

        int[] dp1 = new int[21]; // 이전에 매우 큰 점프를 사용하지 않고 도달했을 때 최소 에너지
        int[] dp2 = new int[21]; // 이전에 매우 큰 점프를 사용하고 도달했을 때 최소 에너지
        Arrays.fill(dp1, MAX);
        Arrays.fill(dp2, MAX);

        dp1[1] = 0;
        dp1[2] = jumpEnergy[1][0];
        dp1[3] = Math.min(jumpEnergy[1][1], dp1[2] + jumpEnergy[2][0]);

        for (int i=4; i<=N; i++) {
            dp1[i] = Math.min(dp1[i - 1] + jumpEnergy[i - 1][0], dp1[i - 2] + jumpEnergy[i - 2][1]);
            dp2[i] = Math.min(
                        dp1[i - 3] + veryBigJumpEnergy,
                        Math.min(dp2[i - 1] + jumpEnergy[i - 1][0], dp2[i - 2] + jumpEnergy[i - 2][1])
            );
        }

//        dp2[1] = Integer.MAX_VALUE;
//        dp2[2] = Integer.MAX_VALUE;
//        dp2[3] = Integer.MAX_VALUE;
        System.out.println(Math.min(dp1[N], dp2[N]));
    }
}
