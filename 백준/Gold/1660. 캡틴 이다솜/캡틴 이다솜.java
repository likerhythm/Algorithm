import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N; // 대포알 개수
    static int[] dp; // dp[i]: i개의 대포알로 만들 수 있는 사면체의 최소 개수
    static Set<Integer> sizeSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int base = 1, baseSize = 1;
        int size = 1;
        while (size <= N) {
            dp[size] = 1;
            sizeSet.add(size);
            base++;
            baseSize += base;
            size += baseSize;
        }

        for (int s : sizeSet) {
            for (int i = 1; i <= N; i++) {
                if (dp[i] == Integer.MAX_VALUE) continue;
                if (i + s > N) continue;
                dp[i + s] = Math.min(dp[i + s], dp[i] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}
