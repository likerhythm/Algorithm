import java.util.*;
import java.io.*;

public class Main {

    static int N; // 기존 휴게소 개수
    static int M; // 더 지으려는 휴게소 개수
    static int L; // 고속도로 길이
    static Queue<Integer> interval;
    static int answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);

        int[] inter;
        if (N > 0) {
            inter = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(inter);
            interval = new LinkedList<>();
            interval.add(inter[0]);
            for (int i=0; i<N-1; i++) {
                interval.add(inter[i + 1] - inter[i]);
            }
            interval.add(L - inter[N - 1]);
        } else {
            inter = new int[0];
            interval = new LinkedList<>();
            interval.add(L);
        }

        

        binarySearch();

        System.out.println(answer);
    }

    // 휴게소 간의 최대 간격 기준으로 탐색
    // 최소 간격 = 1, 최대 간격 = L;
    static void binarySearch() {
        int left = 1;
        int right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            int sum = 0; // 추가로 설치할 수 있는 휴게소 개수
            for (int inter : interval) {
                sum += inter / mid;
                if (inter % mid == 0) {
                    sum--;
                }
            }

            if (sum > M) { // 최대 간격을 mid로 했을 때 더 많은 휴게소를 설치할 수 있는 경우 => 최대 간격을 늘림
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
    }
}