class Solution {
    public int solution(String name) {
        int n = name.length();
        int answer = 0;
        int move = n - 1; // 최악: 오른쪽으로 끝까지
        
        for (int i = 0; i < n; i++) {
            // 1) 위/아래 조작 비용
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            // 2) 좌우 이동 최소 비용 계산
            // i 위치 이후 연속된 'A' 구간 찾기
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') next++;
            
            // i까지 갔다가 되돌아와 왼쪽으로 가기 vs 오른쪽으로 쭉
            move = Math.min(move, i * 2 + (n - next));      // → ← 
            move = Math.min(move, (n - next) * 2 + i);      // ← →
        }
        
        return answer + move;
    }
}