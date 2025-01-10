import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int M;
    static int[][] room;
    static int robotN;
    static int robotM;
    static int robotDirection; // 0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static final int CLEANED = 2;

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        setNM();
        setRobot();
        setRoom();

//        1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
//        2. 현재 칸의 주변 4칸 모두 청소된 경우,
//        1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
//        2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
//        3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
//        1. 반시계 방향으로 90도 회전한다.
//        2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
//        3. 1번으로 돌아간다.

        int answer = 0;
        while (true) {
            if (room[robotN][robotM] == 0) {
                room[robotN][robotM] = CLEANED;
                answer += 1;
                continue;
            }
            if (isClearAround()) {
                int backDirection;
                if (robotDirection > 1) {
                    backDirection = robotDirection - 2;
                } else {
                    backDirection = robotDirection + 2;
                }
                int nn =  robotN + dns[backDirection];
                int nm = robotM + dms[backDirection];
                if (room[nn][nm] == 1) { // 후진할 칸이 벽인 경우 종료
                    break;
                }
                robotN = nn;
                robotM = nm;
            } else {
                if (robotDirection == 0) {
                    robotDirection = 3;
                } else {
                    robotDirection -= 1;
                }
                int nn =  robotN + dns[robotDirection];
                int nm = robotM + dms[robotDirection];
                if (room[nn][nm] == 0) {
                    robotN = nn;
                    robotM = nm;
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isClearAround() {
        int countClear = 0;
        for (int i=0; i<4; i++) {
            int nn = robotN + dns[i];
            int nm = robotM + dms[i];
            if (room[nn][nm] == 2 || room[nn][nm] == 1) {
                countClear += 1;
            }
        }
        return countClear == 4;
    }

    private static void setRoom() throws IOException {
        room = new int[N][M];
        for (int i=0; i<N; i++) {
            String[] split = bf.readLine().split(" ");
            for (int j=0; j<M; j++) {
                room[i][j] = Integer.parseInt(split[j]);
            }
        }
    }

    private static void setNM() throws IOException {
        String[] split = bf.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
    }

    private static void setRobot() throws IOException {
        String[] split;
        split = bf.readLine().split(" ");
        robotN = Integer.parseInt(split[0]);
        robotM = Integer.parseInt(split[1]);
        robotDirection = Integer.parseInt(split[2]);
    }
}
