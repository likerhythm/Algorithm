import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int N; // 그릇의 수
    static int d; // 초밥의 가짓수
    static int k; // 먹는 접시의 수
    static int c; // 쿠폰 번호
    static int[] belt;
    static Map<Integer, Integer> eat = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        d = split[1];
        k = split[2];
        c = split[3];

        belt = new int[N];

        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int left = 0;
        int right = k - 1;
        for (int i = left; i <= right; i++) {
            eat.put(belt[i], eat.getOrDefault(belt[i], 0) + 1);
        }

        int answer = 0;
        do {
            answer = Math.max(answer, eat.size() + (eat.containsKey(c) ? 0 : 1));
            eat.computeIfPresent(belt[left], (k, v) -> v - 1);
            if (eat.get(belt[left]) == 0) {
                eat.remove(belt[left]);
            }
            left = (left + 1) % N;
            right = (right + 1) % N;
            eat.put(belt[right], eat.getOrDefault(belt[right], 0) + 1);
        } while (right != k - 1);

        System.out.println(answer);
    }
}
