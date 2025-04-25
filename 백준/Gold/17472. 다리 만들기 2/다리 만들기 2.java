import java.util.*;
import java.io.*;

public class Main {

    static int N; // 세로 길이
    static int M; // 가로 길이
    static int[][] map;
    static int[][] graph;
    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};
    static int islandCnt; // 노드의 수
    static PriorityQueue<Edge> edges;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int start, end;
        int length;

        Edge (int s, int e, int l) {
            start = s;
            end = e;
            length = l;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.length, e.length);
        }

        public String toString() {
            return "[" + start + ", " + end + ", " + length + "]";
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 섬 발견하기
        setIsland();
        graph = new int[islandCnt + 1][islandCnt + 1];
        for (int[] g : graph) {
            Arrays.fill(g, 100);
        }

        edges = new PriorityQueue<>();
        // 각 섬에서 만들 수 있는 다리 만들어서 graph에 저장
        for (int i=1; i<=islandCnt; i++) {
            makeBridge(i);
        }

        // mst로 최소 비용 구하기
        int answer = mst();
        for (int i=2; i<islandCnt+1; i++) {
            if (find(i) != find(i - 1)) {
                answer = -1;
            }
        }
        System.out.println(answer);
    }

    static void setIsland() {
        boolean[][] visited = new boolean[N][M];
        int islandNum = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (map[i][j] == 1) {
                    islandNum++;
                    List<int[]> result = bfs(i, j);
                    for (int[] r : result) {
                        map[r[0]][r[1]] = islandNum;
                        visited[r[0]][r[1]] = true;
                    }
                }
            }
        }
        islandCnt = islandNum;
    }

    static List<int[]> bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[] {x, y});
        visited[x][y] = true;

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            x = poll[0];
            y = poll[1];

            for (int i=0; i<4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                if (!inRange(nx, ny)) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
                result.add(new int[] {nx, ny});
            }
        }
        return result;
    }

    static void makeBridge(int islandNum) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == islandNum) {
                    for (int dir=0; dir<4; dir++) {
                        int[] result = findBridge(i + dxs[dir], j + dys[dir], islandNum, dir, 0); // 도착 섬 번호, 다리의 길이 리턴
                        if (result == null) {
                            continue;
                        }
                        int resultNum = result[0];
                        int length = result[1];
                        if (length == 1) { // 다리 길이가 1이면 넘김
                            continue;
                        }
                        // graph[islandNum][resultNum] = Math.min(graph[islandNum][resultNum], length);
                        // graph[resultNum][islandNum] = Math.min(graph[resultNum][islandNum], length);

                        edges.add(new Edge(islandNum, resultNum, length));
                    }
                }
            }
        }
    }

    static int[] findBridge(int x, int y, int islandNum, int dir, int length) { // 도착 섬 번호, 다리의 길이 리턴
        if (!inRange(x, y) || map[x][y] == islandNum) { // 범위를 벗어나거나 같은 섬인 경우
            return null;
        }

        if (map[x][y] > 0 && map[x][y] != islandNum) { // 다른 섬을 만난 경우
            return new int[] {map[x][y], length};
        }

        return findBridge(x + dxs[dir], y + dys[dir], islandNum, dir, length + 1);
    }

    static int mst() {
        parents = new int[islandCnt + 1];
        for (int i=1; i<islandCnt+1; i++){
            parents[i] = i;
        }
        int answer = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.start) == find(edge.end)) {
                continue;
            }

            union(edge.start, edge.end);
            answer += edge.length;
        }

        return answer;
    }

    static int find(int num) {
        if (parents[num] == num) {
            return num;
        }

        return parents[num] = find(parents[num]);
    }

    static void union(int num1, int num2) {
        int p1 = find(num1);
        int p2 = find(num2);

        parents[p1] = Math.min(p1, p2);
        parents[p2] = Math.min(p1, p2);
    }
    
    static void printMap() {
        for (int[] m : map) {
            System.out.println(Arrays.toString(m));
        }
    }

    static void printGraph() {
        for (int[] m : graph) {
            System.out.println(Arrays.toString(m));
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}