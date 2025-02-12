import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1~99는 모두 한수이므로 N이 100이상인 경우에만 탐색
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 100) {
            System.out.println(N);
            return;
        }

        int cnt = 99;
        for (int i=100; i<=N; i++) {
            int h = i / 100;
            int t = (i - h * 100) / 10;
            int o = (i - h * 100) % 10;
            if (h - t == t - o) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
