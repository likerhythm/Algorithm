import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] queenX; // queenX[y] : y행에 위치한 퀸의 행 좌표
    static int answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queenX = new int[N];
        Arrays.fill(queenX, -1);
        f(0);

        System.out.println(answer);
    }

    static void f(int x) {
        if (x == N) {
            answer++;
            return;
        }

        for (int y=0; y<N; y++) {
            if (queenRange(x, y)) { // 다른 퀸에게 영향을 받는 경우
                continue;
            }

            queenX[y] = x;
            f(x + 1);
            queenX[y] = -1;
        }
    }

    static boolean queenRange(int x, int y) {
        if (queenX[y] >= 0) { // y열에 퀸이 있는 경우
            return true;
        }

        int rate = 1;
        boolean found = false;
        while (true) {
            if (findQueen(x, y, rate++)) {
                found = true;
                break;
            }

            if (rate == N) {
                break;
            }
        }

        return found;
    }

    static boolean findQueen(int x, int y, int rate) {
        boolean found = false;
        if (inRange(x - rate, y - rate) && queenX[y - rate] == x - rate) { // 왼쪽 위 칸이 범위 안에 있고 퀸이 있는 경우
            return true;
        }

        if (inRange(x - rate, y + rate) && queenX[y + rate] == x - rate) { // 오른쪽 위 대각선
            return true;
        }

        if (inRange(x + rate, y - rate) && queenX[y - rate] == x + rate) {
            return true;
        }

        if (inRange(x + rate , y + rate) && queenX[y + rate] == x + rate) {
            return true;
        }

        return false;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}