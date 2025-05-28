import java.util.*;

class Solution {
    
    static int N, M;
    static char[][] map;
    static boolean[][] pickup;
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        map = new char[N + 2][M + 2];
        pickup = new boolean[N + 2][M + 2];
        
        Arrays.fill(pickup[0], true);
        Arrays.fill(pickup[N + 1], true);
        for (int i=0; i<N+2; i++) {
            pickup[i][0] = true;
            pickup[i][M + 1] = true;
        }
        
        for (int i=1; i<=N; i++) {
            String input = storage[i - 1];
            char[] charArr = input.toCharArray();
            for (int j=1; j<=M; j++) {
                map[i][j] = charArr[j - 1];
            }
        }
        
        for (String req : requests) {
            if (req.length() == 2) {
                crain(req);
            } else {
                jige(req);
            }
        }
        
        int answer = 0;
        for (int i=1; i<=N; i++) {
            System.out.println(Arrays.toString(pickup[i]));
            for (int j=1; j<=M; j++) {
                if (!pickup[i][j]) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    void crain(String req) {
        char c = req.charAt(0);
        for (int i=1; i<=N;i++) {
            for (int j=1; j<=M; j++) {
                if (map[i][j] == c) {
                    pickup[i][j] = true;
                }
            }
        }
    }
    
    void jige(String req) {
        char c = req.charAt(0);
        boolean[][] newPickup = bfs(c);
        pickup = newPickup;
    }
    
    boolean[][] bfs(char c) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] newPickup = new boolean[N + 2][M + 2];
        for (int i=0; i<N+2; i++) {
            for (int j=0; j<M+2; j++) {
                newPickup[i][j] = pickup[i][j];
            }
        }
        q.add(new int[] {0, 0});
        pickup[0][0] = true;
        boolean[][] visited = new boolean[N + 2][M+ 2];
        visited[0][0] = true;
        
        int[] dns = {-1, 0, 1, 0};
        int[] dms = {0, 1, 0, -1};
        
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int n = poll[0];
            int m = poll[1];
            
            for (int i=0; i<4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                
                if (!inRange(nn, nm)) {
                    continue;
                }
                
                if (visited[nn][nm]) {
                    continue;
                }
                
                if (!pickup[nn][nm]) {
                    if (map[nn][nm] == c) {
                        newPickup[nn][nm] = true;
                        visited[nn][nm] = true;
                    }
                    continue;
                }
                
                q.add(new int[] {nn, nm});
                visited[nn][nm] = true;
            }
        }
        
        return newPickup;
    }
    
    boolean inRange(int n, int m) {
        return 0 <= n && n < N + 2 && 0 <= m && m < M + 2;
    }
}