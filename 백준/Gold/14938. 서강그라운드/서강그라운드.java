import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n, m, r;
    static int[] items;
    static int[][] distances;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        r = Integer.parseInt(split[2]);

        items = new int[n + 1];
        distances = new int[n + 1][n + 1];

        for (int i=0; i<n+1; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
            distances[i][i] = 0;
        }

        split = br.readLine().split(" ");
        for (int i=1; i<=n; i++) {
            items[i] = Integer.parseInt(split[i - 1]);
        }

        for (int i=0; i<r; i++) {
            split = br.readLine().split(" ");
            int s = Integer.parseInt(split[0]);
            int e = Integer.parseInt(split[1]);
            int l = Integer.parseInt(split[2]);
            distances[s][e] = l;
            distances[e][s] = l;
        }

        floydWarshall();
//        printDistances();

        int maxSum = countMaxSum();

        System.out.println(maxSum);
    }

    private static void printDistances() {
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                System.out.print(distances[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int countMaxSum() {
        int maxSum = 0;
        for (int s=1; s<=n; s++) {
            int sum = 0;
            for (int e=1; e<=n; e++) {
                if (distances[s][e] > m) {
                    continue;
                }
                sum += items[e];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    private static void floydWarshall() {
        for (int k=1; k<=n; k++) {
            for (int s=1; s<=n; s++) {
                for (int e=1; e<=n; e++) {
                    if (distances[s][k] == Integer.MAX_VALUE || distances[k][e] == Integer.MAX_VALUE) {
                        continue;
                    }
                    distances[s][e] = Math.min(distances[s][e], distances[s][k] + distances[k][e]);
                }
            }
        }
    }
}
