import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N; // 세로선의 개수
    static int M; // 가로선의 개수
    static int H; // 세로선마다 가로선을 놓을 수 있는 위치의 개수
    static boolean[][] ladder;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        H = Integer.parseInt(split[2]);
        ladder = new boolean[N][H];

        for (int m=0; m<M; m++) {
            split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]) - 1;
            int b = Integer.parseInt(split[1]) - 1;
            ladder[b][a] = true;
        }

        for (int cnt=0; cnt<=3; cnt++) {
            boolean flag = setLadders(0, 0, cnt);
            if (flag) {
                answer = cnt;
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean setLadders(int startNum, int nowCnt, int cnt) {
        if (nowCnt == cnt) {
            return canGo();
        }

        for (int i=startNum; i<(N-1)*H; i++) {
            int startLadder = i / H;
            int h = i % H;
            if (ladder[startLadder][h]) {
                continue;
            }

            ladder[startLadder][h] = true;

            boolean flag = setLadders(i + 1, nowCnt + 1, cnt);
            if (flag) {
                return true;
            }
            ladder[startLadder][h] = false;
        }

        return false;
    }

    private static boolean canGo() {
        for (int start=0; start<N; start++) {
            int arriveNum = rideLadder(start, 0);
            if (start != arriveNum) {
                return false;
            }
        }
        return true;
    }

    private static int rideLadder(int start, int startH) {
        for (int h = startH; h < H; h++) {
            if (ladder[start][h]) { // start 사다리에서 오른쪽으로 갈 수 있는 경우
                return rideLadder(start + 1, h + 1);
            }
            if (start > 0 && ladder[start - 1][h]) { // start 사다리에서 왼쪽으로 갈 수 있는 경우
                return rideLadder(start - 1, h + 1);
            }
        }
        return start;
    }
}
