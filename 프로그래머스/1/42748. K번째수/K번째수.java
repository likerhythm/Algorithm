import java.util.*;

class Solution {
    
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int a = 0; a < commands.length; a++) {
            int[] command = commands[a];
            List<Integer> nums = new ArrayList<>();
            int i = command[0] - 1;
            int j = command[1] - 1;
            int k = command[2];
            for (int idx = i; idx <= j; idx++) {
                nums.add(array[idx]);
            }
            Collections.sort(nums);
            answer[a] = nums.get(k - 1);
        }
        return answer;
    }
}