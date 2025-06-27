import java.util.*;
import java.io.*;

public class Main {

    static int[] nums;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(br.readLine());
            nums[i] = v;
        }

        Arrays.sort(nums);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int z = nums[i];
            for (int j = N - 1; j > i; j--) {
                int k = nums[j];
                int target = k - z;
                if (target <= 0) { // 만들 수 없는 경우
                    continue;
                }

                int left = i;
                int right = j - 1;
                while (left <= right) {
                    int x = nums[left];
                    int y = nums[right];
                    if (left >= j || right >= j) {
                        break;
                    }
                    int sum = x + y;
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        answer = Math.max(answer, k);
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}