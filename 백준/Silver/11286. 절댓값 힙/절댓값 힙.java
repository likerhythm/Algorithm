import java.util.*;
import java.io.*;

public class Main {

    static class Node implements Comparable<Node> {
        int value;

        Node(int v) {
            value = v;
        }

        @Override
        public int compareTo(Node n) {
            if (Math.abs(this.value) < Math.abs(n.value)) {
                return -1;
            } else if (Math.abs(this.value) > Math.abs(n.value)) {
                return 1;
            }
            return Integer.compare(this.value, n.value);
        }
    }
    
    public static void main(String[] args) throws Exception {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll().value);
                }
            } else {
                pq.add(new Node(x));
            }
        }
    } 
}