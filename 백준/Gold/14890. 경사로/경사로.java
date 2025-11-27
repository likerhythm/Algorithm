import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[][] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) { // 가로줄 탐색
            boolean canGo = walk(arr[i]);
//            System.out.println((i + 1) + "번째 가로줄: " + canGo);
            if (canGo) answer++;
        }

        for (int j = 0; j < N; j++) { // 세로줄 탐색
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = arr[i][j];
            }
            boolean canGo = walk(a);
//            System.out.println((j + 1) + "번째 세로줄: " + canGo);
            if (canGo) answer++;
        }

        System.out.println(answer);
    }

    private static boolean walk(int[] line) {
        int cnt = 0;
        int pre = line[0];
        for (int i = 1; i < N; i++) {
            int now = line[i];
            cnt++;
            if (now != pre) { // 높이가 달라진 경우
                if (Math.abs(now - pre) > 1) return false; // 높이 차가 1 초과인 경우

                if (now < pre) { // 내리막인 경우
                    cnt = 1;
                    while (++i < N && line[i] == now && cnt < L) {
                        cnt++;
                    }
                    if (cnt < L) return false; // 경사로를 설치할 수 없는 경우
                    cnt = -1;
                    i--;
                } else { // 오르막인 경우
                    if (cnt < L) return false; // 경사로를 설치할 수 없는 경우
                    cnt = 0;
                }
            }
            pre = line[i];
        }
        return true;
    }
}
