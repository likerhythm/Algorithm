import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;

    static class Candy {
        int calory;
        int price;

        public Candy (int calory, int price) {
            this.calory = calory;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            if (N == 0 && M == 0) {
                break;
            }

            Candy[] candies = new Candy[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int calory = Integer.parseInt(st.nextToken());
                int price = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
                candies[i] = new Candy(calory, price);
            }

            int[] dp = new int[M + 1];
            for (int i = 0; i < N; i++) {
                int calory = candies[i].calory;
                int price = candies[i].price;
                for (int j = price; j <= M; j++) {
                    dp[j] = Math.max(dp[j], dp[j - price] + calory);
                }
            }

            System.out.println(dp[M]);
        }
    }
}
