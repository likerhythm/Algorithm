import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static class Pos implements Comparable<Pos>{
        int x, y;

        Pos(int x, int y) {
           this.x = x;
           this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.x < o.x) return -1;
            if (this.x > o.x) return 1;
            if (this.y < o.y) return -1;
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Pos[] poses = new Pos[N];

        for (int i=0; i<N; i++) {
            String[] split = br.readLine().split(" ");
            poses[i] = new Pos(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        Arrays.sort(poses);

        for (Pos pos : poses) {
            System.out.println(pos.x + " " + pos.y);
        }
    }
}
