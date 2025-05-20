import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static int[] nums;
    static int[] answer;
    static Set<Component> answerSet = new HashSet<>();

    static class Component {
        int[] arr;

        Component(int[] arr) {
            this.arr = arr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Component comp = (Component) o;
            return Arrays.equals(arr, comp.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        answer = new int[M];

        f(0, 0);
    }

    static void f(int answerIdx, int numIdx) {
        if (answerIdx == M) {
            if (answerSet.contains(new Component(answer))) {
                return;
            }
            
            for (int a : answer) {
                System.out.print(a + " ");
            }
            System.out.println();
            
            answerSet.add(new Component(answer));
            return;
        }

        for (int i=numIdx; i<N; i++) {
            answer[answerIdx] = nums[i];
            f(answerIdx + 1, i);
        }
    }
}