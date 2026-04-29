class Solution {
    
    int answer;
    int[] numbers;
    int target;
    
    public int solution(int[] n, int t) {
        this.numbers = n;
        this.target = t;
        
        backtracking(0, 0);
        
        return answer;
    }
    
    void backtracking(int idx, int sum) {
        if (idx == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        
        backtracking(idx + 1, sum + numbers[idx]);
        backtracking(idx + 1, sum - numbers[idx]);
    }
}