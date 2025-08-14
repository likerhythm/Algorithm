import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M, K;
    static int[][] keyboard;
    static int rotIdx;

    static class Sticker {

        int r;
        int c;
        int[][] occupied;

        public Sticker(int r, int c, int[][] occupied) {
            this.r = r;
            this.c = c;
            this.occupied = occupied;
        }

        public boolean canPut(int n, int m) {
            for (int i = n; i < n + r; i++) {
                for (int j = m; j < m + c; j++) {
                    if (!inRange(i, j)) { // 스티커가 범위를 벗어나는 경우
                        return false;
                    }

                    if (occupied[i - n][j - m] == 0) { // 스티커가 점유하지 않는 공간이면 건너뛰기
                        continue;
                    }

                    if (occupied[i - n][j - m] == 1 && keyboard[i][j] == 1) { // 스티커를 붙일 공간에 이미 스티커가 있으면 false
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean inRange(int i, int j) {
            return 0 <= i && 0 <= j && i < keyboard.length && j < keyboard[0].length;
        }

        public void put(int n, int m) {
            for (int i = n; i < n + r; i++) {
                for (int j = m; j < m + c; j++) {
                    if (keyboard[i][j] == 1) {
                        continue;
                    }
                    keyboard[i][j] = occupied[i - n][j - m];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        N = Integer.parseInt(split[0]); // 노트북 세로 길이
        M = Integer.parseInt(split[1]); // 노트북 가로 길이
        K = Integer.parseInt(split[2]); // 스티커 개수

        keyboard = new int[N][M];

        for (int i = 0; i < K; i++) {
            split = br.readLine().split(" ");

            int R = Integer.parseInt(split[0]);
            int C = Integer.parseInt(split[1]);
            int[][] occupied = new int[R][C];

            for (int r = 0; r < R; r++) {
                split = br.readLine().split(" ");
                occupied[r] = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            }
            Sticker sticker = new Sticker(R, C, occupied);

            f(sticker); // 스티커 붙이기
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (keyboard[i][j] == 1) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void f(Sticker sticker) {
        boolean flag = false;
        for (int rot = 0; rot < 4; rot++) {
            rotIdx = rot;
            if (!flag) {
                if (rotIdx == 0) {
                    outer : for (int i = 0; i < keyboard.length; i++) {
                        for (int j = 0; j < keyboard[i].length; j++) {
                            if (sticker.canPut(i, j)) {
//                                System.out.println("스티커 붙이기 가능");
                                sticker.put(i, j);
                                flag = true;
                                break outer;
                            }
                        }
                    }
                }
                if (rotIdx == 1) {
                    outer : for (int j = 0; j < keyboard[0].length; j++) {
                        for (int i = keyboard.length - 1; i >= 0; i--) {
                            if (sticker.canPut(i, j)) {
//                                System.out.println("스티커 붙이기 가능");
                                sticker.put(i, j);
                                flag = true;
                                break outer;
                            }
                        }
                    }
                }
                if (rotIdx == 2) {
                    outer : for (int i = keyboard.length - 1; i >= 0; i--) {
                        for (int j = keyboard[0].length - 1; j >= 0; j--) {
                            if (sticker.canPut(i, j)) {
//                                System.out.println("스티커 붙이기 가능");
                                sticker.put(i, j);
                                flag = true;
                                break outer;
                            }
                        }
                    }
                }
                if (rotIdx == 3) {
                    outer : for (int j = keyboard[0].length - 1; j >= 0; j--) {
                        for (int i = 0; i < keyboard.length; i++) {
                            if (sticker.canPut(i, j)) {
//                                System.out.println("스티커 붙이기 가능");
                                sticker.put(i, j);
                                flag = true;
                                break outer;
                            }
                        }
                    }
                }

//                printKeyboard();
            }
            rotateKeyboard();
        }
    }

    // 키보드를 시계 반대방향으로 90도 회전

    private static void rotateKeyboard() {
        int tmpN = keyboard[0].length;
        int tmpM = keyboard.length;
        int[][] temp = new int[tmpN][tmpM];

        for (int n = 0; n < keyboard.length; n++) {
            for (int m = 0; m < keyboard[0].length; m++) {
                temp[keyboard[0].length - m - 1][n] = keyboard[n][m];
            }
        }

        keyboard = temp;
    }

    private static void printKeyboard() {
        System.out.println();
        for (int i = 0; i < keyboard.length; i++) {
            System.out.println(Arrays.toString(keyboard[i]));
        }
    }
}
