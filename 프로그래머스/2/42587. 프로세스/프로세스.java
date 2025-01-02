import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Deque<Integer> processQueue = new ArrayDeque<>();
        Deque<Integer> priority = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();
        
        for (int i=0; i<priorities.length; i++) {
            processQueue.addLast(i);
            priority.addLast(priorities[i]);
        }
        
        while (!processQueue.isEmpty()) {
            int popPriority = priority.pop();
            int popProcess = processQueue.pop();
            boolean reAdd = false;
            for (int pr : priority) {
                if (popPriority < pr) {
                    priority.addLast(popPriority);
                    processQueue.addLast(popProcess);
                    reAdd = true;
                    break;
                }
            }
            if (!reAdd) {
                answer.add(popProcess);
            }
        }
        
        return answer.indexOf(location) + 1;
    }
}