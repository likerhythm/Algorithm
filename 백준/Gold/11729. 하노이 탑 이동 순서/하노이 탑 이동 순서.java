import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int count = (int) (Math.pow(2, N) - 1);
        System.out.println(count);

        hanoi(N, 1, 2, 3);
        System.out.println(sb.toString());
    }

    private static void hanoi(int platePos, int from, int another, int to) {
        if (platePos == 1) {
            sb.append(from + " " + to).append("\n");
            return;
        }
        hanoi(platePos - 1, from, to, another);
        sb.append(from + " " + to).append("\n");
        hanoi(platePos - 1, another, from, to);
    }
}
