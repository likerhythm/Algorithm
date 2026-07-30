import java.util.*;

class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> answer = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return answer;

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> current = new HashSet<>();
        current.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        boolean found = false;

        while (!current.isEmpty() && !found) {
            Set<String> next = new HashSet<>();

            for (String word : current) {
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char origin = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == origin) continue;
                        arr[i] = c;
                        String cand = new String(arr);

                        if (dict.contains(cand) && !visited.contains(cand)) {
                            next.add(cand);
                            parents.computeIfAbsent(cand, k -> new ArrayList<>()).add(word);
                            if (cand.equals(endWord)) found = true;
                        }
                    }
                    arr[i] = origin;
                }
            }

            visited.addAll(next);
            current = next;
        }

        if (found) {
            LinkedList<String> path = new LinkedList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, parents, path, answer);
        }
        return answer;
    }

    private void backtrack(String word, String beginWord, Map<String, List<String>> parents, LinkedList<String> path, List<List<String>> answer) {
        if (word.equals(beginWord)) {
            answer.add(new ArrayList<>(path));
            return;
        }
        for (String parent : parents.getOrDefault(word, Collections.emptyList())) {
            path.addFirst(parent);
            backtrack(parent, beginWord, parents, path, answer);
            path.removeFirst();
        }
    }
}