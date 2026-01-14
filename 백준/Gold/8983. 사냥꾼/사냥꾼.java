import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Main {

    static int N; // 동물의 수
    static int M; // 사대의 수
    static int L; // 사정거리
    static int[] guns; // 사대 x 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        guns = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(guns);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b > L) continue;

            boolean found = binarySearch(a, b);
//            System.out.println("(" + a + ", " + b + ") " + found);
            if (found) answer++;
        }

        System.out.println(answer);
    }

    private static boolean binarySearch(int a, int b) {
        Function<Integer, Integer> inRange = (target) -> {
            if (a - L + b <= target && target <= a + L - b) return 0;
            if (target < a - L + b) return -1;
            else return 1;
        };

        int left = 0, right = M - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int apply = inRange.apply(guns[mid]);
            if (apply == 0) return true;
            else if (apply == -1) left = mid + 1; // mid에 해당하는 사대가 현재 동물보다 왼쪽에 위치한 경우
            else right = mid - 1; // mid에 해당하는 사대가 현재 동물보다 오른쪽에 위치한 경우
        }

        return false;
    }
}
