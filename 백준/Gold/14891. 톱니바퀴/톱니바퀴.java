import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.IsoChronology;
import java.util.Arrays;

public class Main {

    // 회전하는 경우 : 극이 다를 때 - 반대 방향으로 회전
    // 회전하지 않는 경우 : 극이 같을 때, 옆 톱니바퀴가 회전하지 않았을 때
    // 극이 같은 톱니바퀴가 맞닿아 있는 경우 거기서 회전 로직을 끝내면 됨.

    static class Wheel {
        String[] state;

        Wheel (String[] state) {
            this.state = state;
        }

        public boolean isConnectLeft(Wheel wheel) {
            return !this.state[6].equals(wheel.state[2]);
        }

        public boolean isConnectRight(Wheel wheel) {
            return !this.state[2].equals(wheel.state[6]);
        }

        public void rotate(int command) {
            if (command == -1) { // 반시계 방향
                // 0 -> 7
                // 1 -> 0
                // 2 -> 1
                String tmp = state[0];
                for (int i=1; i<8; i++) {
                    state[i - 1] = state[i];
                }
                state[7] = tmp;
                return;
            }
            // 7 -> 0
            // 0 -> 1
            String tmp = state[7];
            for (int i=7; i>0; i--) {
                state[i] = state[i - 1];
            }
            state[0] = tmp;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Wheel[] wheels = new Wheel[4];

    public static void main(String[] args) throws IOException {
        for (int i=0; i<4; i++) {
            String[] split = br.readLine().split("");
            wheels[i] = new Wheel(split);
        }

        visited = new boolean[4];

        int K = Integer.parseInt(br.readLine());
        for (int i=0; i<K; i++) {
            String[] split = br.readLine().split(" ");
            int num = Integer.parseInt(split[0]) - 1;
            int command = Integer.parseInt(split[1]);
            int[] rotateCommands = new int[4];
            Arrays.fill(visited, false);
            rotate(num, command, rotateCommands);
//            for (Wheel wheel : wheels) {
//                System.out.println(Arrays.toString(wheel.state));
//            }
//            System.out.println();

            for (int j=0; j<4; j++) {
                if (rotateCommands[j] == 0) {
                    continue;
                }
                wheels[j].rotate(rotateCommands[j]);
            }
        }

        int score = 0;
        if (wheels[0].state[0].equals("1")) {
            score += 1;
        }
        if (wheels[1].state[0].equals("1")) {
            score += 2;
        }
        if (wheels[2].state[0].equals("1")) {
            score += 4;
        }
        if (wheels[3].state[0].equals("1")) {
            score += 8;
        }
        System.out.println(score);
    }

    static boolean[] visited;

    private static void rotate(int num, int command, int[] rotateCommands) {
        rotateCommands[num] = command;
        visited[num] = true;

        if (num - 1 >= 0 && !visited[num - 1]) {
            if (wheels[num - 1].isConnectRight(wheels[num])) {
                rotate(num - 1, -command, rotateCommands);
            }
        }

        if (num + 1 < 4 && !visited[num + 1]) {
            if (wheels[num + 1].isConnectLeft(wheels[num])) {
                rotate(num + 1, -command, rotateCommands);
            }
        }
    }
}
