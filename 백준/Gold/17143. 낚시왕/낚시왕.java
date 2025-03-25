import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int R, C; // 격자 크기
    static int M;    // 상어 수
    static Shark[] sharks;
    static int[] drs = {-1, 1, 0, 0}; // 위 아래 오 왼
    static int[] dcs = {0, 0, 1, -1};
    static int king = -1;
    static List<Integer>[][] map;
    static int answer = 0;

    static class Shark {
        int r, c;    // 위치
        int s, d, z; // 속력, 방향, 크기
        boolean dead = false;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        M = Integer.parseInt(split[2]);

        map = new ArrayList[R][C];
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        sharks = new Shark[M];
        for (int i=0; i<M; i++) {
            split = br.readLine().split(" ");
            int r = Integer.parseInt(split[0]) - 1;
            int c = Integer.parseInt(split[1]) - 1;
            int s = Integer.parseInt(split[2]);
            int d = Integer.parseInt(split[3]) - 1;
            int z = Integer.parseInt(split[4]);
            map[r][c].add(i);
            sharks[i] = new Shark(r, c, s, d, z);
        }

//        printMap();
        while (king < C - 1) {
            // 낚시왕 오른쪽으로 한 칸
            king++;
            // 땅과 제일 가까운 상어 catch
            catchShark();
            // 상어 이동
            moveShark();
            // 상어 잡아 먹기
            eat();
//            printMap();
        }

        System.out.println(answer);
    }

    private static void printMap() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void catchShark() {
        for (int i=0; i<R; i++) {
            if (!map[i][king].isEmpty()) {
                int idx = map[i][king].get(0);
                answer += sharks[idx].z;
                map[i][king].clear();
                sharks[idx].dead = true;
//                System.out.println(idx + "번째 상어 잡음");
                return;
            }
        }
    }

    private static void moveShark() {
        List<Integer>[][] tempMap = new ArrayList[R][C];
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                tempMap[i][j] = new ArrayList<>();
            }
        }

        for (int i=0; i<M; i++) {
            if (sharks[i].dead) { // 잡힌 상어인 경우 넘어감
                continue;
            }
            // 상어 움직이기
            moveToNextPos(sharks[i], i, tempMap);
        }
        map = tempMap;
    }

    private static void moveToNextPos(Shark shark, int idx, List[][] tempMap) {
        int r = shark.r;
        int c = shark.c;
        int s = shark.s; // 속력
        int d = shark.d; // 방향
//        System.out.println("현재 칸 상어의 수 : " + map[r][c].size());

        int base;
        if (d == 0 || d == 1) {
            base = R;
        } else {
            base = C;
        }
        int cnt = 0;
        while (cnt < s % (base * 2 - 2)) {
            int nr = r + drs[d];
            int nc = c + dcs[d];
            if (!inRange(nr, nc)) { // 벽에 부딪힌 경우
                d = changeDirection(d); // 방향 전환
                nr = r + drs[d];
                nc = c + dcs[d];
            }

            r = nr;
            c = nc;
            cnt++;
        }

//        System.out.println(idx + "번째 상어가 " + r + ", " + c + "로 이동");
        tempMap[r][c].add(idx);
        shark.r = r;
        shark.c = c;
        shark.d = d; // 바뀐 방향 반영
    }

    private static void eat() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j].size() <= 1) { // 한 칸에 상어가 한 마리 이하 있는 경우
                    continue;
                }
                eatUtil(map[i][j]);
            }
        }
    }

    private static void eatUtil(List<Integer> list) {
        int maxSize = 0;
        for (int idx : list) { // 가장 큰 상어 찾기
            int size = sharks[idx].z;
            maxSize = Math.max(maxSize, size);
        }

        List<Integer> remove = new ArrayList<>();
        for (int idx : list) { // 가장 큰 상어를 제외한 모든 상어 remove 리스트에 추가
            if (sharks[idx].z == maxSize) {
                continue;
            }
            sharks[idx].dead = true;
            remove.add(idx);
        }

        list.removeAll(remove); // 상어 잡아먹기
    }

    private static int changeDirection(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    private static boolean inRange(int r, int c) {
        return 0<=r && r<R && 0 <= c && c<C;
    }
}
