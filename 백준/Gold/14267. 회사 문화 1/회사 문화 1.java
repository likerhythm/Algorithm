import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N; // 직원 수
    static int M; // 최초의 칭찬 횟수
    static List<Emp> emps = new ArrayList<>();

    static class Emp {
        private List<Integer> slaves = new ArrayList<>();
        private long happy;

        public void setHappy(long happy) {
            this.happy += happy;
        }

        public void addSlave(int num) {
            this.slaves.add(num);
        }

        public void sendHappyToSlaves(long h) {
            this.happy += h;
            for (int num : slaves) {
                Emp slave = emps.get(num);
                slave.sendHappyToSlaves(this.happy);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            emps.add(new Emp());
        }

        st = new StringTokenizer(br.readLine());
        for (int slave = 1; slave <= N; slave++) {
            int master = Integer.parseInt(st.nextToken());
            if (master == -1) continue;
            Emp masterEmp = emps.get(master);
            masterEmp.addSlave(slave);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int happy = Integer.parseInt(st.nextToken());
            Emp slave = emps.get(num);
            slave.setHappy(happy);
        }

        Emp master = emps.get(1);
        master.sendHappyToSlaves(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            Emp emp = emps.get(i);
            sb.append(emp.happy).append(" ");
        }

        System.out.println(sb);
    }
}
