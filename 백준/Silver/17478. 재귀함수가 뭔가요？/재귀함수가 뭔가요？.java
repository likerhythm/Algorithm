import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int recurCnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        recurCnt = Integer.parseInt(br.readLine());

        run();
    }

    private static void run() {
        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        callRecursive(0);
    }

    private static void callRecursive(int cnt) {
        printQuestion(cnt);

        if (cnt == recurCnt) {
            printRealAnswer(cnt);
            return;
        }
        printAnswer(cnt);
        callRecursive(cnt + 1);
        printFooter(cnt);
    }

    private static void printRealAnswer(int cnt) {
        printUnderBar(cnt);
        System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
        printFooter(cnt);
    }

    private static void printFooter(int cnt) {
        printUnderBar(cnt);
        System.out.println("라고 답변하였지. ");
    }

    private static void printQuestion(int cnt) {
        printUnderBar(cnt);
        System.out.println("\"재귀함수가 뭔가요?\"");
    }

    private static void printUnderBar(int cnt) {
        System.out.print("_".repeat(cnt * 4));
    }

    private static void printAnswer(int cnt) {
        printUnderBar(cnt);
        System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");

        printUnderBar(cnt);
        System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");

        printUnderBar(cnt);
        System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
    }
}
