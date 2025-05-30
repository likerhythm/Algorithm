import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        answer = new int[M];

        f(0);
        System.out.println(sb);
    }

    static void f(int idx) {
        if (idx == M) {
            for (int a : answer) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=1; i<=N; i++) {
            answer[idx] = i;
            f(idx + 1);
        }
    }
}