import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문자열의 뒤에 A를 추가한다.
// 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
public class Main {

    static String S;
    static String T;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        StringBuilder sb = new StringBuilder(T);

        f(sb);

        if (found) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
    }

    private static void f(StringBuilder sb) {
        if (found) {
            return;
        }

        if (sb.toString().equals(S)) {
            found = true;
            return;
        }

        if (sb.toString().length() == S.length()) {
            return;
        }

        if (sb.charAt(0) == 'A' && sb.charAt(sb.length() - 1) == 'A') {
            deleteA(sb);
            return;
        }

        if (sb.charAt(0) == 'B' && sb.charAt(sb.length() - 1) == 'B') {
            deleteB(sb);
            return;
        }

        if (sb.charAt(sb.length() - 1) == 'A') {
            deleteA(sb);
        }

        if (sb.charAt(0) == 'B') {
            deleteB(sb);
        }
    }

    private static void deleteA(StringBuilder sb) {
        sb.deleteCharAt(sb.length() - 1);
        f(sb);
        sb.append("A");
    }

    private static void deleteB(StringBuilder sb) {
        sb.reverse().deleteCharAt(sb.length() - 1);
        f(sb);
        sb.append("B").reverse();
    }
}
