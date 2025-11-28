import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M; // 처음에 심은 나무 개수
    static int K; // K년이 지난 후 살아있는 나무 개수 구하기
    static int[][] A; // 겨울에 추가할 양분
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Grounds {
        private Ground[][] grounds;

        public Grounds() throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            A = new int[N][N];
            for (int i = 0; i < N; i++) {
                A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            this.grounds = new Ground[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    grounds[i][j] = new Ground();
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()) - 1;
                int m = Integer.parseInt(st.nextToken()) - 1;
                int age = Integer.parseInt(st.nextToken());
                grounds[n][m].addTree(new Tree(age));
            }
        }

        // 나무가 자신의 나이만큼 양분을 먹고 나이가 1증가한다.
        public void spring() {
            for (Ground[] gs : grounds) {
                for (Ground g : gs) {
                    g.spring();
                }
            }
        }

        // 봄에 죽은 나무가 양분으로 변한다
        public void summer() {
            for (Ground[] gs : grounds) {
                for (Ground g : gs) {
                    g.summer();
                }
            }
        }

        // 나무가 번식한다
        public void autumn() {
            int[] dns = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dms = {0, 1, 1, 1, 0, -1, -1, -1};
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < N; m++) {
                    Ground ground = grounds[n][m];
                    int breedCnt = ground.autumn();
                    for (int bc = 0; bc < breedCnt; bc++) {
                        for (int k = 0; k < 8; k++) {
                            int nn = n + dns[k];
                            int nm = m + dms[k];
                            if (!inRange(nn, nm)) continue;
                            grounds[nn][nm].addTree(new Tree());
                        }
                    }
                }
            }
        }

        // S2D2가 양분을 뿌린다
        public void winter() {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < N; m++) {
                    Ground ground = grounds[n][m];
                    int nut = A[n][m];
                    ground.winter(nut);
                }
            }
        }

        private boolean inRange(int n, int m) {
            return 0 <= n && n < N && 0 <= m && m < N;
        }

        public int countTree() {
            int cnt = 0;
            for (Ground[] gs : grounds) {
                for (Ground g : gs) {
                    cnt += g.countTree();
                }
            }

            return cnt;
        }
    }

    static class Ground {
        private Queue<Tree> trees = new PriorityQueue<>();
        private int nutrients = 5;

        public void addTree(Tree tree) {
            trees.add(tree);
        }

        public void spring() {
            Queue<Tree> newTrees = new PriorityQueue<>();
            while (!this.trees.isEmpty()) {
                Tree poll = this.trees.poll();
                if (poll.canEat(this.nutrients)) {
                    this.nutrients -= poll.getAge();
                    poll.grow();
                } else {
                    poll.dead();
                }
                newTrees.add(poll);
            }
            this.trees = newTrees;
        }

        public void summer() {
            Queue<Tree> newTrees = new PriorityQueue<>();
            while (!this.trees.isEmpty()) {
                Tree poll = this.trees.poll();
                if (!poll.isDead()) {
                    newTrees.add(poll);
                    continue;
                }
                int nut = poll.getNutrients();
                this.nutrients += nut;
            }
            this.trees = newTrees;
        }

        public int autumn() {
            int breedCnt = 0;
            for (Tree tree : trees) {
                if (tree.canBreed()) breedCnt++;
            }
            return breedCnt;
        }

        public void winter(int nut) {
            this.nutrients += nut;
        }

        public int countTree() {
            return trees.size();
        }
    }

    static class Tree implements Comparable<Tree> {
        private int age = 1;
        private boolean isDead;

        public Tree() {}

        public Tree(int age) {
            this.age = age;
        }

        public boolean canEat(int remainNutrients) {
            return age <= remainNutrients;
        }

        public void grow() {
            age++;
        }

        public boolean canBreed() {
            return this.age % 5 == 0;
        }

        public boolean isDead() {
            return isDead;
        }

        public void dead() {
            this.isDead = true;
        }

        public int getNutrients() {
            return age / 2; // 양분으로 돌아갈 양
        }

        public int compareTo(Tree other) {
            return Integer.compare(this.age, other.age);
        }

        public int getAge() {
            return this.age;
        }
    }

    public static void main(String[] args) throws IOException {
        Grounds grounds = new Grounds();
        for (int year = 1; year <= K; year++) {
            grounds.spring();
            grounds.summer();
            grounds.autumn();
            grounds.winter();
        }
        System.out.println(grounds.countTree());
    }
}
