import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 회의실 배정, 제출 ~2시
// 시작 시간으로 정렬하지 않을 때 반례
// 3
// 2 2
// 1 2
// 2 3
public class Main {

    static int N;
    static int[][] meetings;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        meetings = new int[N][2];

        for (int i=0; i<N; i++) {
            String[] split = br.readLine().split(" ");
            meetings[i][0] = Integer.parseInt(split[0]);
            meetings[i][1] = Integer.parseInt(split[1]);
        }

        run();
    }

    // 1. 회의가 빠르게 끝나야 다음 회의를 진행할 수 있으므로 회의가 끝나는 시간 순으로 정렬
    // 2. 다음에 진행 가능한 회의 중 가장 빠른 회의를 찾기 위해 회의가 시작하는 시간 순으로 정렬
    private static void run() {
        // [회의 정렬]
        // 회의가 시작하는 시간이 빠른 순서대로 meetings 요소 정렬
        // 회의가 끝나는 시간이 빠른 순서대로 meetings 요소 정렬
        // => 끝나는 시간이 빠를 수록, 끝나는 시간이 같다면 시작 시간이 빠를 수록 앞에 위치
        sortMeetings();

        // [그리디]
        // 1. 첫 회의 선택
        // 2. 이전 회의가 끝나는 시간 이후에 가장 빠르게 시작하는 회의 선택
        // 3. 더 이상 회의를 선택할 수 없을 때까지 과정 2 반복
        int answer = greedy();
        System.out.println(answer);
    }

    private static void sortMeetings() {
        // 회의가 시작하는 시간이 빠른 순서대로 meetings 요소 정렬
        Arrays.sort(meetings, Comparator.comparingInt((int[] m) -> m[0]));
        // 회의가 끝나는 시간이 빠른 순서대로 meetings 요소 정렬
        Arrays.sort(meetings, Comparator.comparingInt((int[] m) -> m[1]));
    }

    private static int greedy() {
        // 1. 첫 회의 선택
        int count = 1;
        int finishedTime = meetings[0][1];
        for (int i=1; i<N; i++) {
            int nextMeetingStartTime = meetings[i][0];
            if (finishedTime <= nextMeetingStartTime) {
                // 2. 이전 회의가 끝나는 시간 이후에 가장 빠르게 시작하는 회의 선택
                finishedTime = meetings[i][1];
                count++;
                // System.out.println(meetings[i][0] + " " + meetings[i][1]);
            }
        }
        return count;
    }
}
