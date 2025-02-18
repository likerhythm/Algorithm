import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; // board의 크기
    static int K; // 말의 개수
    static boolean done = false;
    static Horse[] horses;
    static int[][] board; // 0-흰, 1-빨, 2-파
    static Horses[][] horseBoard;
    static int[] dxs = {0, 0, 0, -1, 1}; // 오, 왼, 위, 아
    static int[] dys = {0, 1, -1, 0, 0}; // 오, 왼, 위, 아

    static class Horse {
        int x, y;
        int num;
        int direction;

        Horse (int x, int y, int num, int direction) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.direction = direction;
        }

        public int[] calcNextIdx() {
            int nx = x + dxs[direction];
            int ny = y + dys[direction];

            if (!inRange(nx, ny) || board[nx][ny] == 2) { // 파란색이거나 범위를 벗어나는 경우
                reverseDirection(); // 방향 전환
                nx = x + dxs[direction];
                ny = y + dys[direction];
                if ( !inRange(nx, ny) || board[nx][ny] == 2) { // 방향을 전환해도 파란색이거나 범위를 벗어나는 경우
                    return new int[] {x, y};
                }
            }

            return new int[] {nx, ny};
        }

        private boolean inRange(int x, int y) {
            return 0<=x && x<N && 0<=y && y<N;
        }

        private void reverseDirection() {
            if (direction == 1) {
                direction = 2;
            } else if (direction == 2) {
                direction = 1;
            } else if (direction == 3) {
                direction = 4;
            } else {
                direction = 3;
            }
        }
    }

    static class Horses {
        List<Integer> horseNum = new LinkedList<>(); // 데이터 추가, 삭제 과정이 많으므로 LinkedList 사용

        public void add(int horse) {
            horseNum.add(horse);
        }

        public List<Integer> removeUpper(int hNum) {
            int idx = horseNum.indexOf(hNum);
            List<Integer> removed = new LinkedList<>();
            for (int i=idx; i<horseNum.size(); i++) {
                removed.add(horseNum.get(i));
            }
            horseNum.removeAll(removed);
            return removed;
        }

        public void addAll(List<Integer> hNums) {
            Horse mainHorse = horses[hNums.get(0)];
            int nx = mainHorse.x;
            int ny = mainHorse.y;
            if (board[nx][ny] == 1) { // 빨간색인 경우 뒤집기
                Collections.reverse(hNums);
            }
            horseNum.addAll(hNums);
            if (horseNum.size() >= 4) {
                done = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        K = input[1];

        board = new int[N][N];
        horseBoard = new Horses[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                horseBoard[i][j] = new Horses();
            }
        }
        horses = new Horse[K + 1];

        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i=1; i<=K; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = input[0] - 1;
            int y = input[1] - 1;
            int direction = input[2];
            horses[i] = new Horse(x, y, i, direction);
            horseBoard[x][y].add(i);
        }

        int turn = 0;
//        printHorseDirection();
//        printHorses();
//        System.out.println("start!");
        while (!isDone()) {
            turn++;

            if (turn > 1000) {
                break;
            }

            for (int hNum=1; hNum<=K; hNum++) {
                int x = horses[hNum].x;
                int y = horses[hNum].y;
                int[] nextIdx = horses[hNum].calcNextIdx();
                int nextX = nextIdx[0];
                int nextY = nextIdx[1];
                if (horses[hNum].x == nextX && horses[hNum].y == nextY) {
                    continue;
                }
                List<Integer> moveHorseNums = horseBoard[x][y].removeUpper(hNum);
                for (int n : moveHorseNums) {
                    horses[n].x = nextX;
                    horses[n].y = nextY;
                }

                horseBoard[nextX][nextY].addAll(moveHorseNums);
                if (done) {
                    System.out.println(turn);
                    return;
                }
            }

//            System.out.println("turn: " + turn);
//            printHorseDirection();
//            printHorses();
        }

        if (turn > 1000) {
            System.out.println(-1);
            return;
        }
        System.out.println(turn);
    }

    private static void printHorseDirection() {
        for (int i=1; i<=K; i++) {
            System.out.print(i + "번말:" + horses[i].direction + " ");
        }
        System.out.println();
    }

    private static void printHorses() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(board[i][j] + " " + horseBoard[i][j].horseNum + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isDone() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (horseBoard[i][j].horseNum.size() >= 4) {
                    return true;
                }
            }
        }

        return false;
    }
}
