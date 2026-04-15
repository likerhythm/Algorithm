import java.util.*;
import java.io.*;

public class Main{
  // 1 3 6 7 9
  // 3 6 7 8 10 12 14 15 18 20

  static int N, K;
  static int[] pos;
  
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    pos = new int[N];
    for (int i = 0; i < N; i++) {
      pos[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(pos);

    int[] gaps = new int[N - 1];
    for (int i = 0; i < N - 1; i++) {
      gaps[i] = pos[i + 1] - pos[i];
    }
    Arrays.sort(gaps);

    int totalLength = pos[N - 1] - pos[0];
    int removeCnt = Math.min(K - 1, gaps.length);
    for (int i = gaps.length - 1; i >= gaps.length - removeCnt; i--) {
      totalLength -= gaps[i];
    }
    
    System.out.println(totalLength);
  }
}
