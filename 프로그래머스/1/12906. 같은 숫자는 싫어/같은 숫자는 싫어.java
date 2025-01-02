import java.util.*;

public class Solution {    
    public int[] solution(int []arr) {
        List<Integer> answer = new ArrayList<>();
        for (int a : arr) {
            if (answer.isEmpty()) {
                answer.add(a);
                continue;
            }
            int top = answer.get(answer.size() - 1);
            if (top == a) {
                continue;
            }
            answer.add(a);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}