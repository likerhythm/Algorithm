import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        int x, n;
        while ((line = br.readLine()) != null) {
            x = Integer.parseInt(line) * 10000000; // 구멍의 너비
            n = Integer.parseInt(br.readLine()); // 레고 조각의 수
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(nums);

            boolean flag = false;

            int left = 0;
            int right = n - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum < x) {
                    left++;
                } else if (sum > x) {
                    right--;
                } else {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.out.println("danger");
            } else {
                System.out.println("yes " + nums[left] + " " + nums[right]);
            }
        }
    }
}
