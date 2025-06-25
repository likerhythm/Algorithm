import java.util.*;

class Solution {
    
    static int R, C;
    static int[][] points;
    static int[][] routes;
    static int robotCnt;
    static Set<Integer>[][] footPrints; // 각 위치에 로봇이 방문한 시간
    static Set<Integer>[][] strokeTimes; // 각 위치에서 일어난 충돌 시간
    static int answer;
    
    public int solution(int[][] p, int[][] r) {
        points = p;
        routes = r;
        setRC();
        footPrints = new HashSet[R][C];
        strokeTimes = new HashSet[R][C];
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                footPrints[i][j] = new HashSet<>();
                strokeTimes[i][j] = new HashSet<>();
            }
        }
        
        robotCnt = routes.length;
        
        for (int robot = 0; robot < robotCnt; robot++) {
            f(robot);
        }
    
        return answer;
    }
    
    void printMap(Set<Integer>[][] set) {
        for (int r = 1; r < R; r++) {
            System.out.println(Arrays.toString(set[r]));
        }
        System.out.println();
    }
    
    // 각 로봇의 발자취를 footPrints에 기록
    // footPrint가 같은 시간에 겹치면 answer 갱신
    void f(int robot) {
        int time = 0;
        for (int routeIdx = 0; routeIdx < routes[robot].length; routeIdx++) {
            int route = routes[robot][routeIdx];
            if (routeIdx == routes[robot].length - 1) { // 마지막 point인 경우
                setFootPrints(route, -1, time);
                continue;
            }
            int nextRoute = routes[robot][routeIdx + 1];
            time = setFootPrints(route, nextRoute, time);
        
            // printMap(footPrints);
            // printMap(strokeTimes);
        }
    }
    
    int setFootPrints(int start, int end, int time) {
        int[] startRoute = points[start - 1];
        int[] endRoute;
        if (end != -1) {
            endRoute = points[end - 1];
            int startR = startRoute[0], startC = startRoute[1];
            move(startR, startC, time);
            time++;
        } else {
            endRoute = new int[] {-1, -1};
            int startR = startRoute[0], startC = startRoute[1];
            move(startR, startC, time);
            return time;
        }
        
        int startR = startRoute[0], startC = startRoute[1];
        int endR = endRoute[0], endC = endRoute[1];
        
        if (startR < endR) { // 위에서 아래로 이동하는 경우
            if (startC == endC) { // 가로로 이동할 일이 없는 경우
                endR--;
            }
            for (int r = startR + 1; r <= endR; r++) {
                move(r, startC, time);
                time++;
            }
        } else if (startR > endR) { // 아래에서 위로 이동하는 경우
            if (startC == endC) { // 가로로 이동할 일이 없는 경우
                endR++;
            }
            for (int r = startR - 1; r >= endR; r--) {
                move(r, startC, time);
                time++;
            }
        }
        
        if (startC < endC) { // 왼쪽에서 오른쪽으로 이동하는 경우
            for (int c = startC + 1; c < endC; c++) {
                move(endR, c, time);
                time++;
            }
        } else if (startC > endC) { //오른쪽에서 왼쪽으로 이동하는 경우
            for (int c = startC - 1; c > endC; c--) {
                move(endR, c, time);
                time++;
            }
        }
        
        return time;
    }
    
    void move(int r, int c, int time) {
        Set<Integer> footPrint = footPrints[r][c];
        Set<Integer> strokeTime = strokeTimes[r][c];
        if (footPrint.contains(time)) { // 충돌이 일어난 경우
            if (strokeTime.contains(time)) { // 이 충돌을 이미 count한 경우
                return;
            }
            strokeTime.add(time); // 충돌 등록
            answer++;
        } else { // 충돌이 일어나지 않은 경우
            footPrint.add(time);
        }
    }
    
    void setRC() {
        int maxR = 0;
        int maxC = 0;
        for (int[] point : points) {
            int r = point[0];
            int c = point[1];
            
            maxR = Math.max(maxR, r);
            maxC = Math.max(maxC, c);
        }
        
        R = maxR + 1;
        C = maxC + 1;
    }
}