import java.util.*;
import java.io.*;

public class Main {

    static int N; // 정수의 개수
    static int M; // 구간 개수
    static int[] nums;

    static class SegmentTree {

        final int MAX = 1_000_000_001;
        final int MIN = 0;
        int tree[][]; // 최소, 최대
        int size;

        SegmentTree(int numCount) {
            int h = (int) Math.ceil(Math.log(numCount) / Math.log(2));
            int treeSize = (int) Math.pow(2, h + 1);
            tree = new int[treeSize][2];
            this.size = treeSize;
        }

        int[] init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = new int[] {nums[start], nums[start]};
            }

            int mid = (start + end) / 2;

            int[] left = init(node * 2, start, mid);
            int[] right = init(node * 2 + 1, mid + 1, end);

            return tree[node] = new int[] {Math.min(left[0], right[0]), Math.max(left[1], right[1])};
        }

        int findMin(int node, int start, int end, int targetStart, int targetEnd) {
            if (targetEnd < start || end < targetStart) {
                return MAX;
            }

            if (targetStart <= start && end <= targetEnd) {
                return tree[node][0];
            }

            int mid = (start + end) / 2;
            return Math.min(findMin(node * 2, start, mid, targetStart, targetEnd),
                           findMin(node * 2 + 1, mid + 1, end, targetStart, targetEnd));
        }   

        int findMax(int node, int start, int end, int targetStart, int targetEnd) {
            if (targetEnd < start || end < targetStart) {
                return MIN;
            }

            if (targetStart <= start && end <= targetEnd) {
                return tree[node][1];
            }

            int mid = (start + end) / 2;
            return Math.max(findMax(node * 2, start, mid, targetStart, targetEnd),
                           findMax(node * 2 + 1, mid + 1, end, targetStart, targetEnd));
        }

        void print() {
            for (int i=0; i<size; i++) {
                System.out.print(Arrays.toString(tree[i]));
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        for (int i=1; i<=N; i++) { // 수 입력
            nums[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segTree = new SegmentTree(N);
        segTree.init(1, 1, N);
        // st.print();

        StringBuilder answer = new StringBuilder();
        for (int m=0; m<M; m++) { // 로직 수행
            st = new StringTokenizer(br.readLine());
            int targetStart = Integer.parseInt(st.nextToken());
            int targetEnd = Integer.parseInt(st.nextToken());
            answer.append(segTree.findMin(1, 1, N, targetStart, targetEnd)).append(" ");
            answer.append(segTree.findMax(1, 1, N, targetStart, targetEnd)).append("\n");
        }
        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}