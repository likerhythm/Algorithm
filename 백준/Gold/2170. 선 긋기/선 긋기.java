import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static class Line {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Line[] lines = new Line[N];

        for (int i = 0; i < N; i++) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = split[0];
            int y = split[1];
            lines[i] = new Line(x, y);
        }

        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line l1, Line l2) {
                if (l1.x != l2.x) {
                    return Integer.compare(l1.x, l2.x);
                }
                return Integer.compare(l1.y, l2.y);
            }
        });

        PriorityQueue<Line> pq = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line l1, Line l2) {
                return Integer.compare(l2.y, l1.y);
            }
        });

        for (Line line : lines) {
            if (pq.isEmpty()) {
                pq.add(line);
                continue;
            }

            if (line.y <= pq.peek().y) {
                continue;
            }

            if (line.x <= pq.peek().y) {
                Line poll = pq.poll();
                poll.y = line.y;
                pq.add(poll);
                continue;
            }

            pq.add(line);
        }

//        System.out.println(pq);

        int answer = 0;
        while (!pq.isEmpty()) {
            Line poll = pq.poll();
            answer += poll.y - poll.x;
        }

        System.out.println(answer);
    }
}
