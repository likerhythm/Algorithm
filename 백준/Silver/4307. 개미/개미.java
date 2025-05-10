import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int t=0; t<testcase; t++) {
            String[] input = br.readLine().split(" ");

            int l = Integer.parseInt(input[0]); // 막대의 길이
            int n = Integer.parseInt(input[1]); // 개미의 수

            int minTime = 0;
            int maxTime = 0;
            for (int i=0; i<n; i++) { // 개미 위치 입력
                int pos = Integer.parseInt(br.readLine());
                if (pos <= l / 2 && pos > minTime) {
                    minTime = pos;
                } else if (pos > l / 2 && l - pos > minTime) {
                    minTime = l - pos;
                }

                if (pos <= l / 2 && l - pos > maxTime) {
                    maxTime = l - pos;
                } else if (pos > l / 2 && pos > maxTime) {
                    maxTime = pos;
                }
            }

            System.out.println(minTime + " " + maxTime);
        }
    }
}