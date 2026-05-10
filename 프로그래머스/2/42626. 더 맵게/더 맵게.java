import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        
        int answer = 0;
        while (pq.peek() < K) {
            if (pq.size() == 1) {
                answer = -1;
                break;
            }
            int v = pq.poll() + pq.poll() * 2;
            pq.add(v);
            answer++;
        }
        
        return answer;
    }
}