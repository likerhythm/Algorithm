import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static int C; // 모을 고객의 수
    static int N; // 도시의 수
    static int[] dp; // 고객 i명을 모을 때 최소 비용
    static List<City> cities = new ArrayList<>();

    static class City implements Comparable<City> {
        int cost;
        int customer;

        public City(int cost, int customer) {
            this.cost = cost;
            this.customer = customer;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.customer, o.customer);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        C = split[0]; // 모을 고객 수
        N = split[1]; // 도시의 수

        dp = new int[C + 10001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            cities.add(new City(split[0], split[1]));
        }

        Collections.sort(cities);
        int atLeastCustomer = cities.get(0).customer;

        for (City city : cities) { // dp 초기화
            int cost = city.cost;
            int customer = city.customer;
            dp[customer] = Math.min(cost, dp[customer]);
        }

        for (int target = atLeastCustomer; target <= C + 10000; target++) { // 고객 target명을 모을 때 최소 비용 구하기
            for (City city : cities) {
                int cost = city.cost;
                int customer = city.customer;

                int count = 1;
                while (true) {
                    if (target - customer * count < atLeastCustomer) {
                        break;
                    }

                    if (dp[target - customer * count] == Integer.MAX_VALUE) {
                        count++;
                        continue;
                    }

                    dp[target] = Math.min(dp[target], dp[target - customer * count] + cost * count);

                    count++;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = C; i <= C + 10000; i++) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
//        System.out.println(Arrays.toString(dp));
    }
}
