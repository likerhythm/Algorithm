import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static long N, P, Q;
    static Map<Long, Long> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        dp = new HashMap<>();
        dp.put(0L, 1L);

        setDp(N);

        System.out.println(dp.get(N));
    }

    private static long setDp(long num) {
        if (dp.containsKey(num) && dp.get(num) > 0) {
            return dp.get(num);
        }

        dp.put(num, setDp(num / Q) + setDp(num / P));
        return dp.get(num);
    }
}
