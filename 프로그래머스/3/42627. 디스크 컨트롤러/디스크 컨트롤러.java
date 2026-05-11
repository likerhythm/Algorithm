import java.util.*;

class Solution {
    static class Job implements Comparable<Job> {
        int num, reqTime, cost, startTime, endTime;
        
        public Job(int num, int reqTime, int cost) {
            this.num = num;
            this.reqTime = reqTime;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Job o) {
            if (this.cost != o.cost) return Integer.compare(this.cost, o.cost);
            if (this.reqTime != o.reqTime) return Integer.compare(this.reqTime, o.reqTime);
            return Integer.compare(this.num, o.num);
        }
    }
    
    public int solution(int[][] j) {
        int N = j.length;
        Job[] jobs = new Job[N];
        PriorityQueue<Job> pq = new PriorityQueue<>();
        
        // 요청 시각 순으로 정렬해서 처리하는 게 더 깔끔합니다
        Job[] sortedByReq = new Job[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Job(i, j[i][0], j[i][1]);
            sortedByReq[i] = jobs[i];
        }
        Arrays.sort(sortedByReq, (a, b) -> Integer.compare(a.reqTime, b.reqTime));
        
        int time = 0, idx = 0, totalTurnaround = 0;
        
        while (idx < N || !pq.isEmpty()) {
            while (idx < N && sortedByReq[idx].reqTime <= time) {
                pq.offer(sortedByReq[idx++]);
            }
            
            if (pq.isEmpty()) {
                time = sortedByReq[idx].reqTime;
                continue;
            }
            
            Job cur = pq.poll();
            time += cur.cost;
            totalTurnaround += time - cur.reqTime;
        }
        
        return totalTurnaround / N;
    }
}