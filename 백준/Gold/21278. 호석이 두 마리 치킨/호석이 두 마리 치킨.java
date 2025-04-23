import java.util.*;
import java.io.*;

public class Main {

    static int N; // 건물의 개수(1 ~ N)
    static int M; // 도로의 개수
    static int[][] graph;
    static int[][] distances;
    static int[] answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        graph = new int[N + 1][N + 1];
        distances = new int[N + 1][N + 1];
        for (int[] arr : distances) {
            Arrays.fill(arr, Integer.MAX_VALUE / 3);
        }

        for (int i=1; i<=N; i++) {
            distances[i][i] = 0;
        }
        answer = new int[3];
        answer[2] = Integer.MAX_VALUE;
        for (int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph[a][b] = 1;
            graph[b][a] = 1;
            
            distances[a][b] = 1;
            distances[b][a] = 1;
        }

        floydWarshall();
        dijkstra();
        bellmanFord();

        // printDistances();

        backtracking(0, 0, new int[2]);

        for (int a : answer) {
            System.out.print(a + " ");
        }
    }

    static void floydWarshall() {
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    int oldValue = distances[start][end];
                    int newValue = distances[start][mid] + distances[mid][end];
                    if (oldValue < newValue) {
                        continue;
                    }
                    distances[start][end] = newValue;
                }
            }
        }
    }

    static void dijkstra() {
        
    }

    static void bellmanFord() {
        
    }

    static void backtracking(int cnt, int pre, int[] tmpAnswer) {
        if (cnt == 2) { // 건물 두 개를 선택했다면 왕복 시간의 합 계산
            int tmpTime = calcTime(tmpAnswer[0], tmpAnswer[1]);
            if (answer[2] > tmpTime) {
                answer[0] = tmpAnswer[0];
                answer[1] = tmpAnswer[1];
                answer[2] = tmpTime;
            }
            return;
        }

        for (int i=pre+1; i<=N; i++) {
            tmpAnswer[cnt] = i;
            backtracking(cnt + 1, i, tmpAnswer);
        }
    }

    static int calcTime(int a, int b) {
        int sum = 0;
        for (int i=1; i<=N; i++) {
            sum += Math.min(distances[i][a] * 2, distances[i][b] * 2);
        }
        return sum;
    }

    static void printDistances() {
        for (int[] arr : distances) {
            System.out.println(Arrays.toString(arr));
        }
    }
}