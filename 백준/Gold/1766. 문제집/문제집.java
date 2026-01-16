import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N; // 문제의 수
    static int M; // 문제에 대한 정보
    static int[] indegree;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            indegree[b]++;
            graph[a].add(b);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] > 0) continue;
            pq.add(i);
        }

        while (!pq.isEmpty()) {
            int poll = pq.poll();
            System.out.print(poll + " ");
            for (int next : graph[poll]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
    }
}
