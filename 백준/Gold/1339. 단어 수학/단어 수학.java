import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Node implements Comparable<Node> {
        char c;
        double weight;

        public Node(char c, double weight) {
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(other.weight, this.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Node> nodes = new ArrayList<>();
        double[] weights = new double[26];
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                int idx = words[i].length() - j - 1;
                weights[c - 'A'] += Math.pow(10, idx);
            }
        }

        for (int i = 0; i < 26; i++) {
            if (weights[i] == 0) continue;
            nodes.add(new Node((char) ('A' + i), weights[i]));
        }

        Collections.sort(nodes);

        int answer = 0;
        int num = 9;
        for (Node node : nodes) {
            answer += (int) node.weight * num;
            num--;
        }

        System.out.println(answer);
    }
}
