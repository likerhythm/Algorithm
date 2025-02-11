import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// r과 c는 1부터 시작한다.
// 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다.
// 각각의 집은 치킨 거리를 가지고 있다.
// 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
// 임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.
// 치킨집 M개를 어떻게 고르면 도시의 치킨 거리가 가장 작게 될지
public class Main {

    static int N, M;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static int[][] distances;
    static boolean[] picked;
    static int minChickDistance = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        for (int i=0; i<N; i++) {
            split = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                int value = Integer.parseInt(split[j]);
                if (value == 1) {
                    houses.add(new int[] {i, j});
                }
                if (value == 2) {
                    chickens.add(new int[] {i, j});
                }
            }
        }

        picked = new boolean[chickens.size()];
        if (M > chickens.size() / 2) { // 시간 초과 방지
            Arrays.fill(picked, true);
            M = chickens.size() - M;
        }

        distances = new int[houses.size()][chickens.size()];
        for (int h=0; h<houses.size(); h++) { // 집과 치킨집 거리 미리 계산
            int minDist = Integer.MAX_VALUE;
            for (int ch=0; ch<chickens.size(); ch++) {
                int dist = calcDist(houses.get(h), chickens.get(ch));
                distances[h][ch] = Math.min(minDist, dist);
            }
        }

        f(0, 0);

        System.out.println(minChickDistance);
    }

    /**
     *
     * @param from: 이번 과정에서 선택할 수 있는 치킨집의 시작점
     * @param cnt: 이때까지 선택한 치킨집의 수
     */
    private static void f(int from, int cnt) {
        if (cnt == M) {
            // 치킨 거리 계산
            int chickDistance = calcChickDistance();
            // 최소 치킨 거리 업데이트
            minChickDistance = Math.min(minChickDistance, chickDistance);
            return;
        }
        for (int i=from; i<chickens.size(); i++) { // 치킨집 선택
            picked[i] = !picked[i];
//            System.out.println(Arrays.toString(picked));
            f(i + 1, cnt + 1);
            picked[i] = !picked[i];
        }
    }

    private static int calcChickDistance() {
//        if (allFalse(picked)) {
//            return Integer.MAX_VALUE;
//        }
        int totalDist = 0;
        for (int h=0; h<houses.size(); h++) {
            int minDist = Integer.MAX_VALUE;
            for (int ch=0; ch<chickens.size(); ch++) {
                if (!picked[ch]) {
                    continue;
                }
                int dist = distances[h][ch];
                minDist = Math.min(minDist, dist);
            }
//            System.out.println("totalDist, minDist: " + totalDist + ", " + minDist);
            totalDist += minDist;
        }
        return totalDist;
    }

    private static boolean allFalse(boolean[] picked) {
        for (boolean flag : picked) {
            if (flag) {
                return false;
            }
        }
        return true;
    }

    private static int calcDist(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
}
