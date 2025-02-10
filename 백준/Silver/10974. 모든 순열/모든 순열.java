import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int N =Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        List<Integer> result = new LinkedList<>();
        f(N, result);
    }

    private static void f(int N, List<Integer> result) {
        if (result.size() == N) {
            for (int r : result) {
                System.out.print(r + " ");
            }
            System.out.println();
            return;
        }

        for (int i=1; i<=N; i++) {
            if (result.contains(i)) {
                continue;
            }
            result.add(i);
            f(N, result);
            result.remove(result.size() - 1);
        }
    }
}
