import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp1 = new int[N]; // dp1[i] : i행 사자를 놓을 때 사자를 배치하는 최대 경우의 수
        int[] dp2 = new int[N]; // dp2[i] : i행 사자를 놓지 않을 때 사자를 배치하는 최대 경우의 수

        dp1[0] = 2;
        dp2[0] = 1;

        for (int i=1; i<N; i++) {
            dp1[i] = (dp1[i - 1] + dp2[i - 1] * 2) % 9901;
            dp2[i] = (dp1[i - 1] + dp2[i - 1]) % 9901;
        }

        System.out.println((dp1[N - 1] + dp2[N - 1]) % 9901);
    }
}
