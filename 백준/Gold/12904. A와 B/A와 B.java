import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        StringBuilder sb = new StringBuilder(s2);

        int answer = 1;
        while (true) {
            if (s1.length() > sb.length()) {
                answer = 0;
                break;
            }

            if (s1.contentEquals(sb)) {
                break;
            }

            if (sb.charAt(sb.length() - 1) == 'A') {
                sb.replace(sb.length() - 1, sb.length(), "");
            } else {
                sb.replace(sb.length() - 1, sb.length(), "");
                sb.reverse();
            }
        }

        System.out.println(answer);
    }
}
