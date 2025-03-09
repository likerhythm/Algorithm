import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N; // 최대 인식 개수
    static String str; // 문자열
    static int[] countChar = new int[26];
    static int answer = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        // 입력==================================

        int left = 0, right = 0;
        int answerL = 0, answerR = 0;
        while (true) {
            if (countChar[getCharIdx(right)]++ == 0) {
                count++;
            }

            if (count > N) {
                while (true) {
                    if (--countChar[getCharIdx(left)] == 0) {
                        left++;
                        break;
                    }
                    left++;
                }
                count--;
            }

            if (answer < right - left + 1) {
                answerL = left;
                answerR = right;
                answer = right - left + 1;
            }
            answer = Math.max(answer, right - left + 1);

            if (right == str.length() - 1) {
                break;
            }

            right++;
        }

//        System.out.println("answerL=" + answerL);
//        System.out.println("answerR=" + answerR);
        System.out.println(answer);
    }

    private static int getCharIdx(int right) {
        return str.charAt(right) - 'a';
    }
}
