import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N; // 아이들의 수
    static int M; // 관계 수
    static int K; // 선택 제한 수
    static int[] candies;
    static List<Integer>[] graph;
    static List<Group> groups;
    static int[] dp;

    static class Group {
        List<Integer> members = new ArrayList<>();

        void addMember(int v) {
            members.add(v);
        }

        int size() {
            return members.size();
        }

        int getAllCandies() {
            int cnt = 0;
            for (int member : members) {
                cnt += candies[member];
            }
            return cnt;
        }
    }

    // 최대 K-1명을 선택했을 때 얻을 수 있는 사탕의 최대 수. 단, 한 명 선택 시 엮여있는 모든 사람이 함께 선택 됨.
    public static void main(String[] args) throws IOException {
        getInput();

        groups = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (visited[i]) continue;
            makeGroup(i, visited);
        }

        dp = new int[K];
        for (Group group : groups) {
            for (int k = K - 1; k >= group.size(); k--) {
                dp[k] = Math.max(dp[k], dp[k - group.size()] + group.getAllCandies());
            }
        }

//        printdp();
        System.out.println(dp[K - 1]);
    }

    private static void printdp() {
        System.out.println(Arrays.toString(dp));
    }

    private static void makeGroup(int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        Group group = new Group();

        while (!q.isEmpty()) {
            int poll = q.poll();
            group.addMember(poll);

            for (int next : graph[poll]) {
                if (visited[next]) continue;
                q.add(next);
                visited[next] = true;
            }
        }
        groups.add(group);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
    }
}
