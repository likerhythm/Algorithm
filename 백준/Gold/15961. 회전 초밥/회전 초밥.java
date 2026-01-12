import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N; // 접시의 수
    static int D; // 초밥의 가짓수
    static int K; // 연속해서 먹는 접시의 수
    static int C; // 쿠폰 번호
    static int[] eat;
    static int[] plates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        plates = new int[N];
        eat = new int[3001];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            plates[i] = num;
        }

        int answer = 0;
        for (int i = 0; i < K; i++) {
            int num = plates[i];
            if (eat[num] == 0) answer++;
            eat[num]++;
        }

        int start = 0, end = K - 1;
        int beforeTmp = answer;
        if (eat[C] == 0) answer++;

        while (start < N) {
            int tmp = beforeTmp;
            int startNum = plates[start];
            eat[startNum]--;
            if (eat[startNum] == 0) tmp--;
            start++;

            end = (end + 1) % N;
            int endNum = plates[end];
            if (eat[endNum] == 0) tmp++;
            eat[endNum]++;
            beforeTmp = tmp;

            if (eat[C] == 0) tmp++;
            answer = Math.max(answer, tmp);
        }

        System.out.println(answer);
    }
}
