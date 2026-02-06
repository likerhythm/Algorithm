import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L; // 암호를 구성하는 알파벳의 수(중복 없음)
    static int C; // 암호로 사용할 수 있는 알파벳의 수
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = br.readLine().replace(" ", "").toCharArray();
        Arrays.sort(arr);

        backtracking(0, 0, new StringBuilder(), 0);
    }

    // type1: 모음의 개수
    // type2: 자음의 개수
    private static void backtracking(int type1, int type2, StringBuilder sb, int start) {
        if (type1 + type2 == L) {
            if (type1 >= 1 && type2 >= 2) System.out.println(sb);
            return;
        }

        for (int i = start; i < C; i++) {
            char c = arr[i];
            StringBuilder newSb = new StringBuilder(sb);
            if (isType1(c)) {
                backtracking(type1 + 1, type2, newSb.append(c), i + 1);
            } else {
                backtracking(type1, type2 + 1, newSb.append(c), i + 1);
            }
        }
    }

    private static boolean isType1(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
