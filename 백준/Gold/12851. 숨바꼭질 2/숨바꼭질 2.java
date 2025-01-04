import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    static long N;
    static long K;
    static Map<Long, Long> dpMap;
    static long count = 1;
    static Queue<Long> q = new LinkedList<>();

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = bf.readLine().split(" ");
        N = Long.parseLong(split[0]);
        K = Long.parseLong(split[1]);

        dpMap = new LinkedHashMap<>();
        if (N != K) {
            bfs();
        }
        long minTime = dpMap.getOrDefault(K, 0L);
        System.out.println(minTime);
        System.out.println(count);
    }

    private static void bfs() {
        q.add(N);
        dpMap.put(N, 0L);

        while(!q.isEmpty()) {
            long n = q.poll();
            long nn1 = n + 1;
            updateDp(n, nn1);
            long nn2 = n - 1;
            updateDp(n, nn2);
            long nn3 = n * 2;
            updateDp(n, nn3);
        }
    }

    private static void updateDp(long n, long nextN) {
        if (nextN > 200_000 || nextN < 0) {
            return;
        }
        if (dpMap.getOrDefault(nextN, Long.MAX_VALUE) >= dpMap.get(n) + 1) {
            if (dpMap.getOrDefault(nextN, Long.MAX_VALUE) == dpMap.get(n) + 1 && nextN == K) {
                count++;
                return;
            }
            dpMap.put(nextN, dpMap.get(n) + 1);
            q.add(nextN);
            if (nextN == K) {
                count = 1;
            }
        }
    }
}
