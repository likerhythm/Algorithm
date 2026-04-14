import java.util.*;
import java.io.*;

public class Main{
  
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    int[][] arr = new int[N][M];
    for (int i = 0; i < N; i++) {
      arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    int[][] target = new int[N][M];
    for (int i = 0; i < N; i++) {
      target[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    if (N < 3 || M < 3) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (arr[i][j] != target[i][j]) {
            System.out.println(-1);
            return;
          }
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < N - 2; i++) {
      for (int j = 0; j < M - 2; j++) {
        if (arr[i][j] == target[i][j]) continue;
        answer++;
        for (int a = i; a < i + 3; a++) {
          for (int b = j; b < j + 3; b++) {
            arr[a][b] = 1 - arr[a][b];
          }
        }
      }
    }

    outer: for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr[i][j] != target[i][j]) {
          answer = -1;
          break outer;
        } 
      }
    }

    System.out.println(answer);
  }
}
