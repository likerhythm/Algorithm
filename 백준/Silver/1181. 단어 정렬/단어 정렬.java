import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Set<String> values = new HashSet<>();

    static class Comp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() < o2.length()) return -1;
            if (o1.length() > o2.length()) return 1;
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++) {
            String input = br.readLine();
            values.add(input);
        }

        List<String> strings = new ArrayList<>(values);
        strings.sort(new Comp());

        for (String s : strings) {
            System.out.println(s);
        }
    }
}
