import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 문자열의 양 끝이 같으면서 해당 문자열이 K개 포함된 문자열의 최소 길이와 최대 길이 구하기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<Integer>[] alphaIndices = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                alphaIndices[i] = new ArrayList<>();
            }

            for (int i = 0; i < W.length(); i++) {
                alphaIndices[W.charAt(i) - 'a'].add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = -1;

            for (int i = 0; i < 26; i++) {
                List<Integer> indices = alphaIndices[i];
                if (indices.size() < K) continue;

                for (int j = 0; j <= indices.size() - K; j++) {
                    int start = indices.get(j);
                    int end = indices.get(j + K - 1);
                    int length = end - start + 1;

                    minLen = Math.min(minLen, length);
                    maxLen = Math.max(maxLen, length);
                }
            }

            if (minLen == Integer.MAX_VALUE) {
                System.out.println("-1");
            } else {
                System.out.println(minLen + " " + maxLen);
            }
        }
    }
}
