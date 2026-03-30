import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        int baseMask = 0;
        for (char c : "antic".toCharArray()) {
            baseMask |= (1 << c - 'a');
        }

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int[] wordMasks = new int[N];
        for (int i = 0; i < N; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) {
                mask |= (1 << c - 'a');
            }
            wordMasks[i] = mask;
        }

        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if ((baseMask & (1 << i)) != 0) continue;
            candidates.add(i);
        }

        int extra = K - 5;
        int answer = 0;

        int total = 1 << candidates.size();
        for (int subset = 0; subset < total; subset++) {
            if (Integer.bitCount(subset) != extra) continue;

            int chosenMask = baseMask;
            for (int i = 0; i < candidates.size(); i++) {
                if ((subset & (1 << i)) != 0) {
                    chosenMask |= (1 << candidates.get(i));
                }
            }

            int count = 0;
            for (int wm : wordMasks) {
                if ((wm & chosenMask) == wm) count++;
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}
