import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Univ[] univs;
    static boolean[] lecture;

    static class Univ implements Comparable<Univ> {
        int pay, deadLine;

        Univ(int pay, int deadLine) {
            this.pay = pay;
            this.deadLine = deadLine;
        }

        @Override
        public int compareTo(Univ other) {
            if (this.pay == other.pay) {
                return this.deadLine - other.deadLine;
            }
            return other.pay - this.pay;
        }

        @Override
        public String toString() {
            return "[" + pay + ", " + deadLine + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        univs = new Univ[N];
        lecture = new boolean[10_001];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pay = Integer.parseInt(st.nextToken());
            int deadLine = Integer.parseInt(st.nextToken());
            univs[i] = new Univ(pay, deadLine);
        }

        PriorityQueue<Univ> pq = new PriorityQueue<>();
        for (Univ univ : univs) {
            pq.add(univ);
        }
        int answer = 0;
        while (!pq.isEmpty()) {
            Univ poll = pq.poll();
            int pay = poll.pay;
            int deadLine = poll.deadLine;
            for (int day = deadLine; day > 0; day--) {
                if (lecture[day]) continue;
                lecture[day] = true;
                answer += pay;
                break;
            }
        }

        System.out.println(answer);
    }
}
