import java.util.*;
import java.io.*;

public class Main{

  static int N;
  static int[] costs;
  static int[] dp;
  
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    costs = new int[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      costs[i] = Integer.parseInt(st.nextToken());
    }

    dp = new int[N + 1];
    Arrays.fill(dp, 987654321);

    dp[0] = 0;
    for (int buy = 1; buy <= N; buy++) {
      for (int card = 1; card <= buy; card++) {
        if (dp[buy - card] == 987654321) continue;
        dp[buy] = Math.min(dp[buy - card] + costs[card], dp[buy]);
      }
    }
    
    System.out.println(dp[N]);
  }
}
