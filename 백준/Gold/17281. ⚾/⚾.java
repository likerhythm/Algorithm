import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Person[] persons;
    static int answer;

    static class Person {
        int[] commands;

        Person() {
            this.commands = new int[N];
        }

        void addCommand(int idx, int value) {
            this.commands[idx] = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        persons = new Person[9];

        for (int i = 0; i < 9; i++) {
            persons[i] = new Person();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                persons[j].addCommand(i, Integer.parseInt(st.nextToken()));
            }
        }

        boolean[] visited = new boolean[9];
        visited[0] = true;
        int[] taja = new int[9];
        taja[3] = 0;
        arrangeOrder(0, taja, visited);

        System.out.println(answer);
    }

    private static void arrangeOrder(int idx, int[] taja, boolean[] visited) {
        if (allTrue(visited)) {
            int score = simulate(taja);
            answer = Math.max(answer, score);
            return;
        }

        if (idx == 3) { // 4번 타자를 1번 선수로 미리 결정함
            arrangeOrder(idx + 1, taja, visited);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (visited[i]) continue;
            taja[idx] = i;
            visited[i] = true;
            arrangeOrder(idx + 1, taja, visited);
            visited[i] = false;
        }
    }

    private static boolean allTrue(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) return false;
        }
        return true;
    }

    private static int simulate(int[] taja) {
        int inning = 1;
        int out = 0;
        boolean[] loo = new boolean[4];
        int score = 0;

        int idx = 0;
        while (inning <= N) {
            while (out < 3) {
                int now = taja[idx];
                loo[0] = true;
                int command = persons[now].commands[inning - 1];
                if (command == 0) {
                    out++;
                    loo[0] = false;
                } else if (command == 1) {
                    score += jinloo(1, loo);
                } else if (command == 2) {
                    score += jinloo(2, loo);
                } else if (command == 3) {
                    score += jinloo(3, loo);
                } else if (command == 4) {
                    score += jinloo(4, loo);
                }
                idx = (idx + 1) % 9;
            }
            inning++;
            out = 0;
            loo = new boolean[4];
        }

        return score;
    }

    private static int jinloo(int cnt, boolean[] loo) {
        int score = 0;
        for (int i = 3; i >= 0; i--) {
            if (!loo[i]) continue;
            if (i + cnt > 3) {
                score++;
                loo[i] = false;
            } else {
                loo[i] = false;
                loo[i + cnt] = true;
            }
        }
        return score;
    }
}
