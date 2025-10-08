import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int N, K;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        K = split[1];

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (N == 1) {
            System.out.println(1);
            return;
        }

        int start = 0, end = 0;
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        while (end < N) {
            int val = arr[end];
            map.put(val, map.getOrDefault(val, 0) + 1);

            // 조건 위반 시 start 이동
            while (map.get(val) > K) {
                map.put(arr[start], map.get(arr[start]) - 1);
                if (map.get(arr[start]) == 0) {
                    map.remove(arr[start]);
                }
                start++;
            }

            answer = Math.max(answer, end - start + 1);
            end++;
        }

        System.out.println(answer);
    }
}
