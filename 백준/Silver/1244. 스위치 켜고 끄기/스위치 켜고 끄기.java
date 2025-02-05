import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
    // 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다.

    static int switchCnt; // 0 < 스위치 개수 <= 100
    static int[] switches; // 스위치 상태(1 - on, 0 - off)
    static int studentCnt; // 학생 수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        switchCnt = Integer.parseInt(br.readLine());
        switches = new int[switchCnt + 1];

        String[] split = br.readLine().split(" ");
        for (int i=1; i<=switchCnt; i++) {
            switches[i] = Integer.parseInt(split[i - 1]);
        }

        studentCnt = Integer.parseInt(br.readLine());// 학생 수
        run();
    }

    private static void run() throws IOException {
        for (int i=0; i<studentCnt; i++) {
            String[] split = br.readLine().split(" ");
            int number = Integer.parseInt(split[1]);
            if (split[0].equals("1")) { // 남학생
                for (int j=number; j<=switchCnt; j+=number) {
                    switches[j] = 1 - switches[j];
                }
            } else { // 여학생
                switches[number] = 1 - switches[number];
                int right = number + 1;
                int left = number - 1;
                for (int jump=1;  0<left && right<=switchCnt; jump++) {
                    if (switches[right] != switches[left]) {
                        break;
                    }
                    switches[right] = 1 - switches[right];
                    switches[left] = 1 - switches[left];
                    right++;
                    left--;
                }
            }
        }

        printSwitches();
    }

    private static void printSwitches() {
        int idx = 1;
        while (true) {
            if (idx > switchCnt) {
                break;
            }
            if (idx % 20 == 1 && idx != 1) {
                System.out.println();
            }
            System.out.print(switches[idx] + " ");
            idx++;
        }
    }
}
