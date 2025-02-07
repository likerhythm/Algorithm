import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N, M;
    static int minCount = Integer.MAX_VALUE;
    static List<Node> cctvs = new ArrayList<>(); // cctv 저장
    static Node[][] map;
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class Node {
        int n, m;
        int direction;
        int kind;

        public Node (int n, int m, int kind) {
            this.n = n;
            this.m = m;
            this.kind = kind;
        }

        private void inject(int[][] scopes, int change) {
            if (kind == 1) {
                injectUtil1(scopes, change);
            } else if (kind == 2) {
                injectUtil2(scopes, change);
            } else if (kind == 3) {
                injectUtil3(scopes, change);
            } else if (kind == 4) {
                injectUtil4(scopes, change);
            } else {
                injectUtil5(scopes, change);
            }
        }

        private void injectUtil1(int[][] scopes, int change) {
            int nn = n + dns[direction];
            int nm = m + dms[direction];
            while (inRange(nn, nm)) {
                if (map[nn][nm].isWall()) {
                    break;
                }
                scopes[nn][nm] += change;
                nn += dns[direction];
                nm += dms[direction];
            }
        }

        private void injectUtil2(int[][] scopes, int change) {
            rotateDirLeft90();
            injectUtil1(scopes, change);
            rotateDir180();
            injectUtil1(scopes, change);
            rotateDirLeft90();
        }

        private void injectUtil3(int[][] scopes, int change) {
            injectUtil1(scopes, change);
            rotateDirRight90();
            injectUtil1(scopes, change);
            rotateDirLeft90();
        }

        private void injectUtil4(int[][] scopes, int change) {
            injectUtil1(scopes, change);
            injectUtil2(scopes, change);
        }

        private void injectUtil5(int[][] scopes, int change) {
            injectUtil4(scopes, change);
            rotateDir180();
            injectUtil1(scopes, change);
            rotateDir180();
        }

        private void rotateDirRight90() {
            direction = (direction + 1) % 4;
        }

        private void rotateDir180() {
            direction = (direction + 2) % 4;
        }

        private void rotateDirLeft90() {
            direction -= 1;
            if (direction < 0) {
                direction = 3;
            }
        }

        private boolean isWall() {
            return kind == 6;
        }

        public void setDirection(int dir) {
            direction = dir;
        }

        public boolean isSpace() {
            return kind == 0;
        }

        public void injectScope(int[][] scopes) {
            inject(scopes, 1);
        }

        public void removeScope(int[][] scopes) {
            inject(scopes, -1);
        }
    }

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        map = new Node[N][M];

        for (int i=0; i<N; i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                int kind = Integer.parseInt(split[j]);
                Node node = new Node(i, j, kind);
                if (0 < kind && kind <= 5) {
                    cctvs.add(node);
                }
                map[i][j] = node;
            }
        }

        int[][] scopes = new int[N][M];
        f(0, scopes);
        System.out.println(minCount);
    }

    private static void f(int cctvIdx, int[][] scopes) {
        if (cctvIdx == cctvs.size()) {
            updateCount(scopes);
//            System.out.println("중간 minCount 값=" + minCount);
//            printScopes(scopes);
            return;
        }
        for (int i=0; i<4; i++) {
            Node cctv = cctvs.get(cctvIdx);
            cctv.setDirection(i);
            cctv.injectScope(scopes);
            f(cctvIdx + 1, scopes);
            cctv.removeScope(scopes);
        }
    }

    private static void printScopes(int[][] scopes) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(scopes[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void updateCount(int[][] scopes) {
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (scopes[i][j] == 0 && map[i][j].isSpace()) {
                    cnt++;
                }
            }
        }
        minCount = Math.min(cnt, minCount);
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}
