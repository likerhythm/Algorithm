import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static Cook[] cooks;
    static String binary;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        cooks = new Cook[N];
        for (int i=0; i<N; i++) {
            String[] split = br.readLine().split(" ");
            cooks[i] = new Cook(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        int minDiff = Integer.MAX_VALUE;
        for (int i=1; i< Math.pow(2, N); i++) {
            binary = Integer.toBinaryString(i);
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<N-binary.length(); j++) {
                sb.append('0');
            }
            sb.append(binary);
            binary = sb.toString();
            int SScore = 1;
            int BScore = 0;
            for (int j=0; j<N; j++) {
                char flag = binary.charAt(j);
                if (flag == '0') { // 추가하지 않음
                    continue;
                }
                if (flag == '1') { // 추가함
                    Cook cook = cooks[j];
                    SScore *= cook.S;
                    BScore += cook.B;
                }
            }
            minDiff = Math.min(minDiff, Math.abs(SScore - BScore));
        }

        System.out.println(minDiff);
    }

    private static class Cook {
        int S, B;

        Cook(int S, int B) {
            this.S = S;
            this.B = B;
        }
    }
}
