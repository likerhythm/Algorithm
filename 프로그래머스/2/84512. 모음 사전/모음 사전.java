class Solution {
    int count = 0;
    int answer = 0;
    String target;

    public int solution(String word) {
        target = word;
        dfs("");
        return answer;
    }

    boolean dfs(String current) {
        if (current.length() > 5) return false;
        if (!current.isEmpty()) {
            count++;
            if (current.equals(target)) {
                answer = count;
                return true;
            }
        }
        for (char c : "AEIOU".toCharArray()) {
            if (dfs(current + c)) return true;
        }
        return false;
    }
}