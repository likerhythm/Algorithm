import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; // 학생 수
    static int M; // 비교 횟수
    static int[] count; // 각 학생보다 작은 학생의 수
    static List<List<Integer>> whoIsBiggerThan;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        count = new int[N + 1];
        whoIsBiggerThan = new ArrayList<>();
        for (int i=0; i<N+1; i++) {
            whoIsBiggerThan.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int small = input[0];
            int big = input[1];
            whoIsBiggerThan.get(small).add(big);
            count[big]++;
        }

        run();

        System.out.println(sb);
    }

    private static void run() {
        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<N+1; i++) { // 키가 가장 작은 사람들 집어넣기
            if (count[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int poll = q.poll();
            sb.append(poll).append(" ");
            for (int big : whoIsBiggerThan.get(poll)) {
                count[big]--;
                if (count[big] == 0) {
                    q.add(big);
                }
            }
        }
    }
}
