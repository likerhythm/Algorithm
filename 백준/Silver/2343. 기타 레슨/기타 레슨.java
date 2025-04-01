import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// M개의 블루레이에
// N개의 강의를 녹화한다
// N개의 강의는 각각 다른 강의 길이를 가진다.
// M개의 블루레이는 모두 같은 용량을 가진다.
// 이때 가능한 블루레이 크기 중 최소값은?
// 단, 녹화를 할 때 연속된 강의는 하나의 블루레이에 녹화되어야 한다.
public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]); // 블루레이 개수

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 블루레이는 최소 강의 하나를 녹화할 수 있는 크기여야 하고
        // 최대 모든 강의를 녹화할 수 있는 크기여야 한다.
        int left = Arrays.stream(arr).max().getAsInt();
        int right = Arrays.stream(arr).sum();

        int mid = -1;
        while (left <= right) {
            mid = (left + right) / 2; // 블루레이 크기
            int blueLayCount = calcBlueLayCount(mid); // 블루레이 크기를 mid로 했을 때 필요한 블루레이의개수
            if (blueLayCount > M) { // 계산한 블루레이의 개수가 M보다 큰 경우 -> 블루레이 용량을 늘려야 함
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    private static int calcBlueLayCount(int volume) {
        int cnt = 1;
        int tmpSum = 0;
        for (int i=0; i<arr.length; i++) {
            int l = arr[i];
            tmpSum += l;
            if (tmpSum > volume) {
                cnt++;
                tmpSum = l;
            }
        }
        return cnt;
    }
}
