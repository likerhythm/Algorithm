import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    static int N, M;
    static int[] nums;
    static String[] strings; // 뽑은 문자열
    static Set<String> results = new LinkedHashSet<>();
    static boolean[] checked;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        checked = new boolean[N];
        strings = new String[M];

        f(0);

        for (String str : results) {
            System.out.println(str);
        }
    }

    /**
     *
     * @param cnt - 이전 과정까지 뽑은 숫자의 수
     */
    private static void f(int cnt) {
        if (cnt == M) {
            String join = String.join(" ", strings);
            results.add(join);
            return;
        }

        for (int i=0; i<N; i++) {
            if (checked[i]) {
                continue;
            }
            strings[cnt] = String.valueOf(nums[i]);
            checked[i] = true;
            f(cnt + 1);
            strings[cnt] = null;
            checked[i] = false;
        }
    }
}
