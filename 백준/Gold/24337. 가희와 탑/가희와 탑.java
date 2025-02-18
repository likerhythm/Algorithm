import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N; // 건물의 개수
    static int a; // 왼쪽에서 볼 수 있는 건물의 수
    static int b; // 오른쪽에서 볼 수 있는 건물의 수

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(new BufferedReader(new InputStreamReader(System.in)).readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        N = input[0];
        a = input[1];
        b = input[2];

        int[] buildings = new int[N];

        // 가장 높은 건물의 높이 = max(a, b)
        // a가 가장 높다면 건물 높이는 1, 2, 3, ... , a...로 시작
        // b가 가장 높다면 건물 높이는 ..., b, b-1, ... , 2, 1로 끝
        if (a + b > N + 1) {
            System.out.println(-1);
            return;
        }

        if (a == 1 && b > 1) {
            buildings[0] = b;
            for (int i=1; i<N-b+1; i++) {
                buildings[i] = 1;
            }
            int tmp = b - 1;
            for (int i=N-b+1; i<N; i++) {
                buildings[i] = tmp--;
            }
            printBuilding(buildings);
            return;
        }

        if (a > 1 && b == 1) {
            for (int i=0; i<a; i++) {
                buildings[N - 1- i] = a - i;
            }
            for (int i=0; i<N - a + 1; i++) {
                buildings[i] = 1;
            }
            printBuilding(buildings);
            return;
        }

        if (a >= b) {
            f1(buildings);
        } else {
            f2(buildings);
        }

        printBuilding(buildings);
    }

    private static void printBuilding(int[] buildings) {
        for (int i=0; i<N; i++) {
            System.out.print(buildings[i] + " ");
        }
    }

    private static void f1(int[] buildings) {
        buildings[N - b] = a; // 가장 큰 건물을 뒤로 미룰 수 있는 만큼 미루기
        for (int i=0; i<N - b - a + 1; i++) {
            buildings[i] = 1;
        }

        for (int i = N - b - a + 1; i<N - b; i++) {
            buildings[i] = i - (N - b - a);
        }

        int height = b - 1;
        for (int i = N - b + 1; i<N; i++) {
            buildings[i] = height--;
        }
    }

    private static void f2(int[] buildings) {
        buildings[N - b] = b; // 가장 큰 건물을 뒤로 미룰 수 있는 만큼 미루기
        for (int i=0; i<N - b - a + 1; i++) {
            buildings[i] = 1;
        }

        for (int i = N - b - a + 1; i<N - b; i++) {
            buildings[i] = i - (N - b - a);
        }

        int height = b - 1;
        for (int i = N - b + 1; i<N; i++) {
            buildings[i] = height--;
        }
    }
}
