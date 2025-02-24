import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int t; // 테스트케이스 수
    static int n; // 편의점의 수
    static Pos[] posList;
    static boolean[] visited;
    static boolean found = false;

    static class Pos {
        int x, y, dest;

        Pos(int x, int y, int dest) {
            this.x = x;
            this.y = y;
            this.dest = dest;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int tc=0; tc<t; tc++) {
            getInput(br);
            visited = new boolean[n + 2];
            found = false;
            run();
        }
    }

    private static void run() {
        bfs(0);
        if (found) {
            System.out.println("happy");
        } else {
            System.out.println("sad");
        }
    }

    private static void bfs(int idx) {
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        visited[idx] = true;

        while (!q.isEmpty()) {
            int poll = q.poll();
            if (poll == n + 1) {
                found = true;
                return;
            }
            int x = posList[poll].x;
            int y = posList[poll].y;

            for (int i=0; i<n+2; i++) {
                int dist = Math.abs(x - posList[i].x) + Math.abs(y - posList[i].y);
                if (dist <= 1000 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    private static void getInput(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        posList = new Pos[n + 2];

        StringTokenizer st;
        for (int i=0; i<n+2; i++) {
            st = new StringTokenizer(br.readLine());
            posList[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.MAX_VALUE);
        }
    }
}
