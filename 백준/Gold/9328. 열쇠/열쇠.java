import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 입구 찾기
// bfs
// 열지 못하는 문의 경우 큐의 맨뒤로 옮기기
// 열 수 있는 문은 '.'으로 바꾸기
public class Main {

    static char[][] map;
    static int H, W;
    static Set<Character> keys = new HashSet<>();
    static int[] dhs = {-1, 0, 1, 0};
    static int[] dws = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc=0; tc<TC; tc++) {
            String[] split = br.readLine().split(" ");

            H = Integer.parseInt(split[0]);
            W = Integer.parseInt(split[1]);

            map = new char[H][W];

            for (int i=0; i<H; i++) {
                map[i] = br.readLine().toCharArray();
            }

            char[] charArray = br.readLine().toCharArray();
            for (char c :charArray) {
                keys.add(c);
            }

            List<int[]> entry = findEntry(); // 입구 찾기

            int answer = bfs(entry);
            System.out.println(answer);
            keys.clear();
        }
    }

    private static List<int[]> findEntry() {
        List<int[]> entry = new ArrayList<>();
        if (map[0][0] != '*') {
            entry.add(new int[] {0, 0});
        }
        if (map[H - 1][0] != '*') {
            entry.add(new int[] {H - 1, 0});
        }
        if (map[0][W - 1] != '*') {
            entry.add(new int[] {0, W - 1});
        }
        if (map[H - 1][W - 1] != '*') {
            entry.add(new int[] {H - 1, W - 1});
        }
        for (int i = 1; i< H-1; i++) {
            if (map[i][0] != '*') {
                entry.add(new int[] {i, 0});
            }

            if (map[i][W - 1] != '*') {
                entry.add(new int[] {i, W - 1});
            }
        }

        for (int j = 1; j< W-1; j++) {
            if (map[0][j] != '*') {
                entry.add(new int[] {0, j});
            }

            if (map[H - 1][j] != '*') {
                entry.add(new int[] {H - 1, j});
            }
        }
        return entry;
    }

    private static int bfs(List<int[]> entry) {
        Queue<int[]> q = new LinkedList<>(entry);
        boolean[][] visited = new boolean[H][W];
        for (int[] e : entry) {
            visited[e[0]][e[1]] = true;
        }
//        System.out.println(Arrays.deepToString(visited));
        int answer = 0;
        int cnt = 0;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int h = poll[0];
            int w = poll[1];
            if (isKey(map[h][w])) {
                keys.add(map[h][w]);
            }
            if (map[h][w] == '$') {
//                System.out.println(h + " " + w);
                answer++;
            }
            cnt++;
            if (cnt > H * W * 100) {
                break;
            }
            char now = map[h][w];
            if (isDoor(now)) {
                if (!keys.contains(Character.toLowerCase(now))) { // 문을 열 수 없는 경우 다시 큐에 삽입
                    q.add(poll);
                    continue;
                }
            }

            for (int i=0; i<4; i++) {
                int nh = h + dhs[i];
                int nw = w + dws[i];

                if (!inRange(nh, nw)) { // 범위를 벗어나는 경우
                    continue;
                }
                if (visited[nh][nw]) { // 이미 방문한 경우
                    continue;
                }
                char next = map[nh][nw];
                if (next == '*') { // 벽인 경우
                    continue;
                }
                if ('a' <= next && next <= 'z') { // 열쇠를 발견한 경우
                    keys.add(next);
//                    System.out.println(keys);
                }
                visited[nh][nw] = true;
                q.add(new int[] {nh, nw});
            }

        }

        return answer;
    }

    private static boolean isKey(char c) {
        return 'a'<= c && c <= 'z';
    }

    private static boolean isDoor(char now) {
        return 'A' <= now && now <= 'Z';
    }

    private static boolean inRange(int h, int w) {
        return 0<=h && h<H && 0<=w && w<W;
    }
}
