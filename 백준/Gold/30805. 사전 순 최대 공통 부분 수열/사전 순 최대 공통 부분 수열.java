import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int ALength;
    static int BLength;
    static int[] A;
    static int[] B;
    static List<Integer> answer = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ALength = Integer.parseInt(br.readLine());
        A = new int[ALength];
        String[] split = br.readLine().split(" ");
        for (int i=0; i<ALength; i++) {
            A[i] = Integer.parseInt(split[i]);
        }
        BLength = Integer.parseInt(br.readLine());
        B = new int[BLength];
        split = br.readLine().split(" ");
        for (int i=0; i<BLength; i++) {
            B[i] = Integer.parseInt(split[i]);
        }

        findMax(0, 0);

        System.out.println(answer.size());
        if (answer.size() > 0) {
            for (int a : answer) {
                System.out.print(a + " ");
            }
        }
    }

    private static void findMax(int AStart, int BStart) {
        int maxCommonVal = 0;
        int nextAStart = -1;
        int nextBStart = -1;
        for (int i=AStart; i<ALength; i++) {
            for (int j=BStart; j<BLength; j++) {
                if (A[i] == B[j] && A[i] > maxCommonVal) {
                    maxCommonVal = A[i];
                    nextAStart = i;
                    nextBStart = j;
                }
            }
        }
        if (nextAStart != -1) {
            answer.add(maxCommonVal);
            findMax(nextAStart + 1, nextBStart + 1);
        }
    }
}