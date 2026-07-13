import java.util.*;

class Solution {
    
    class Pos {
        int x, y;
        
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pos other)) return false;
            return this.x == other.x && this.y == other.y;
        }
    }
    
    class Edge {
        Pos p1, p2;
        
        Edge(Pos p1, Pos p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        
        @Override
        public int hashCode() {
            return p1.hashCode() + p2.hashCode();
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge other)) return false;
            return (this.p1.equals(other.p1) && this.p2.equals(other.p2))
                || (this.p1.equals(other.p2) && this.p2.equals(other.p1));
        }
    }
    
    int[] dxs = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dys = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public int solution(int[] arrows) {
        Set<Pos> visited = new HashSet<>();
        Set<Edge> footPrint = new HashSet<>();
        int x = 0, y = 0;
        Pos p = new Pos(x, y);
        visited.add(p);
        
        int answer = 0;
        for (int a : arrows) {
            int nx = x + dxs[a];
            int ny = y + dys[a];
            Pos np = new Pos(nx, ny);
            Edge edge = new Edge(p, np);
            // 이전에 이동한 엣지인 경우 무시
            if (!footPrint.contains(edge)) {
                if (a % 2 == 1) { // 대각선인 경우
                    if (a == 1) {
                        Edge comp = new Edge(new Pos(x - 1, y), new Pos(x, y + 1));
                        if (footPrint.contains(comp)) answer++;
                    } else if (a == 3) {
                        Edge comp = new Edge(new Pos(x + 1, y), new Pos(x, y + 1));
                        if (footPrint.contains(comp)) answer++;
                    } else if (a == 5) {
                        Edge comp = new Edge(new Pos(x + 1, y), new Pos(x, y - 1));
                        if (footPrint.contains(comp)) answer++;
                    } else if (a == 7) {
                        Edge comp = new Edge(new Pos(x, y - 1), new Pos(x - 1, y));
                        if (footPrint.contains(comp)) answer++;
                    }
                }
                // 이미 방문한 위치인 경우
                if (visited.contains(np)) {
                    answer++;
                } else {
                    visited.add(np);
                }
                footPrint.add(edge);
            }
            x = nx;
            y = ny;
            p = np;
        }
        return answer;
    }
}