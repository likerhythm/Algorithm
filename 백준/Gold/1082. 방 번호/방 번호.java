import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());

        int minCost = 100;
        int minCostNum = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (costs[i] < minCost) {
                minCost = costs[i];
                minCostNum = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        while (minCost <= M) {
            result.add(minCostNum);
            M -= minCost;
        }

        int remainCost = M;
        int start = 0;
        for (int idx = 0; idx < result.size(); idx++) {
            for (int num = N - 1; num >= 0; num--) {
                if (costs[num] <= remainCost + minCost) {
                    result.set(idx, num);
                    remainCost -= (costs[num] - minCost);
                    break;
                }
            }

            if (result.get(start) == 0) {
                remainCost += minCost;
                start++;
            }
        }
        
        if (start == result.size()) {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = start; idx < result.size(); idx++) {
            sb.append(result.get(idx));
        }

        System.out.println(sb.toString());
    }

    private static boolean allZero(List<Integer> result) {
        for (int a : result) {
            if (a != 0) {
                return false;
            }
        }
        return true;
    }
}
