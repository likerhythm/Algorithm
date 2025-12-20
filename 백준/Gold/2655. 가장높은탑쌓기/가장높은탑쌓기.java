import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dp; // dp[i]: i번째 벽돌을 가장 아래에 두었을 때 최대 높이
    static int answer;
    static List<Integer> answerList;

    static class Block {
        int num;
        int square, height, weight;

        Block(int num, int square, int height, int weight) {
            this.num = num;
            this.square = square;
            this.height = height;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Block[] blocks = new Block[N + 1];
        dp = new int[N + 1];

        blocks[0] = new Block(0, 0, 0, 0);
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Block block = new Block(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            blocks[i] = block;
        }

        Arrays.sort(blocks, Comparator.comparingInt(b -> b.square));

        int maxHeight = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (blocks[j].weight < blocks[i].weight) {
                    dp[i] = Math.max(dp[i], dp[j] + blocks[i].height);
                }
            }
            maxHeight = Math.max(maxHeight, dp[i]);
        }

        Stack<Integer> answer = new Stack<>();
        while (N > 0) {
            if (maxHeight == dp[N]) {
                answer.push(blocks[N].num);
                maxHeight -= blocks[N].height;
            }
            N--;
        }

        System.out.println(answer.size());
        while (!answer.isEmpty()) {
            System.out.println(answer.pop());
        }
    }
}
