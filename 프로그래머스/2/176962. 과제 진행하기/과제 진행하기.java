// 시작 시간 순으로 정렬
// 잠시 멈춘 과제는 큐로 이동

import java.util.*;

class Solution {
    
    int planCount;
    Task[] tasks;
    
    class Task implements Comparable<Task> {
        String name;
        int startTime;
        int endTime;
        int progress;
        int playTime;
        
        Task(String name, int startTime, int endTime, int playTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
            this.playTime = playTime;
            this.progress = 0;
        }
        
        @Override
        public int compareTo(Task o) {
            return Integer.compare(this.startTime, o.startTime);
        }
        
        @Override
        public String toString() {
            return "[" + name + ", " + startTime + ", " + endTime + "]";
        }
    }
    
    public String[] solution(String[][] plans) {
        planCount = plans.length;
        tasks = new Task[planCount];
        
        for (int i=0; i<planCount; i++) {
            String name = plans[i][0];
            String start = plans[i][1];
            String playtime = plans[i][2];
            
            int hour = Integer.parseInt(start.split(":")[0]);
            int minute = Integer.parseInt(start.split(":")[1]);
            int startTime = hour * 60 + minute;
            int endTime = startTime + Integer.parseInt(playtime);
            
            tasks[i] = new Task(name, startTime, endTime, Integer.parseInt(playtime));
        }
        
        // System.out.println(Arrays.toString(tasks));
        
        Arrays.sort(tasks);
        String[] answer = new String[planCount];
        int answerCursor = 0;
        Stack<Task> pauseTask = new Stack<>();
        Task nowTask = tasks[0];
        int nextCursor = 1;
        
        while (answerCursor < planCount) {
            Task nextTask;
            if (nextCursor < planCount) {
                nextTask = tasks[nextCursor];
            } else {
                nextTask = null;
            }
            
            if (nextTask != null) { // 시작하지 않은 다음 과제가 있는 경우
                if (nextTask.startTime < nowTask.endTime) { // 다음 과제를 먼저 시작해야 하는 경우
                    nowTask.progress += nextTask.startTime - nowTask.startTime; // 현재 작업의 진척도 계산
                    pauseTask.push(nowTask); // 현재 진행중인 과제를 잠시 멈추고
                    nowTask = nextTask; // 다음 과제 바로 시작
                    nextCursor++;
                } else if (nextTask.startTime == nowTask.endTime) { // 다음 과제를 바로 시작해야 하는 경우
                    answer[answerCursor] = nowTask.name; // 현재 과제를 완료(정답에 추가)
                    answerCursor++;
                    nowTask = nextTask;
                    nextCursor++;
                } else { // 다음 과제를 시작하기 까지 시간이 남은 경우
                    answer[answerCursor] = nowTask.name; // 현재 과제를 완료(정답에 추가)
                    answerCursor++;
                    // nowTask = nextTask; // 다음 과제 시작
                    // nextCursor++;
                    if (pauseTask.isEmpty()) { // 잠시 멈춘 과제가 없다면
                        nowTask = nextTask; // 바로 다음 과제 시작
                        nextCursor++;
                    } else { // 잠시 멈춘 과제가 있다면
                        Task pTask = pauseTask.pop(); // 가장 최근에 멈춘 과제 가져오기
                        pTask.startTime = nowTask.endTime; // 시작 시간 재설정
                        pTask.endTime = nowTask.endTime + (pTask.playTime - pTask.progress); // 끝나는 시간 재설정
                        nowTask = pTask; // 멈춘 과제 시작
                    }
                }
            } else { // 시작하지 않은 과제가 없는 경우(중간에 멈춘 과제만 있는 경우)
                answer[answerCursor] = nowTask.name; // 현재 과제를 마치고
                answerCursor++;
                while (!pauseTask.isEmpty()) {
                    answer[answerCursor] = pauseTask.pop().name;
                    answerCursor++;
                }
            }
        }
        
        return answer;
    }
}