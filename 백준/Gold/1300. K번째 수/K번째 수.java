import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        // B[K] -> B[K]보다 작거나 같은 원소가 최소 K개이다.
        // 각 행마다 임의의 수 mid 보다 작거나 같은 원소의 개수를 count 해서 합친다.
            // 합친 결과가 K보다 크면 right를 줄인다.
            // 합친 결과가 K보다 작으면 left를 늘린다.

        int left = 1, right = K;
        while (left < right) {
            int mid = (left + right) / 2; // 임의의 수
            int count = 0;
            for (int i=1; i<=N; i++) {
                count += Math.min(mid / i, N);
            }

            if (count >= K) {
                right = mid;
            } else {
                left = mid + 1;
            }
//            System.out.println(left + " " + right);
        }

        System.out.println(left);
    }

    //    1  2  3  4
    // 1  1  2  3  4
    // 2  2  4  6  8
    // 3  3  6  9  12
    // 4  4  8  12 16
}
