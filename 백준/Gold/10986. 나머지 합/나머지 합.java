import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static int[] remains;
    static long[] remainCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        remains = new int[N];
        arr = new int[N];
        remainCnt = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            arr[i] = input;
            if (i > 0) remains[i] = (remains[i - 1] + input % M) % M;
            else remains[0] = input % M;

            remainCnt[remains[i]]++;
        }

        long answer = 0;
        for (int i = 0; i < M; i++) {
            if (remainCnt[i] == 0) continue;
            if (i == 0) answer += remainCnt[i];
            answer += ((remainCnt[i] - 1) * remainCnt[i]) / 2;
        }

//        System.out.println(Arrays.toString(remains));
//        System.out.println(Arrays.toString(remainCnt));

        System.out.println(answer);
    }
}
