import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N, M;
    static int G, R;
    static int[][] ground; // 0: 호수, 1: 배양액x 땅, 2: 배양액o 땅
    static List<int[]> canSeed = new ArrayList<>();
    static int answer = 0;
    static char NON_SEED = ' ';
    static char FLOWER = 'f';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        M = split[1];
        G = split[2];
        R = split[3];

        ground = new int[N][M];
        for (int i = 0; i < N; i++) {
            ground[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                if (ground[i][j] == 2) {
                    canSeed.add(new int[] {i, j});
                }
            }
        }

        run();

        System.out.println(answer);
    }

    private static void run() {
        // R + G개 선택
        // 그 중에서 R개를 선택, 나머지는 G에게 할당
        int[][] grPos = new int[G + R][2];
        getGRSeed(grPos, 0, 0);
    }

    private static void getGRSeed(int[][] grPos, int cnt, int start) {
        if (cnt == G + R) {
            char[][] seedMap = new char[N][M]; // g: 초록 배양액, r: 빨간 배양액
            for (int j = 0; j < N; j++) {
                Arrays.fill(seedMap[j], NON_SEED);
            }
            setGRSeed(grPos, 0, 0, seedMap);
            return;
        }
        for (int i = start; i < canSeed.size(); i++) {
            grPos[cnt] = canSeed.get(i);
            getGRSeed(grPos, cnt + 1, i + 1);
        }
    }

    private static void setGRSeed(int[][] grPos, int cnt, int start, char[][] seedMap) {
        if (cnt == G) { // 초록 배양액을 모두 뿌린 경우
            for (int[] pos : grPos) { // 빨간 배양액 뿌리기
                int n = pos[0];
                int m = pos[1];
                if (seedMap[n][m] != 'g') {
                    seedMap[n][m] = 'r';
                }
            }
//            System.out.println("초기 상태");
//            printMap(seedMap);
            char[][] tempSeedMap = new char[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tempSeedMap[i][j] = seedMap[i][j];
                }
            }
            int flowerCnt = countFlower(tempSeedMap);
            answer = Math.max(answer, flowerCnt);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (seedMap[i][j] == 'r') {
                        seedMap[i][j] = NON_SEED;
                    }
                }
            }

            return;
        }

        for (int i = start; i < grPos.length; i++) {
            int[] pos = grPos[i];
            int n = pos[0];
            int m = pos[1];
            seedMap[n][m] = 'g';
            setGRSeed(grPos, cnt + 1, i + 1, seedMap);
            seedMap[n][m] = NON_SEED;
        }
    }

    private static int countFlower(char[][] seedMap) {
        Queue<int[]> q = new LinkedList<>();
        int[][] timeMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (seedMap[i][j] != NON_SEED) {
                    q.add(new int[] {i, j});
                }
            }
        }
        int[] dns = {-1, 0, 1, 0};
        int[] dms = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int n = poll[0];
            int m = poll[1];

            for (int i = 0; i < 4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) {
                    continue;
                }

                if (isLake(nn, nm)) {
                    continue;
                }

                if (seedMap[nn][nm] != NON_SEED) { // 다음 장소에 이미 다른 씨앗이나 꽃이 있는 경우
                    continue;
                }

                boolean genFlower = false;
                for (int j = 0; j < 4; j++) {
                    int nnn = nn + dns[j];
                    int nnm = nm + dms[j];

                    if (!inRange(nnn, nnm)) {
                        continue;
                    }

                    if (isLake(nnn, nnm)) {
                        continue;
                    }

                    if (seedMap[nnn][nnm] != NON_SEED
                            && seedMap[n][m] != seedMap[nnn][nnm]
                            && seedMap[nnn][nnm] != FLOWER
                            && timeMap[nnn][nnm] == timeMap[n][m]) { // 다다음 공간에 현재 위치와 다른 씨앗이 존재하는 경우
                        genFlower = true;
                        break;
                    }
                }

                if (genFlower) {
                    seedMap[nn][nm] = FLOWER;
                } else {
                    seedMap[nn][nm] = seedMap[n][m];
                    timeMap[nn][nm] = timeMap[n][m] + 1;
                    q.add(new int[] {nn, nm});
                }
            }
        }

//        System.out.println("완료 상태");
//        printMap(seedMap);

        int flowerCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (seedMap[i][j] == FLOWER) {
                    flowerCnt++;
                }
            }
        }

        return flowerCnt;
    }

    private static boolean isLake(int n, int m) {
        return ground[n][m] == 0;
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    private static void printMap(char[][] seedMap) {
        for (char[] chars : seedMap) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println();
    }
}
