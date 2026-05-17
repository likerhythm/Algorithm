class Solution {
    public int solution(String name) {
        int n = name.length();
        int answer = 0;
        int move = n - 1;
        
        for (int i = 0; i < n; i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') next++;
            
            move = Math.min(move, i * 2 + (n - next));
            move = Math.min(move, (n - next) * 2 + i);
        }
        
        return answer + move;
    }
}