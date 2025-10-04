import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static int N, M;
    static Set<Integer> set = new HashSet<>();
    static List<Integer> nums;
    static int[] tmp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        tmp = new int[M];
        split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int a : split) {
            set.add(a);
        }

        nums = new ArrayList<>(set);
        Collections.sort(nums);
        backtracking(0);

        System.out.println(sb);
    }

    private static void backtracking(int cnt) throws IOException {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(tmp[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            tmp[cnt] = num;
            backtracking(cnt + 1);
        }
    }
}
