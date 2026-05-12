import java.util.*;

class Solution {
    
    class Node {
        int num;
        int pri;
        
        public Node(int num, int pri) {
            this.num = num;
            this.pri = pri;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Node(i, priorities[i]));
        }
        
        int count = 0;
        while (true) {
            Node poll = q.poll();
            boolean found = false;
            for (Node other : q) {
                if (poll.pri < other.pri) {
                    q.add(poll);
                    found = true;
                    break;
                }
            }
            
            if (found) continue;
            count++;
            if (poll.num == location) {
                return count;
            }
        }
    }
}