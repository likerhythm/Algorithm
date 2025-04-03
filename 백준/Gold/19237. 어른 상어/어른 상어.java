import java.util.*;
import java.io.*;

public class Main {

    static int N; // 격자 크기
    static int M; // 상어의 수
    static int k; // 냄새가 남아있는 시간
    static int answer;
    static Shark[] sharks;
    static Queue<Smell> smells;
    static int[][] sharkMap;
    static int[][] tempSharkMap;
    static int[][] smellMap;
    static int[][] smellCount;
    static int[] dxs = {-1, 1, 0, 0};
    static int[] dys = {0, 0, -1, 1};
    static int nowTime;

    static class Shark {

        int x, y;
        int direction; // 0 ~ 3의 값
        boolean isDead;
        int[][] priority;

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Smell {

        int x, y;
        int time;

        Smell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        k = Integer.parseInt(split[2]);

        sharkMap = new int[N][N];
        smellMap = new int[N][N];
        smellCount = new int[N][N];
        sharks = new Shark[M + 1];
        smells = new LinkedList<>();

        for (int i=0; i<N; i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                if (!split[j].equals("0")) { // 상어가 입력된 경우
                    int sharkId = Integer.parseInt(split[j]);

                    sharks[sharkId] = new Shark(i, j);
                    sharkMap[i][j] = sharkId;

                    Smell smell = new Smell(i, j);
                    smell.time = k;
                    smells.add(smell);
                    smellMap[i][j] = sharkId;
                    smellCount[i][j] = 1;
                }
            }
        }

        String[] directions = br.readLine().split(" ");
        for (int i=1; i<=M; i++) {
            sharks[i].direction = Integer.parseInt(directions[i - 1]) - 1;
        }

        for (int i=1; i<=M; i++) {
            int[][] priority = new int[4][];
            for (int j=0; j<4; j++) {
                priority[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(p -> Integer.parseInt(p) - 1).toArray();
            }
            sharks[i].priority = priority;
        }

        // 입력 끝

        simulation();

        System.out.println(answer);
    }

    static void simulation() {
        while (!isDone()) {
            if (nowTime >= 1000) {
                answer = -1;
                return;
            }
            nowTime++;
//            printTogether();
            // 상어 움직이기
            moveShark();
            // 냄새 추가하기
            addSmell();
            // 냄새 지우기
            pollSmell();
        }
        answer = nowTime;
    }

    static void moveShark() {
        tempSharkMap = new int[N][N];
        for (int i=1; i<=M; i++) {
            Shark shark = sharks[i];
            if (shark.isDead) { // 상어가 격자 밖으로 쫓겨난 경우 넘김
                continue;
            }
            int x = shark.x;
            int y = shark.y;
            int[] priority = shark.priority[shark.direction];
            boolean moved = false;
            for (int p : priority) {
                int nx = x + dxs[p];
                int ny = y + dys[p];
                if (!inRange(nx, ny)) { // 범위를 벗어나는 경우
                    continue;
                }

                if (smellMap[nx][ny] == 0) { // 냄새가 없는 칸인 경우 이동
                    sharks[i].direction = p;
                    sharkMap[x][y] = 0;
                    moveSharkUtil(nx, ny, i);
                    moved = true;
                    break;
                }
            }
            if (moved) { // 냄새가 없는 칸으로 이동한 경우 다음 상어로 넘어감
                continue;
            }
            // 그렇지 않은 경우 자신의 냄새가 있는 칸으로 이동
            for (int p : priority) {
                int nx = shark.x + dxs[p];
                int ny = shark.y + dys[p];
                if (!inRange(nx, ny)) { // 범위를 벗어나는 경우
                    continue;
                }

                if (smellMap[nx][ny] == i) { // 자신의 냄새가 있는 칸인 경우
                    sharks[i].direction = p;
                    sharkMap[x][y] = 0;
                    moveSharkUtil(nx, ny, i);
                    break;
                }
            }
        }
    }

    static void moveSharkUtil(int x, int y, int sharkId) {
        if (sharkMap[x][y] > 0) { // 다른 상어가 이미 있는 경우
            int fightSharkId = sharkMap[x][y];
            if (fightSharkId > sharkId) { // 이동한 상어가 이기는 경우
                sharkMap[x][y] = sharkId;
                sharks[sharkId].x = x;
                sharks[sharkId].y = y;
                sharks[fightSharkId].isDead = true;
                smellCount[x][y]++;
            } else {                      // 이동한 상어가 지는 경우
                sharks[sharkId].isDead = true;
            }
        } else {
            sharkMap[x][y] = sharkId;
            sharks[sharkId].x = x;
            sharks[sharkId].y = y;
            smellCount[x][y]++;
        }
    }

    static void addSmell() {
        for (int i=1; i<=M; i++) {
            Shark shark = sharks[i];
            if (shark.isDead) {
                continue;
            }
            smellMap[shark.x][shark.y] = i;
            Smell newSmell = new Smell(shark.x, shark.y);
            newSmell.time = nowTime + k;
            smells.add(newSmell);
        }
    }

    static void pollSmell() {
        while (smells.peek().time == nowTime) {
            Smell poll = smells.poll();
            int x = poll.x;
            int y = poll.y;
            if (smellCount[x][y] == 1) {
                smellMap[x][y] = 0;
            }
            smellCount[x][y]--;
        }
    }

    static boolean isDone() {
        for (int i=2; i<=M; i++) {
            if (!sharks[i].isDead) {
                return false;
            }
        }

        return true;
    }

    static void printMap(int[][] map) {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static void printTogether() {
        for (int i=0; i<N; i++) {
            System.out.print(Arrays.toString(sharkMap[i]) + "   ");
            System.out.print(Arrays.toString(smellMap[i]) + "   ");
            System.out.println(Arrays.toString(smellCount[i]));
        }
        System.out.println();
    }

    static boolean inRange(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}
