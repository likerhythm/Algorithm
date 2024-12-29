import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int X;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        X = Integer.parseInt(bf.readLine());
        int answer = 0;
        while (X > 0) {
            double length = 0;
            for (int i=0; i<9; i++) {
                length = Math.pow(2, i);
                if (length > X) {
                    break;
                }
            }
            length = length / 2;

            X -= (int) length;
            answer++;
        }
        System.out.println((int) answer);
    }
}
