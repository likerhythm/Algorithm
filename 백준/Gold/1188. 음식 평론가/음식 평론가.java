import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int ssgCnt = Integer.parseInt(split[0]);
        int userCnt = Integer.parseInt(split[1]);

        if (ssgCnt % userCnt == 0) { // 소세지가 평론가 배수만큼 있는 경우
            System.out.println(0);
            return;
        }
        if (userCnt % ssgCnt == 0) { // 평론가가 소세지 배수만큼 있는 경우
            System.out.println((userCnt / ssgCnt - 1) * ssgCnt);
            return;
        }

        int gcd = ssgCnt * userCnt;
        for (int i=Math.max(ssgCnt, userCnt); i<=ssgCnt * userCnt; i++) {
            if (i % ssgCnt == 0 && i % userCnt == 0) {
                gcd = i;
                break;
            }
        }

        int x = 0;
        for (int i=gcd; i<ssgCnt*userCnt; i++) {
            if (i % gcd == 0) {
                x++;
            }
        }
        int answer = (userCnt - 1) - x; // x는 ssgCnt * userCnt 보다 작은 최소공배수의 배수의 개수
        System.out.println(answer);
    }
}
