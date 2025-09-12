import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {

    static char[] input;
    static int[] pairIdx;
    static Set<String> answer = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        pairIdx = new int[input.length];

        Stack<Integer> stack = new Stack<>();
        int pairCnt = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.push(i);
            } else if (input[i] == ')') {
                int other = stack.pop();
                pairIdx[other] = i;
                pairIdx[i] = other;
                pairCnt++;
            }
        }

        for (int i = 1; i <= pairCnt; i++) {
            boolean[] isRemoved = new boolean[input.length];
            removePair(i, 0, 0, isRemoved);
        }

        List<String> answerList = new ArrayList<>(answer);
        Collections.sort(answerList);
        for (String a : answerList) {
            System.out.println(a);
        }
    }

    private static void removePair(int pairCnt, int start, int nowCnt, boolean[] isRemoved) {
        if (nowCnt == pairCnt) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < input.length; i++) {
                if ((input[i] == '(' || input[i] == ')') && isRemoved[i]) continue;
                sb.append(input[i]);
            }
            answer.add(sb.toString());
            return;
        }

        for (int i = start; i < input.length; i++) {
            if (input[i] != '(') {
                continue;
            }

            isRemoved[i] = true;
            isRemoved[pairIdx[i]] = true;
            removePair(pairCnt, i + 1, nowCnt + 1, isRemoved);
            isRemoved[i] = false;
            isRemoved[pairIdx[i]] = false;
        }
    }
}
