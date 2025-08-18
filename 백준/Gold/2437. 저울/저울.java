import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static List<Integer> weights = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(i -> weights.add(i));

        Collections.sort(weights);

        int target = 0;
        for (int w : weights) {
            if (w > target + 1) {
                break;
            }
            target += w;
        }

        System.out.println(target + 1);
    }
}
