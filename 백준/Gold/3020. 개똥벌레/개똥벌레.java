import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int[] up = new int[H + 1];
        int[] down = new int[H + 1];

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            if (i % 2 == 0) up[height]++;
            else down[height]++;
        }

        for (int i = H - 1; i >= 1; i--) {
            up[i] += up[i + 1];
            down[i] += down[i + 1];
        }

        int min = N, count = 0;
        for (int i = 1; i <= H; i++) {
            int total = up[i] + down[H - i + 1];
            if (total < min) {
                min = total;
                count = 1;
            } else if (total == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
}
