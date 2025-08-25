import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> tails = new ArrayList<>();
        for (int a : A) {
            if (tails.isEmpty() || tails.get(tails.size() - 1) < a) {
                tails.add(a);
                continue;
            }

            if (tails.get(tails.size() - 1) > a) {
                insertTail(a, tails);
            }
        }

        System.out.println(tails.size());
    }

    private static void insertTail(int a, List<Integer> tails) {
        int left = 0;
        int right = tails.size() - 1;
        int target = tails.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (tails.get(mid) > a) {
                right = mid;
                target = mid;
            } else if (tails.get(mid) < a) {
                left = mid + 1;
            } else {
                target = mid;
                break;
            }
        }

        tails.set(target, a);
    }
}
