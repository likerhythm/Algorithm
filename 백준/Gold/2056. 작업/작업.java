import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dp;
    static Task[] tasks;

    static class Task {
        int cost;
        int indegree;
        List<Integer> requiredTask = new ArrayList<>();

        public Task(int cost) {
            this.cost = cost;
        }

        public void addRequiredTask(int num) {
            requiredTask.add(num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        tasks = new Task[N + 1];

        for (int num = 1; num <= N; num++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int preTaskCount = Integer.parseInt(st.nextToken());
            Task task = new Task(cost);
            if (preTaskCount == 0) {
                dp[num] = cost;
            }
            for (int i = 0; i < preTaskCount; i++) {
                int pre = Integer.parseInt(st.nextToken());
                task.addRequiredTask(pre);
            }
            tasks[num] = task;
        }

        for (int num = 1; num <= N; num++) {
            Task task = tasks[num];
            List<Integer> requiredTask = task.requiredTask;
            for (int req : requiredTask) {
                tasks[req].indegree++;
            }
        }

        for (int num = 1; num <= N; num++) {
            Task task = tasks[num];
            if (dp[num] != -1) continue;
            if (task.indegree == 0) { // 마지막에 수행되는 작업인 경우
                setDp(num);
            }
        }

        int answer = 0;
        for (int time : dp) {
            answer = Math.max(answer, time);
        }

        System.out.println(answer);
    }

    private static int setDp(int num) {
        if (dp[num] != -1) {
            return dp[num];
        }
        int maxCost = 0;
        for (int pre : tasks[num].requiredTask) {
            maxCost = Math.max(maxCost, setDp(pre) + tasks[num].cost);
        }
        return dp[num] = maxCost;
    }
}
