import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N], B = new int[N], C = new int[N], D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int size = N * N;
        long[] ab = new long[size];
        long[] cd = new long[size];

        int idx = 0;
        for (int a : A)
            for (int b : B)
                ab[idx++] = (long) a + b;

        idx = 0;
        for (int c : C)
            for (int d : D)
                cd[idx++] = (long) c + d;

        Arrays.sort(ab);
        Arrays.sort(cd);

        long answer = 0;
        int left = 0, right = size - 1;

        while (left < size && right >= 0) {
            long sum = ab[left] + cd[right];

            if (sum == 0) {
                long cntAB = 1, cntCD = 1;
                while (left + 1 < size && ab[left] == ab[left + 1]) { left++;  cntAB++; }
                while (right - 1 >= 0 && cd[right] == cd[right - 1]) { right--; cntCD++; }
                answer += cntAB * cntCD;
                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answer);
    }
}