import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] commands;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        commands = new int[K][3];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < K; i++) {
            commands[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

//        arr = rotate(arr, new int[] {3, 4, 2});
//        arr = rotate(arr, new int[] {4, 2, 1});
//        print(arr);

        boolean[] visited = new boolean[K];
        backtracking(arr, visited);

        System.out.println(answer);
    }

    private static void print(int[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    private static void backtracking(int[][] arr, boolean[] visited) {
        boolean allVisited = true;
        for (boolean b : visited) {
            if (!b) {
                allVisited = false;
                break;
            }
        }

        if (allVisited) {
//            print(arr);
            answer = Math.min(answer, calcScore(arr));
//            System.out.println(answer);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            int[][] tmpArr = rotate(arr, commands[i]);
            visited[i] = true;
            backtracking(tmpArr, visited);
            visited[i] = false;
        }
    }

    private static int[][] rotate(int[][] arr, int[] command) {
        int r = command[0] - 1;
        int c = command[1] - 1;
        int s = command[2];

        int[][] result = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = arr[i][j];
            }
        }
        for (int layer = s; layer > 0; layer--) {
            int length = layer * 2 + 1;
            int startR = r - layer;
            int startC = c - layer;
            int tmp = arr[startR][startC];
            for (int i = 1; i < length; i++) { // 왼쪽 세로줄
                result[startR + i - 1][startC] = result[startR + i][startC];
            }
            for (int i = 1; i < length; i++) { // 아래쪽 가로줄
                result[startR + length - 1][startC + i - 1] = result[startR + length - 1][startC + i];
            }
            for (int i = 1; i < length; i++) { // 오른쪽 세로줄
                result[startR + length - i][startC + length - 1] = result[startR + length - i - 1][startC + length - 1];
            }
            for (int i = 1; i < length - 1; i++) { // 위쪽 가로줄 회전
                result[startR][startC + length - i] = result[startR][startC + length - i - 1];
            }
            result[startR][startC + 1] = tmp;
        }
        return result;
    }

    private static int calcScore(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int score = 0;
            for (int j = 0; j < M; j++) {
                score += arr[i][j];
            }
            min = Math.min(min, score);
        }

        return min;
    }
}
