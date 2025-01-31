import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, C; // 집 개수, 공유기 개수
    static int[] houses;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);

        houses = new int[N];
        for (int i=0; i<N; i++) {
            int house = Integer.parseInt(br.readLine());
            houses[i] = house;
        }
        Arrays.sort(houses);

        int left = 0;
        int right = houses[N - 1] - houses[0];
        int answer = 0;
        while (left <= right) {
            int nowTerm = (left + right) / 2;
            int cnt = 1;
            int preHouse = houses[0];

            for (int i=1; i<N; i++) {
                if (houses[i] - preHouse >= nowTerm) {
                    cnt++;
                    preHouse = houses[i];
                }
            }

            if (cnt >= C) {
                answer = nowTerm;
                left = nowTerm + 1;
            } else {
                right = nowTerm - 1;
            }
        }
        System.out.println(answer);
    }
}
