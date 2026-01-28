import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> lowerQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> upperQ = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (upperQ.size() == lowerQ.size()) {
                lowerQ.add(num);
            } else {
                upperQ.add(num);
            }

            if (!upperQ.isEmpty() && lowerQ.peek() > upperQ.peek()) {
                int tmp1 = lowerQ.poll();
                int tmp2 = upperQ.poll();
                lowerQ.add(tmp2);
                upperQ.add(tmp1);
            }

            sb.append(lowerQ.peek() + "\n");
        }
        System.out.println(sb);
    }
}
