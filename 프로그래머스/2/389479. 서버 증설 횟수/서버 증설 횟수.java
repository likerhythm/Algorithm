import java.util.*;
import java.io.*;

class Solution {
    
    Queue<Server> q = new LinkedList<>();
    
    static class Server {
        
        int life;
        
        public Server(int life) {
            this.life = life;
        }
    }
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        for (int player : players) {
            while (!q.isEmpty() && q.peek().life == 0) {
                q.poll();
            }
            int serverCnt = q.size(); // 현재 서버 개수
            int scaledServerCnt = player / m; // 필요한 증설 서버 개수
            if (serverCnt < scaledServerCnt) {
                int cnt = scaledServerCnt - serverCnt;
                answer += cnt;
                for (int i = 0; i < cnt; i++) {
                    q.add(new Server(k));
                }
            }
            
            Queue<Server> newQ = new LinkedList<>();
            while (!q.isEmpty()) {
                Server server = q.poll();
                server.life--;
                newQ.add(server);
            }
            
            q = newQ;
        }
        
        return answer;
    }
}