import java.util.*;
import java.io.*;

public class Main {

    static long T;
    static long[] A, B;
    static List<Long> subsumA = new ArrayList<>(), subsumB = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int m = Integer.parseInt(br.readLine());
        B = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long[] sumA = new long[n];
        sumA[0] = A[0];
        for (int i = 1; i < n; i++) { // 누적합 구하기
            sumA[i] = sumA[i - 1] + A[i];
        }

        long[] sumB = new long[m];
        sumB[0] = B[0];
        for (int i = 1; i < m; i++) { // 누적합 구하기
            sumB[i] = sumB[i - 1] + B[i];
        }

        for (int i = 1; i <= n; i++) { // 길이가 i인 부배열의 합 모두 구해서 subsum에 넣기
            for(int start = 0; start <= n - i; start++) {
                if (start == 0) {
                    subsumA.add(sumA[start + i - 1]);
                    continue;
                }
                long subsum = sumA[start + i - 1] - sumA[start - 1];
                subsumA.add(subsum);
            }
        }

        for (int i = 1; i <= m; i++) { // 길이가 i인 부배열의 합 모두 구해서 subsum에 넣기
            for(int start = 0; start <= m - i; start++) {
                if (start == 0) {
                    subsumB.add(sumB[start + i - 1]);
                    continue;
                }
                long subsum = sumB[start + i - 1] - sumB[start - 1];
                subsumB.add(subsum);
            }
        }

        Collections.sort(subsumA);
        Collections.sort(subsumB);

        // System.out.println(subsumA);
        // System.out.println(subsumB);

        // 이분탐색
        long answer = 0;
        for (long a : subsumA) {
            answer += bs(a); // A의 부 배열의 합이 a인 경우 가짓수
        }
        
        System.out.println(answer);
    }

    static long bs(long a) {
        long target = T - a;
        long rb = rightBound(target);
        long lb = leftBound(target);
        // System.out.println("a = " + a + ", rb = " + rb + ", lb = " + lb);
        return rb - lb;
    }

    static long leftBound(long target) {
        int left = 0;
        int right = subsumB.size();

        while (left < right) {
            int mid = (left + right) / 2;
            long v = subsumB.get(mid);

            if (v >= target) {
                right = mid;
            } else if (v < target) {
                left = mid + 1;
            }
        }
        return right;
    }

    static long rightBound(long target) {
        int left = 0;
        int right = subsumB.size();

        while (left < right) {
            int mid = (left + right) / 2;
            long v = subsumB.get(mid);

            if (v <= target) {
                left = mid + 1;
            } else if (v > target) {
                right = mid;
            }
        }
        return right;
    }
}