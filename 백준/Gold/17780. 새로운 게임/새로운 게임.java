import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int N; // 보드판 크기
    static int K; // 말의 수
    static int[][] map;
    static Node[][] nodes;
    static Horse[] horses;
    enum D {R, L, U, D}
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;

    static class Horse {
        int idx;
        int x, y;
        D d;

        public Horse(int idx, int x, int y, D d) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public void reverseD() {
            if(this.d == D.R) {
                this.d = D.L;
            } else if (this.d == D.L) {
                this.d = D.R;
            } else if (this.d == D.U) {
                this.d = D.D;
            } else {
                this.d = D.U;
            }
        }
    }

    static class Node {
        int x, y;
        Deque<Horse> horses = new LinkedList<>();
        boolean isReverse = false;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void addHorse(Horse horse) {
            if (!isReverse) {
                horses.addLast(horse);
                return;
            }

            horses.addFirst(horse);
        }

        public void invert() {
            this.isReverse = !isReverse;
        }

        public void reverseD() {
            if (!isReverse) {
                horses.peekFirst().reverseD();
                return;
            }
            horses.peekLast().reverseD();
        }

        public D getDirection() {
            if (!isReverse) {
                return horses.peekFirst().d;
            }

            return horses.peekLast().d;
        }

        public void removeHorses() {
            horses.clear();
        }

        public void union(Node other) {
            Deque<Horse> otherHorses = other.horses;
            if (!other.isReverse) {
                while (!otherHorses.isEmpty()) {
                    Horse horse = otherHorses.pollFirst();
                    horse.x = this.x;
                    horse.y = this.y;
                    if (this.isReverse) {
                        this.horses.addFirst(horse);
                    } else {
                        this.horses.addLast(horse);
                    }
                }
            } else {
                while (!otherHorses.isEmpty()) {
                    Horse horse = otherHorses.pollLast();
                    horse.x = this.x;
                    horse.y = this.y;
                    if (this.isReverse) {
                        this.horses.addFirst(horse);
                    } else {
                        this.horses.addLast(horse);
                    }
                }
            }

            other.isReverse = false;
            other.removeHorses();
        }

        public boolean isBot(Horse horse) {
            if (this.isReverse) {
                return horses.peekLast().idx == horse.idx;
            }

            return horses.peekFirst().idx == horse.idx;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (this.isReverse) {
                for (int i = horses.size() - 1; i >= 0; i--) {
                    Horse horse = horses.pollLast();
                    sb.append(horse.idx);
                    horses.addFirst(horse);
                }
            } else {
                for (int i = 0; i < horses.size(); i++) {
                    Horse horse = horses.pollFirst();
                    sb.append(horse.idx);
                    horses.addLast(horse);
                }
            }
            sb.append("]");
            return sb.toString();
        }

        public boolean isDone() {
            return horses.size() >= 4;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = split[0];
        K = split[1];
        map = new int[N][N];
        nodes = new Node[N][N];
        horses = new Horse[K];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }

        for (int i = 0; i < K; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = input[0] - 1;
            int y = input[1] - 1;
            D d = D.values()[input[2] - 1];

            Horse horse = new Horse(i, x, y, d);
            nodes[x][y].addHorse(horse);
            horses[i] = horse;
        }

        int[] dxs = {0, 0, -1, 1}; // 오, 왼, 위, 아래
        int[] dys = {1, -1, 0, 0};

        int turn = -1;
//        printNodes();
        outer : while (true) {
            turn++;
            if (turn >= 1000) {
                System.out.println(-1);
                return;
            }

            for (int horseIdx = 0; horseIdx < K; horseIdx++) {
//                System.out.println("horseIdx = " + horseIdx);
                Horse horse = horses[horseIdx];
                int x = horse.x, y = horse.y;
                if (nodes[x][y].isBot(horse)) {
                    D d = horse.d;
                    int nx = x + dxs[d.ordinal()];
                    int ny = y + dys[d.ordinal()];

                    if (!inRange(nx, ny) || map[nx][ny] == BLUE) {
                        nodes[x][y].reverseD();
                        d = horse.d;
                        nx = x + dxs[d.ordinal()];
                        ny = y + dys[d.ordinal()];
                        if (!inRange(nx, ny) || map[nx][ny] == BLUE) {
//                            printNodes();
                            continue;
                        }
                    }

                    if (map[nx][ny] == RED) {
                        nodes[x][y].invert();
                    }

                    nodes[nx][ny].union(nodes[x][y]);
//                    printNodes();
                    if (nodes[nx][ny].isDone()) {
                        break outer;
                    }
                }
            }
        }

        System.out.println(turn + 1);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void printNodes() {
        for (Node[] n : nodes) {
            System.out.println(Arrays.toString(n));
        }
        System.out.println();
    }
}
