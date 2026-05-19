import java.util.*;

class Solution {
    
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length; // 총 논문의 수
        
        for (int i = 0; i < n; i++) {
            int h = n - i; // citations[i] 번 이상 인용된 논문의 수
            if (citations[i] >= h) {
                return h;
            }
        }
        return 0;
    }
}