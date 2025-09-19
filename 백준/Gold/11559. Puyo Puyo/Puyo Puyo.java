import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static char[][] board = new char[12][6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean end = false;
        int answer = 0;
        while (!end) {
            boolean[][] visited = new boolean[12][6];

            char[][] tmp = new char[12][6];
            for (int k = 0; k < 12; k++) {
                for (int l = 0; l < 6; l++) {
                    tmp[k][l] = board[k][l];
                }
            }

            int boomCnt = 0;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == '.') continue;
                    if (visited[i][j]) continue;

                    List<int[]> result = bfs(i, j, visited);
                    if (result.size() < 4) {
                        continue;
                    }

                    for (int[] arr : result) {
                        tmp[arr[0]][arr[1]] = '.';
                    }
                    boomCnt++;
                }
            }

            // 중력 적용하기
            for (int i = 11; i >= 1; i--) {
                for (int j = 0; j < 6; j++) { // 맨 아랫줄 왼쪽부터 탐색
                    if (tmp[i][j] != '.') continue;

                    int top = i;
                    for (int k = i - 1; k >= 0; k--) { // 빈 공간을 발견하면 그 위의 요소들 모두 아래로 내려오게
                        if (tmp[k][j] != '.') {
                            tmp[top][j] = tmp[k][j];
                            tmp[k][j] = '.';
                            top--;
                        }
                    }
                }
            }

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    board[i][j] = tmp[i][j];
                }
            }
//            printArray(board);

            if (boomCnt > 0) {
                end = false;
            } else {
                end = true;
            }
            
            if (!end) { // 연쇄가 일어나는 경우
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void printArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    private static List<int[]> bfs(int i, int j, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        visited[i][j] = true;
        q.add(new int[] {i, j});

        int[] dis = {-1, 0, 1, 0};
        int[] djs = {0, 1, 0, -1};
        List<int[]> result = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            i = poll[0];
            j = poll[1];
            result.add(new int[] {i, j});

            for (int k = 0; k < 4; k++) {
                int ni = i + dis[k];
                int nj = j + djs[k];

                if (!inRange(ni, nj)) continue;
                if (visited[ni][nj]) continue;
                if (board[ni][nj] != board[i][j]) continue;

                q.add(new int[] {ni, nj});
                visited[ni][nj] = true;
            }
        }
        return result;
    }

    private static boolean inRange(int i, int j) {
        return 0 <= i && i < 12 && 0 <= j && j < 6;
    }
}
