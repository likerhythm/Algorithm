import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static int[][] dp;
    static int N, M, H;
    static List<Integer>[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        H = split[2];

        dp = new int[H + 1][N];
        blocks = new List[N];
        for (int i = 0; i < N; i++) {
            blocks[i] = new ArrayList<>();
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < split.length; j++) {
                blocks[i].add(split[j]);
            }
            blocks[i].add(0);
            Collections.sort(blocks[i]);
        }

        for (int h : blocks[0]) {
            if (h == 0) continue;
            dp[h][0] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int h : blocks[i]) { // 블럭
                for (int targetH = 1; targetH < H + 1; targetH++) { // 만들고자 하는 높이
                    if (h == 0) {
                        dp[targetH][i] = dp[targetH][i - 1];
                        continue;
                    }
                    if (targetH == h) { // 이 블럭만으로 targetH 높이를 만들 수 있는 경우
                        dp[h][i] += 1;
                        continue;
                    }
                    if (targetH - h < 0) continue; // 블럭이 targetH 높이보다 높은 경우
                    if (dp[targetH - h][i - 1] == 0) continue; // targetH 높이를 만들 수 없는 경우
                    dp[targetH][i] = (dp[targetH][i] + dp[targetH - h][i - 1]) % 10007;
                }
            }
        }

//        for (int[] a :dp) {
//            System.out.println(Arrays.toString(a));
//        }

        System.out.println(dp[H][N - 1]);
    }
}
