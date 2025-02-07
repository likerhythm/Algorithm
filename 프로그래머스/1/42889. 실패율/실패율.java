import java.util.*;

class Solution {
    
    static Comparator<Stage> comp = new Comparator<Stage> () {
        @Override
        public int compare(Stage o1, Stage o2) {
            if (o1.failRate == o2.failRate) return o1.id - o2.id;
            else {
                if (o2.failRate - o1.failRate < 0) {
                    return -1;
                } else if (o2.failRate - o1.failRate > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    };
    
    static class Stage {
        int id; // 1~
        double failRate;
        int pCnt;
        
        public Stage(int i) {
            id = i;
        }
        
        public void addP() {
            pCnt++;
        }
    }
    
    public int[] solution(int N, int[] personStages) {
        Stage[] stages = new Stage[N + 2];
        for (int i=1; i<=N+1; i++) {
            Stage stage = new Stage(i);
            stages[i] = stage;
        }
        for (int s : personStages) {
            stages[s].addP();
        }
        
        for (int i=1; i<=N; i++) {
            int totalPersons = 0;
            for (int j=i; j<=N+1; j++) {
                totalPersons += stages[j].pCnt;
            }
            if (totalPersons == 0) {
                stages[i].failRate = 0;
                continue;
            }
            stages[i].failRate = (double) stages[i].pCnt / totalPersons;
        }
        Stage[] resultStages = Arrays.copyOfRange(stages, 1, N + 1);
        Arrays.sort(resultStages, comp);
        
        int[] answer = new int[N];
        for (int i=0; i<N; i++) {
            answer[i] = resultStages[i].id;
        }
        return answer;
    }
}