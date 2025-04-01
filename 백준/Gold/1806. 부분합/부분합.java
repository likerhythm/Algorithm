import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br. readLine().split(" ");

        int N = Integer.parseInt(split[0]); // 수열의 길이
        int S = Integer.parseInt(split[1]); // 합이 S 이상

        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = 0;
        int answer = Integer.MAX_VALUE;
        int sum = numbers[0];

        while (left <= right) {
            if (sum < S) {
                if (right == N - 1) {
                    break;
                }
                sum += numbers[++right];
            } else {
                answer = Math.min(answer, right - left + 1);
                sum -= numbers[left++];
                if (left == N) {
                    break;
                }
                if (right < left) {
                    right = left;
                    sum = numbers[right];
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);
  }
}