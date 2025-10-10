import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N, M;
    static int B;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = split[0];
        M = split[1];
        B = split[2];

        int[][] arr =  new int[N][M];
        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                min = Math.min(arr[i][j], min);
                max = Math.max(arr[i][j], max);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int answerH = 0;
        for (int h = min; h <= max; h++) {
            int time = 0;
            int blocks = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] < h) {
                        time += h - arr[i][j];
                        blocks -= h - arr[i][j];
                    } else if (arr[i][j] > h) {
                        time += (arr[i][j] - h) * 2;
                        blocks += arr[i][j] - h;
                    }
                }
            }

            if (blocks < -B) continue; // 사용한 블럭이 주어진 블럭보다 많은 경우 넘김
            if (time <= minTime) {
                minTime = time;
                answerH = h;
            }
        }

        System.out.println(minTime + " " + answerH);
    }
}