import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static final int CLASSROOM_SIZE = 5;
    static final int MAX_STUDENT = 7;

    static char[][] classroom;
    static boolean[][] visited;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<int[]> indexes = new ArrayList<>();

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        classroom = new char[CLASSROOM_SIZE][CLASSROOM_SIZE];
        visited = new boolean[CLASSROOM_SIZE][CLASSROOM_SIZE];
        for (int i=0; i<CLASSROOM_SIZE; i++) {
            String input = bf.readLine();
            for (int j=0; j<CLASSROOM_SIZE; j++) {
                classroom[i][j] = input.charAt(j);
            }
        }

        f(0, new int[] {-1, -1, -1, -1, -1, -1, -1}, -1);

        for (int[] numbers : indexes) {
            checkConnect(numbers);
        }

        System.out.println(answer);
    }

    private static void checkConnect(int[] numbers) {
        boolean[][] visited = new boolean[CLASSROOM_SIZE][CLASSROOM_SIZE];
        int[][] tempClassroom = new int[CLASSROOM_SIZE][CLASSROOM_SIZE];
        int[] dis = {-1, 0, 1, 0};
        int[] djs = {0, 1, 0, -1};

        for (int n : numbers) {
            int i = n / 5;
            int j = n % 5;
            tempClassroom[i][j] = 1;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {numbers[0] / 5, numbers[0] % 5});
        visited[numbers[0] / 5][numbers[0] % 5] = true;
        int connCount = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            connCount++;
            int i = poll[0];
            int j = poll[1];

            for (int k=0; k<4; k++) {
                int ni = i + dis[k];
                int nj = j + djs[k];
                if (inRange(ni, nj) && tempClassroom[ni][nj] == 1 && !visited[ni][nj]) {
                    q.add(new int[] {ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }
        if (connCount == 7) {
            answer++;
        }
    }

    private static void f(int count, int[] numbers, int pre) { // 0 ~ 24 숫자 중 무작위 7개를 선택하는 경우 중 S의 개수가 4 이상인 경우
        if (count == 7) {
            int SCount = 0;
            for (int number : numbers) {
                if (classroom[number / CLASSROOM_SIZE][number % CLASSROOM_SIZE] == 'S') {
                    SCount++;
                }
            }
            if (SCount >= 4) {
                indexes.add(numbers.clone());
            }
            return;
        }
        for (int i=pre+1; i<CLASSROOM_SIZE * CLASSROOM_SIZE; i++) {
            numbers[count] = i;
            f(count + 1, numbers, i);
            numbers[count] = -1;
        }
    }

    private static boolean inRange(int i, int j) {
        return 0 <= i && i < CLASSROOM_SIZE && 0 <= j && j < CLASSROOM_SIZE;
    }
}
