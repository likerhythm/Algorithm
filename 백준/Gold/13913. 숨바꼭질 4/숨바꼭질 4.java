import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N, K;
    static int[] arr;
    static int[] footprint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = split[0];
        K = split[1];
        int arrSize = Math.max(N, K);
        
        arr = new int[2 * arrSize + 1];
        footprint = new int[2 * arrSize + 1];
        Arrays.fill(arr, -1);
        Arrays.fill(footprint, -1);
        arr[N] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        while (!q.isEmpty()) {
            int poll = q.poll();
            if (poll == K) break;
            if (poll - 1 >= 0) {
                if (arr[poll - 1] > arr[poll] + 1 || arr[poll - 1] == -1) {
                    arr[poll - 1] = arr[poll] + 1;
                    footprint[poll - 1] = poll;
                    q.add(poll - 1);
                }
            }
            if (poll + 1 <= 2 * arrSize) {
                if (arr[poll + 1] > arr[poll] + 1 || arr[poll + 1] == -1) {
                    arr[poll + 1] = arr[poll] + 1;
                    footprint[poll + 1] = poll;
                    q.add(poll + 1);
                }
            }
            if (poll * 2 <= 2 * arrSize) {
                if (arr[poll * 2] > arr[poll] + 1 || arr[poll * 2] == -1) {
                    arr[poll * 2] = arr[poll] + 1;
                    footprint[poll * 2] = poll;
                    q.add(poll * 2);
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        int idx = K;
        while (idx != -1) {
            answer.add(idx);
            idx = footprint[idx];
        }
        System.out.println(arr[K]);
        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }
}
