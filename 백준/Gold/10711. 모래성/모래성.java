import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[] dns = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dms = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (input[j] == '.') {
                    arr[i][j] = 0;
                    q.add(new int[] {i, j});
                } else {
                    arr[i][j] = input[j] - '0';
                }
            }
        }

        int answer = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] poll = q.poll();
                int n = poll[0];
                int m = poll[1];

                for (int i = 0; i < 8; i++) {
                    int nn = n + dns[i];
                    int nm = m + dms[i];

                    if (!inRange(nn, nm)) continue;

                    if (arr[nn][nm] > 0) {
                        arr[nn][nm]--; 
                        // 이번 파도에 성을 깎은 모래는 큐에서 나가기 때문에 다시 참여하지 않음
                        // 그래서 arr의 값을 깎아도 상관 없음

                        if (arr[nn][nm] == 0) {
                            q.add(new int[] {nn, nm});
                        }
                    }
                }
            }

            if (!q.isEmpty()) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}