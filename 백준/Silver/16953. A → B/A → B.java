import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        long start = Integer.parseInt(split[0]);
        long end = Integer.parseInt(split[1]);

        bfs(start, end);
    }

    static class QueueComponent {
        long number;
        long cnt;

        public QueueComponent(long number, long cnt) {
            this.number = number;
            this.cnt = cnt;
        }
    }

    private static void bfs(long start, long end) {
        Queue<QueueComponent> queue = new LinkedList<>();
        queue.add(new QueueComponent(start, 0));

        while (!queue.isEmpty()) {
            QueueComponent poll = queue.poll();
            long number = poll.number;
            long cnt = poll.cnt;
            if (number == end) {
                System.out.println(cnt + 1);
                return;
            }

            if (number * 2 <= end) {
                queue.add(new QueueComponent(number * 2, cnt + 1));
//                System.out.printf("%3d : %d from %3d : %d\n", number * 2, cnt + 1, number, cnt);
            }
            if (number * 10 + 1 <= end) {
                queue.add(new QueueComponent(number * 10 + 1, cnt + 1));
//                System.out.printf("%3d : %d from %3d : %d\n", number * 10 + 1, cnt + 1, number, cnt);
            }
        }
        System.out.println(-1);
    }
}
