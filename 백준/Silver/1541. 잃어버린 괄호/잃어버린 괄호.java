import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("-");
        List<Integer> intList = new ArrayList<>();

        for (String s : split) {
            int[] intS = Arrays.stream(s.split("\\+")).mapToInt(Integer::parseInt).toArray();
            int sum = Arrays.stream(intS).sum();
            intList.add(sum);
        }

        int answer = intList.get(0);
        for (int i=1; i<intList.size(); i++) {
            answer -= intList.get(i);
        }

        System.out.println(answer);
    }
}
