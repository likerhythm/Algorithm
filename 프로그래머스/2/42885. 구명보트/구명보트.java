import java.util.*;

class Solution {
    
    int N;
    
    public int solution(int[] people, int limit) {
        N = people.length;
        Arrays.sort(people);
        
        int left = 0, right = N - 1;
        int count = 0;
        while (left <= right) {
            if (left == right) {
                count++;
                break;
            }
            int w1 = people[left];
            int w2 = people[right];
            if (w1 + w2 > limit) {
                count++;
                right--;
            } else {
                count++;
                left++;
                right--;
            }
        }
        
        return count;
    }
}