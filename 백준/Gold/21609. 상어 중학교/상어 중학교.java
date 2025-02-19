// 블록 그룹은 연결된 블록의 집합이다.
// 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 한다.
// 검은색 블록은 포함되면 안 되고, 무지개 블록은 얼마나 들어있든 상관없다.
// 그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다.
// 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int BLANK = -2;
    static int N; // 격자의 크기
    static int M; // 일반 블록 색상의 개수
    static int[][] colorMap;
    static boolean[][] visited;

    static class Block implements Comparable<Block> {
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Block o) {
            if (this.x < o.x) return -1;
            if (this.x > o.x) return 1;
            if (this.y < o.y) return -1;
            if (this.y > o.y) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "{" + x + ", " + y + "}";
        }
    }

    static class BlockGroup implements Comparable<BlockGroup>{
        List<Block> blocks;
        Block titleBlock;

        BlockGroup(List<Block> blocks) {
            this.blocks = blocks;
            setTitleBlock();
        }

        private void setTitleBlock() {
            PriorityQueue<Block> pq = new PriorityQueue<>();
            for (Block block : blocks) {
                if (colorMap[block.x][block.y] == 0) {
                    continue;
                }
                pq.add(block);
            }
            titleBlock = pq.poll();
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.blocks.size() < o.blocks.size()) return 1;
            if (this.blocks.size() > o.blocks.size()) return -1;
            if (this.countRainbow() < o.countRainbow()) return 1;
            if (this.countRainbow() > o.countRainbow()) return -1;
            if (this.titleBlock.x < o.titleBlock.x) return 1;
            if (this.titleBlock.x > o.titleBlock.x) return -1;
            if (this.titleBlock.y < o.titleBlock.y) return 1;
            if (this.titleBlock.y > o.titleBlock.y) return -1;
            return 0;
        }

        private int countRainbow() {
            int cnt = 0;
            for (Block b : blocks) {
                if (colorMap[b.x][b.y] == 0) {
                    cnt++;
                }
            }
            return cnt;
        }

        @Override
        public String toString() {
            return blocks.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        colorMap = new int[N][N];

        for (int i=0; i<N; i++) {
            colorMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int score = 0;
        while (true) {
            PriorityQueue<BlockGroup> pq = new PriorityQueue<>();
            visited = new boolean[N][N];
            for (int i=0; i<N; i++) { // 블록 그룹 찾기
                for (int j=0; j<N; j++) {
//                    if (visited[i][j]) { // 이미 방문한 경우
//                        continue;
//                    }

                    if (colorMap[i][j] == -1) { // 검은색 블록인 경우
                        continue;
                    }

                    List<Block> foundBlocks = findBlockGroup(i, j);

                    if (foundBlocks.size() < 2) { // 블록 그룹의 블록 수가 부족한 경우
                        continue;
                    }

                    if (allRainBow(foundBlocks)) { // 블록 그룹에 일반 블록이 없는 경우
                        continue;
                    }

                    BlockGroup bg = new BlockGroup(foundBlocks);
                    pq.add(bg);
                }
            }

//            System.out.println("pq: " + pq);

            if (pq.isEmpty()) { // 게임 종료;
//                System.out.println("게임 종료");
                break;
            }
            BlockGroup poll = pq.poll(); // 블록 그룹 선택
            score += (int) Math.pow(poll.blocks.size(), 2);

            // 블록 제거
            for (Block b : poll.blocks) {
                colorMap[b.x][b.y] = BLANK;
            }
//            System.out.println("블록 제거");
            printColor();

            // 중력(검은 블록 제외)
            gravity();
//            System.out.println("중력1");
            printColor();

            // 90도 반시계 회전
            rotate90CounterClockWise();
//            System.out.println("회전");
            printColor();

            // 중력(검은 블록 제외)
            gravity();
//            System.out.println("중력2");
            printColor();

            printColor();
        }

        System.out.println(score);
    }

    private static void printColor() {
        for (int[] row : colorMap) {
//            System.out.println(Arrays.toString(row));
        }
//        System.out.println();
    }

    private static boolean allRainBow(List<Block> foundBlocks) {
        for (Block b : foundBlocks) {
            if (colorMap[b.x][b.y] > 0) {
                return false;
            }
        }

        return true;
    }

    private static List<Block> findBlockGroup(int x, int y) {
        int[] dxs = {-1, 0, 1, 0};
        int[] dys = {0, 1, 0, -1};

        int color = colorMap[x][y];

        List<Block> result = new ArrayList<>();
        Queue<Block> q = new LinkedList<>();
        Block b = new Block(x, y);
        result.add(b);
        q.add(b);
        visited = new boolean[N][N];
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Block poll = q.poll();
            x = poll.x;
            y = poll.y;

            for (int i=0; i<4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];

                if (!inRange(nx) || !inRange(ny)) { // 범위를 벗어나는 경우
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (colorMap[nx][ny] == -1) { // 검은색 블록인 경우
                    continue;
                }

                if (colorMap[nx][ny] == -2) { // 빈공간인 경우
                    continue;
                }

                if (color == 0) { // 현재 result에 무지개 블록 밖에 없는 경우 색상 상관 없이 추가 가능
                    b = new Block(nx, ny);
                    result.add(b);
                    q.add(b);
                    visited[nx][ny] = true;
                    color = colorMap[nx][ny];
                } else if (color > 0) { // 블록 그룹에 일반 블록이 한 개 이상 있는 경우
                    if (colorMap[nx][ny] > 0 && colorMap[nx][ny] != color) { // 색상이 다른 일반 블록인 경우
                        continue;
                    }
                    b = new Block(nx, ny);
                    result.add(b);
                    q.add(b);
                    visited[nx][ny] = true;
                }
            }
        }

        return result;
    }

    private static void rotate90CounterClockWise() {
        int[][] tmpMap = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                tmpMap[N - 1 - j][i] = colorMap[i][j];
            }
        }

        colorMap = tmpMap;
    }

    private static void gravity() {
        for (int j=0; j<N; j++) {
            for (int i=N-1; i>=0; i--) {
                if (colorMap[i][j] >= 0) {
                    int nx = fall(i, j);
                    colorMap[nx][j] = colorMap[i][j];
                    for (int x=i; x<nx; x++) {
                        colorMap[i][j] = BLANK;
                    }
                }
            }
        }
    }

    private static int fall(int x, int y) {
        int nx = x + 1;
        while (true) {
            if (!inRange(nx)) { // 격자 범위를 벗어나는 경우
                nx--;
                break;
            }

            if (colorMap[nx][y] >= -1) { // 블록을 만나는 경우
                nx--;
                break;
            }

            nx++;
        }

        return nx;
    }

    private static boolean inRange(int x) {
        return 0 <= x && x < N;
    }
}
