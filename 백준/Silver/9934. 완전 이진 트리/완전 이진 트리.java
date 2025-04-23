import java.util.*;
import java.io.*;

public class Main {

    static int K;
    static int buildingCnt;
    static int[] initArr;
    static StringBuilder[] answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        buildingCnt = (int) Math.pow(2, K) - 1;
        answer = new StringBuilder[K + 1];
        for (int i=1; i<=K; i++) {
            answer[i] = new StringBuilder();
        }

        initArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        printFunction(0, buildingCnt - 1, 1); // start, end, level

        for (int level=1; level <= K; level++) {
            System.out.println(answer[level]);
        }
    }

    static void printFunction(int start, int end, int level) {
        int mid = (start + end) / 2;
        answer[level].append(initArr[mid] + " ");
        if (level == K) {
            return;
        }
        printFunction(start, mid - 1, level + 1);
        printFunction(mid + 1, end, level + 1);
    }
}