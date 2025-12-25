import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static Count[][] counts;

    static class Count {
        int value, num;

        Count(int value, int num) {
            this.value = value;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        counts = new Count[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] == 1) continue;
                setCount(i, j, num++);
            }
        }

//        printCounts();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) continue;
                Set<Integer> numSet = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int nn = i + dns[k];
                    int nm = j + dms[k];
                    if (!inRange(nn, nm)) continue;
                    if (arr[nn][nm] > 0) continue;
                    if (numSet.contains(counts[nn][nm].num)) continue;
                    if (arr[nn][nm] == 0) {
                        arr[i][j] += counts[nn][nm].value;
                        numSet.add(counts[nn][nm].num);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] % 10);
            }
            System.out.println();
        }
    }

    private static void printCounts() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (counts[i][j] == null) {
                    System.out.print(0);
                } else {
                    System.out.print(counts[i][j].value);
                }
            }
            System.out.println();
        }
    }

    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    private static void setCount(int n, int m, int num) {
        Count count = new Count(1, num);
        counts[n][m] = count;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {n, m});
        visited[n][m] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) continue;
                if (visited[nn][nm]) continue;
                if (arr[nn][nm] == 1) continue;

                visited[nn][nm] = true;
                q.add(new int[] {nn, nm});
                counts[nn][nm] = count;
                count.value += 1;
            }
        }
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
