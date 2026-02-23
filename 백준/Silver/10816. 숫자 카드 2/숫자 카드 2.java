import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] cards;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(cards);

        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            sb.append(findRight(num) - findLeft(num)).append(" ");
        }

        System.out.println(sb);
    }

    private static int findRight(int target) {
        int left = 0, right = N;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] <= target) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    private static int findLeft(int target) {
        int left = 0, right = N;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] < target) left = mid + 1;
            else right = mid;
        }

        return left;
    }
}
