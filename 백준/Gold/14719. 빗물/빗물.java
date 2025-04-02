import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int H, W;
    static int[] arr;
    static int answer;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        H = Integer.parseInt(split[0]);
        W = Integer.parseInt(split[1]);

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        check = new boolean[W];

        int newStart = 0;
        while (arr[newStart] == 0) {
            newStart++;
        }

        check[W - 1] = true;
        for (int start=newStart; start<W-1; start++) {
            if (!check[start]) {
                f(arr[start], start);
            }
        }

        System.out.println(answer);
    }

    private static void f(int height, int start) {
        check[start] = true;
        boolean found = false;

        for (int now=start+1; now<W; now++) {
            check[now] = true;
            if (arr[now] >= height) {
                check[now] = false;
                found = true;
                for (int k=start+1; k<now; k++) {
                    answer += height - arr[k];
                }
                break;
            }
        }

        if (!found) {
            f(height - 1, start);
        }
    }
}
