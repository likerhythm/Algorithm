import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N, M;
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static int answer;
    static boolean[] isCycle;
    static boolean[] visited;
    static int[] graph;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        isCycle = new boolean[N * M];
        visited = new boolean[N * M];
        graph = new int[N * M];

        for (int i=0; i<N; i++) { // 입력 map을 바탕으로 그래프 구성
            char[] line = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                char c = line[j];
                if (c == 'U') {
                    graph[i * M + j] = (i - 1) * M + j;
                } else if (c == 'R') {
                    graph[i * M + j] = i * M + j + 1;
                } else if (c == 'D') {
                    graph[i * M + j] = (i + 1) * M + j;
                } else {
                    graph[i * M + j] = i * M + j - 1;
                }
            }
        }

        // printGraph();

        int answer = 0;
        for (int node=0; node<N*M; node++) {
            if (!isCycle[node]) {
                // System.out.println("start node " + node);
                boolean isNew = dfs(node);
                if (isNew) {
                    answer++; 
                }
            }
        }

        System.out.println(answer);
    }

    static boolean dfs(int node) {
        if (visited[node] && !isCycle[node]) { // 이미 방문한 노드인데 사이클을 발견하지 못한 경우
            isCycle[node] = true;
            visited[node] = true;
            return true; // 새로 발견한 사이클
        }

        if (isCycle[node]) {
            isCycle[node] = true;
            visited[node] = true;
            return false; // 이미 발견한 사이클
        }

        visited[node] = true;
        boolean isNew = dfs(graph[node]);
        isCycle[node] = true;

        // System.out.println("node = " + node);z
        // System.out.println(Arrays.toString(isCycle));

        return isNew;
    }

    static void printGraph() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(graph[i * M + j]);
            }
            System.out.println();
        }
    }
}